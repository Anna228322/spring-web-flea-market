package com.example.springwebforms.config;

import com.example.springwebforms.loggers.CachedFileEventLogger;
import com.example.springwebforms.loggers.EventLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingConfiguration {
    private static final String LOG_FILE_NAME = "shop_logs.log";

    @Bean
    public EventLogger getEventLogger() {
        return new CachedFileEventLogger(LOG_FILE_NAME, 10);
    }
}
