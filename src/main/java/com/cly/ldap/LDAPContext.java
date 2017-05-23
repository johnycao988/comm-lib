package com.cly.ldap;

import java.util.Enumeration;
import java.util.Properties;

import javax.naming.Context;

public class LDAPContext {

	private final Properties env = new Properties();

	public LDAPContext() {
		setFactory("com.sun.jndi.ldap.LdapCtxFactory").setSecurityAuthentication("simple");
	}
	
	public LDAPContext(Properties p) {

		@SuppressWarnings("unchecked")
		Enumeration<String> e= (Enumeration<String>)p.propertyNames();
		
		while(e.hasMoreElements()){
			
			String pn=e.nextElement();
			this.env.setProperty(pn,p.getProperty(pn));
			
		} 

	}

	public LDAPContext setFactory(String ctxFactory) {
		env.put(Context.INITIAL_CONTEXT_FACTORY, ctxFactory);
		return this;
	}

	public LDAPContext setSecurityAuthentication(String ctrxSecurityAuth) {
		env.put(Context.SECURITY_AUTHENTICATION, ctrxSecurityAuth);
		return this;
	}

	public LDAPContext setUser(String user) {
		env.put(Context.SECURITY_PRINCIPAL, user);
		return this;
	}

	public LDAPContext setPassword(String password) {
		env.put(Context.SECURITY_CREDENTIALS, password);
		return this;
	}

	public LDAPContext setServerUrl(String url) {
		env.put(Context.PROVIDER_URL, url);
		return this;
	}

	public LDAPContext setProperty(String name, String value) {
		env.put(name, value);
		return this;
	}

	public Properties getProperties() {
		return this.env;
	}

	public static String getAttributeMapping(Properties p, String name) {
		String key = "ldap.attribute.mapping.[" + name + "]";
		return p.getProperty(key);
	}
	
 
}
