package com.socket;

import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.util.ImageUtil;

@ServerEndpoint("/CusSvcChat/{Name}")
public class CusSvcChat {
	//訪客跟會員
	private static Map<String, Session> cusVisSessionsMap = new ConcurrentHashMap<>();
	private static final Set<Session> empSessions = Collections.synchronizedSet(new HashSet<Session>());
	
	Gson gson = new Gson();
	ImageUtil imageutil = new ImageUtil();
	Base64.Decoder decoder = Base64.getDecoder();
	Base64.Encoder encoder = Base64.getEncoder();
	@OnOpen
	public void onOpen(@PathParam("Name") String userId, Session userSession) throws IOException, JSONException{
		if(userId.contains("emp")){
			empSessions.add(userSession);
			System.out.println("來自員工" + userId +"的連線 = " + userSession.getId());
		}else {
			cusVisSessionsMap.put(userId,userSession);
			System.out.println("客戶連線成功");
		}	
//		}else if(userId.contains("CUS")) {
//			cusVisSessionsMap.put(userId,userSession);
//			System.out.println("來自會員" + userId +"的連線 = " + userSession.getId());
//			
//		}else if(userId.contains("vis")) { //訪客
//			cusVisSessionsMap.put(userId,userSession);
//			System.out.println("來自訪客" + userId +"的連線 = " + userSession.getId());
//		}
	}
	
	@OnMessage
	public void onMessage(Session userSession, String message) throws JSONException {
		ChatMessage chatMessage = gson.fromJson(message, ChatMessage.class);
		String sender = chatMessage.getSender();
		String receiver = chatMessage.getReceiver();
		String userMessage = chatMessage.getMessage();
		Integer isPic = chatMessage.getIsPic();
//		System.out.println("message = " + chatMessage.getMessage());
		JSONObject output = null;
		
		
		if(isPic == 1) {
			String base64Str = userMessage.substring(userMessage.indexOf(",")+1);
			String picType = userMessage.substring(userMessage.indexOf(":")+1, userMessage.indexOf(";"));
//			System.out.println("picType = " + picType);
			userMessage = "<img id=\"chatPic\" src=\"data:" + picType +";base64," + encoder.encodeToString(ImageUtil.shrink(decoder.decode(base64Str), 500)) + "\">";
//			System.out.println(userMessage);
		}
		output = new JSONObject();
		
		
		output.put("sender", sender);
		output.put("message", userMessage);
		output.put("isPic",isPic);
		
		if(receiver.contains("emp")) {//給員工
			for(Session session : empSessions) {
				if (session != null && session.isOpen()) {
					session.getAsyncRemote().sendText(output.toString());
				}
			}
			if (userSession != null && userSession.isOpen()) {
				userSession.getAsyncRemote().sendText(output.toString());
			}
			System.out.println("給員工");
		}else {//給顧客
			Session receiverSession = cusVisSessionsMap.get(receiver);
			if (receiverSession != null && receiverSession.isOpen()) {
				receiverSession.getAsyncRemote().sendText(output.toString());
			}
			System.out.println("給顧客");
			for(Session session : empSessions) {//除了傳給特定顧客，也得傳給所有員工
				if (session != null && session.isOpen()) {
					session.getAsyncRemote().sendText(output.toString());
				}
			}
		}
		
		
		ChatMessage putToJedis = new ChatMessage(sender, receiver, userMessage, isPic);
		
		
		JedisHandleMessage.saveChatMessage(sender,receiver,gson.toJson(putToJedis));
	}

	@OnError
	public void onError(Session userSession, Throwable e){
		e.printStackTrace();
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		for(Entry<String, Session> map : cusVisSessionsMap.entrySet()) {
			if(map.getValue().equals(userSession)) {
				cusVisSessionsMap.remove(map.getKey());
			}
		}
		empSessions.remove(userSession);
		
		cusVisSessionsMap.values().remove(userSession);
		System.out.println(userSession.getId() + ": Disconnected: " + Integer.toString(reason.getCloseCode().getCode()));
	}
}








