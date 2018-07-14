package com.yjp.bifam.board.model.service;

import java.util.ArrayList;

import com.yjp.bifam.board.model.vo.Board;

public interface BoardService {

	ArrayList<Board> boardList(int board_categoty_no);

	Board boardDetail(int board_no);

	int boardWrite(Board board);
	
	int boardRecent(Board board);

	int boardDelete(int board_no);

	int boardUpdate(Board board);
}
