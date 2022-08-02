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

    @Scheduled(cron = "0 0/1 * * * ?")
    public void scrapingSchedule() throws IOException {
        log.info("success");
        ProcessBuilder processBuilder = new ProcessBuilder();
//        processBuilder.command("sh", "/home/ubuntu/startScraping.sh","&");
        processBuilder.command("su","-s","/bin/sh","ubuntu","-c","export");
        processBuilder.directory(new File("/home/ubuntu"));
        Process p = processBuilder.start();
//        java.lang.Process p = Runtime.getRuntime().exec("cd /home/ubuntu;export");
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

        processBuilder.environment().forEach((k,v)->System.out.println(k+" : "+v));
    }
}
