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
		 
		
		System.out.println(rm.getMessage());
	}

}
