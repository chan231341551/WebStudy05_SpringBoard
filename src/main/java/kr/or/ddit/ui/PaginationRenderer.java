package kr.or.ddit.ui;

import kr.or.ddit.vo.PagingVO;

public interface PaginationRenderer {
	public String renderPagination(PagingVO<?> pagingVO);
}
