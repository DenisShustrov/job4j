package ru.job4j.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import ru.job4j.tracker.TrackerSQL;

import java.io.InputStream;
import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Class SqlRuParser.
 *
 * @author dshustrov
 * @version 1
 * @since 17.02.2019
 */
public class SqlRuParser implements AutoCloseable, Job {

    private static final Logger LOG = LogManager.getLogger(SqlRuParser.class);

    private static List<String> urls = new ArrayList<>();

    private Connection connect;

    public boolean init() {
        try (InputStream in = SqlRuParser.class.getClassLoader().getResourceAsStream("app2.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            this.connect = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return this.connect != null;
    }

    public static void main(String[] args) throws Exception {
        String cron = "";
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app2.properties")) {
            Properties config = new Properties();
            config.load(in);
            cron = config.getProperty("time");
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        JobDetail job = JobBuilder
                .newJob(SqlRuParser.class)
                .withIdentity("SqlRuParser", "group1")
                .build();

        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("trigger", "group1")
                .withSchedule(CronScheduleBuilder
                        .cronSchedule(cron))
                .build();


        SchedulerFactory schedFact = new StdSchedulerFactory();
        Scheduler sched = schedFact.getScheduler();
        sched.start();
        sched.scheduleJob(job, trigger);
    }

    @Override
    public void close() throws Exception {
        if (connect != null) {
            connect.close();
        }
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        init();
        // количество позиций строк в базе vacancy
        int countIdBase = 0;
        // количество позиций в urls
        int countIdArrayList = 0;
        try (PreparedStatement statement = connect.prepareStatement("SELECT COUNT(link) FROM vacancy")) {
            ResultSet date = statement.executeQuery();
            date.next();
            countIdBase = date.getInt(1);
            //parser.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }

        // добавление данных в базу
        try (PreparedStatement statement = connect.prepareStatement("INSERT INTO vacancy (names_v, text_v, link) values (?, ?, ?) "
                +
                "ON CONFLICT (link) \n"
                +
                "DO NOTHING;", Statement.RETURN_GENERATED_KEYS)) {
            Document doc;
            // начало года
            int startPositionForParsing = 5;
            // получаем список ссылок с вакансиями и заносим в urls
            for (int i = 1; i < 20; i++) {
                doc = Jsoup.connect("https://www.sql.ru/forum/job-offers/" + i).get();
                Elements aElements = doc.select(".postslisttopic > a");
                for (Element url : aElements) {
                    String address = url.attr("href");
                    String anchor = url.text();
                    Pattern pattern = Pattern.compile("([S]*[s]*cript)");
                    Matcher matcher = pattern.matcher(anchor);
                    if (!matcher.find()) {
                        pattern = Pattern.compile("([J]*[j]*ava)");
                        matcher = pattern.matcher(anchor);
                        if (matcher.find()) {
                            urls.add(address);
                            System.out.println(address);
                            System.out.println(anchor);
                        }
                    }
                }
            }
            countIdArrayList = urls.size();
            System.out.println("countIdBase " + countIdBase);
            System.out.println("countIdArrayList " + countIdArrayList);
            for (int i = 0; i < countIdArrayList; i++) {
                String url = urls.get(i);
                doc = Jsoup.connect(url).get();
                String title = doc.title();
                String text = doc.select(".msgBody").get(1).text();
                statement.setString(1, title);
                statement.setString(2, text);
                statement.setString(3, url);
                statement.executeUpdate();
            }
            close();
            urls.clear();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
