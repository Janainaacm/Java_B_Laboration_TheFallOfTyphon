package com.Janaina.laboration.Game.GameMenu.Levels;

import com.Janaina.laboration.DBConnection;
import com.Janaina.laboration.Game.Variables.Hero.Inventory;
import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Game.Variables.Monsters.Fury;
import com.Janaina.laboration.Game.Variables.Monsters.Siren;
import com.Janaina.laboration.Game.Variables.Monsters.Typhon;
import com.Janaina.laboration.Resources.Scanners;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.PrintHandler.*;
import static com.Janaina.laboration.Resources.TextDelay.*;

public class LevelSix {

    public void playLevelSix(Player player, Inventory inventory, Scanners sc, DBConnection db) {
        int originalAgility = player.getAgility();
        int originalStrength = player.getStrength();
        int weaponOriginalStrength = db.getEquippedWeaponStrength(player);


        Typhon typhon = new Typhon();
        Fury fury = new Fury();
        player.setGold(200);

        sleepThread(YELLOW + """
                As the hero crosses the threshold into the underworld, an oppressive darkness envelopes him, seeping into the very core of his \s
                being. The air becomes heavy, laden with an unbearable sense of despair that tugs at his every step. The once vivid colors of the \s
                mortal realm faded into muted hues, with the echoes of agonizing screams leading the path.\s
                                
                After what feels like an eternity, the hero emerges at the edge of a vast, stagnant lake. Its murky waters reflect the tormented \s
                spirits trapped within the underworld. The lake's stillness seems to amplify the hero's own internal turmoil, reflecting his \s
                struggle against the pervasive hopelessness. On the edge of the water lays a small rowing boat, visually on the brink of ruin. \s
                In the distance, a lone figure stands – the boatman, a spectral silhouette against the dimly lit shores.\s
                """ + RESET);


        System.out.println(BLACK + BOLD + UNDERLINED + "Boatman:" + RESET);
        sleepThread(BLACK + "I can offer you safe passage across the lake... For a price of course." + RESET);
        sleepThread(YELLOW_LIGHT + "200 " + BLACK + "gold coins." + RESET);
        chillForASecond(1000);
        sleepThread(GRAY + ITALIC + "What do you want to do?" + RESET);
        System.out.println(YELLOW_LIGHT + ITALIC + "Gold: " + player.getGold() + RESET +
                YELLOW + "\n1. Safely cross the lake with the boatman\n2. Take your chances with the rowing boat" + RESET);

        boolean isChoosing = true;
        boolean didGoWithBoatMan = false;
        while (isChoosing) {
            switch (sc.scannerNumber()) {
                case 1 -> {
                    if (player.getGold() >= 200) {
                        goWithBoatMan(sc);
                        didGoWithBoatMan = true;
                        player.setGold(player.getGold() - 200);
                        isChoosing = false;
                    } else {
                        sleepThread(RED + ITALIC + "You do not have the capacity for that big man" + RESET);
                        System.out.println(GRAY + "Try again" + RESET);
                    }
                }
                case 2 -> {
                    rowingBoat(player, inventory, sc, db);
                    isChoosing = false;
                }

                default -> printRed("Invalid input");
            }
        }

        sleepThread(YELLOW + """
                Upon reaching the shore, the hero steps onto what appears to be a beach, only to discover it's not sand beneath his feet \s
                but a haunting landscape littered with skeletal remains, silent witnesses to the underworld's eternal suffering.\s
                """);

        castleGates(player, inventory, sc, db);

        player.actTyphon(typhon, inventory, sc, db);
        sleepThread(WHITE + ITALIC + "What is going on?" + RESET);
        System.out.println(ORANGE + "Typhon fled to the castle with Althea!" + RESET);
        chillForASecond(1000);
        System.out.println(YELLOW_LIGHT + "Press enter to follow inside castle!" + RESET);
        sc.pressEnterNoText();
        sleepThread(YELLOW + """
                Little does he know that the castle itself is a maze of the mind, a place where reality intertwines with illusion. \s
                As he pursues Typhon through the winding corridors, the very architecture of the castle seems to play tricks on his senses. \s
                Whispers of doubt and shadows of the past assail his mind, creating a psychological battleground where discerning reality \s
                from illusion becomes increasingly challenging.\s
                """);
        suspensefulDots(".");

        if (didGoWithBoatMan) {
            sleepThread(GRAY + ITALIC + "Did you pay attention to what the boatman said?" + RESET);
        }
        trappedInsideCastle(sc);

        sleepThread(YELLOW + """
                As the hero navigates the convoluted corridors of the castle, he manages to discern the elusive path leading to the heart of \s
                Typhon's lair. With every step, the illusionary obstacles fall away, revealing the true route to the confrontation he seeks. \s
                The hero bursts into a chamber adorned with ominous symbols, where Typhon stands with Althea chained to yet another altar.\s
                """);
        chillForASecond(500);
        playerSpeaking("Come face me then!", player);
        player.actTyphon(typhon, inventory, sc, db);

        sleepThread(YELLOW + "Typhon has once again fled the fight! The hero seizes the opportunity to rush to Althea's side and free her\nfrom the chains that bound her to the altar.\n");
        chillForASecond(500);
        System.out.println(PINK + BOLD + UNDERLINED + "Althea:" + RESET);
        sleepThread(PINK + player.getName() + "! My beloved brother! You came to my rescue!\n" + RESET);
        chillForASecond(500);
        playerSpeaking("I would never leave you with that beast. Come now, we must make haste\n", player);
        chillForASecond(1000);

        sleepThread(YELLOW + """
                With Althea now liberated, they make a hasty exit from the darkened castle, hoping to put the horrors behind them. However, \s
                as they step into the cool night air, a disconcerting sight awaits them. Typhon, far from defeated, stands with an air of \s
                sinister anticipation, flanked by his loyal minions. It becomes apparent that this confrontation is far from over.\s
                """);
        chillForASecond(1000);
        typhonSpeaking("Did you really think I would let you take my betrothed without a battle?\n");
        chillForASecond(500);
        playerSpeaking("You do not frighten me Typhon!\n", player);
        chillForASecond(500);
        typhonSpeaking("You will learn to fear my name, " + player.getName());
        chillForASecond(500);
        sleepThread(YELLOW + "\nAnd with that, the air filled with a magical mist\n");
        playerSpeaking("What is going on?", player);
        suspensefulDots(".");
        sleepThread(GRAY + ITALIC + "Your agility and strength have been reduced by half." + RESET);

        player.setAgility(player.getAgility() / 2);
        player.setStrength(player.getStrength() / 2);
        db.setEquippedWeaponStrength(player, (weaponOriginalStrength/2));

        chillForASecond(1000);
        furySpeaking("\nNot so tough now are we?");

        player.actTyphon(fury, inventory, sc, db);

        chillForASecond(1000);
        furySpeaking("This is not over!");
        chillForASecond(1000);

        player.actTyphon(fury, inventory, sc, db);

        chillForASecond(1000);
        furySpeaking("You are not done with us yet!");
        chillForASecond(1000);

        player.actTyphon(fury, inventory, sc, db);

        suspensefulDots(RED + ".");
        chillForASecond(500);
        typhonSpeaking("Fine, I'll do it myself");
        chillForASecond(1000);

        player.actTyphon(typhon, inventory, sc, db);
        player.setStrength(originalStrength);
        player.setAgility(originalAgility);
        db.setEquippedWeaponStrength(player, weaponOriginalStrength);


        suspensefulDots(".");
        sleepThread(YELLOW + """
                As the hero and Althea emerge from the treacherous underworld, victorious against Typhon's malevolent forces, a newfound \s
                sense of relief and joy envelops them. The journey home is marked by shared smiles and unspoken gratitude, each step \s
                reinforcing the bonds forged through adversity.\s
                
                However, upon reaching the familiar grounds of their home, an unexpected scene unfolds.\s
                Waiting amidst a quaint arrangement of flowers and flickering candles is a man.\s
                """);

        playerSpeaking("Krille!? What are you doing here", player);
        chillForASecond(500);
        sleepThread(YELLOW + """
                His eyes sparkle with nervous excitement as he clutches a small velvet box. In the quietude of the moment, Krille \s
                steps forward, determination masking his anxiety. He addresses Althea,
                """);
        chillForASecond(1000);
        System.out.println(GREEN_DARK + BOLD + UNDERLINED + "Krille:" + RESET);
        sleepThread(GREEN_DARK + "My love, through the shadows and trials, you've returned. Will you make me the happiest man alive? \nWill you marry me?" + RESET);
        chillForASecond(500);
        System.out.println(PINK + BOLD + UNDERLINED + "Althea:" + RESET);
        sleepThread(PINK + player.getName() + "YES! YES OF COURSE I WILL!" + RESET);


        db.updateGlock(player, 0);
        db.addSpecialAttack(player, typhon.getName());
        sleepThread(GRAY + ITALIC + "You have completed level six");
        suspensefulDots(".");
        chillForASecond(1000);
        sleepThread(GRAY + ITALIC + "I would highly recommend visiting the weapons shop" + RESET);
        chillForASecond(1000);

    }


    private void goWithBoatMan(Scanners sc) {

        sleepThread(YELLOW + """
                Accepting the boatman's offer, the hero boards the boat, eager to cross the river swiftly. Attempting conversation, his \s
                questions linger in the air. Mysterious splashes in the water catch his attention, yet an unseen barrier \s
                shields the boat from the source. Harmonizing sounds attempt to enchant, but the protective shield keeps the hero unaffected.\s
                """);
        suspensefulDots(GRAY + ".");
        System.out.println(WHITE + "What do you want to ask the boatman?" + RESET);
        sleepThread(GRAY + ITALIC + "One question will give you a clue that will be useful later on, \nbut another one will make the boatman not want to speak to you anymore.");
        chillForASecond(1000);

        boolean askingQuestions = true;
        while (askingQuestions){

            System.out.println(WHITE + "What do you want to ask the boatman?" + RESET);
            System.out.println(YELLOW_LIGHT + ITALIC + "1. Who are you\n2. Why are you here?\n3. Has anyone else made it out alive?\n4. Does Typhon have a weakness?\n0. I'm done asking questions");

            switch (sc.scannerNumber()){
                case 1 -> {
                    System.out.println(BLACK + BOLD + UNDERLINED + "Boatman:" + RESET);
                    sleepThread(BLACK + ITALIC + "I have no identity, not that i remember anyways." + RESET);
                    sc.pressEnter();
                }
                case 2 -> {
                    System.out.println(BLACK + BOLD + UNDERLINED + "Boatman:" + RESET);
                    sleepThread(BLACK + ITALIC + "I am here for the same reason as you. My sister, Persephone, was taken by Hades. Same as you, I \nsought vengeance. Hades imprisoned my fate to this river");
                    suspensefulDots(".");
                    sleepThread("I'm done with your questions." + RESET);
                    askingQuestions = false;
                }
                case 3 -> {
                    System.out.println(BLACK + BOLD + UNDERLINED + "Boatman:" + RESET);
                    sleepThread(BLACK + ITALIC + "One has, though he was never the same. He came for Typhons head to give to Zeus. Failed miserably, he \nkept mumbling the same numbers, '1132', With the damage to his mind he was as good as dead" + RESET);
                    sc.pressEnter();
                }
                case 4 -> {
                    System.out.println(BLACK + BOLD + UNDERLINED + "Boatman:" + RESET);
                    sleepThread("*laughs*\nWeakness? No son, I apologize for the harsh truth, but you are on a suicide mission");
                    sc.pressEnter();
                }

                case 0 -> askingQuestions = false;
                default -> System.out.println(RED + BOLD + "Invalid input." + RESET);
            }

        }

    }

    private void rowingBoat(Player player, Inventory inventory, Scanners sc, DBConnection db) {
        Siren siren = new Siren();
        sleepThread(YELLOW + """
                Refusing the boatman's offer, the hero finds a small rowboat by the shore. Determined, he starts rowing across the \s
                desolate river, navigating the eerie waters. As he progresses, haunting melodies fill the air – the enchanting song \s
                of sirens seeking to lure him into the depths. The hero battles the hypnotic pull, gripping the oars with newfound \s
                determination as the sirens' haunting voices crescendo in the underworld's solemn symphony.\s
                """);

        int rowing = 0;
        int rowsNeeded = 20;

        System.out.println(GRAY + "Press enter to row" + RESET);
        while (rowing < rowsNeeded) {
            sc.pressEnterNoText();
            rowing++;
        }

        System.out.println(GRAY + "Thud");
        suspensefulDots(".");

        player.actTyphon(siren, inventory, sc, db);
        player.actTyphon(siren, inventory, sc, db);
        player.actTyphon(siren, inventory, sc, db);

        suspensefulDots(".");

    }

    private void castleGates(Player player, Inventory inventory, Scanners sc, DBConnection db) {
        Fury fury = new Fury();

        sleepThread(YELLOW + """
                Approaching the gates of Typhon's foreboding castle, the hero witnesses the macabre spectacle of suffering souls hanging \s
                on the wall. Three fierce Furies stand guard at the entrance, their watchful eyes piercing through the shadows, ready to \s
                unleash their wrath upon any intruder. The air is heavy with the weight of despair, and the hero braces for the imminent \s
                confrontation that lies ahead.\s
                """);

        furySpeaking(player.getName() + "! Finally, we've been waiting for your arrival");
        chillForASecond(1500);
        playerSpeaking("I mustn't keep you waiting then", player);
        chillForASecond(500);

        player.actTyphon(fury, inventory, sc, db);
        player.actTyphon(fury, inventory, sc, db);
        player.actTyphon(fury, inventory, sc, db);


        sleepThread(YELLOW + """
                As the hero triumphs over the Furies, he charges through the imposing castle gates only to be met with a surreal sight. \s
                A grotesque wedding ceremony unfolds before him – his beloved sister, Althea, is chained to the altar, and the malevolent Typhon \s
                stands beside her with a sinister grin. The air grows thick with tension as the hero realizes the urgency of the impending battle \s
                to save his sister from the clutches of this monstrous foe.\s
                """);

        playerSpeaking("Typhon you beast! You will wed my sister over my dead body. Come meet your faith!", player);
        chillForASecond(1000);
        sleepThread(YELLOW + "Typhons eyes slowly narrow as he turns around, his face full of hatred");
        chillForASecond(500);
        typhonSpeaking("You fool, you believe you can defeat me? I am the great Typhon! I am the embodiment of violence and fear");
        chillForASecond(500);
        playerSpeaking("Come prove it then, you beast", player);
        chillForASecond(500);

    }

    private void trappedInsideCastle(Scanners sc) {
        boolean inMaze = true;
        int correctPathsChosen = 0;

        while (inMaze) {

            sleepThread(ORANGE + BOLD + " Choose which path to follow:" + RESET);
            chillForASecond(700);
            System.out.println(YELLOW_DARK + """
                                 ↟
                    ↞ Left     Middle     Right ↠
                       1         2          3
                    """ + RESET);

            switch (correctPathsChosen) {
                case 0, 1 -> {
                    switch (sc.scannerNumber()) {
                        case 1 -> {
                            System.out.println(PINK_LIGHT + ITALIC + "slay queen" + RESET);
                            correctPathsChosen++;
                        }
                        case 2, 3 -> System.out.println("no lol");
                        default -> printRed("Invalid input");
                    }
                }

                case 2 -> {
                    switch (sc.scannerNumber()) {
                        case 1, 2 -> System.out.println("no lol");
                        case 3 -> {
                            System.out.println(PINK_LIGHT + ITALIC + "slay queen" + RESET);
                            correctPathsChosen++;
                        }
                        default -> printRed("Invalid input");
                    }
                }

                case 3 -> {
                    switch (sc.scannerNumber()) {
                        case 1 -> System.out.println("noo");
                        case 2 -> {
                            System.out.println(PINK_LIGHT + ITALIC + "slay queen" + RESET);
                            correctPathsChosen++;
                            inMaze = false;
                        }
                        case 3 -> System.out.println("no");
                        default -> printRed("Invalid input");
                    }
                }

            }
        }
    }
}
