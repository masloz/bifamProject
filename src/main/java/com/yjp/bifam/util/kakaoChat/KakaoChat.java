package com.yjp.bifam.util.kakaoChat;

import java.util.ArrayList;

import org.json.simple.JSONObject;
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
	public String message(@RequestBody String content){
		JSONObject messageText = new JSONObject();			// text (String), message_button (JSONObject), photo (JSONObject)
			JSONObject message_button = new JSONObject();	// label (String), url (String)
			JSONObject photo = new JSONObject();			// url (String), width (int), height (int)
		JSONObject keyboard = new JSONObject();				// type (String), buttons (ArrayList)

		ArrayList<String> buttons = buttons();
		keyboard.put("type", "buttons");
		keyboard.put("buttons", buttons);

		if (content.contains("Click this button!")) {		// messageText
			// TEXT
			messageText.put("text", "hi");
		} else if (content.contains("Go to BIFAM!")) {	// messageText (+ message_button)
			// TEXT
			messageText.put("text", "BIFAM!");
			
			// LABEL BUTTON (+ URL)
			message_button.put("label", "사이트로 이동");
			message_button.put("url", "http://228220.ddns.net:228/bifam");
			messageText.put("message_button", message_button);
		} else if(content.contains("Photo test")){	// messageText (+ photo)
			// TEXT
			messageText.put("text", "This is Photo test.");
			
			// PHOTO
			photo.put("url", "https://via.placeholder.com/640x480");
			photo.put("width", 640);
			photo.put("height", 480);
			messageText.put("photo", photo);
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
		btns.add("Click this button!");
		btns.add("Go to BIFAM!");
		btns.add("Photo test");
		
		return btns;
	}
	
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
}
