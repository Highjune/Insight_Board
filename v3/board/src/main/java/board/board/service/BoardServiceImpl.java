package board.board.service;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import board.board.dto.BoardDto;
import board.board.mapper.BoardMapper;

@Service
@Transactional 
public class BoardServiceImpl implements BoardService {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	//데이터베이스에 접근하는 DAO 빈을 선언.
	@Autowired
	private BoardMapper boardMapper;  

	@Override
	public List<BoardDto> selectBoardList() throws Exception {
		return boardMapper.selectBoardList();
	}
	
	@Override
	public void insertBoard(BoardDto board) throws Exception {
		boardMapper.insertBoard(board);
	}

	@Override
	public BoardDto selectBoardDetail(int boardIdx) throws Exception {
		// 특정 게시글 조회수 증가
		boardMapper.updateHitCount(boardIdx); 		
		
//		int i = 10 / 0; // 일부러 에러 발생. 2가지 용도 {1. 트랜잭션-조회수 올라가지 않음, 2. 에러처리 화면 보기 위해서)
		
		// 특정 게시글 상세조회
		BoardDto board = boardMapper.selectBoardDetail(boardIdx); 
		
		return board;
	}


	@Override
	public void updateBoard(BoardDto board) throws Exception {
		boardMapper.updateBoard(board);
	}

	@Override
	public void deleteBoard(int boardIdx) throws Exception {
		boardMapper.deleteBoard(boardIdx);
		
	}
		
}
