package com.example.hotdealnoti.hotdeal.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
@Slf4j
@RequiredArgsConstructor
public class HotDealScrapingScheduler {

    @Scheduled(cron = "0 0/2 * * * ?")
    public void scrapingSchedule() throws IOException {
        log.info("success");
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("nohup","sh", "/home/ubuntu/startScraping.sh","&");
        processBuilder.directory(new File(System.getProperty("user.home")));
        java.lang.Process p = processBuilder.start();
        BufferedReader std = new BufferedReader(new InputStreamReader(p.getErrorStream()));
        String outputLine = "";
        while ((outputLine = std.readLine()) != null) {
           log.info(outputLine);
        }
        std = new BufferedReader(new InputStreamReader(p.getInputStream()));
        outputLine = "";
        while ((outputLine = std.readLine()) != null) {
           log.info(outputLine);
        }

    }
}
