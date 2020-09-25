package com.example.pswman.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {

	public static String formatedData(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");		
		return sdf.format(date); 
	}
}
