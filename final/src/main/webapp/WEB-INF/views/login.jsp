<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title>HoneyPot</title>
<link rel="shortcut icon" href="resources/img/bee.jpg">
<!-- Bootstrap core CSS -->
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
<title>로그인 폼</title>

<style>
/* The Modal (background) */
.modal {
	display: none;
	/* Hidden by default */
	position: fixed;
	/* Stay in place */
	z-index: 1;
	/* Sit on top */
	left: 0;
	top: 0;
	width: 100%;
	/* Full width */
	height: 100%;
	/* Full height */
	overflow: auto;
	/* Enable scroll if needed */
	background-color: rgb(0, 0, 0);
	/* Fallback color */
	background-color: rgba(0, 0, 0, 0.4);
	/* Black w/ opacity */
}

/* Modal Content/Box */
.modal-content {
	background-color: #fefefe;
	margin: 15% auto;
	/* 15% from the top and centered */
	padding: 20px;
	border: 1px solid #888;
	width: 50%;
	/* Could be more or less, depending on screen size */
}

/* The Close Button */
.close {
	color: white;
	float: right;
}

.close:hover, .close:focus {
	text-decoration: none;
	cursor: pointer;
}
</style>
</head>

<body>
	<c:if test="${msg != null}">
		<script>
			alert('${msg}');
		</script>
	</c:if>
	<c:if test="${msg2 != null}">
		<script>
			alert('${msg2}');
		</script>
	</c:if>
	<!-- Header - set the background image for the header in the line below -->
	<header class="py-5 bg-image-full"
		style="height: 1100px; background-image: url(resources/img/3.png); background-color: #FFEB5A">

		<img class="img-fluid d-block mx-auto"
			src="resources/img/honeypot3.png" alt="" style="padding-top: 80px;">

		<div class="card align-middle"
			style="width: 20rem; border-radius: 20px; margin: auto;">
			<div class="card-title" style="margin-top: 30px;">
				<h2 class="card-title text-center" style="color: #113366;">회원로그인</h2>
			</div>
			<div class="card-body">
				<form action="memberlogin" method="post" onSubmit="logincall();return false">
					<h5 class="form-signin-heading">회원님의 정보를 입력해주세요</h5>
					<label for="inputEmail" class="sr-only">Your ID</label> 
					<input type="text" id="id" name="id" class="form-control"
						placeholder="Your ID" required autofocus> <BR> <label
						for="inputPassword" class="sr-only">Password</label> <input
						type="password" id="upw" name="password" class="form-control"
						placeholder="Password" required><br>
					<div class="checkbox"></div>
					<button id="btn-Yes" class="btn btn-lg btn-primary btn-block"
						type="submit">로 그 인</button>
					
					
				</form><br>
				<button class="btn btn-lg btn-primary btn-block" onclick="location.href='idfind'">아 이 디 찾 기</button>
				<button class="btn btn-lg btn-primary btn-block" onclick="location.href='pwdfind'">비 밀 번 호 찾 기</button>
				<h5 class="form-signin-heading" style="text-align: center;">
				<br>아직 회원이 아니신가요?
				</h5>
				<br>
				<!-- Trigger/Open The Modal -->
				<button id="myBtn" class="btn btn-lg btn-primary btn-block">회 원 가 입</button>

				<!-- The Modal -->
				<div id="myModal" class="modal">

					<!-- Modal content -->
					<div class="modal-content">

						<div class="page-header">
							<h1>HoneyPot 회원가입</h1>
						</div>
						<div class="col-md-6 col-md-offset-3">
							<form action="memberjoin" method="post">
								<div class="form-group">
									<label for="InputID">사용하실 아이디</label> 
									<input type="text" class="form-control" name="id" placeholder="아이디" required>
								</div>
								<div class="form-group">
									<label for="InputPassword">비밀번호</label> <input type="password"
										class="form-control" name="password" placeholder="비밀번호"
										required>
								</div>
								<div class="form-group">
									<label for="username">이름</label> <input type="text"
										class="form-control" name="name" placeholder="이름" required>
								</div>
								<div class="form-group">
									<label for="userage">나이</label> <input type="number"
										class="form-control" name="age" placeholder="나이" required>
								</div>
								<div class="form-group">
									<label for="useremail">이메일</label> <input type="text"
										class="form-control" name="email" placeholder="이메일" required>
								</div>
								<div class="form-group">
									<label for="useraddress">주소</label> <input type="text"
										class="form-control" name="address" placeholder="주소">
								</div>
								<div class="form-group">
									<label for="usergender">성별</label><br> <input type="radio"
										name="gender" value="남자" checked="checked" /> 남자 <input
										type="radio" name="gender" value="여자" /> 여자
								</div>
								<div class="form-group text-center">
									<button type="submit" class="btn btn-info">
										회원가입<i class="fa fa-check spaceLeft"></i>
									</button>
									<button class="btn btn-warning">
										<span class="close" style="font-size: 15pt">가입취소</span><i
											class="fa fa-times spaceLeft"></i>
									</button>

								</div>
							</form>
						</div>

					</div>

				</div>
				<br>
				<button class="btn btn-lg btn-primary btn-block"
					onclick="location.href='main'">비 로 그 인 접 속</button>
			</div>
		</div>



	</header>




	<!-- Footer -->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; HoneyPot
				2018</p>
		</div>
		<!-- /.container -->
	</footer>

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
