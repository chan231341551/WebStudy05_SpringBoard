<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">

</style>
<meta charset="UTF-8">
<title>member/memberView.do</title>
<jsp:include page="/includee/preScript.jsp" />
<c:if test="${not empty message }">
	<script type="text/javascript">
		alert("${message}");
	</script>
</c:if>
</head>
<body>
	<c:if test="${not empty member }">
	
	<table class="table table-bordered">
		<tr>
			<th>회원아이디</th>
			<td>${member.memId}</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td>${member.memPass}</td>
		</tr>
		<tr>
			<th>회원명</th>
			<td>${member.memName}</td>
		</tr>
		<tr>
			<th>프로필 이미지</th>
			<td>
				<img src="data:image/*;base64,${member.base64MemImg }">
			</td>
		</tr>
		<tr>
			<th>주민번호1</th>
			<td>${member.memRegno1}</td>
		</tr>
		<tr>
			<th>주민번호2</th>
			<td>${member.memRegno2}</td>
		</tr>
		<tr>
			<th>생일</th>
			<td>${member.memBir}</td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td>${member.memZip}</td>
		</tr>
		<tr>
			<th>주소1</th>
			<td>${member.memAdd1}</td>
		</tr>
		<tr>
			<th>주소2</th>
			<td>${member.memAdd2}</td>
		</tr>
		<tr>
			<th>집전번</th>
			<td>${member.memHometel}</td>
		</tr>
		<tr>
			<th>회사전번</th>
			<td>${member.memComtel}</td>
		</tr>
		<tr>
			<th>휴대폰</th>
			<td>${member.memHp}</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${member.memMail}</td>
		</tr>
		<tr>
			<th>직업</th>
			<td>${member.memJob}</td>
		</tr>
		<tr>
			<th>취미</th>
			<td>${member.memLike}</td>
		</tr>
		<tr>
			<th>기념일</th>
			<td>${member.memMemorial}</td>
		</tr>
		<tr>
			<th>기념일자</th>
			<td>${member.memMemorialday}</td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td>${member.memMileage}</td>
		</tr>
		<tr>
			<th>탈퇴여부</th>
			<td>${member.memDelete}</td>
		</tr>
		<c:if test="${sessionScope.authMember eq member }"> <!-- .equals -->
			<tr>
				<td colspan="2">
					<a href='<c:url value="/member/memberUpdate.do"></c:url>' class="btn btn-primary">수정</a>
					<a data-bs-toggle="modal" data-bs-target="#exampleModal" class="btn btn-danger">탈퇴</a> 
				</td>
			</tr>
		</c:if>
		
		<tr>
			<th>구매 기록</th>
			<td>
				<table>
					<thead>
						<tr>
							<th>상품 아이디</th>
							<th>상품명</th>
 							<th>분류명</th> 
							<th>거래처명</th> 
							<th>구매가</th>
							<th>판매가</th>
							<th>마일리지</th>
						</tr>
					</thead>
						<tbody>
						<c:set var="prodList" value="${member.prodList }"></c:set>
						<c:choose>
							<c:when test="${not empty prodList}">
								<c:forEach items="${prodList}" var="prod">
									<tr>
										<td>${prod.prodId}</td>
		 								<td>
		 									<c:url value="/prod/prodView.do" var="prodViewURL">
		 										<c:param name="what" value="${prod.prodId }"></c:param>
		 									</c:url>
		 									<a href="${prodViewURL }">${prod.prodName}</a>
		 								</td>  
		 	 							<td>${prod.lprodNm }</td>  
			 							<td>${prod.buyer.buyerName}</td>   
		 	 							<td>${prod.prodCost}</td>  
		 	 							<td>${prod.prodPrice}</td>  
		 	 							<td>${prod.prodMileage}</td>  
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="7">구매기록 없음.</td>
								</tr>
							</c:otherwise>
						</c:choose>
						</tbody>
				</table>
			</td>
		</tr>
		
	</table>

		<div class="modal fade" id="exampleModal" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-5" id="exampleModalLabel">Modal
							title</h1>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<form method="post" action='<c:url value="/member/memberDelete.do"></c:url>'>
						<div class="modal-body">
							<input type="password" name="memPass" />
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
							<button type="submit" class="btn btn-primary">탈퇴</button>
						</div>
					</form>
				</div>
			</div>
		</div>

	</c:if>
	<script type="text/javascript">
		$("#exampleModal").on("hidden.bs.modal",function(event){
			$(this).find("form").get(0).reset() // get(0)->[0] 꺼낸것과 같음
		})
	</script>
</body>
<jsp:include page="/includee/postScript.jsp" />
</html>