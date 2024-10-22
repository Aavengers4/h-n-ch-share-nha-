/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.time.LocalDate;
import java.util.Scanner;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Lenovo
 */
public class Inputter {

    public static Scanner sc = new Scanner(System.in);
    public static int inputIntLimit(String msg, int min, int max) { 
        int data;
        while (true) {
            try {
                System.out.print(msg);
                data = Integer.parseInt(sc.nextLine());
                if (data >= min && data <= max) {
                    break;
                } else {
                    System.out.println("Input value should be in between " + min + " and " + max + ", try again.");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid input, try again.");
            }
        }
        return data;
    }

    public static int inputInt(String msg) { 
        int data;
        while (true) {
            try {
                System.out.print(msg);
                data = Integer.parseInt(sc.nextLine());
 
                break;
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid input, try again.");
            }
        }
        return data;
    }

    public static int inputIntOrBlank(String msg, int min, int max) {
        int data;
        String temp;
        while (true) {
            try {
                System.out.print(msg);
                temp = sc.nextLine().trim();
                if (temp.isEmpty()) {
                    return -1;
                }
                data = Integer.parseInt(temp);
                if (data < min || data > max) {
                    System.out.println("Input must be in between " + min + " and " + max + ", try again.");
                } else {
                    break;
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid input, try again.");
            }
        }
        return data;
    }

    public static String inputStr(String msg) {
        System.out.print(msg);
        String data = sc.nextLine().trim();
        return data;
    }

    public static String inputNonBlankStr(String msg) {
        String data;
        do {
            System.out.print(msg);
            data = sc.nextLine().trim(); 
            if (data.length() == 0) {
                System.out.println("Input must not be empty.");
            }
        } while (data.length() == 0);
        return data;
    }

    public static boolean confirmation(String text) { 
        String data;
        do {
            System.out.print(text + " (Y/N): "); //abc x

            data = sc.nextLine().trim().toUpperCase();
            if (!"Y".equals(data) && !"N".equals(data)) {
                System.out.println("Invalid syntax, try again.");
            }
        } while (!"Y".equals(data) && !"N".equals(data));
        return "Y".equals(data);
    }

    public static LocalDate inputDate(String msg) {
        while (true) {
            try {
                System.out.print(msg + "(yyyy-MM-dd): ");
                String data = sc.nextLine().trim();
                if (data.trim().isEmpty()) {
                    return null;
                }
                LocalDate result = LocalDate.parse(data); 

                    return result;

            } catch (java.time.DateTimeException dtpe) {
                System.out.println("Invalid format, try again.");
            }
        }
    }

}
