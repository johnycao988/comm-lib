package com.cly.ldap;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;

import org.junit.Before;
import org.junit.Test;

public class LDAPSearchTest {
	
	LDAPContext ctx;
	LDAPSearch ldapSearch;
	@Before
	public void init() throws NamingException{
		
		LDAPContext ctx=new LDAPContext();
		ctx.setServerUrl("ldap://10.39.101.226:10389").setUser("uid=johnny.cao,ou=users,dc=example,dc=com").setPassword("ldap123");
		ldapSearch=new LDAPSearch(ctx);
	}
	
	
	public void testUsers() throws NamingException{
		
	 	Attributes[] atts=ldapSearch.multiSearch("ou=users,dc=example,dc=com", "uid=*",SearchControls.SUBTREE_SCOPE);
		
		for(Attributes a:atts){
			
		 	 System.out.println("user-id:"+a.get("uid").get().toString());
			 System.out.println("user-cn:"+a.get("cn").get().toString());
			 System.out.println("user-sn:"+a.get("sn").get().toString());
			 
		}
		 
	}
	
	@Test
	public void testUserGroups() throws NamingException{
		
	 	Attributes[] atts=ldapSearch.multiSearch("cn=admin,ou=userGroups,dc=example,dc=com", "cn=admin",SearchControls.SUBTREE_SCOPE);
		
		for(Attributes a:atts){
			
		  
			 System.out.println("group cn:"+a.get("cn").get().toString());
			 			 
			 Attribute at=a.get("member");
			 
			 for(int i=0;i<at.size();i++)			 
			 System.out.println(at.get(i).toString());
			 
		}
		 
	}
	 
}
