package com.tcodes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        ArrayList<String> input = new ArrayList<>();
        int numberOfLines = Integer.parseInt(getLines(1).get(0));
        input = getLines(numberOfLines);
        for (String line : input) {
            LocalDate currDate = LocalDate.parse(line.split(" ")[1], format);
            LocalDate signinDate = LocalDate.parse(line.split(" ")[0], format);
            ArrayList<LocalDate> dates = getDateRange(signinDate, currDate);
            if(dates != null){
                printDateRange(dates.get(0), dates.get(1), format);
            }
            else {
                System.out.println("No range");
            }
        }
    }

    private static ArrayList<String> getLines(int number)
    {
        ArrayList<String> result = new ArrayList<>();

        Scanner s = new Scanner(System.in);
        while (number > 0){
            number--;
            result.add((s.nextLine()));
        }

        return result;
    }

    private static ArrayList<LocalDate> getDateRange(LocalDate signinDate, LocalDate currDate)
    {
        ArrayList<LocalDate> result = new ArrayList<>();
        LocalDate firstDate = signinDate.minusDays(30);
        LocalDate lastDate = signinDate.plusDays(30);
        if (firstDate.compareTo(currDate) < 0) {
            while (firstDate.plusYears(1).compareTo(currDate) < 0) {
                signinDate = signinDate.plusYears(1);
                firstDate = signinDate.minusDays(30);
                lastDate = signinDate.plusDays(30);
            }
            if (firstDate.compareTo(currDate) < 0 && lastDate.compareTo(currDate) > 0) {
                lastDate = currDate;
            }
            result.add(firstDate);
            result.add(lastDate);
        } else {
            return null;
        }

        return result;
    }

    private static void printDateRange(LocalDate firstDate, LocalDate lastDate, DateTimeFormatter format)
    {
        System.out.print(firstDate.format(format));
        System.out.print(" ");
        System.out.println(lastDate.format(format));
    }
}
