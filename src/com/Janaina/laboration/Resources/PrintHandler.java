package com.Janaina.laboration.Resources;

import com.Janaina.laboration.Game.Variables.Hero.Player;

import static com.Janaina.laboration.Resources.Colors.*;

public class PrintHandler {

    public static void println(String text){
        System.out.println(text);
    }

    public static void printPurple(String text){
        System.out.println(PURPLE + text + RESET);
    }
    public static void printCyan(String text){
        System.out.println(CYAN + text + RESET);
    }
    public static void printYellow(String text){
        System.out.println(YELLOW + text + RESET);
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

    public static void sirenSpeaking(String text){

        System.out.println(BLUE_LIGHT + BOLD + "Siren:" + RESET);

        for (int i = 0; i < text.length(); i++) {
            System.out.print(BLUE_LIGHT + text.charAt(i) + RESET);
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

    public static void medusaSpeaking(String text){
        System.out.println(GREEN_DARK + BOLD + UNDERLINED +"Medusa:" + RESET);
        for (int i = 0; i < text.length(); i++) {
            System.out.print(GREEN_DARK + text.charAt(i) + RESET);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("\n");
    }

}
