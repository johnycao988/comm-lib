package com.cly.ldap;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.junit.Test;

public class LDAPSearchTest {

	@Test
	public void testSearch() throws NamingException{
		
		LDAPContext ctx=new LDAPContext();
		ctx.setServerUrl("ldap://10.39.101.226:10389").setUser("uid=admin,ou=system").setPassword("secret");
		LDAPSearch ldapSearch=new LDAPSearch(ctx);
		
		Attributes[] atts=ldapSearch.multiSearch("ou=users,dc=example,dc=com", "uid=johnny.cao");
		for(Attributes a:atts){
			System.out.println(a.get("uid").get());			
		}
		
		 
		 		
	}
}
