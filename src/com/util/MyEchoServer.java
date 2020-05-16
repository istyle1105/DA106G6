package com.util;
import java.io.*;
import java.util.*;

import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.Session;
import javax.websocket.OnOpen;
import javax.websocket.OnMessage;
import javax.websocket.OnError;
import javax.websocket.OnClose;
import javax.websocket.CloseReason;

@ServerEndpoint("/MyEchoServer/{number}")
public class MyEchoServer {

    private static int online_num = 0;
    private static Map<Session,String> userMap;
	
private static final Set<Session> allSessions = Collections.synchronizedSet(new HashSet<Session>());
	
	@OnOpen
	public void onOpen(@PathParam("number") String number,Session userSession) throws IOException {
		allSessions.add(userSession);
		System.out.println(userSession.getId() + ": 已連線");
		System.out.println(number + ": 已連線");
		userMap=new HashMap();
		userMap.put(userSession, number);
//		if(!number.contains("E")) {
		addOnlineCount();
//		}
		for (Session session : allSessions) {
			if (session.isOpen())
				session.getAsyncRemote().sendText(String.valueOf(getOnline_num()));
		}
		System.out.println("counter" + String.valueOf(getOnline_num()));
		
		
	}

	
	@OnMessage
	public void onMessage(Session userSession, String message) {
		for (Session session : allSessions) {
			if (session.isOpen())
				session.getAsyncRemote().sendText(String.valueOf(getOnline_num()));
		}
		System.out.println("counter" + String.valueOf(getOnline_num()));
	}
	
	@OnError
	public void onError(Session userSession, Throwable e){
//		e.printStackTrace();
	}
	
	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		allSessions.remove(userSession);
//		if(!userMap.get(userSession).contains("E")) {
			subOnlineCount();
//		}
		System.out.println(userSession.getId() + ": Disconnected: " + Integer.toString(reason.getCloseCode().getCode()));
		for (Session session : allSessions) {
			if (session.isOpen())
				session.getAsyncRemote().sendText(String.valueOf(getOnline_num()));
		}
		System.out.println("counter" + String.valueOf(getOnline_num()));
	}
	

    public synchronized int getOnline_num(){
        return MyEchoServer.online_num;
    }
    public synchronized int subOnlineCount(){
        return MyEchoServer.online_num--;
    }
    public synchronized int addOnlineCount(){
        return MyEchoServer.online_num++;
    }

 
}
