package com.atguigu.time;
import	java.util.Date;
import	java.time.format.DateTimeFormatter;
import java.time.DayOfWeek;
import	java.time.temporal.TemporalAdjusters;
import	java.time.Period;
import java.time.Duration;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import	java.time.ZoneOffset;
import java.time.Instant;
import	java.time.LocalDateTime;

import org.junit.Test;

public class TestLocalDateTime {
    //1.localDate localTime localDateTime
    @Test
    public void test(){
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        LocalDateTime localDateTime = LocalDateTime.of(2015, 10, 19, 13, 22, 33);
        System.out.println(localDateTime);

        LocalDateTime localDateTime1 = localDateTime.plusYears(2);
        System.out.println(localDateTime1);

        LocalDateTime localDateTime2 = localDateTime.minusMonths(2);
        System.out.println(localDateTime2 );

        System.out.println(localDateTime.getYear());
        System.out.println(localDateTime.getMonthValue());
        System.out.println(localDateTime.getDayOfMonth());
        System.out.println(localDateTime.getHour());
        System.out.println(localDateTime.getMinute());
        System.out.println(localDateTime.getSecond());
    }
    
    //时间撮
    @Test
    public void test2(){
        Instant ins1 = Instant.now();
        System.out.println(ins1);
        OffsetDateTime odt = ins1.atOffset(ZoneOffset.ofHours(8));
        System.out.println(odt);
        System.out.println(ins1.toEpochMilli());
        Instant instant = Instant.ofEpochSecond(60);
        System.out.println(instant);
    }
    //计算两个时间的时间间隔
    @Test
    public void test3(){
        Instant start = Instant.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Instant end = Instant.now();
        Duration between = Duration.between(start, end);
        System.out.println(between.getSeconds());
    }

    @Test
    public void test4(){
        LocalDate localDate = LocalDate.of(2015, 1, 1);
        LocalDate start = LocalDate.now();

        Period period = Period.between(localDate, start);
        System.out.println(period);
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
    }

    //时间校正器TemporalAdjusters
    @Test
    public void test5(){
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        LocalDateTime localDateTime1 = localDateTime.withDayOfMonth(10);
        System.out.println(localDateTime1);
        LocalDateTime dateTime = localDateTime.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(dateTime);
        //自定义：下一个工作日
        LocalDateTime with = localDateTime.with(l -> {
            LocalDateTime localDate = (LocalDateTime) l;
            DayOfWeek dayOfWeek = localDate.getDayOfWeek();
            if (dayOfWeek.equals(DayOfWeek.FRIDAY)) {
                return localDate.plusDays(3);
            } else if (localDate.equals(DayOfWeek.SATURDAY)) {
                return localDate.plusDays(2);
            } else {
                return localDate.plusDays(1);
            }
        });
        System.out.println(with);
    }

    //格式划日期
    @Test
    public void test6(){
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        LocalDateTime localDateTime = LocalDateTime.now();
        String format = localDateTime.format(formatter);
        System.out.println(format);

        System.out.println("------------");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String format1 = dateTimeFormatter.format(localDateTime);
        System.out.println(format1);
        LocalDateTime parse = localDateTime.parse(format1, dateTimeFormatter);
        System.out.println(parse);
    }


}
