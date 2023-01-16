<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

	<table class="table table-bordered">
		<thead class="thead-dark">
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>이메일</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="boardList" value="${pagingVO.dataList}" />
			<c:choose>
				<c:when test="${not empty boardList}">
					<c:forEach items="${boardList}" var="board">
						<tr>
							<td>${board.rnum}</td>
							<td><c:url value="/board/boardView.do" var="viewURL">
									<c:param name="what" value="${board.boNo}" />
								</c:url> <a href="${viewURL}">${board.boTitle}[${board.attCount }]</a></td>
							<td>${board.boWriter}</td>
							<td>${board.boMail}</td>
							<td>${board.boDate}</td>
							<td>${board.boHit}</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="7">조건에 맞는 글이 없음</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="7"> 
					<div id="pagingArea">${pagingVO.pagingHTML}<!-- el에서는 직접적으로 getter을 호출하지 않는다. -->
						<form:form id="searchUI" modelAttribute="simpleCondition" onclick="return false;">
							<form:select path="searchType">
								<option value>전체</option>
								<form:option value="writer" label="작성자"></form:option>
								<form:option value="content" label="글의내용"></form:option>
							</form:select>
							<form:input path="searchWord"/>
							<input id="searchBtn" type="button" value="검색" />
						</form:form>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2"><c:url value="/board/boardInsert.do"
						var="listURL" /> <a class="btn btn-secondary" href="${listURL }">게시글작성</a>
			</tr>
		</tfoot>
	</table>
	<form:form id="searchForm" modelAttribute="simpleCondition">
		<form:hidden path="searchType"/>
		<form:hidden path="searchWord"/>
		<input type="hidden" name="page">
	</form:form>
<script>
   let searchForm = $("#searchForm");
   let searchUI = $("#searchUI").on("click","#searchBtn",function(){
	   let inputs = searchUI.find(":input[name]") // name 속성을 가진 모든 입력태그들
	   
	   $.each(inputs,function(index,input){
		   let name = this.name;
		   let value = $(this).val();
		   searchForm.find("[name="+name+"]").val(value);
	   });
	   searchForm.submit();
   });
   
   $("a.paging").on("click", function(event){
      event.preventDefault();
      
      let page = $(this).data("page");
      if (!page) return false;
      searchForm.find("[name=page]").val(page);
      searchForm.submit();
      
      return false;
   });
</script>
