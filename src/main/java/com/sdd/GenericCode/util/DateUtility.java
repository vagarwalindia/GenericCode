package com.sdd.GenericCode.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtility {

    public static String getLocalTimeDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY-MM-DD hh:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
