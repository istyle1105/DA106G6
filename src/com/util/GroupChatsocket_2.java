package com.util;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;


@ServerEndpoint("/oneonone/{memberno}/{customerserviceid}")
public class GroupChatsocket_2 {

	private static final Map<String, Set<Session>> map = new Hashtable<String, Set<Session>>();
	

	Gson gson = new Gson();

	@OnOpen
	public void onOpen(@PathParam("memberno") String memberno, @PathParam("customerserviceid") String customerserviceid,
			Session userSession) throws IOException {

		System.out.println("客服編號=" + customerserviceid);
		System.out.println("會員上線:" + memberno);
		Set<Session> groupallSessions = null;
		if (map.containsKey(customerserviceid)) {
			groupallSessions = map.get(customerserviceid);
			groupallSessions.add(userSession);
			map.put(customerserviceid, groupallSessions);
		} else {
			groupallSessions = Collections.synchronizedSet(new HashSet<Session>());
			groupallSessions.add(userSession);
			map.put(customerserviceid, groupallSessions);
		}
		
	}

	@OnMessage
	public void onMessage(@PathParam("customerserviceid") String customerserviceid, Session userSession, String message) throws JSONException {
		Set<Session> groupallSessions = map.get(customerserviceid);
		JSONObject jsonobj = new JSONObject(message);
		String memberno = jsonobj.getString("memberno");
		for (Session session : groupallSessions) {
			if (session.isOpen())
				session.getAsyncRemote().sendText(message);
		}
		System.out.println("Message received: " + message);
	}

	@OnError
	public void onError(Session userSession, Throwable e) {

	}

	@OnClose
	public void onClose(@PathParam("memberno") String memberno, @PathParam("customerserviceid") String customerserviceid, Session userSession) {
//		map.remove(userSession);
		
		System.out.println("memberID " + memberno);
		Set<Session> newAllSessions = map.get(customerserviceid);
		newAllSessions.remove(userSession);
		map.put(customerserviceid,newAllSessions);
		System.out.println();
	}
}
