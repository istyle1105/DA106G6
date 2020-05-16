package com.socket;

public class ChatMessage {
//	private String type;
	private String sender;
	private String receiver;
	private String message;
	private Integer isPic;

	public ChatMessage( String sender, String receiver, String message, Integer isPic) {
//		this.type = type;
		this.sender = sender;
		this.receiver = receiver;
		this.message = message;
		this.isPic = isPic;
	}

	public Integer getIsPic() {
		return isPic;
	}

	public void setIsPic(Integer isPic) {
		this.isPic = isPic;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
//
//	public String getType() {
//		return type;
//	}
//
//	public void setType(String type) {
//		this.type = type;
//	}

	@Override
	public String toString() {
		return "ChatMessage [sender=" + sender + ", receiver=" + receiver + ", message=" + message + ", isPic=" + isPic
				+ "]";
	}
	
}
