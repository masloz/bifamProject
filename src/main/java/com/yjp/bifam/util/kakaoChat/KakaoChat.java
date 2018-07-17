package com.yjp.bifam.util.kakaoChat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KakaoChat {
	
	@SuppressWarnings("unchecked")	// because of JSON type...
	@RequestMapping(value = "/keyboard", produces="application/json;charset=utf-8")
	public String keybord(){
		// TYPE
		String type = "buttons";
		
		// BUTTONS
		ArrayList<String> btns = buttons();
		
		// Put in JSONObject (type safety...)
		JSONObject keyboard = new JSONObject();
		keyboard.put("type", type);
		keyboard.put("buttons", btns);
		
		
		return keyboard.toJSONString();
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/message", produces="application/json; charset=utf-8")
	public String message(@RequestBody String content, HttpServletRequest request){
		JSONObject messageText = new JSONObject();			// text (String), message_button (JSONObject), photo (JSONObject)
			JSONObject message_button = new JSONObject();	// label (String), url (String)
			JSONObject photo = new JSONObject();			// url (String), width (int), height (int)
		JSONObject keyboard = new JSONObject();				// type (String), buttons (ArrayList)

		ArrayList<String> buttons = buttons();
		keyboard.put("type", "buttons");
		keyboard.put("buttons", buttons);
		
		

		if (content.contains("Today Random Menu!")) {		// messageText
			// FOOD LIST LOAD food.txt
			File file = null;
			try {
				// FILE RELATIVE PATH (파일 상대경로)
				file = new File(request.getSession().getServletContext().getResource("/resources/etc/food.txt").getPath());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			
			// TEXT
			messageText.put("text", randomMenu(file));
			
		} else if (content.contains("Go to BIFAM!")) {	// messageText (+ message_button)
			// TEXT
			messageText.put("text", "BIFAM!");
			
			// LABEL BUTTON (+ URL)
			message_button.put("label", "사이트로 이동");
			message_button.put("url", "http://228220.ddns.net:228/bifam/404");
			messageText.put("message_button", message_button);
		} else if(content.contains("Photo test")){	// messageText (+ photo)
			// TEXT
			messageText.put("text", "This is Photo test.");
			
			// PHOTO
			photo.put("url", "https://i.imgur.com/PN5Fqqh.jpg");
			photo.put("width", 900);
			photo.put("height", 900);
			messageText.put("photo", photo);
		} else if(content.contains("Translate")){
			// KEYBOARD TYPE (buttons -> text)
			keyboard.replace("type", "text");
			
			// TEXT
			messageText.put("text", "translate");
			
		} else {

			try {
				// TRANSLATE (PAPAGO)
				JSONParser jParser = new JSONParser();
				JSONObject jObject = (JSONObject)jParser.parse(content);

				// TEXT
				messageText.put("text", papago(jObject.get("content").toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			
		}

		// 보낼 MESSAGE (JSONObject)
		JSONObject message = new JSONObject();
		message.put("message", messageText);
		message.put("keyboard", keyboard);
		return message.toJSONString();
	}
	
	// BASIC BUTTONS
	public ArrayList<String> buttons(){
		ArrayList<String> btns = new ArrayList<>();
		btns.add("Today Random Menu!");
		btns.add("Go to BIFAM!");
		btns.add("Photo test");
		btns.add("Translate");
		
		return btns;
	}
	
	// PAPAGO API
	public static String papago(String textToTranslate){
		String result = null;
		
		String clientID = "2VqQsYhBIuet4sBOze_e";
		String clientSecret = "xumtb_d3SM";
		
		try {
			String text = URLEncoder.encode(textToTranslate, "UTF-8");
			String urlString = "https://openapi.naver.com/v1/language/translate";
			URL url = new URL(urlString);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			
			con.setRequestMethod("POST");
			con.setRequestProperty("X-Naver-Client-Id", clientID);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			
			String source = "ko";	// 원본 언어
			String target = "en"; // 목적 언어
			String postParams = "source=" + source + "&target=" + target + "&text=" + text;
			
			con.setDoOutput(true); // 스트림 출력 설정(?)
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(postParams);
			wr.flush();
			wr.close();
			
			int responseCode = con.getResponseCode();
			
			BufferedReader br;
			if(responseCode == 200){
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			}else{
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			}
			String inputLine;
			StringBuffer response = new StringBuffer();
			while((inputLine = br.readLine()) != null){
				response.append(inputLine);
			}
			br.close();
			JSONParser jParser = new JSONParser();
			JSONObject jsonO = (JSONObject)jParser.parse(response.toString());
			JSONObject message = (JSONObject)jsonO.get("message");
			result = ((JSONObject)message.get("result")).get("translatedText").toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	// RANDOM MENU PRINT
	public static String randomMenu(File file){
		ArrayList<String> list = new ArrayList<>();
		
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "EUC-KR"));
			
			String food;
			while((food = br.readLine()) != null){
				list.add(food);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return list.get(new Random().nextInt(list.size()));
	}
	
	/* 친구 삭제, 차단, 채팅창 나가기 기능
	// ADD FRIEND
	@RequestMapping(value = "/friend")
	public void addFriend(@RequestBody String content){
		System.out.println(content);
		System.out.println("친추");
	}
	
	// BLOCK FRIEND
	@RequestMapping(value = "/friend/*")
	public void blockFriend(){
		System.out.println("차단");
	}
	
	// EXIT CHAT ROOM
	@RequestMapping(value = "/chat_room/*")
	public void exitChatRoom(){
		System.out.println("채팅창 나가기");
	}
	*/
}
