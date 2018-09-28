<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>ui</title>
<meta charset="utf-8">

<meta charset="UTF-8">
<title>topui</title>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-muted navbar-fixed-top" style="margin-bottom:0px;background-image:url('img/honeybgcm3.png');">
		<!-- Brand/logo -->
		<img src="img/honeypot3.png" style="width: 200px; height: 80px;"></img>
		<div style="width:50px"></div>
		<!-- Links -->
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link text-warning" href="main">Home</a></li>
			<li class="nav-item"><a class="nav-link text-warning" href="#">My Page</a></li>
			<li class="nav-item"><a class="nav-link text-warning" href="#">Admin Page</a></li>
			<c:choose>
			<c:when test="${which ne null}"> 
			<li class="nav-item"><form class="form-inline"
					action="boardList">
					<select class="custom-select" name="option">
					<option selected value="제목">제목</option>
					<option value="태그">태그</option>
					<option value="작성자">작성자</option>
					<option value="내용&지역">내용&지역</option>
					</select>
					<input class="form-control mr-auto" type="text"
						placeholder="search" name="search">
						<div style="width:700px;">
							<button class="btn btn-light" type="submit">Search</button>
						</div>
						<input type="hidden" name="bWhich" value="${which}">
				</form></li>
				</c:when>
				<c:otherwise>
				<li class="nav-item"><div style="width:1019px;"></div></li>
				</c:otherwise>
				</c:choose>
			<li class="nav-item">
				<div class="btn-group">
					<c:if test="${sessionScope.id == null }">
						<button class="btn btn-warning" type="button">로그인</button>
						<button class="btn btn-info" type="button">회원가입</button>
					</c:if>
					<c:if test="${sessionScope.id != null }">
						<button class="btn btn-warning" type="button">${sessionScope.id }님 안녕하세요</button>
						<form action="memberlogout" method="post"><button>로 그 아 웃 </button></form>
					</c:if>
				</div>
			</li>
		</ul>
	</nav>
</body>
</html>