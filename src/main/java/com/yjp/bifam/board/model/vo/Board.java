package com.yjp.bifam.board.model.vo;

import java.sql.Date;

public class Board {
	private int board_no;
	private int board_category_no;
	private String board_title;
	private String board_content;
	private String image_name;
	private Date board_date;
	private String member_id;
	private int board_hit;
	private String etc;
	
	
	private BoardCategory boardCategoryJoin;

	
	public Board() {
	}


	public Board(int board_no, int board_category_no, String board_title, String board_content, String image_name,
			Date board_date, String member_id, int board_hit, String etc, BoardCategory boardCategoryJoin) {
		super();
		this.board_no = board_no;
		this.board_category_no = board_category_no;
		this.board_title = board_title;
		this.board_content = board_content;
		this.image_name = image_name;
		this.board_date = board_date;
		this.member_id = member_id;
		this.board_hit = board_hit;
		this.etc = etc;
		this.boardCategoryJoin = boardCategoryJoin;
	}


	@Override
	public String toString() {
		return "Board [board_no=" + board_no + ", board_category_no=" + board_category_no + ", board_title="
				+ board_title + ", board_content=" + board_content + ", image_name=" + image_name + ", board_date="
				+ board_date + ", member_id=" + member_id + ", board_hit=" + board_hit + ", etc=" + etc
				+ ", board_category=" + boardCategoryJoin + "]";
	}


	public int getBoard_no() {
		return board_no;
	}


	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}


	public int getBoard_category_no() {
		return board_category_no;
	}


	public void setBoard_category_no(int board_category_no) {
		this.board_category_no = board_category_no;
	}


	public String getBoard_title() {
		return board_title;
	}


	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}


	public String getBoard_content() {
		return board_content;
	}


	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}


	public String getImage_name() {
		return image_name;
	}


	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}


	public Date getBoard_date() {
		return board_date;
	}


	public void setBoard_date(Date board_date) {
		this.board_date = board_date;
	}


	public String getMember_id() {
		return member_id;
	}


	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}


	public int getBoard_hit() {
		return board_hit;
	}


	public void setBoard_hit(int board_hit) {
		this.board_hit = board_hit;
	}


	public String getEtc() {
		return etc;
	}


	public void setEtc(String etc) {
		this.etc = etc;
	}


	public BoardCategory getBoardCategoryJoin() {
		return boardCategoryJoin;
	}


	public void setBoardCategoryJoin(BoardCategory boardCategoryJoin) {
		this.boardCategoryJoin = boardCategoryJoin;
	}

	
	
	
}
