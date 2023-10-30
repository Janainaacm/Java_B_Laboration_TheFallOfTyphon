package com.Janaina.laboration.Resources;

import static com.Janaina.laboration.Resources.Colors.*;

public class TextDelay {

    public static void sleepThread(String text) {
        for (int i = 0; i < text.length(); i++) {
            System.out.print(text.charAt(i));
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void suspensefulDots(String dot){
        int numberOfDots = 3;
        try {
            for (int i = 0; i < numberOfDots; i++) {
                Thread.sleep(1000);
                System.out.print(dot);
            }
        } catch (InterruptedException ignored) {

        }
        System.out.println("\n");
    }

    public static void yellowSuspensefulDots(){
        int numberOfDots = 3;
        try {
            for (int i = 0; i < numberOfDots; i++) {
                Thread.sleep(1000);
                System.out.print(YELLOW + "." + RESET);
            }
        } catch (InterruptedException ignored) {

        }
    }



}
