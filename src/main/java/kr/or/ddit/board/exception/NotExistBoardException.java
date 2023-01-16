package kr.or.ddit.board.exception;

public class NotExistBoardException extends RuntimeException {

	public NotExistBoardException(int boNo) {
		super(String.format("%d 번의 글은 존재하지않습니다.", boNo));
		// TODO Auto-generated constructor stub
	}
	
}
