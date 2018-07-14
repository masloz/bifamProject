package com.yjp.bifam.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yjp.bifam.member.model.vo.Member;

@Repository
public class MemberDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public MemberDao(){
		
	}
	
	public Member selectMember(Member m){
		return sqlSession.selectOne("Member.loginCheck", m);
	}
}
