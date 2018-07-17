package com.yjp.bifam.util.crawling.controller;

import java.io.*;
import java.net.*;
import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yjp.bifam.util.crawling.model.Crawling;

@Controller
public class CrawlingController {
	
	@RequestMapping(value = "/crawling", method = RequestMethod.GET)
	public String naverNews(Model model){
		String result = "utilTest/crawling";
		int cnt = 0;
		ArrayList<Crawling> news = new ArrayList<>();;
		
		// Crawling 할 주소
		String[] crawlingTarget = {"https://news.naver.com/", "https://media.daum.net/digital/"};
		for(int i = 0; i < crawlingTarget.length; i++){
			String page = "";
			// Crawling
			news = crawlingPage(crawlingTarget[i]);
			
			// 주소 확인
			if(crawlingTarget[i].contains("naver")){
				page = "naver";
			}else if(crawlingTarget[i].contains("daum")){
				page = "daum";
			}
			model.addAttribute(page + "CrawlingList", news);
			
			cnt += news.size(); 
		}
		
		// Crawling 내용물이 없으면 404 page
		if(cnt == 0)
			result = "main/404";
			
		return result;
	}
	
	public ArrayList<Crawling> crawlingPage(String target){
		ArrayList<Crawling> news = new ArrayList<>();
		try{
			// 주소 연결 (네이버는 EUC-KR, 다음은 UTF-8 로 인코딩되어 있음)
			HttpURLConnection con = (HttpURLConnection) new URL(target).openConnection();
			BufferedReader br = null;
			if(target.contains("naver")){
				br = new BufferedReader(new InputStreamReader(con.getInputStream(), "EUC-KR"));
			}else if(target.contains("daum")){
				br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			}

			// (DIV의) 특정 부분 긁어오기
			String temp;
			while((temp = br.readLine()) != null){
				if(target.contains("naver")){
					if(temp.contains("newsnow_tx_inner")){
						String line = br.readLine();
						news.add(new Crawling(line.split("\"")[1], line.split("<strong>")[1].split("</strong>")[0].replaceAll("&quot;", "\"")));
					}
				}else if(target.contains("daum")){
					if(temp.contains("link_txt #article_main")){
						news.add(new Crawling(temp.split("\"")[3], temp.split(">")[2].replaceAll("</a", "")));
					}
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		return news;
	}
}
