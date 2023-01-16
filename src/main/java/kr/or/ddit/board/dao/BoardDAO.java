package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

@Mapper //myBatis sanner로 인해 인식
public interface BoardDAO {
	public int insertBoard(BoardVO board);
	public List<BoardVO> selectBoardList(PagingVO<BoardVO> pagingVO);
	public int selectTotalRecord(PagingVO<BoardVO> pagingVO);
	public BoardVO selectBoard(@Param("boNo") int boNo);
	public void incrementHit(int boNo);
	public int updateBoard(BoardVO board);
	public int deleteBoard(int boNo);
}
