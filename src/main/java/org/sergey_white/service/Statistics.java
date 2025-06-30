package org.sergey_white.service;


import java.util.ArrayList;
import java.util.List;

public class Statistics {
    private int integerCount = 0;
    private int floatCount = 0;
    private int stringCount = 0;

    private final List<Double> floats = new ArrayList<>();
    private final List<Long> integers = new ArrayList<>();
    private final List<String> strings = new ArrayList<>();

    public void addInteger(long value) {
        integerCount++;
        integers.add(value);
    }

    public void addFloat(double value) {
        floatCount++;
        floats.add(value);
    }

    public void addString(String value) {
        stringCount++;
        strings.add(value);
    }

    public void printShort() {
        System.out.println("Integers: " + integerCount);
        System.out.println("Floats: " + floatCount);
        System.out.println("Strings: " + stringCount);
    }

    public void printFull() {
        System.out.println("Integers:");
        if (!integers.isEmpty()) {
            long min = Long.MAX_VALUE, max = Long.MIN_VALUE, sum = 0;
            for (long val : integers) {
                min = Math.min(min, val);
                max = Math.max(max, val);
                sum += val;
            }
            double avg = (double) sum / integers.size();
            System.out.println("Min: " + min);
            System.out.println("Max: " + max);
            System.out.println("Sum: " + sum);
            System.out.println("Avg: " + avg);
        } else {
            System.out.println("No data");
        }

        System.out.println("Floats:");
        if (!floats.isEmpty()) {
            double min = Double.MAX_VALUE, max = Double.MIN_VALUE, sum = 0;
            for (double val : floats) {
                min = Math.min(min, val);
                max = Math.max(max, val);
                sum += val;
            }
            double avg = sum / floats.size();
            System.out.println("Min: " + min);
            System.out.println("Max: " + max);
            System.out.println("Sum: " + sum);
            System.out.println("Avg: " + avg);
        } else {
            System.out.println("No data");
        }

        System.out.println("Strings:");
        if (!strings.isEmpty()) {
            int minLen = Integer.MAX_VALUE, maxLen = 0;
            for (String s : strings) {
                int len = s.length();
                minLen = Math.min(minLen, len);
                maxLen = Math.max(maxLen, len);
            }
            System.out.println("Total: " + stringCount);
            System.out.println("Min length: " + minLen);
            System.out.println("Max length: " + maxLen);
        } else {
            System.out.println("No data");
        }
    }
}
