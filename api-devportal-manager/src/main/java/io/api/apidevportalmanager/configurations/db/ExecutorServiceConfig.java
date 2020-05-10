package io.api.apidevportalmanager.configurations.db;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ExecutorServiceConfig {

    @Bean
    private Scheduler executorService() {
        ExecutorService executor = Executors.newFixedThreadPool(100);
        return Schedulers.from(executor);
    }
}
