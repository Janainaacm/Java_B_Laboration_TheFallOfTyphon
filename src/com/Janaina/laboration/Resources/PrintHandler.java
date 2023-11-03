package com.Janaina.laboration.Resources;

import com.Janaina.laboration.Game.Variables.Hero.Player;

import static com.Janaina.laboration.Resources.Colors.*;

public class PrintHandler {

    public static void println(String text){
        System.out.println(text);
    }

    public static void printf(String text) {
        System.out.printf(text);
    }
    public static void printPurple(String text){
        System.out.println(PURPLE + text + RESET);
    }
    public static void printBlue(String text){
        System.out.println(BLUE + text + RESET);
    }
    public static void printCyan(String text){
        System.out.println(CYAN + text + RESET);
    }
    public static void printGreen(String text){
        System.out.println(GREEN + text + RESET);
    }
    public static void printYellow(String text){
        System.out.println(YELLOW + text + RESET);
    }
    public static void printBlack(String text){
        System.out.println(BLACK + text + RESET);
    }
    public static void printRed(String text){
        System.out.println(RED + text + RESET);
    }


    public static void PythiaSpeaking(String text){
        System.out.println(PURPLE_UNDERLINED + PURPLE_BOLD_BRIGHT +"Pythia:" + RESET);
        for (int i = 0; i < text.length(); i++) {
            System.out.print(PURPLE + text.charAt(i) + RESET);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("\n");
    }

    public static void playerSpeaking(String text, Player player){

        System.out.println(PURPLE_PASTEL + BOLD + player.getName() + ":" + RESET);

        for (int i = 0; i < text.length(); i++) {
            System.out.print(PURPLE_PASTEL + text.charAt(i) + RESET);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.print("\n");
    }

    public static void furySpeaking(String text){
        System.out.println(RED + BOLD + UNDERLINED +"Fury:" + RESET);
        for (int i = 0; i < text.length(); i++) {
            System.out.print(RED + text.charAt(i) + RESET);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("\n");
    }

}
