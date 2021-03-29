package com.piettrotorres.cursomc.resources.exception;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StandardError implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer status;
	private String msg;
//	private LocalDateTime timeStamp;
	private String timeStamp;
	
	
	public StandardError(Integer status, String msg, LocalDateTime timeStamp) {
		super();
		this.status = status;
		this.msg = msg;
		this.timeStamp = timeStamp.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
	
}
