package com.example.backend_hometask.Service;

public class EmailNotificationService implements NotificationService{

   public boolean sendNotification() {
        System.out.println(" Email Sent !!!");
        return true;
    }
}
