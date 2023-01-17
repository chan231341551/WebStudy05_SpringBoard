package kr.or.ddit.ui;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import kr.or.ddit.vo.PagingVO;

public class PaginationTag extends TagSupport{
	private String type;
	
	public void setType(String type) {
		this.type = type;
	}
	
	private PagingVO<?> pagingVO;
	public void setPagingVO(PagingVO<?> pagingVO) {
		this.pagingVO = pagingVO;
	}
	
	@Override
	public int doEndTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		ServletContext application = pageContext.getServletContext();
		
		WebApplicationContext springContainer = RequestContextUtils.findWebApplicationContext(request,application);
		
		PaginationManager manager = springContainer.getBean(PaginationManager.class);
		
		PaginationRenderer renderer = manager.rendererType(type);
				
		JspWriter out = pageContext.getOut();
		try {
			out.println(renderer.renderPagination(pagingVO));
			return EVAL_PAGE;
		} catch (IOException e) {
			throw new JspException(e);
		}
	}
}
