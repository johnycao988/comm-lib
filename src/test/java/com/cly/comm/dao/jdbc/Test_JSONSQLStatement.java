package com.cly.comm.dao.jdbc;

import org.junit.Test;

import com.cly.comm.dao.jdbc.JSONSQL;
import com.cly.comm.dao.jdbc.JSONSQLStatement;

import junit.framework.Assert;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Test_JSONSQLStatement {
	
	@Test
	public void Test_JSONSQLStatement(){
		
		String testds="testds";
		
		JSONSQLStatement jst=new JSONSQLStatement(testds);
		
		JSONSQL js=new JSONSQL("INSERT FA, FB, FC TEST_TABLE VALUES (?,?,?)");		
		js.addSQLValues("FA");
		js.addSQLValues("FB");
		js.addSQLValues("FC");
		jst.addJSONSQL(js);		
		
		js=new JSONSQL("UPDATE TEST_TABLE SET FA=?, FB=?, FC=? WHERE FD=? AND FE=?");		
		js.addSQLValues("FA");
		js.addSQLValues("FB");
		js.addSQLValues("FC");
		js.addSQLValues("FD");
		js.addSQLValues("FE");
		jst.addJSONSQL(js);
		
		js=new JSONSQL("SELECT FA,FB,FC FROM TEST_TABLE WHERE FD=? AND FE=?",200);		
		js.addSQLValues("FD");
		js.addSQLValues("FE");
		jst.addJSONSQL(js);
		 
		JSONObject jo=jst.getJSONSQLs();
		
		Assert.assertEquals(testds,jo.get(JSONSQL.DATA_SOURCE));
		
		JSONArray ja=jo.getJSONArray(JSONSQL.SQL_LIST_HEADER);
		JSONObject job=(JSONObject)ja.get(0);
		//System.out.println(job.toString());
		String exs="{\"SQL\":\"INSERT FA, FB, FC TEST_TABLE VALUES (?,?,?)\",\"MAX_ROWS\":-1,\"VALUES\":[\"FA\",\"FB\",\"FC\"]}";
		Assert.assertEquals(exs,job.toString());
		Assert.assertEquals(-1,job.get(JSONSQL.MAX_ROWS));
		
		
		job=(JSONObject)ja.get(1);		
		//System.out.println(job.toString());
		exs="{\"SQL\":\"UPDATE TEST_TABLE SET FA=?, FB=?, FC=? WHERE FD=? AND FE=?\",\"MAX_ROWS\":-1,\"VALUES\":[\"FA\",\"FB\",\"FC\",\"FD\",\"FE\"]}";
		Assert.assertEquals(exs,job.toString());
		Assert.assertEquals(-1,job.get(JSONSQL.MAX_ROWS));

		job=(JSONObject)ja.get(2);
		//System.out.println(job.toString());
		exs="{\"SQL\":\"SELECT FA,FB,FC FROM TEST_TABLE WHERE FD=? AND FE=?\",\"MAX_ROWS\":200,\"VALUES\":[\"FD\",\"FE\"]}";
		Assert.assertEquals(exs,job.toString());
		Assert.assertEquals(200,job.get(JSONSQL.MAX_ROWS));
		
         
		
	}
	

}
