<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function goBack() {
		window.history.back();
	}
</script>
</head>
<body>
<body>
	<form action="pwfindservice" method="post">
		<table>
			<tr>
				<th>비밀번호 찾기</th>
			</tr>
			<tr>
				<td>이이디와 회원가입시 사용한 이메일주소를 입력해주십시요</td>
			</tr>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="id" placeholder="아이디입력" required></td>
			</tr>
			<tr>
			<td><input type="submit" value="페스워드 찾기"></td>
			<td><button onclick="goBack()" onclick="return confirm('확인시 가입시 사용한 이메일주소에 인증메일이 전송됩니다');">뒤로가기</button></td>
			</tr>
		</table>
	</form>
</body>
</body>
</html>