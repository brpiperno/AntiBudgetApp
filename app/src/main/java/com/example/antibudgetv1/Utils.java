package com.example.antibudgetv1;

import java.time.chrono.ChronoLocalDate;

public class Utils {
    public static void checkNull(Object o) {
        if (null == o) {
            throw new IllegalArgumentException("Provided object is null");
        }
    }

    public static void checkDateRange(ChronoLocalDate start, ChronoLocalDate end){
        Utils.checkNull(start);
        Utils.checkNull(end);
        if (start.isAfter(end)) {
            throw new IllegalArgumentException(
                    String.format("provided start date %s is after end date %s", start, end));
        }
    }
}
