package com.Janaina.laboration.Resources;

import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

import static com.Janaina.laboration.Resources.Colors.RED;
import static com.Janaina.laboration.Resources.Colors.RESET;

public class Scanners {
    public static int scannerNumber() {
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) {
            return sc.nextInt();
        }
        System.out.println(RED + "Not a number." + RESET);
        return scannerNumber();
    }

    public static String scannerText() {
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) {
            System.out.println("Invalid input, try again!");
            return scannerText();
        } else {
            return sc.nextLine();
        }

    }

    public static String scannerYesOrNo() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine().toLowerCase(Locale.ROOT);
        if (Objects.equals(input, "yes") || Objects.equals(input, "no")) {
            return input;
        } else {
            System.out.println("Invalid input, only 'yes' or 'no'");
            return scannerYesOrNo();
        }

    }

    public static String pressEnter() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
