package com.javarush.task.task40.task4003;

import java.util.Properties;

/* 
Отправка письма с файлом
*/

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.sendMail("name.lastname@gmail.com", "password", "friend@gmail.com");
    }

    public void sendMail(final String username, final String password, final String recipients) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

    }}