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

import java.io.IOException;
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

    private List<String> urls = new ArrayList<>();

    private Connection connect;

    public SqlRuParser() {
        init();
    }

    private boolean init() {
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


    private int parserUrls() {
        // количество позиций строк в базе vacancy
        int countIdBase = 0;
        // количество позиций в urls
        int countIdArrayList = 0;
        try (PreparedStatement preparedStatement = connect.prepareStatement("SELECT COUNT(link) FROM vacancy")) {
            ResultSet date = preparedStatement.executeQuery();
            date.next();
            countIdBase = date.getInt(1);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        Document doc;
        // начало года
        int startPositionForParsing = 5;
        // получаем список ссылок с вакансиями и заносим в urls
        try {
            for (int i = 1; i < startPositionForParsing; i++) {
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
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        countIdArrayList = urls.size();
        System.out.println("countIdBase " + countIdBase);
        System.out.println("countIdArrayList " + countIdArrayList);
        return countIdArrayList;
    }


    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        try {
            int count = parserUrls();
            for (int i = 0; i < count; i++) {
                String url = urls.get(i);
                String title = parseTitle(url);
                String text = parseText(url);
                addDateDB(title, text, url);
            }
            urls.clear();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {
            try {
                close();
            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

    private void addDateDB(String title, String text, String url) {
        try (PreparedStatement preparedStatement = connect.prepareStatement("INSERT INTO vacancy (names_v, text_v, link) values (?, ?, ?) "
                +
                "ON CONFLICT (link) \n"
                +
                "DO NOTHING;", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, text);
            preparedStatement.setString(3, url);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private String parseText(String url) {
        String result = null;
        try {
            Document doc = Jsoup.connect(url).get();
            result = doc.select(".msgBody").get(1).text();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    private String parseTitle(String url) {
        String result = null;
        try {
            Document doc = Jsoup.connect(url).get();
            result = doc.title();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
}
