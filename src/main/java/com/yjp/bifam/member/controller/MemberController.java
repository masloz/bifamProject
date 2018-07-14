package com.yjp.bifam.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yjp.bifam.member.model.service.MemberSerivce;
import com.yjp.bifam.member.model.vo.Member;

@Controller
public class MemberController {
	
	@Autowired
	private MemberSerivce memberService;
	
//	LOG IN
	@RequestMapping(value = "login.bf", method = RequestMethod.POST)
	public String loginCheck(Member m, HttpSession session){
		Member member = memberService.loginMember(m);
		if(member != null)
			session.setAttribute("member", member);
		return "home";
	}
	
//	LOG OUT
	@RequestMapping(value = "logout.bf", method = RequestMethod.GET)
	public String logoutMember(HttpSession session){
		if(session.getAttribute("member") != null)
			session.invalidate();
		return "home";
	}
	
//	SIGN UP
	@RequestMapping(value = "memberSignUp.bf", method = RequestMethod.POST)
	public String memberSignUp(){
		return "member/signUp";
	}
}
