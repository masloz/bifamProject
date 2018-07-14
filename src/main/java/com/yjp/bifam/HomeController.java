package com.yjp.bifam;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws IOException 
	 */
	@RequestMapping(value = "home.bf", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException{
		// ip 출력
		logger.info("client locale : {}, ip : {}.", locale, request.getRemoteAddr());
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		logger.info(formattedDate);
		String ip = getRemoteIP(request);
		String useMobile = mobileCheck(request, response); 
		logFileOutput(formattedDate, locale, ip, useMobile);
		
		return "home";
	}
	
	 public String getRemoteIP(HttpServletRequest request){
         String ip = request.getHeader("X-FORWARDED-FOR"); 
         
         //proxy 환경일 경우
         if (ip == null || ip.length() == 0) {
             ip = request.getHeader("Proxy-Client-IP");
         }
 
         //웹로직 서버일 경우
         if (ip == null || ip.length() == 0) {
             ip = request.getHeader("WL-Proxy-Client-IP");
         }
 
         if (ip == null || ip.length() == 0) {
             ip = request.getRemoteAddr() ;
         }
         
         return ip;
	}

	public void logFileOutput(String formattedDate, Locale locale, String ip, String useMobile) throws IOException {
		FileOutputStream fsOut = new FileOutputStream("C:\\Users\\masloz\\Desktop\\log.txt", true);
		 
		byte[] content = (formattedDate + " (" + locale + ") - " + ip + " (" + useMobile + ")\r\n").getBytes();
		fsOut.write(content);
		fsOut.flush();
		fsOut.close();
	}

	public String mobileCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String browser = request.getHeader("User-Agent"); // 브라우저 구해오기
		String result = "com";

		if (browser.indexOf("Android") > 0) { // 안드로이드  true
			result = "Android";
		} else if (browser.indexOf("iPhone") > 0) { // 아이폰 true
			result = "iPhone";
		}

		return result;
	}

}
