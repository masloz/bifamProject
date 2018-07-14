package com.yjp.bifam.member.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Member implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3330582446160979316L;
	private String member_id;
	private String member_pwd;
	private String member_name;
	private Date birth;
	private Date regist_date;
	private String phone;
	private String address;
	private int point;
	private int grade;
	private String email;
	
	
	public Member(){
		
	}


	public Member(String member_id, String member_pwd, String member_name, Date birth, Date regist_date, String phone,
			String address, int point, int grade, String email) {
		super();
		this.member_id = member_id;
		this.member_pwd = member_pwd;
		this.member_name = member_name;
		this.birth = birth;
		this.regist_date = regist_date;
		this.phone = phone;
		this.address = address;
		this.point = point;
		this.grade = grade;
		this.email = email;
	}


	public String getMember_id() {
		return member_id;
	}


	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}


	public String getMember_pwd() {
		return member_pwd;
	}


	public void setMember_pwd(String member_pwd) {
		this.member_pwd = member_pwd;
	}


	public String getMember_name() {
		return member_name;
	}


	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}


	public Date getBirth() {
		return birth;
	}


	public void setBirth(Date birth) {
		this.birth = birth;
	}


	public Date getRegist_date() {
		return regist_date;
	}


	public void setRegist_date(Date regist_date) {
		this.regist_date = regist_date;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public int getPoint() {
		return point;
	}


	public void setPoint(int point) {
		this.point = point;
	}


	public int getGrade() {
		return grade;
	}


	public void setGrade(int grade) {
		this.grade = grade;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
