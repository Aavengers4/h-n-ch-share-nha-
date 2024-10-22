/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Scanner;

/**
 *
 * @author Lenovo
 */
public class Menu {

    public static int getChoice(String[] options) {
        
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i] + ".");
        }
        System.out.print("Choose 1 of " + options.length + ": ");
        Scanner sc = new Scanner(System.in);
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException nfe) {
            return -1;
        }
    }
}
