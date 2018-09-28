<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="pwmailcheack" method="post">
		<table>
			<tr>
				<th>인증번호를 전송했습니다!</th>
			</tr>
			<tr>
				<td>회원님의 회원가입때 입력한 이메일로 인증주소가 전송되었습니다. 입력시 새로운 비밀번호로 재설정하실수있습니다.</td>
			</tr>
			<tr>
				<td>전송받은 인증코드를 입력해주세요</td>
				<td><input type="text" name="mailcheack" placeholder="인증코드" required></td>
			</tr>
			<tr>
			<td><input type="submit" value="확인"></td>
			<td><button onclick="location.href='login'">로그인창으로 이동</button></td>
			</tr>
		</table>
	</form>
</body>
</html>