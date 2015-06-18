package org.roommanager.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateGenerator {
	private static final String dateformat = "yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'";
    private static final int hourDifference = 4;
    private static final DateFormat dateFormat = new SimpleDateFormat(dateformat);
    
    public static String getStartDate(){
          Calendar calendar = new GregorianCalendar();
          calendar.add(Calendar.HOUR, hourDifference);
          return dateFormat.format(calendar.getTime());
    }
    
    public static String getEndDate(int duration){
          Calendar calendar = new GregorianCalendar();
          calendar.add(Calendar.HOUR, hourDifference);
          calendar.add(Calendar.MINUTE,duration);
          return dateFormat.format(calendar.getTime());
    }

}
