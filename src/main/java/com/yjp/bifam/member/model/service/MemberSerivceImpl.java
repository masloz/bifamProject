package com.yjp.bifam.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjp.bifam.member.model.dao.MemberDao;
import com.yjp.bifam.member.model.vo.Member;

@Service("memberService")
public class MemberSerivceImpl implements MemberSerivce {
	@Autowired
	private MemberDao memberDao;
	
	public MemberSerivceImpl(){
		
	}
	
	@Override
	public Member loginMember(Member m){
		return memberDao.selectMember(m);
	}
}
