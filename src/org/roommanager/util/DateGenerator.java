package org.roommanager.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateGenerator {
	private static final String DATE_FORMAT_AS_STRING= "yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'";
   private static final int HOUR_DIFFERENCE = 4;
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT_AS_STRING);
    
    public static String getStartDate(){
       Calendar calendar = new GregorianCalendar();
       calendar.add(Calendar.HOUR, HOUR_DIFFERENCE);
       return DATE_FORMAT.format(calendar.getTime());
    }
    
    public static String getEndDate(int durationInMinutes){
       Calendar calendar = new GregorianCalendar();calendar.add(Calendar.HOUR, HOUR_DIFFERENCE);
       calendar.add(Calendar.MINUTE,durationInMinutes);
       return DATE_FORMAT.format(calendar.getTime());
    }

    public static int getHourOfDay(String completeDate){
        Date date = null;
        String datesAsString = getStartDate();
        try {
            date = DATE_FORMAT.parse(datesAsString);
        } catch (ParseException ex) {
        	TestLogger.error(ex.getMessage());
        }
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        
        return calendar.get(Calendar.HOUR_OF_DAY); 
     }
}
