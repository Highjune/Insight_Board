package board.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.board.dto.BoardDto;
import board.board.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
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
