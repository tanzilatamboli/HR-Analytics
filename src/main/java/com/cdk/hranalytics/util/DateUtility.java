package com.cdk.hranalytics.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tambolit on 8/30/2016.
 */
public class DateUtility {
    public static Date stringToDate(String dateInString, String format)  {
        Date date = null;
        try {
            date = new SimpleDateFormat(format).parse(dateInString);
        } catch (ParseException e) {
            //throw new EcomException(e.toString());
        }
        return  date;
    }
}
