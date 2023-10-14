package com.example.antibudgetv1;

public class Utils {
    public static void checkNull(Object o) {
        if (null == o) {
            throw new IllegalArgumentException("Provided object is null");
        }
    }
}
