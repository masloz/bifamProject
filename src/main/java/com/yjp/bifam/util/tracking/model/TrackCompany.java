package com.yjp.bifam.util.tracking.model;

public class TrackCompany {
	private String code;
	private String name;
	
	public TrackCompany(){}
	
	public TrackCompany(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "TrackCompany [code=" + code + ", name=" + name + "]";
	}
}
