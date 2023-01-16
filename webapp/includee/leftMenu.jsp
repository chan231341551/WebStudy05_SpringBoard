<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <nav id="sidebarMenu" class="col-md-2 col-lg-2 d-md-block bg-light sidebar collapse">
      <div class="sidebar-sticky pt-3">
        <ul class="nav flex-column">    
        <c:forEach items="${menuList }" var="menu">
			<li class="nav-item">
				<a class="nav-link active" href="<c:url value='${menu.menuURL }'></c:url>">${menu.menuText }</a>
			</li>
        </c:forEach>
        </ul>
      </div>
    </nav>