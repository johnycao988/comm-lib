package com.cly.comm.json;

import org.junit.Test;

import com.cly.comm.util.JSONResult;
import com.cly.comm.util.JSONUtil;

import junit.framework.Assert;
import net.sf.json.JSONObject;

public class Test_JSON {
	
	@Test
	public void testJSONInit(){
		
		String jsErr="JSON TEST ERROR MSG.";
		
		JSONObject obt=JSONUtil.initSuccess();
		
		JSONResult jr=new JSONResult(obt);
		
		Assert.assertEquals(true,jr.isSuccess()); 
		

		obt=JSONUtil.initFailed("0000",jsErr);
		
		jr=new JSONResult(obt);
		
		Assert.assertEquals(false,jr.isSuccess());
		Assert.assertEquals(jsErr,jr.getErrorMessage());
		
	
	}

}
