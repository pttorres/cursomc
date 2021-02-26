package com.piettrotorres.cursomc.resources.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{

	private static final long serialVersionUID = 1L;

	private List<FieldMessage> list = new ArrayList<>();
	
	public ValidationError(Integer status, String msg, LocalDateTime timeStamp) {
		super(status, msg, timeStamp);
	}
	
	public List<FieldMessage> getErros(){
		return list;
	}

	public void addError(String fieldName, String message) {
		list.add(new FieldMessage(fieldName, message));
	}

}
