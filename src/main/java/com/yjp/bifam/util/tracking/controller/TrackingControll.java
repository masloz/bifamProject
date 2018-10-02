package com.yjp.bifam.util.tracking.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yjp.bifam.util.tracking.model.TrackCompany;
import com.yjp.bifam.util.tracking.model.Tracking;

@Controller
public class TrackingControll {
//	@Autowired
//	private Tracking tracking;

	// 택배사 코드
	@RequestMapping(value = "trackingInquiry.bf", method = RequestMethod.GET)
	public String trackingPage(Model model){
		String result = "main/404";
		ArrayList<TrackCompany> companyList = new ArrayList<>();
		String key = "9zOsiF7nFDpYy3zrZtr6Kw";
		String urlFormat = String.format("http://info.sweettracker.co.kr/api/v1/companylist?t_key=%s", key);
		
		BufferedReader in = null;
		
		URL url;
		try {
			url = new URL(urlFormat);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			
			// 전송방식 GET 지정
			con.setRequestMethod("GET");

			// 요청헤더 추가
			con.setRequestProperty("Accept", "application/json");
			
			// 응답코드
			int responseCode = con.getResponseCode();
			if(responseCode == 200){	// 200 (success)
				// 응답된 JSON 처리
				in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8")); 
				
				String stringObject = in.readLine();
				try {
					JSONParser jsonParser = new JSONParser();
					JSONObject jsonObject = (JSONObject) jsonParser.parse(stringObject);

					if(jsonObject.get("code") != null){	// error
						System.out.println("jsonCode : " + jsonObject.get("code"));
						System.out.println("error msg : " + jsonObject.get("msg"));
					}else{
						JSONArray company = (JSONArray) jsonObject.get("Company");
						
						for(int i = 0; i < company.size(); i++){
							String code = ((JSONObject)company.get(i)).get("Code").toString();
							String name = ((JSONObject)company.get(i)).get("Name").toString();
							companyList.add(new TrackCompany(code, name));
						}
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
			}else{	// the other response error (fail)
				System.out.println("오류 코드 : " + responseCode);
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(companyList.size() > 0){
			model.addAttribute("companyList", companyList);
			result = "utilTest/tracking/trackingInquiry";
		}
		
		return result;
	}
	
	// 택배 조회
	@RequestMapping(value = "trackingInfo.bf", method = RequestMethod.GET)
	public void trackingInfo(Model model, Tracking tracking, HttpServletResponse response){
		BufferedReader in = null;
		try {
			// URL 및 연결
			tracking.setKey("9zOsiF7nFDpYy3zrZtr6Kw");		// key 대입
			String urlFormat = String.format("http://info.sweettracker.co.kr/api/v1/trackingInfo?t_key=%s&t_code=%s&t_invoice=%s", tracking.getKey(), tracking.getCode(), tracking.getInvoice());
			URL url = new URL(urlFormat);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			
			// 전송방식 GET 지정
			con.setRequestMethod("GET");
			
			// 요청헤더 추가
			con.setRequestProperty("Accept", "application/json");
			
			// 응답코드
			int responseCode = con.getResponseCode();
			if(responseCode == 200){	// 200 (success)
				// 응답된 JSON 처리
				in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8")); 
				
				String stringObject = in.readLine();
				try {
					JSONParser jsonParser = new JSONParser();
					JSONObject jsonObject = (JSONObject) jsonParser.parse(stringObject);

//					int jsonCode = Integer.parseInt((String) jsonObject.get("code"));
					if(jsonObject.get("code") != null){	// error
						System.out.println("jsonCode : " + jsonObject.get("code"));
						System.out.println("error msg : " + jsonObject.get("msg"));
					}else{
						response.setCharacterEncoding("UTF-8");
						response.getWriter().print(stringObject);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
			}else{	// the other response error (fail)
				System.out.println("오류 코드 : " + responseCode);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
