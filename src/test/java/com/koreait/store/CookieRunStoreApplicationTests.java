package com.koreait.store;

import com.koreait.store.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@SpringBootTest
class CookieRunStoreApplicationTests {
    @Autowired private JavaMailSender mailSender;

    @Autowired private EmailService emailService;
    @Test
    void send_test() throws MessagingException {
        emailService.send_cert_mail("fnvit@naver.com");
    }

    @Test
    void send_mail() throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        // helper를 통해 간단히 메일을 구성
        // MimeMessageHelper에 true를 설정시, 파일 첨부 가능! + 인코딩 설정도 가능!
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom("fnvit@naver.com"); // 보내는 사람 (application.properties에 작성한 유저밖에 안됩니다..!)
        helper.setTo("fnvit@naver.com"); // 받는 사람. 아무나.
        helper.setSubject("웹개발 메일 테스트"); // 메일 제목
        helper.setText("<h1 style=\"color: blue;\">잘 받았니? ㅋ</h1>", true); // 메일 내용, true 설정 시 HTML형식이라고 설정함!
        // 생성한 MimeMessage를 SEND
        mailSender.send(message);

    }
}
