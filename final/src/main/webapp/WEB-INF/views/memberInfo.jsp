<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css"
	type="text/css" rel="stylesheet">

<!-- Custom styles for this template -->
<link
	href="${pageContext.request.contextPath}/resources/css/full-width-pics.css"
	type="text/css" rel="stylesheet">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

<style>
.tr {
	width: 500px;
	heigt: 100px;
	border: 1px solid #444444;
}
</style>
<script>
	function goBack() {
		window.history.back();
	}
</script>
</head>
<body>
	<table>
		<tr>
			<th><%=session.getAttribute("id")%>회원님의 정보</th>
		</tr>
		<tr>
			<td>아이디</td>
			<td>${info.id}</td>
		</tr>
		<tr>
			<td>이름</td>
			<td>${info.name}</td>
		</tr>
		<tr>
			<td>나이</td>
			<td>${info.age}</td>
		</tr>
		<tr>
			<td>이메일주소</td>
			<td>${info.email}</td>
		</tr>
		<tr>
			<td>집주소</td>
			<td>${info.address}</td>
		</tr>
		<tr>
			<td>성별</td>
			<td>${info.gender}</td>
		</tr>
	</table>
	<button onclick="goBack()">뒤로가기</button>

	<!-- Trigger/Open The Modal -->
	<button id="myBtn">프로필수정</button>
	<!-- The Modal -->
	<div id="myModal" class="modal">

		<!-- Modal content -->
		<div class="modal-content">

			<div class="page-header">
				<h1>프로필 수정</h1>
			</div>
			<div class="col-md-6 col-md-offset-3">
				<form action="memberalter" method="post">
					<div class="form-group">
						<label for="InputID">아이디(변경불가)</label> <input type="text"
							class="form-control" name="id"
							value="<%=session.getAttribute("id")%>" readonly>
					</div>
					<div class="form-group">
						<label for="InputPassword">비밀번호 변경</label> <input type="password"
							class="form-control" name="password" placeholder="비밀번호" required>
					</div>
					<div class="form-group">
						<label for="username">이름 변경</label> <input type="text"
							class="form-control" name="name" value="${info.name}"
							placeholder="이름" required>
					</div>
					<div class="form-group">
						<label for="userage">나이 변경</label> <input type="number"
							class="form-control" name="age" value="${info.age}"
							placeholder="나이" required>
					</div>
					<div class="form-group">
						<label for="useremail">이메일 변경</label> <input type="text"
							class="form-control" name="email" value="${info.email}"
							placeholder="이메일" required>
					</div>
					<div class="form-group">
						<label for="useraddress">집 주소 변경</label> <input type="text"
							class="form-control" name="address" value="${info.address}"
							placeholder="주소">
					</div>
					<div class="form-group">
						<label for="usergender">성별 변경</label><br> <input type="radio"
							name="gender" value="man" checked="checked" /> 남자 <input
							type="radio" name="gender" value="woman" /> 여자
					</div>
					<div class="form-group text-center">
						<button type="submit" class="btn btn-info" onclick="return confirm('경고! 회원 정보를 수정하시겠습니까?');">
							프로필수정<i class="fa fa-check spaceLeft"></i>
						</button>
						<button class="btn btn-warning"><span class="close" style="font-size: 15pt">수정취소</span><i class="fa fa-times spaceLeft"></i></button>

					</div>
				</form>
			</div>

		</div>

	</div>
	<!-- Bootstrap core JavaScript -->
	<script
		src="<c:url value=" /resources/vendor/jquery/jquery.min.js " />"></script>
	<script
		src="<c:url value=" /resources/vendor/bootstrap/js/bootstrap.bundel.min.js " />"></script>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
	<script>
		// Get the modal
		var modal = document.getElementById('myModal');

		// Get the button that opens the modal
		var btn = document.getElementById("myBtn");

		// Get the <span> element that closes the modal
		var span = document.getElementsByClassName("close")[0];

		// When the user clicks on the button, open the modal 
		btn.onclick = function() {
			modal.style.display = "block";
		}

		// When the user clicks on <span> (x), close the modal
		span.onclick = function() {
			modal.style.display = "none";
		}

		// When the user clicks anywhere outside of the modal, close it
		window.onclick = function(event) {
			if (event.target == modal) {
				modal.style.display = "none";
			}
		}
	</script>
</body>
</html>