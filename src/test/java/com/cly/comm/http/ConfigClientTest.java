package com.cly.comm.http;

 
import java.io.IOException; 

import org.junit.Test;

import com.cly.comm.client.config.ConfigClient; 

public class ConfigClientTest {
	
	@Test	
	public void testConfigOnLocal() throws IOException{ 
	  
		try{ 
		
		String s=ConfigClient.getText("/tomcat.conf/catalina.properties"); 		
	   // String s=ConfigClient.getText("/test.properties");
        System.out.println(s);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	
	
	public void testConfigOn226() throws IOException{ 
	  
		String sc=ConfigClient.getText("cloud.security.server.properties");
		System.out.println(sc);
		
	}
	
}
