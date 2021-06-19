package com.bay1.assessment.util;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateUtil {
  public static LocalDate dateFromString(String date) {
    var dateTimeFormatter = DateTimeFormatter.ofPattern("MMyy").withZone(ZoneId.of("UTC"));
    return YearMonth.parse(date, dateTimeFormatter).atDay(1);
  }
}
