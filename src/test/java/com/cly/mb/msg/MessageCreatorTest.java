package com.cly.mb.msg;

import org.junit.Before;
import org.junit.Test;

import com.cly.mb.msg.impl.RequestMessageCreator;

public class MessageCreatorTest {
	
	@Before
	public void init(){
		
	}
	
	@Test
	public void testRequestMessageCreator(){
		
		RequestMessageCreator rm= new RequestMessageCreator();
		rm.setMsgInfo_Id("123");
		rm.setMsgInfo_Id("1234");
		rm.setMsgInfo_CorrelationId("01234");
		rm.setMsgInfo_CorrelationId("4321");
		rm.setMsgInfo_ExpiredSeconds(30);
		
		System.out.println(rm.getMessage());
	}

}
