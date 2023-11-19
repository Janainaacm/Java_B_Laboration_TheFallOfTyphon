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

    public void playLevelSix(Player player, Inventory inventory, Scanners sc) {
        int originalAgility = player.getAgility();
        int originalDamage = player.getMaxDamage();
        Typhon typhon = new Typhon();
        Fury fury = new Fury();

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
        chillForASecond(1500);
        sleepThread(GRAY + ITALIC + "What do you want to do?" + RESET);
        System.out.println(YELLOW_LIGHT + ITALIC + "Gold: " + player.getGold() + RESET +
                YELLOW + "1. Safely cross the lake with the boatman\n2. Take your chances with the rowing boat" + RESET);

        boolean isChoosingBoatman = true;
        boolean didGoWithBoatMan = false;
        while (isChoosingBoatman) {
            switch (sc.scannerNumber()) {
                case 1 -> {
                    if (player.getGold() >= 200) {
                        didGoWithBoatMan = goWithBoatMan(player, inventory, sc);
                        player.setGold(player.getGold() - 200);
                        isChoosingBoatman = false;
                    } else {
                        sleepThread(RED + ITALIC + "You do not have the capacity for that big man" + RESET);
                        System.out.println(GRAY + "Try again" + RESET);
                    }

                }

                case 2 -> {
                    rowingBoat(player, inventory, sc);
                    isChoosingBoatman = false;
                }

                default -> printRed("Invalid input");
            }
        }

        castleGates(player, inventory, sc);

        player.actTyphon(typhon, inventory, sc);

        //Runs to his castle
        System.out.println(YELLOW_LIGHT + "Press enter to follow inside castle!" + RESET);

        //The hallways are playing games with his head, he is trapped in a maze. Find the way out
        if (didGoWithBoatMan) {
            sleepThread(GRAY + ITALIC + "Did you pay attention to what the boatman said?" + RESET);
        }
        trappedInsideCastle(player, inventory, sc);

        //found him
        player.actTyphon(typhon, inventory, sc);

        //Omg you think you won bc he fled! Talk to Althea, run outside and SIKE

        //Lore ahaha you rlly thought

        //Oh no Typhon bewitched something your strength and agility are divided by 2

        player.setAgility(player.getAgility() / 2);
        player.setMaxDamage(player.getMaxDamage() / 2);

        player.actTyphon(fury, inventory, sc);

        player.actTyphon(fury, inventory, sc);

        player.actTyphon(fury, inventory, sc);

        //Oh you think you bad huh?

        player.actTyphon(typhon, inventory, sc);

            /*
            You manage to rescue Althea, and together you go home. but PLOT TWIST
            when you get home Krille is waiting to propose to Althea lmfao
             */

        player.setMaxDamage(originalDamage);
        player.setAgility(originalAgility);
        player.setAvailableLevels(7);
        sleepThread(GRAY + ITALIC + "I would highly recommend visiting the weapons shop" + RESET);

    }


    private boolean goWithBoatMan(Player player, Inventory inventory, Scanners sc) {
    /*
    Accepting the boatman's offer, the hero boards the boat, eager to cross the river swiftly. Attempting conversation, his questions linger unanswered in the air. Mysterious splashes in the water catch his attention, yet an unseen barrier shields the boat from the source. Harmonizing sounds attempt to enchant, but the protective shield keeps the hero unaffected.
    ksk lägg olika alternativ på vad han kan fråga, en fråga får han att sluta svara men en av frågorna ger han rätt väg i slottet
Upon reaching the shore, the hero steps onto what appears to be a beach, only to discover it's not sand beneath his feet but a haunting landscape littered with skeletal remains, silent witnesses to the underworld's eternal suffering.

     han säger 1132

     */
        return true;
    }

    private void rowingBoat(Player player, Inventory inventory, Scanners sc) {
        Siren siren = new Siren();
               /*
               Refusing the boatman's offer, the hero finds a small rowboat by the shore. Determined, he starts rowing across the desolate river, navigating the eerie waters. As he progresses, haunting melodies fill the air – the enchanting song of sirens seeking to lure him into the depths. The hero battles the hypnotic pull, gripping the oars with newfound determination as the sirens' haunting voices crescendo in the underworld's solemn symphony.

                */
        int rowing = 0;
        int rowsNeeded = 20;

        while (rowing < rowsNeeded) {
            sc.pressEnterNoText();
            rowing++;
        }

        //Lore you hit something


        player.actTyphon(siren, inventory, sc);

        player.actTyphon(siren, inventory, sc);

        player.actTyphon(siren, inventory, sc);


        //sirens.
    }

    private void castleGates(Player player, Inventory inventory, Scanners sc) {
        //Fight furies & ksk nån gåta? ksk är det man kan få ur boatman
        //All lore, fight typhon direkt efter


        Fury fury = new Fury();

        player.actTyphon(fury, inventory, sc);

        player.actTyphon(fury, inventory, sc);

        //Divide player agility by 2

        player.actTyphon(fury, inventory, sc);

        //Write lore
        //He arrives just in time to see his sister chained to the altar, the beast that is his target grinning next to her
        //next is fighting typhon

    }

    private void trappedInsideCastle(Player player, Inventory inventory, Scanners sc) {
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
                case 0 -> {
                    switch (sc.scannerNumber()) {
                        case 1 -> correctPathsChosen++;
                        case 2 -> System.out.println("no");
                        case 3 -> System.out.println("noo");
                        default -> printRed("Invalid input");
                    }
                }
                case 1 -> {
                    switch (sc.scannerNumber()) {
                        case 1 -> correctPathsChosen++;
                        case 2 -> System.out.println("noo");
                        case 3 -> System.out.println("no0o");
                        default -> printRed("Invalid input");
                    }
                }

                case 2 -> {
                    switch (sc.scannerNumber()) {
                        case 1 -> System.out.println("noo");
                        case 2 -> System.out.println("no");
                        case 3 -> correctPathsChosen++;
                        default -> printRed("Invalid input");
                    }
                }

                case 3 -> {
                    switch (sc.scannerNumber()) {
                        case 1 -> System.out.println("noo");
                        case 2 -> {
                            correctPathsChosen++;
                            inMaze = false;
                        }
                        case 3 -> System.out.println("no");
                        default -> printRed("Invalid input");
                    }
                }

            }


        }

        //found him, Lore, next thing is fight


    }


}
