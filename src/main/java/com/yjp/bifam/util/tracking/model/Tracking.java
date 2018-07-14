package com.yjp.bifam.util.tracking.model;

public class Tracking {
	private String key;
	private String code;
	private String invoice;

	public Tracking(){}
	
	public Tracking(String key){
		this.key = key;
	}
	
	public Tracking(String key, String code, String invoice) {
		this.key = key;
		this.code = code;
		this.invoice = invoice;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	@Override
	public String toString() {
		return "tracking [key=" + key + ", code=" + code + ", invoice=" + invoice + "]";
	}
}
