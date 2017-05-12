package com.cly.comm.http;

 
import java.io.IOException;
import java.util.Properties;

import org.junit.Test;

import com.cly.comm.client.config.ConfigClient;
import com.cly.comm.util.IOUtil;
import com.cly.comm.util.YamlParser;

import junit.framework.Assert; 

public class ConfigClientTest {
	
	
	public void testConfigOnLocal() throws IOException{ 
	  
		 
		Properties p=ConfigClient.getProperties("test.properties"); 		
		Assert.assertEquals("a1",p.get("a"));
		Assert.assertEquals("b2",p.get("b"));
		
		YamlParser yp= ConfigClient.getYaml("test.yml");
		Assert.assertEquals("123",yp.getStringValue("com.cs.yml.test.str"));
		
		IOUtil.writeFile(ConfigClient.getInputStream("003_bak.mp4"),"c:/temp/a.mp4");
	}

	
	@Test	
	public void testConfigOn226() throws IOException{ 
	  
		String sc=ConfigClient.getText("cloud.security.server.properties");
		System.out.println(sc);
		
	}
	
}
