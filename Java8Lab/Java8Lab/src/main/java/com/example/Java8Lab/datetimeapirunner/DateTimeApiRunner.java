package com.example.Java8Lab.datetimeapirunner;

import com.example.Java8Lab.BaseRunner;
import org.springframework.cglib.core.Local;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.*;
import java.time.format.DateTimeFormatter;

@Component
@Order(11)
public class DateTimeApiRunner extends BaseRunner {
    @Override
    protected void execute() {
        System.out.println("=== 1. LocalDate & LocalDateTime ===");
        localDateDemo();

        System.out.println("\n=== 2. ZonedDateTime ===");
        zonedDateTimeDemo();

        System.out.println("\n=== 3. Period Vs Duration ===");
        periodVsDuration();

        System.out.println("\n=== 4. Formatting & Parsing");
        formattingDemo();
    }
    private void localDateDemo(){
        LocalDate today = LocalDate.now();
        LocalDate birthDay = LocalDate.of(1995,5,10);
        System.out.println("Today "+today);
        System.out.println("Birthday : "+birthDay);

        System.out.println("Is birthday before today? "+birthDay.isBefore(today));

        LocalDate nextWeek = today.plusWeeks(1);
        System.out.println("Next week "+nextWeek);
    }

    private void zonedDateTimeDemo(){
        ZonedDateTime indiaTime = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));

        ZonedDateTime usTime = ZonedDateTime.now(ZoneId.of("America/New_York"));
        System.out.println("India time : "+indiaTime);
        System.out.println("USA time : "+usTime);
    }
    private void periodVsDuration(){
        LocalDate start = LocalDate.of(2020,1,1);
        LocalDate end = LocalDate.of(2025,1,1);
        Period period = Period.between(start,end);
        System.out.println("Period : "+period.getYears()+" years");

        LocalTime t1 = LocalTime.of(10,0);
        LocalTime t2 = LocalTime.of(12,30);

        Duration duration = Duration.between(t1,t2);
        System.out.println("Duration (minutes): "+duration.toMinutes());
    }
    private void formattingDemo(){
        LocalDate today = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formatted = today.format(formatter);
        System.out.println("Formatted: "+formatted);

        LocalDate parsed = LocalDate.parse(formatted, formatter);
        System.out.println("Parsed : "+parsed);
    }
}
/* Java 8 introduced java.time package to provide immutable, thread-safe date and time handling,
replacing the flawed legacy Date and Calendar APIs. Period handles date-based differences, and
Duration handles time-based differences.
 */