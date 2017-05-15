package com.cly.ldap;

import java.util.ArrayList;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

public class LDAPSearch {

	private final LdapContext ctx;

	public LDAPSearch(LDAPContext ldapCtx) throws NamingException {

		ctx = new InitialLdapContext(ldapCtx.getProperties(), null);
	}

	public Attributes search(String searchBase, String searchFilter) throws NamingException {

		Attributes[] atts = search(searchBase, searchFilter, true);
		if (atts != null && atts.length>0)
			return atts[0];
		else
			return null;

	}

	public Attributes[] multiSearch(String searchBase, String searchFilter) throws NamingException {

		return search(searchBase, searchFilter, false);

	}

	private Attributes[] search(String searchBase, String searchFilter, boolean isSingle) throws NamingException {

		SearchControls searchCtls = new SearchControls();
		searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		searchCtls.setReturningAttributes(null);
		NamingEnumeration<SearchResult> answer = ctx.search(searchBase, searchFilter, searchCtls);

		ArrayList<Attributes> al = new ArrayList<Attributes>();

		while (answer.hasMoreElements()) {

			SearchResult sr = (SearchResult) answer.next();

			al.add(sr.getAttributes());

			if (isSingle) {
				break;
			}

		}

		return al.toArray(new Attributes[0]);
	}

}
