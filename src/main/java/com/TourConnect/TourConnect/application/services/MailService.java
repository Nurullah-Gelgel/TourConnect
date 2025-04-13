package com.TourConnect.TourConnect.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendReservationConfirmation(String toEmail, String reservationCode) {
        String subject = "Rahvan - Rezervasyon Onayı";
        String body = "Sayın misafirimiz,\n\nRezervasyonunuz başarıyla alınmıştır.\nPNR Kodunuz: " + reservationCode + "\n\nİyi tatiller dileriz!\nRahvan Ekibi";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ngelgel36@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}
