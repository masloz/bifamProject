package com.yjp.bifam.util.crawling.model;

public class Crawling {
	private String link;
	private String title;
	
	public Crawling(){}
	
	public Crawling(String link, String title) {
		super();
		this.link = link;
		this.title = title;
	}
	
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public String toString() {
		return "Crawling [link=" + link + ", title=" + title + "]";
	}
}
