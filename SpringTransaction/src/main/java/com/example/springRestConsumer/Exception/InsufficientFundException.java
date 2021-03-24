package com.example.springRestConsumer.Exception;

import org.springframework.web.bind.annotation.ResponseStatus;


public class InsufficientFundException extends Exception{
	public InsufficientFundException(String message) {
		super(message);
	}

}
