package kr.or.ddit.board.service;

import kr.or.ddit.board.vo.AttatchVO;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.PagingVO;

public interface BoardService {
	/**
	 * 게시글 생성
	 * @param board 
	 * @return 생성성공 , 생성실패
	 */
	public int createBoard(BoardVO board);
	public void retrieveBoardList(PagingVO<BoardVO> pagingVO);
	/**
	 * 게시글 조회
	 * @param boNo
	 * @return 존재여부(NotExistBoardException)
	 */
	public BoardVO retrieveBoard(int boNo);
	/**
	 * 게시글 수정
	 * @param board
	 * @return 존재여부, 인증성공여부(AuthenticationException), rowcnt
	 */
	public ServiceResult modifyBoard(BoardVO board);
	public int removeBoard(int boNo);

	public AttatchVO retrieveFordownload(int attNo);
}
