package javajournal.chronos;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class ChronoUnitExamples {

    /**
     * Bonus!
     * @param input a java.util.Date
     * @return java.time.LocalDate
     */
    static LocalDate toLocalDate(Date input) {
        Instant instant = input.toInstant();
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
        LocalDate date = zdt.toLocalDate();
        return date;
    }

    public static void main(String[] args) {
        //How to convert java.util.Date to java.time.LocalDate
        Date input = new Date();
        Instant instant = input.toInstant();
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
        LocalDate date = zdt.toLocalDate();
        
        //Two dates, difference in days, months and years using java.time.temporal.ChronoUnit and java.time.LocalDate
        LocalDate startDate = LocalDate.of(1972, Month.MARCH, 25);//Easy to use!!
        LocalDate endDate = LocalDate.of(2017, 3, 25);//Easy to use! 
        System.out.println("Two dates, difference in days, months and years");
        System.out.println("years: " + ChronoUnit.YEARS.between(startDate, endDate));
        System.out.println("months: " + ChronoUnit.MONTHS.between(startDate, endDate));
        System.out.println("days: " + ChronoUnit.DAYS.between(startDate, endDate));
    }
}
