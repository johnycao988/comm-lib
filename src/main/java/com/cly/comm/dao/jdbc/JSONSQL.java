package com.cly.comm.dao.jdbc;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONSQL {

	public final static String SQL_HEADER = "SQL";
	
	public final static String SQL_LIST_HEADER = "SQL_LIST";

	public final static String VALUES_HEADER = "VALUES";

	public final static String MAX_ROWS = "MAX_ROWS";
	
	public final static String DATA_SOURCE = "DATA_SOURCE";

	public final static String BATCH_VALUES_HEADER = "BATCH_VALUES";
	
	public final static String RESULT_NUMS="RESULT_NUMS";
	
	public final static String RESULT_DATA="RESULT_DATA";
	
	public final static String FIELD_NAMES="FIELD_NAMES";

	private JSONObject jsql;

	private JSONArray japara;

	public JSONSQL(String sql) {

		init(sql, -1);

	}

	public JSONSQL(String sql, int maxRows) {

		init(sql, maxRows);

	}

	private void init(String sql, int maxRows) {

		jsql = new JSONObject();

		japara = new JSONArray();

		jsql.put(SQL_HEADER, sql);

		jsql.put(MAX_ROWS, maxRows);

	}

	public JSONSQL addSQLValues(Object obj) {

		japara.add(obj);
		
		return this;

	}

	public void resetSQLValues(int index, Object obj) {

		japara.set(index, obj);

	}

	public JSONObject getJSONSQL() {

		jsql.remove(VALUES_HEADER);

		jsql.put(VALUES_HEADER, japara);

		return jsql;
	}

	public JSONArray getSQLParaVaules() {

		return japara;
	}
}
