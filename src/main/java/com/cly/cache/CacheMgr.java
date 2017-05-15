package com.cly.cache;

import java.io.InputStream;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

public class CacheMgr {

	private static CacheManager cMgr;

	public static void init(InputStream in) {
		
		if(cMgr!=null)
			cMgr.clearAll();

		cMgr = new CacheManager(in); 

	}

	public static Cache getCache(String name) {
		return cMgr.getCache(name);
	}

	
 
}
