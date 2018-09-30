<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Testing websockets</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</head>
<body>
	<fieldset>
		<textarea id="messageWindow" rows="10" cols="50" readonly="true"></textarea>
		<div id="test" style="width: 500px; overflow-y: auto; height: 100px; border: 2px solid #09c;">
			<p align="right">ddd</p>
			<p align="right">ddd</p>
			<p align="right">ddd</p>
			<p align="right">ddd</p>
			<p align="right">ddd</p>
			<p align="right">ddd</p>
			<p align="right">ddd</p>
			<p align="right">ddd</p>
			<p align="right">ddd</p>
			<p align="right">ddd</p>
			<p align="right">ddd</p>
			<p align="right">ddd</p>
			<p align="right">ddd</p>
			<p align="right">ddd</p>
			<p align="right">ddd</p>
			<p align="left">ddd</p>
		</div>
		<br /> <input id="inputMessage" type="text" /> <input type="submit"
			value="send" onclick="send()" />
	</fieldset>
</body>
<script type="text/javascript">
	$(document).ready(function() {

		$("#sendBtn").click(function() {

			sendMessage();

			$('#message').val('')

		});

		$("#inputMessage").keydown(function(key) {
			if (key.keyCode == 13) {// 엔터
				send();
				$('#inputMessage').val('')
			}
		});
	});
</script>
<script type="text/javascript">
	var textarea = document.getElementById("messageWindow");
	var webSocket = new WebSocket("ws://192.168.219.105:8040/hpot/broadcasting");
	var inputMessage = document.getElementById("inputMessage");
	var elem = document.getElementById('messageWindow');
	webSocket.onerror = function(event) {
		onError(event)
	};
	webSocket.onopen = function(event) {
		onOpen(event);
	};
	webSocket.onmessage = function(event) {
		onMessage(event);
	};
	function onMessage(event) {
		console.log(elem.scrollTop);
		console.log(elem.scrollHeight);
		if(elem.scrollTop+154 == elem.scrollHeight){
			textarea.value += event.data + "\n";
			elem.scrollTop = elem.scrollHeight;
		} else{
			textarea.value += event.data + "\n";
		}
	}
	function onOpen(event) {
		textarea.value += "연결 성공\n";
		webSocket.send("'${sessionScope.id}'님이 입장하셨습니다.qsd");
	}
	function onError(event) {
		alert(event.data);
	}
	function send() {
		if (inputMessage.value == "") {
		} else {
			var massage = "${sessionScope.id} : " + inputMessage.value;
			textarea.value += "나 : " + inputMessage.value + "\n";
			webSocket.send(massage);
			inputMessage.value = "";
			var elem = document.getElementById('messageWindow');
			elem.scrollTop = elem.scrollHeight;
		}
	}
	//     채팅이 많아져 스크롤바가 넘어가더라도 자동적으로 스크롤바가 내려가게함
	/* window.setInterval(function() {
		var elem = document.getElementById('messageWindow');
		elem.scrollTop = elem.scrollHeight;
	}, 0); */
	window.setInterval(function() {
		var elem2 = document.getElementById('test');
		elem2.scrollTop = elem.scrollHeight;
	}, 0);
</script>
</html>