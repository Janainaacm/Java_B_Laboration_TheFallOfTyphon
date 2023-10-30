package com.Janaina.laboration.Resources;

import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

import static com.Janaina.laboration.Resources.Colors.*;

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
        System.out.println("\u001B[37m" + "Press enter to continue." + RESET);

        String readString = sc.nextLine();
        while(readString!=null) {

            if (readString.isEmpty()) {
                return readString;
            } else {
                break;
            }
        }
        return pressEnter();
    }

    public static String pressEnterToAttack() {
        Scanner sc = new Scanner(System.in);
        System.out.println(PURPLE_BOLD_BRIGHT + "Press enter to attack!" + RESET);

        String readString = sc.nextLine();
        while(readString!=null) {

            if (readString.isEmpty()) {
                return readString;
            } else {
                break;
            }
        }
        return pressEnterToAttack();
    }

}
