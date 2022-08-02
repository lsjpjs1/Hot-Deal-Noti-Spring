package com.example.hotdealnoti.hotdeal.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class HotDealScrapingScheduler {

    @Scheduled(cron = "59 35 * * * ?")
    public void scrapingSchedule() throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("ls > ls.txt");
        processBuilder.directory(new File(System.getProperty("user.home")));
        processBuilder.start();
        log.info("success");

    }
}
