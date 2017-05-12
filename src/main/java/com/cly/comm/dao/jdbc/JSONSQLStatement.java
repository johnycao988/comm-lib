package com.cly.comm.dao.jdbc;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONSQLStatement {

	private JSONArray jsa;

	private JSONObject jsqls;

	private String ds;

	public JSONSQLStatement(String dataSource) {

		this.ds = dataSource;

		clean();

	}

	public void clean() {

		jsa = new JSONArray();

		jsqls = new JSONObject();

		jsqls.put(JSONSQL.DATA_SOURCE, ds);
	}

	public void addJSONSQL(JSONSQL jsql) {

		jsa.add(jsql.getJSONSQL());

	}

	public JSONObject getJSONSQLs() {

		jsqls.remove(JSONSQL.SQL_LIST_HEADER);

		jsqls.put(JSONSQL.SQL_LIST_HEADER, jsa);

		return jsqls;

	}

}
