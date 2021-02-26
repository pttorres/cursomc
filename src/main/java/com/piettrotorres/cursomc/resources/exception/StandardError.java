package com.piettrotorres.cursomc.resources.exception;

import java.io.Serializable;
import java.time.LocalDateTime;

public class StandardError implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer status;
	private String msg;
	private LocalDateTime timeStamp;
	
	public StandardError(Integer status, String msg, LocalDateTime timeStamp) {
		super();
		this.status = status;
		this.msg = msg;
		this.timeStamp = timeStamp;
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

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
	
}
