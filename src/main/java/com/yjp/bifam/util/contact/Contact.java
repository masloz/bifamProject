package com.yjp.bifam.util.contact;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Contact {

	@RequestMapping(value = "contact.bf", method = RequestMethod.GET)
	public String contactView(){
		return "utilTest/contact";
	}
	
	@RequestMapping(value = "sendMail.bf", method = RequestMethod.GET)
	public void sendMail(String name, String email, String comment){
		// Google 의 경우 stmp.google.com
		String host = "smtp.naver.com";
		
		// 메일 아이디, 비밀번호
		String mailID = "bmxpower2";
		String mailPW = "sp2qjdkdlel";
		// 포트번호
		int port = 465;
		
		// mail 내용 정리
		String mSubject = "bifam 공지 메일"; 
		String mcontent = name + "님으로부터 온 메세지\n" + email + "\n\n" + comment;
		
		Properties props = System.getProperties(); // 정보를 담기 위한 객체 생성
		
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", host);
		
		// 뭔진 모르겠는데 손봐야될 듯 
		// Authenticator 입증하는 사람; 인증 부호
		Authenticator auth = new Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(mailID, mailPW);
			}
		};
		
		// session 생성
		Session session = Session.getDefaultInstance(props, auth);
		session.setDebug(true);	// for debug
		
		
		try {
			Message mimeMessage = new MimeMessage(session);	// MimeMessage 생성
			// 발신자 setting
			mimeMessage.setFrom(new InternetAddress("bmxpower2@naver.com"));
			// 수신자 setting (.TO는 말그대로 to(투), CC(참조), BCC(숨은참조)
			mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
			
			mimeMessage.setSubject(mSubject);	// 메일 제목
			mimeMessage.setText(mcontent);	// 메일 내용
			Transport.send(mimeMessage);	// javax.mail.Transport.send()를 통해 메일 발송
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		
		
	}
}