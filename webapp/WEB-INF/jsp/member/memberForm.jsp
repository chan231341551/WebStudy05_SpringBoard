<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>

<jsp:include page="/includee/preScript.jsp" />

<c:if test="${not empty message }">
	<script type="text/javascript">
		alert("${message}");
	</script>
</c:if>

</head>
<body>
	<h4>가입 양식</h4>
	<form:form modelAttribute="member" enctype="multipart/form-data">
		<table class="table table-bordered">
			<tr>
				<th><spring:message code="member.memId" /></th>
				<td>
					<form:input path="memId" class="form-control" />
					<form:errors path="memId" element="span" cssClass="text-danger" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="member.memPass" /></th>
				<td>
					<input class="form-control" type="text"  name="memPass" />
					<form:errors path="memPass" element="span" cssClass="text-danger" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="member.memName" /></th>
				<td>
					<form:input path="memName" cssClass="form-control"/>
					<form:errors path="memName" element="span" cssClass="text-danger" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="member.memImage" /></th>
				<td>
					<input type="file" name="memImage" accept="image/*">
					<form:errors path="memImage" element="span" cssClass="text-danger" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="member.memRegno1" />1</th>
				<td>
					<form:input path="memRegno1" cssClass="form-control"/>
					<form:errors path="memRegno1" element="span" cssClass="text-danger" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="member.memRegno2" />2</th>
				<td>
					<form:input path="memRegno2" cssClass="form-control"/>
					<form:errors path="memRegno2" element="span" cssClass="text-danger" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="member.memBir" /></th>
				<td>
					<form:input type="date" path="memBir" cssClass="form-control"/>
					<form:errors path="memBir" element="span" cssClass="text-danger" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="member.memZip" /></th>
				<td>
					<form:input path="memZip" cssClass="form-control"/>
					<form:errors path="memZip" element="span" cssClass="text-danger" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="member.memAdd1" /></th>
				<td>
					<form:input path="memAdd1" cssClass="form-control"/>
					<form:errors path="memAdd1" element="span" cssClass="text-danger" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="member.memAdd2" /></th>
				<td>
					<form:input path="memAdd2" cssClass="form-control"/>
					<form:errors path="memAdd2" element="span" cssClass="text-danger" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="member.memHometel" /></th>
				<td>
					<form:input path="memHometel" cssClass="form-control"/>
					<form:errors path="memHometel" element="span" cssClass="text-danger" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="member.memComtel" /></th>
				<td>
					<form:input path="memComtel" cssClass="form-control"/>
					<form:errors path="memComtel" element="span" cssClass="text-danger" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="member.memHp" /></th>
				<td>
					<form:input path="memHp" cssClass="form-control"/>
					<form:errors path="memHp" element="span" cssClass="text-danger" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="member.memMail" /></th>
				<td>
					<form:input type="email" path="memMail" cssClass="form-control"/>
					<form:errors path="memMail" element="span" cssClass="text-danger" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="member.memJob" /></th>
				<td>
					<form:input path="memJob" cssClass="form-control"/>
					<form:errors path="memJob" element="span" cssClass="text-danger" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="member.memLike" /></th>
				<td>
					<form:input path="memLike" cssClass="form-control"/>
					<form:errors path="memLike" element="span" cssClass="text-danger" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="member.memMemorial" /></th>
				<td>
					<form:input  path="memMemorial" cssClass="form-control"/>
					<form:errors path="memMemorial" element="span" cssClass="text-danger" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="member.memMemorialday" /></th>
				<td>
					<form:input type="date" path="memMemorialday" cssClass="form-control"/>
					<form:errors path="memMemorialday" element="span" cssClass="text-danger" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="member.memMileage" /></th>
				<td>
					<form:input type="number" path="memMileage" cssClass="form-control"/>
					<form:errors path="memMileage" element="span" cssClass="text-danger" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="member.memDelete" /></th>
				<td>
					<form:input path="memDelete" cssClass="form-control"/>
					<form:errors path="memDelete" element="span" cssClass="text-danger" />
				</td>
			</tr>
		</table>
		<input type="submit" class="btn btn-primary" value="가입">
	</form:form>
	<jsp:include page="/includee/postScript.jsp" />

</body>
</html>