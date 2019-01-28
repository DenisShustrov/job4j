package ru.job4j.poll;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {
    private ExecutorService poll;


    public EmailNotification() {
        poll = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    public void emailTo(User user) {
        String subject = String.format("Notification %s to email %s", user.getUserName(), user.getEmail());
        String body = String.format("Add a new event to %s", user.getUserName());
        poll.submit(() -> send(subject, body, user.getEmail()));
    }

    public void send(String subject, String body, String email) {
    }

    public void close() {
        poll.shutdown();
    }
}


