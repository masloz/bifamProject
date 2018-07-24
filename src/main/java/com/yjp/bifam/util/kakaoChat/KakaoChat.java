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

import org.json.simple.JSONArray;
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
	public String message(@RequestBody String content, HttpServletRequest request) throws ParseException{
		JSONObject messageText = new JSONObject();			// text (String), message_button (JSONObject), photo (JSONObject)
			JSONObject message_button = new JSONObject();	// label (String), url (String)
			JSONObject photo = new JSONObject();			// url (String), width (int), height (int)
		JSONObject keyboard = new JSONObject();				// type (String), buttons (ArrayList)

		ArrayList<String> buttons = buttons();
		keyboard.put("type", "buttons");
		keyboard.put("buttons", buttons);
		
		JSONParser jParser = new JSONParser();
		JSONObject jObject = (JSONObject) jParser.parse(content);
		String jContent = jObject.get("content").toString();

		if (jContent.contains("오늘 뭐 먹지? (랜덤 메뉴)")) {		// messageText
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
			
		} else if (jContent.contains("Go to BIFAM!")) {	// messageText (+ message_button)
			// TEXT
			messageText.put("text", "BIFAM!");
			
			// LABEL BUTTON (+ URL)
			message_button.put("label", "사이트로 이동");
			message_button.put("url", "http://228220.ddns.net:228/bifam/404");
			messageText.put("message_button", message_button);
		} else if(jContent.contains("Photo test")){	// messageText (+ photo)
			// TEXT
			messageText.put("text", "This is Photo test.");
			
			// PHOTO
			photo.put("url", "https://i.imgur.com/PN5Fqqh.jpg");
			photo.put("width", 900);
			photo.put("height", 900);
			messageText.put("photo", photo);
		} else if(jContent.contains("파파고 번역 (한->영)")){
			// KEYBOARD TYPE (buttons -> text)
			keyboard.replace("type", "text");
			
			// TEXT
			messageText.put("text", "번역을 원하시는 문장을 적어주세요.");
			
		} else if(jContent.contains("지역별 미세먼지 조회")){
			// KEYBOARD TYPE (buttons -> text)
			keyboard.replace("type", "text");
			
			// TEXT
			messageText.put("text", "조회를 원하는 지역을 @와 함께 적어주세요.\n 예)@강남, @양평");
		} else if(jContent.charAt(0) == '@'){
			String stationName = jContent.substring(1);
			
			// FOOD LIST LOAD food.txt
			File file = null;
			try {
				// FILE RELATIVE PATH (파일 상대경로)
				file = new File(request.getSession().getServletContext().getResource("/resources/etc/stationName.txt").getPath());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			ArrayList<String> list = searchStationName(stationName, file);
			int listSize = list.size();
			
			if(listSize == 0){
				// KEYBOARD TYPE (buttons -> text)
				keyboard.replace("type", "text");
				
				// TEXT
				messageText.put("text", "측정소가 없는 지역입니다.\n가까운 다른 곳을 적어주세요.\n 예)@강남, @양평");
			}else if(listSize == 1){
				// TEXT
				messageText.put("text", fineDustInquiry(list.get(0).substring(1)));
			}else{
				// TEXT
				messageText.put("text", "중복되는 지명이 존재합니다.\n원하는 지역을 선택해주세요.");
				
				// BUTTONS
				keyboard.put("buttons", list);
			}
			
		} else {
			// TRANSLATE (PAPAGO)
			// TEXT
			messageText.put("text", papago(jContent));
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
		btns.add("오늘 뭐 먹지? (랜덤 메뉴)");
		btns.add("지역별 미세먼지 조회");
		btns.add("파파고 번역 (한->영)");
		btns.add("Go to BIFAM!");
//		btns.add("Photo test");
		
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
			e.printStackTrace();
		}
		
		return result;
	}
	
	// RANDOM MENU PRINT
	public static String randomMenu(File file){
		ArrayList<String> list = new ArrayList<>();
		
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			
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
	
	// 주소(미세먼지 관측소) 검색
	public static ArrayList<String> searchStationName(String query, File file){
		ArrayList<String> list = new ArrayList<>();
		ArrayList<String> allList = stationName(file);
		for(int i = 0; i < allList.size(); i++){
			if(allList.get(i).contains(query))
				list.add("@" + allList.get(i));
		}
		return list;
	}

	// 미세먼지 관측소 목록
	public static ArrayList<String> stationName(File file) {
		ArrayList<String> list = new ArrayList<>();

		BufferedReader br;

		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

			String stationName;
			while ((stationName = br.readLine()) != null) {
				list.add(stationName);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return list;
	}

	// 미세먼지 조회
	public static String fineDustInquiry(String stationName){
		StringBuffer result = new StringBuffer(0);
		try {
			String key = "iaO0GasN3DJm52RYjFiZzYv%2Fo%2F4eiUvAefcioIu2CIOVPu%2FtzBrkqap0Mb5FYrzPoESsY8zJP3v9xLlCasMXow%3D%3D";
			String urlString = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty";

			String serviceKey = "?serviceKey=" + key;
			String location = "&stationName=" + URLEncoder.encode(stationName, "UTF-8");
			String returnType = "&_returnType=json";
			String option = "&dataTerm=DAILY";
			
			URL url = new URL(urlString + serviceKey + location + returnType + option);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-type", "application/json");
			// System.out.println("ResponseCode : " + con.getResponseCode());

			// Response
			BufferedReader rd;
			if (con.getResponseCode() >= 200 && con.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				rd = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}

			try {
				// String -> JSONObject
				JSONParser jsonParser = new JSONParser();
				JSONObject jsonObject = (JSONObject) jsonParser.parse(sb.toString());
				JSONArray jsonArr = (JSONArray)jsonObject.get("list");
				
				// 값이 없을 땐 1시간 전 값 추출
				JSONObject jsonO = null;
				for(int i = 0; i < jsonArr.size(); i++){
					if(!((JSONObject) jsonArr.get(i)).get("pm10Value").equals("-")){
						jsonO = (JSONObject) jsonArr.get(i);
						break;
					}
				}
				
				// 미세먼지 농도에 따른 단계 (grade)
				// 0~30 좋음 / 31~80 보통 / 81~120 약간 나쁨 / 121~200 나쁨 / 201~300 매우 나쁨
				int pm10Value = Integer.parseInt(jsonO.get("pm10Value").toString());
				String grade;
				if(pm10Value < 31)
					grade = "(좋음)";
				else if(pm10Value < 80)
					grade = "(보통)";
				else if(pm10Value < 121)
					grade = "(약간 나쁨)";
				else if(pm10Value < 200)
					grade = "(나쁨)";
				else
					grade = "(매우 나쁨)";

				// 출력할 문자 버퍼에 추가
				result.append(jsonO.get("dataTime") + "\n");
				result.append("'" + stationName + "'의 미세먼지 농도\n");
				result.append("미세먼지 : " + jsonO.get("pm10Value") + "μg/㎥ " + grade + "\n");
				if(!jsonO.get("pm25Value").equals("-"))
					result.append("초미세먼지 : " + jsonO.get("pm25Value") + "μg/㎥");
				
			} catch (ParseException e) {
				e.printStackTrace();
			} finally{
				rd.close();
				con.disconnect();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result.toString();
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
