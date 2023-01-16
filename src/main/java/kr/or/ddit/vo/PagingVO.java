package kr.or.ddit.vo;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 페이징과 관련된 모든 데이터를 가진 객체
 * @author PC-02
 *
 */
@Getter
@NoArgsConstructor
@ToString
public class PagingVO<T> {
	
	
	public PagingVO(int screenSize, int blockSize) {
		super();
		this.screenSize = screenSize;
		this.blockSize = blockSize;
	}

	private int totalRecord; // DB 조회
	private int screenSize=10; // 임의 설정
	private int blockSize=5; // 임의 설정
	
	private int currentPage; // 클라이언트 파라미터
	
	private int totalPage;
	private int startRow;
	private int endRow;
	private int startPage;
	private int endPage;
	
	private List<T> dataList;
	
	private T detailCondition; // 단순 키워드 검색용
	private SearchVO simpleCondition; //  상세 검색용
	
	public void setDetailCondition(T detailCondition) {
		this.detailCondition = detailCondition;
	}
	
	public void setSimpleCondition(SearchVO simpleCondition) {
		this.simpleCondition = simpleCondition;
	}
	
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
	
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		totalPage = (totalRecord + (screenSize - 1)) / screenSize;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		endRow = currentPage * screenSize;
		startRow = endRow - (screenSize - 1);
		endPage = ((currentPage + (blockSize - 1)) / blockSize) * blockSize;
		startPage = endPage - (blockSize - 1);
	}
	
	private final String APATTERN =  "<a class='paging' href='#' data-page='%d'>%s</a>";
	public String getPagingHTML() {
		StringBuffer html = new StringBuffer();
		
		if(startPage > 1) {
			html.append(
				String.format(APATTERN, currentPage-1,"이전")
			);
		}
		
		endPage = endPage > totalPage ? totalPage : endPage;
		for(int page=startPage; page<=endPage; page++) {
			if(page == currentPage) {
				html.append(
					"<a href='#'>"+page+"</a>"
				);
			}
			else {
				html.append(
					String.format(APATTERN, page, page+"")	
				);
				
			}
		}
		
		if(endPage < totalPage) {
			html.append(
				String.format(APATTERN, currentPage+1 ,"다음")
					
			);
		}
		return html.toString();
	}
	
}
