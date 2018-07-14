package com.yjp.bifam.board.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yjp.bifam.board.model.vo.Board;

@Repository("boardDao")
public class BoardDao {

	@Autowired
	SqlSessionTemplate sqlSession;
	
	public ArrayList<Board> boardList(int board_categoty_no) {
		List<Board> boardList = sqlSession.selectList("Board.boardList", board_categoty_no);
		return (ArrayList<Board>)boardList;
	}

	public Board boardDetail(int board_no) {
		Board board = sqlSession.selectOne("Board.boardDetail", board_no);
		return board;
	}

	public int boardWrite(Board board) {
		return sqlSession.insert("Board.boardWrite", board);
	}
	
	public int boardRecent(Board board){
		return sqlSession.selectOne("Board.boardRecent", board);
	}

	public int boardDelete(int board_no) {
		return sqlSession.update("Board.boardDelete", board_no);
	}

	public int boardUpdate(Board board) {
		return sqlSession.update("Board.boardUpdate", board);
	}

}
