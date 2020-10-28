package board.board.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import board.board.dto.BoardDto;
import board.board.service.BoardService;

@Controller
public class BoardController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BoardService boardService;
	
	//게시판 모든 목록 불러오기
	@RequestMapping("/board/openBoardList.do") 
	public ModelAndView openBoardList() throws Exception{
		log.trace("trace level log");
		log.debug("debug level log");
		log.info("info level log");
		log.warn("warn level log");
		log.error("error level log");
		
		ModelAndView mv = new ModelAndView("/board/boardList"); //view를 지정한 것. templates/board/boardList.html을 의미
		
		List<BoardDto> list = boardService.selectBoardList();
		mv.addObject("list", list); //view에서 "list"라는 속성이름으로 list 값들을 사용할 수 있다.
		
		return mv;
	}
	
	//게시글 등록화면 호출하는 것
	@RequestMapping("/board/openBoardWrite.do") 
	public String openBoardWrite() throws Exception{
		return "/board/boardWrite"; //view를 지정한 것. templates/board/boardWrite.html을 의미
	}
	
	//게시글 등록하는 것 기능
	@RequestMapping("/board/insertBoard.do") //view에서 <form>의 action속성주소
	public String insertBoard(BoardDto board) throws Exception { //commander1 방식. html의 name과 클래스의 변수가 동일할 때.
		boardService.insertBoard(board);
		return "redirect:/board/openBoardList.do"; // 게시글 등록 이후에 게시글 목록 화면으로 이동
	}
	
	//게시글 상세조회
	@RequestMapping("/board/openBoardDetail.do")
	public ModelAndView openBoardDetail(@RequestParam int boardIdx) throws Exception {
		//게시글 상세화면을 보여줄 view를 지정(이름)
		ModelAndView mv = new ModelAndView("/board/boardDetail"); 
		//특정 게시물에 대한 정보를 들고 오는 것. 
		BoardDto board = boardService.selectBoardDetail(boardIdx); 
		mv.addObject("board", board);
		
		//리턴하는 mv객체 안에는 상세화면view, 특정 게시물에 대한 정보 포함
		return mv; 
	}
	
	//게시글 수정
	@RequestMapping("/board/updateBoard.do")
	public String updateBoard(BoardDto board) throws Exception{ 
		boardService.updateBoard(board);
		//수정 후 게시글 목록으로 이동
		return "redirect:/board/openBoardList.do"; 
	}
	
	//게시글 삭제
	@RequestMapping("/board/deleteBoard.do")
	public String deleteBoard(int boardIdx) throws Exception{
		boardService.deleteBoard(boardIdx);
		// 수정 후 게시글 목록으로 이동
		return "redirect:/board/openBoardList.do";
	}
	
}

