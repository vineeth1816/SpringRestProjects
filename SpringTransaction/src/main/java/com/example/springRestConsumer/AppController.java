package com.example.springRestConsumer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.springRestConsumer.Exception.InsufficientFundException;
import com.example.springRestConsumer.Service.transferService;

@RestController
public class AppController {
	
	@Autowired
	transferService TransferService;
	
	@GetMapping("/sendMoney/{sid}/{rid}/{amount}")
	public ResponseEntity<Object> transferMoney(@PathVariable int sid,@PathVariable int rid,@PathVariable int amount){
		
		try{
			TransferService.transferMoney(sid, rid, amount);
		}
		catch(InsufficientFundException e) {
			throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "no fund");
		}
		catch(RuntimeException e) {
			throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "no fund");
		}
		return new ResponseEntity<Object>("transfer done",HttpStatus.OK);
	}
	
	
	
	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<Object> handleException1(ResponseStatusException e){
		return new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_GATEWAY);
	}
	

}
