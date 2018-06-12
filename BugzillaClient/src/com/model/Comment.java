package com.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Comment {

	public Comment() {
		// TODO Auto-generated constructor stub
	}
	private int id;
	private String comment;
	boolean is_private;
	String token;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public boolean isIs_private() {
		return is_private;
	}
	public void setIs_private(boolean is_private) {
		this.is_private = is_private;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	
}
