package kr.or.ddit.board.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.inject.Inject;
import javax.xml.crypto.dsig.keyinfo.PGPData;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration("file:webapp/WEB-INF/spring/*-context.xml")
@WebAppConfiguration
public class BoardDAOTest {
	
	@Inject
	private BoardDAO boardDAO;
	
	private PagingVO<BoardVO> pagingVO;
	private BoardVO board;
	
	@Before
	public void setUp() {
		pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(1);
	}

	@Test
	public void testInsertBoard() {
		int insertBoard = boardDAO.insertBoard(board);
		log.info("insertBoard : {}",insertBoard);
	}

	@Test
	public void testSelectBoardList() {
		List<BoardVO> dataList = boardDAO.selectBoardList(pagingVO);
		assertNotEquals(0, dataList.size());
	}

	@Test
	public void testSelectTotalRecord() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectBoard() {
		BoardVO board = boardDAO.selectBoard(31);
		assertNotNull(board);
		board.getAttatchList()
			 .stream().forEach(System.out::println);
		
	}

	@Test
	public void testUpdateBoard() {
		int updateBoard = boardDAO.updateBoard(board);
		log.info("updateBoard : {}",updateBoard);
	}

	@Test
	public void testDeleteBoard() {
		fail("Not yet implemented");
	}

}
