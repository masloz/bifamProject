package com.yjp.bifam.board.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjp.bifam.board.model.dao.BoardDao;
import com.yjp.bifam.board.model.vo.Board;

@Service("boardService")
public class BoardServiceImpl implements BoardService{

	@Autowired
	BoardDao boardDao;
	
	@Override
	public ArrayList<Board> boardList(int board_categoty_no){
		ArrayList<Board> boardList = boardDao.boardList(board_categoty_no);
		return boardList;
	}

	@Override
	public Board boardDetail(int board_no) {
		Board board = boardDao.boardDetail(board_no);
		return board;
	}

	@Override
	public int boardWrite(Board board) {
		
		return boardDao.boardWrite(board);
	}

	@Override
	public int boardRecent(Board board) {
		return boardDao.boardRecent(board);
	}

	@Override
	public int boardDelete(int board_no) {
		return boardDao.boardDelete(board_no);
	}

	@Override
	public int boardUpdate(Board board) {
		return boardDao.boardUpdate(board);
	};
}
