package com.jkl.hpot.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.web.socket.WebSocketSession;

@ServerEndpoint("/broadcasting")
public class Broadsocket {

	private static Set<Session> clients = Collections
			.synchronizedSet(new HashSet<Session>());

	@OnMessage
	public void onMessage(String message, Session session) throws IOException {
		System.out.println(message);
		synchronized (clients) {
			// Iterate over the connected sessions
			// and broadcast the received message
			for (Session client : clients) {
				if (!client.equals(session)) {
					client.getBasicRemote().sendText(message);
				}
			}
		}
	}

	@OnOpen
	public void onOpen(/*String message, */Session session) throws IOException {
		// Add session to the connected sessions set
		System.out.println(session);
		clients.add(session);
		/*synchronized (clients) {
			// 들어왔을때 메시지 띄우기
			for (Session client : clients) {
				if (!client.equals(session)) {
					client.getBasicRemote().sendText(message);
				}
			}
		}*/
	}

	@OnClose
	public void onClose(Session session) throws IOException {
		// Remove session from the connected sessions set
		clients.remove(session);
		synchronized (clients) {
			// Iterate over the connected sessions
			// and broadcast the received message
			for (Session client : clients) {
				client.getBasicRemote().sendText("상대방이 나갔습니다.");
			}
		}
	}
}