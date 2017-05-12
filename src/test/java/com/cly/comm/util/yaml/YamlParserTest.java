package com.cly.comm.util.yaml;


import org.junit.Assert;
import org.junit.Test;

import com.cly.comm.util.YamlParser;

public class YamlParserTest {
	
 
	@Test
	public void testYamlUtil(){
		
		try{
			
		 String ymlTestFile=YamlParserTest.class.getResource("/yml/test.yml").getFile();
		 
		 YamlParser yp=new  YamlParser();
		 
		 yp.parse(ymlTestFile);
		 
		 //get Object Value  Test
		 Assert.assertEquals("123",yp.getObjectValue("com.cs.yml.test.str").toString());
		 Assert.assertEquals("123",yp.getObjectValue("com.cs.yml.test.str","123456"));
		 Assert.assertEquals(null,yp.getObjectValue("com.cs.yml.test.null"));
		 Assert.assertEquals("123",yp.getObjectValue("com.cs.yml.test.null","123"));
		
		//get String Value  Test
		 Assert.assertEquals("123",yp.getStringValue("com.cs.yml.test.str"));
		 Assert.assertEquals("123",yp.getStringValue("com.cs.yml.test.str","123456"));
		 Assert.assertEquals(null,yp.getStringValue("com.cs.yml.test.null"));
		 Assert.assertEquals("123",yp.getStringValue("com.cs.yml.test.null","123"));

		//get Integer Value  Test
		 Assert.assertEquals(123,yp.getIntegerValue("com.cs.yml.test.int").intValue());
		 Assert.assertEquals(123,yp.getIntegerValue("com.cs.yml.test.int",12345).intValue());
		 Assert.assertEquals(null,yp.getIntegerValue("com.cs.yml.test.null"));
		 Assert.assertEquals(123,yp.getIntegerValue("com.cs.yml.test.null",123).intValue());
		 Assert.assertEquals(null,yp.getIntegerValue("com.cs.yml.test.int.null"));
		 Assert.assertEquals(123,yp.getIntegerValue("com.cs.yml.test.int.null",123).intValue());

		//get Double Value  Test
		  
		 Assert.assertTrue(yp.getDoubleValue("com.cs.yml.test.double").doubleValue()==123.123);
		 Assert.assertTrue(123.123==yp.getDoubleValue("com.cs.yml.test.double",12345.12).doubleValue());
		 Assert.assertTrue(null==yp.getDoubleValue("com.cs.yml.test.null"));
		 Assert.assertTrue(123.123==yp.getDoubleValue("com.cs.yml.test.null",123.123).doubleValue());
		 Assert.assertTrue(null==yp.getDoubleValue("com.cs.yml.test.double.null"));
		 Assert.assertTrue(123.123==yp.getDoubleValue("com.cs.yml.test.double.null",123.123).doubleValue());
		 
		//get Boolean Value  Test
		 Assert.assertEquals(true,yp.getBooleanValue("com.cs.yml.test.boolean").booleanValue());
		 Assert.assertEquals(true,yp.getBooleanValue("com.cs.yml.test.boolean",false).booleanValue());
		 Assert.assertEquals(null,yp.getBooleanValue("com.cs.yml.test.null"));
		 Assert.assertEquals(false,yp.getBooleanValue("com.cs.yml.test.null",false).booleanValue());
		 Assert.assertEquals(null,yp.getBooleanValue("com.cs.yml.test.boolean.null"));
		 Assert.assertEquals(false,yp.getBooleanValue("com.cs.yml.test.boolean.null",false).booleanValue());


		 
		}catch(Exception e){
			e.printStackTrace();
		}
		 
		 
		 
		
	}

}
