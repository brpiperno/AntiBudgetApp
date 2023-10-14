package com.example.antibudgetv1;

public class Utils {
    public static void checkNull(Object o) {
        if (o.equals(null)) {
            throw new IllegalArgumentException("Provided object is null");
        }
    }
}
