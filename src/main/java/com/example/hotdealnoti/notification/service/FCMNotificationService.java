package com.example.hotdealnoti.notification.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;

@Slf4j
@Service
@RequiredArgsConstructor
public class FCMNotificationService {
    @Value("${fcm.key.path}")
    private String FCM_PRIVATE_KEY_PATH;

    @Value("${fcm.key.scope}")
    private String fireBaseScope;

    private final ObjectMapper objectMapper;

    @PostConstruct
    public void init(){
        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(
                            GoogleCredentials
                                    .fromStream(new ClassPathResource(FCM_PRIVATE_KEY_PATH).getInputStream())
                                    .createScoped(Arrays.asList(fireBaseScope)))
                    .build();
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                log.info("Firebase application has been initialized");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            // spring 뜰때 알림 서버가 잘 동작하지 않는 것이므로 바로 죽임
            throw new RuntimeException(e.getMessage());
        }
    }

    public void sendMessage(String token, Object payload, String notificationTitle, String notificationBody){
        String stringPayload;
        try {
            stringPayload = objectMapper.writeValueAsString(payload);
        } catch (JsonProcessingException jsonProcessingException){
            jsonProcessingException.printStackTrace();
            return;
        }

        Message message = Message.builder()
                .putData("payload", stringPayload)
                .setNotification(Notification.builder().setTitle(notificationTitle).setBody(notificationBody).build())
                .setToken(token)
                .build();
        try{
            FirebaseMessaging.getInstance().send(message);
        }catch (FirebaseMessagingException firebaseMessagingException){
            log.error(firebaseMessagingException.getMessage());
            log.error(firebaseMessagingException.getLocalizedMessage());
        }

    }
}
