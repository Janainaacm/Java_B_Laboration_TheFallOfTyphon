package com.Janaina.laboration.Game.GameMenu.Levels;

import com.Janaina.laboration.Game.Variables.Hero.Inventory;
import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Game.Variables.Monsters.Fury;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.PrintHandler.furySpeaking;
import static com.Janaina.laboration.Resources.PrintHandler.playerSpeaking;
import static com.Janaina.laboration.Resources.Scanners.pressEnter;
import static com.Janaina.laboration.Resources.TextDelay.*;

public class LevelOne {

    public void playLevelOne(Player player, Inventory inventory) {
        Fury fury = new Fury();

        /*
        sleepThread(YELLOW + "In the heart of ancient Greece, our hero " + player.getName() + " stands ready to embark on a perilous journey. His beloved sister Althea, has been captured by the monstrous Typhon. \n" +
                "With unwavering determination, he sets forth to rescue her from the clutches of darkness. But as night's chilling embrace tightens, \nthe faint sound of heavy breathing slowly emerges from the shadows.\n" + RESET);

        chillForASecond(1000);
        playerSpeaking("Who's there!", player);
        suspensefulDots(GRAY + "." + RESET);
        sleepThread(PURPLE_PASTEL + "Answer me!\n" + RESET);
        chillForASecond(2000);
        furySpeaking("You fool. You dare challenge the mighty Typhon?");
        chillForASecond(500);
        playerSpeaking("He does not frighten me. Tell me where to find him!", player);
        chillForASecond(1000);
        furySpeaking("Not to worry mortal, after we take your life your soul will join your precious sister");

         */


        while (true) {

            player.act(fury, inventory);
            if (fury.isAlive()) {
                break;
            }
            fury.revive();

            playerSpeaking("Is that all you've got?", player);
            chillForASecond(500);

            player.act(fury, inventory);
            if (fury.isAlive()) {
                break;
            }
            fury.revive();

            playerSpeaking("Where is Typhon holding my sister?! Answer me and i will consider sparing your life!", player);
            furySpeaking("You will never find her");
            chillForASecond(1000);

            player.act(fury, inventory);
            if (fury.isAlive()) {
                break;
            }


            sleepThread(YELLOW + "Surrounded by the corpses of the dead furies, " + player.getName() + " approaches the only living beast. \nClinging on to life, pleading for mercy.\n");
            chillForASecond(500);
            playerSpeaking("Where is he? Where did he take my Althea!\nAnswer me and i will heal you!", player);
            chillForASecond(1000);
            furySpeaking("m- map, you will not find h- her... without t- the.. map");
            chillForASecond(500);
            playerSpeaking("Map? What map?", player);
            chillForASecond(1000);
            furySpeaking("The m- map of t- the u- underworld");
            chillForASecond(500);
            playerSpeaking("Where is this map?", player);
            chillForASecond(1000);
            suspensefulDots(RED + "." + RESET);
            playerSpeaking("Answer me!", player);
            furySpeaking("It is located in the Scaled Garden of Stone...");
            suspensefulDots(RED + "." + RESET);
            furySpeaking("Now hea-");
            suspensefulDots(RED + "." + RESET);
            chillForASecond(1000);
            playerSpeaking("The Scaled Garden of Stone...", player);
            sleepThread(YELLOW + "With the mysterious location across the Sea of Serene Whispers, " + player.getName() + " feared what was to come, \nfor he knew that he was about to enter the enthralling realm of the aquatic enchantresses\nfor they guard the path to the hidden map that holds the key to unlocking the next chapter of his epic journey\n");

            chillForASecond(500);
            suspensefulDots(GRAY + "." + RESET);
            chillForASecond(500);
            sleepThread(GRAY + "You have completed level one." + RESET);
            pressEnter();


            player.unlockNewLevel();
            break;

        }
    }
}
