package com.cly.comm.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class FormatDate {

	private Date dt;

	public FormatDate() {

		dt = Calendar.getInstance().getTime();

	}

	public FormatDate(Date date) {
		this.dt = date;
	}

	public FormatDate(String date, String dateFormat) throws ParseException {
		
		DateFormat format = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
		 
		dt = format.parse(date);
		
	}
	
	public String format(String dateFormat){
		
	    SimpleDateFormat formatter; 
	    formatter = new SimpleDateFormat (dateFormat); 
	    String ctime = formatter.format(dt);  
	    return ctime; 

	}
	
	public Date getDate(){
		return dt;
	}

}
