package com.cly.comm.dao.jdbc;

import org.junit.Test;

import com.cly.comm.dao.jdbc.JSONSQL;

import junit.framework.Assert;

public class Test_JSONSQL {

	@Test	
	public void Test_JSONSQL(){
		
		JSONSQL js=new JSONSQL("SELECT FA, FB, FC FROM TEST_TABLE WHERE FD=? AND FE=?",100);
		
		js.addSQLValues("FD");
		js.addSQLValues("FE");
		
		String s =js.getJSONSQL().toString();
		
		s =js.getJSONSQL().toString();
		String exs="{\"SQL\":\"SELECT FA, FB, FC FROM TEST_TABLE WHERE FD=? AND FE=?\",\"MAX_ROWS\":100,\"VALUES\":[\"FD\",\"FE\"]}";
		Assert.assertEquals(exs,s);
		
		s =js.getJSONSQL().toString();
		Assert.assertEquals(exs,s);
		
	     s=js.getSQLParaVaules().toString();
		 exs="[\"FD\",\"FE\"]";
		 Assert.assertEquals(exs,s);	
		 
		 js.resetSQLValues(0, "FD-NEW");
		 js.resetSQLValues(1, "FE-NEW");
		 exs="{\"SQL\":\"SELECT FA, FB, FC FROM TEST_TABLE WHERE FD=? AND FE=?\",\"MAX_ROWS\":100,\"VALUES\":[\"FD-NEW\",\"FE-NEW\"]}";
		 s =js.getJSONSQL().toString(); 
		 Assert.assertEquals(exs,s);			
		 
		 exs="[\"FD-NEW\",\"FE-NEW\"]";
		 s=js.getSQLParaVaules().toString();
		 Assert.assertEquals(exs,s);	
		 
		 
		 
	}
	
	
}
