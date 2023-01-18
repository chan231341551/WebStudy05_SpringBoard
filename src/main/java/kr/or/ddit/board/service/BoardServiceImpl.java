package kr.or.ddit.board.service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.board.dao.AttatchDAO;
import kr.or.ddit.board.dao.BoardDAO;
import kr.or.ddit.board.exception.AuthenticationException;
import kr.or.ddit.board.exception.NotExistBoardException;
import kr.or.ddit.board.vo.AttatchVO;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.PagingVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class BoardServiceImpl implements BoardService {
	
	@Inject
	private BoardDAO boardDAO;
	
	@Inject
	private AttatchDAO attatchDAO;
	
	@Inject
	private PasswordEncoder encoder;
	
	//@Value의 장점은 PropertySource를 사용하여 다양한 프로퍼티 파일을 쉽게 불러들여서 값을 지정한다는 장점이 있다. 
	@Value("#{appInfo.saveFiles}")
	private File saveFiles;
	
	@PostConstruct
	public void init() throws IOException {
		log.info("EL로 주입된 첨부파일 저장 경로 : {}",saveFiles.getCanonicalPath());
	}
	
	//CommonsMultipartResolver 의 프로퍼티를 이용하여 최대 가능한 업로드 사이즈 등 지정 가능
//	StandardServletMultipartResolver -> Servlet 3.0 이상부터 표준
	private int processAttathList(BoardVO board) {
		List<AttatchVO> attatchList = board.getAttatchList();
		if(attatchList==null || attatchList.isEmpty()) {
			return 0;
		}
		//1.metadata 저장 - DB (ATTATCH)
		int rowcnt = attatchDAO.insertAttatches(board);
		//2.binary 저장 - Middle Tier : (D:\saveFiles)
			try {
				for(AttatchVO attatch : attatchList) {
//					if(true) {
//						throw new RuntimeException("강제 발생 예외");
//					}
				attatch.saveTo(saveFiles);				
			}
				return rowcnt;
		}catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
//	@Transactional //선언적 프로그래밍 기법 (AOP)
	@Override
	public int createBoard(BoardVO board) {
		String plain = board.getBoPass();
		String encoded = encoder.encode(plain);
		board.setBoPass(encoded);
		int rowcnt = boardDAO.insertBoard(board);
		//첨부 파일 등록
		rowcnt += processAttathList(board);
						
		return rowcnt;
	}

	@Override
	public void retrieveBoardList(PagingVO<BoardVO> pagingVO) {
		pagingVO.setTotalRecord(boardDAO.selectTotalRecord(pagingVO));
		
		List<BoardVO> boardList = boardDAO.selectBoardList(pagingVO);
		pagingVO.setDataList(boardList);
		boardList.stream().forEach(System.out::println);
	}
	
	@Override
	public BoardVO retrieveBoard(int boNo) {
		BoardVO selectBoard = boardDAO.selectBoard(boNo);
		if(selectBoard == null) {
			throw new NotExistBoardException(boNo);
		}
		boardDAO.incrementHit(boNo);
		return selectBoard;
	}
	
		

	@Override
	public int modifyBoard(BoardVO board) {
		BoardVO savedBoard = boardDAO.selectBoard(board.getBoNo());
		if(savedBoard == null) {
			throw new NotExistBoardException(board.getBoNo());
		}
		boardAthenticate(board.getBoPass(),savedBoard.getBoPass());
		// 1.board update
		int rowcnt = boardDAO.updateBoard(board);
		// 2.new attatch insert (metadata, binary)
		rowcnt += processAttathList(board);
		
		int[] delAttNos = board.getDelAttNos();
		Arrays.sort(delAttNos);
		if(delAttNos != null && delAttNos.length > 0) {
			// 3.delete attatch(metadata, binary)
			rowcnt += attatchDAO.deleteAttathes(board);
			String[] delAttSavenames = savedBoard.getAttatchList().stream()
					  .filter(attatch->{
						  // 트리구조 기반 이진탐색
						  // 2,3만 걸러낸 attatch
						  return Arrays.binarySearch(delAttNos, attatch.getAttNo()) >= 0;
					  }).map(AttatchVO::getAttSavename)
					  	.toArray(String[]::new);
			for(String saveName : delAttSavenames) {
				FileUtils.deleteQuietly(new File(saveFiles,saveName));
			}
		}
		
		return rowcnt;
	}

	private void boardAthenticate(String inputPass, String savedPass) {
		if(!encoder.matches(inputPass, savedPass)) {
			throw new AuthenticationException("비밀번호 인증 실패");
		}
	}

	@Override
	public int removeBoard(int boNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AttatchVO retrieveFordownload(int attNo) {
		// TODO Auto-generated method stub
		return null;
	}

}
