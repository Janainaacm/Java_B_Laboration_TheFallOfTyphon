package com.Janaina.laboration.Game.GameMenu.Levels;

import com.Janaina.laboration.Game.Variables.Hero.Inventory;
import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Game.Variables.Monsters.Fury;
import com.Janaina.laboration.Game.Variables.Monsters.Siren;
import com.Janaina.laboration.Game.Variables.Monsters.Typhon;
import com.Janaina.laboration.Resources.Scanners;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.PrintHandler.printRed;
import static com.Janaina.laboration.Resources.TextDelay.chillForASecond;
import static com.Janaina.laboration.Resources.TextDelay.sleepThread;

public class LevelSix {

    public void playLevelSix(Player player, Inventory inventory, Scanners sc){
        int originalAgility = player.getAgility();
        int originalStrength = player.getStrength();
        Typhon typhon = new Typhon();
        Fury fury = new Fury();
        Siren siren = new Siren();

        sleepThread(YELLOW + """
                As the hero crosses the threshold into the underworld, an oppressive darkness envelopes him, seeping into the very core of his \s
                being. The air becomes heavy, laden with an unbearable sense of despair that tugs at his every step. The once vivid colors of the \s
                mortal realm faded into muted hues, with the echoes of agonizing screams leading the path.\s
                                
                After what feels like an eternity, the hero emerges at the edge of a vast, stagnant lake. Its murky waters reflect the tormented \s
                spirits trapped within the underworld. The lake's stillness seems to amplify the hero's own internal turmoil, reflecting his \s
                struggle against the pervasive hopelessness. On the edge of the water lays a small rowing boat, visually on the brink of ruin. \s
                In the distance, a lone figure stands – the boatman, a spectral silhouette against the dimly lit shores.\s
                """ + RESET);

        System.out.println(BLACK + BOLD + UNDERLINED +"Boatman:" + RESET);
        sleepThread(BLACK + "I can offer you safe passage across the lake... For a price of course." + RESET);
        sleepThread(YELLOW_LIGHT + "200 " + BLACK + "gold coins." + RESET);
        chillForASecond(1500);
        sleepThread(GRAY + ITALIC + "What do you want to do?" + RESET);
        System.out.println(YELLOW_LIGHT + ITALIC + "Gold: " + player.getGold() + RESET +
                YELLOW + "1. Safely cross the lake with the boatman\n2. Take your chances with the rowing boat" + RESET);

        boolean isChoosingBoatman = true;
        while (isChoosingBoatman) {
            switch (sc.scannerNumber()) {
                case 1 -> {
                    if (player.getGold() >= 200){
                        System.out.println("cross");
                        isChoosingBoatman = false;
                    } else {
                        sleepThread(RED + ITALIC + "You do not have the capacity for that big man" + RESET);
                        System.out.println(GRAY + "Try again" + RESET);
                    }

                }

                case 2 -> {
                    System.out.println("rowing boat");
                    isChoosingBoatman = false;
                }

                default -> printRed("Invalid input");
            }
        }

        /*
        When the player reaches the underworld he comes to a lake, and face to face with the man with the boat,
        he says he can take him past the river in exchange for 200 gold, if yes: skip scene, else. cross river and fight
        sirens.

        something more

        He arrives just in time to see his sister chained to the altar, the beast that is his target grinning next to her
        he starts fighting Typhon but when his health reaches 150, he flees and 2 furies attacks

        Quee entering his castle, choose the correct path to find him:
        Fight him again (full health) but when he reaches 100 health he runs again.

        You think he's given up, you take Althea and you start making your way out of the castle, but just as
        you're about to leave he attacks you.

        You fight till the death, and you finally manage to defeat him. Rescuing your sister and leaving hell

         */

        Fury fury1 = new Fury();

        while (true){
            player.act(fury, inventory, sc);
            if (fury.isAlive()) {
                break;
            }
            fury.revive();

            player.act(fury, inventory, sc);
            if (fury.isAlive()) {
                break;
            }
            fury.revive();

            //Divide player agility by 2

            player.act(fury, inventory, sc);
            if (fury.isAlive()) {
                break;
            }
            fury.revive();





        }
    }


    private void goWithBoatMan(){

    }

    private void rowingBoat(){

    }

}
