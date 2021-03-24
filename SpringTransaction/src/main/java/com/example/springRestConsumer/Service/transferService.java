package com.example.springRestConsumer.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springRestConsumer.Exception.InsufficientFundException;
@Service
@Transactional
public class transferService {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public boolean transferMoney(int sid,int rid, int amount) throws InsufficientFundException{
		
		
		int deposit=jdbcTemplate.query("select salary from employee where id=?",new ResultSetExtractor<Integer>() {

			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				rs.next();
				return rs.getInt("salary");
			}
			
		}, sid);
		
		if(deposit<amount)
		{
			throw new InsufficientFundException("insufficient fund");
		}
		
		jdbcTemplate.update("update employee set salary=salary-? where id=?",amount,sid);
		
		//boolean flag=true;
		double traffic1=Math.random();
		traffic1=traffic1*10;
		int traffic=(int)traffic1;
		System.out.println(traffic);
		 if(traffic>5) {
	            throw new RuntimeException("network error");
	        }
		 
		jdbcTemplate.update("update employee set salary=salary+? where id=?",amount,rid);
		return true;
		
	}
}
