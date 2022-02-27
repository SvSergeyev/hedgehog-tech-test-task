package tech.sergeyev.hedgehogtechtesttask.ping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class PingTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(PingTask.class);

    @Scheduled(fixedRate = 1200000)
    public void ping() {
        try {
            URL url = new URL("https://www.google.com");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            LOGGER.info("Ping={}, i'm not sleeping...", url.getHost());
            connection.disconnect();
        } catch (IOException e) {
            LOGGER.info("Ping task failed");
            e.printStackTrace();
        }

    }
}
