package com.yjp.bifam.board.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yjp.bifam.board.model.service.BoardService;
import com.yjp.bifam.board.model.vo.Board;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
//	BOARD LIST
	@RequestMapping(value = "boardList.bf", method = RequestMethod.GET)
	public String boardList(Model model){
		ArrayList<Board> boardList = boardService.boardList(1);
//		TODO board_categodry_no setting
		model.addAttribute("boardList", boardList);
		return "board/boardList";
	}
	
//	BOARD DETAIL
	@RequestMapping(value = "boardDetail.bf", method = RequestMethod.GET)
	public String boardDetail(Board board, Model model){
		String returnResult = "main/404";
		Board board_return = boardService.boardDetail(board.getBoard_no());
		// TODO 조회수 처리 (시간차를 두고?)
		model.addAttribute("board", board_return);
		
		// ETC = '1' 일 때 처리
		if(board_return.getEtc().equals("0")){
			returnResult = "board/boardDetail";
		}
		return returnResult;
	}
	
//	BOARD WRITE FORM
	@RequestMapping(value = "boardWriteForm.bf", method = RequestMethod.GET)
	public String boardWriteForm(HttpSession session){
		String returnResult = "main/404";
		if(session.getAttribute("member") != null)
			returnResult = "board/boardWriteForm";
		return returnResult;
	}
	
//	BOARD WRITE FORM -> WRITE CONFIRM
//	TODO GET 방식은 지원 안함 -> 어떤 게 GET이고 어떤 게 POST인지?
	@RequestMapping(value = "boardWrite.bf", method = RequestMethod.POST)
	public String boardWrite(HttpSession session, Board board, Model model){
		String returnResult = "main/404";
		
		// XSS 방어 (중간에 script 구문을 삽입하여 공격하는 방식)
		board.setBoard_title(board.getBoard_title().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
		board.setBoard_content(board.getBoard_content().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
		if(session.getAttribute("member") != null){
			int writeResult = boardService.boardWrite(board);
			if(writeResult > 0){
				int board_no = boardService.boardRecent(board);
				model.addAttribute("board", boardService.boardDetail(board_no));
				returnResult = "redirect:boardDetail.bf?board_no=" + board_no;
			}else{
				// TODO 글쓰기 실패 시
			}
		}
		return returnResult;
	}
	
//	BOARD DELETE
	// TODO 뒤로가기 방지 ?
	@RequestMapping(value = "boardDelete.bf", method = RequestMethod.GET)
	public String boardDelete(HttpSession session, Board board){
		String returnResult = "main/404";
		if(session.getAttribute("member") != null){
			int deleteResult = boardService.boardDelete(board.getBoard_no());
			if(deleteResult > 0)
				returnResult = "redirect:boardList.bf";
			else{
				// TODO 삭제 처리 실패 시
			}
		}
		return returnResult;
	}
	
//	BOARD UPDATE
	@RequestMapping(value = "boardUpdate.bf", method = RequestMethod.POST)
	public String boardUpdate(HttpSession session, Board board){
		String returnResult = "main/404";
		
		if(session.getAttribute("member") != null){
			int updateResult = boardService.boardUpdate(board);
			if(updateResult > 0){
				returnResult = "redirect:boardDetail.bf?board_no=" + board.getBoard_no();
			}else{
				// TODO 수정 실패 시
			}
		}
		
		return returnResult;
	}
	
//	BOARD UPDATE VIEW
	@RequestMapping(value = "boardUpdateView.bf", method = RequestMethod.GET)
	public String boardUpdateView(HttpSession session, Board board, Model model){
		String returnResult = "main/404";
		
		if(session.getAttribute("member") != null){
			model.addAttribute("board", boardService.boardDetail(board.getBoard_no()));
			returnResult = "board/boardUpdateView";
		}
		
		return returnResult;
				
	}
}
