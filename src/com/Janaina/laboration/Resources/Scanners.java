package com.Janaina.laboration.Resources;

import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

import static com.Janaina.laboration.Resources.Colors.*;

public class Scanners {

    static Scanner sc = new Scanner(System.in);

    public int chooseFromGameMenu(){
        return scannerNumber();
    }
    public int chooseFromMainMenu(){
        return scannerNumber();
    }
    public int chooseFromLevelMenu(){
        return scannerNumber();
    }
    public int chooseFromShopMenu(){
        return scannerNumber();
    }
    public int chooseFromWeapons(){
        return scannerNumber();
    }
    public int chooseFromPotions(){
        return scannerNumber();
    }
    public int chooseFromWeaponsInventory(){
        return scannerNumber();
    }
    public int chooseFromPotionsInventory(){
        return scannerNumber();
    }

    public int scannerNumber() {
        if (sc.hasNextInt()) {
            return sc.nextInt();
        }
        System.out.println(RED + "Not a number." + RESET);
        return scannerNumber();
    }

    public String scannerText() {
        if (sc.hasNextInt()) {
            System.out.println("Invalid input, try again!");
            return scannerText();
        } else {
            return sc.nextLine();
        }

    }

    public String scannerYesOrNo() {
        String input = sc.nextLine().toLowerCase(Locale.ROOT);
        if (Objects.equals(input, "yes") || Objects.equals(input, "no")) {
            return input;
        } else {
            System.out.println("Invalid input, only 'yes' or 'no'");
            return scannerYesOrNo();
        }

    }

    public String pressEnter() {
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

    public String pressEnterToAttack() {
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

    public int scannerOnlyOneNumber() {
        int number = scannerNumber();

        if (number >= 1 && number <= 9) {
            return number;
        }
        System.out.println(RED + "Enter one number" + RESET);
        return scannerOnlyOneNumber();
    }

}
