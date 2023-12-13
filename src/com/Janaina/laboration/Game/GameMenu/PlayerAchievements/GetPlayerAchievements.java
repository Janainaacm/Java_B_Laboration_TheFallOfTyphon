package com.Janaina.laboration.Game.GameMenu.PlayerAchievements;

import com.Janaina.laboration.DBConnection;
import com.Janaina.laboration.Game.Variables.ACharacters;
import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Game.Variables.Monsters.*;
import com.Janaina.laboration.Resources.Scanners;

import java.util.Objects;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.PrintHandler.printRed;
import static com.Janaina.laboration.Resources.Storyteller.*;
import static com.Janaina.laboration.Resources.TextDelay.chillForASecond;
import static com.Janaina.laboration.Resources.TextDelay.sleepThread;

public class GetPlayerAchievements {

    public void playerAchievements(Player player, Scanners sc, DBConnection db) {
        boolean isChoosing = true;

        while (isChoosing) {
            System.out.println(BLUE + BLACK_BACKGROUND + BOLD + "       " + UNDERLINED + "Achievements" + RESET + BLACK_BACKGROUND + "       " + RESET);

            System.out.println(BLUE_LIGHT + ITALIC + "1. Fight Log\n2. Kill log" + RESET + BLUE + "\n0. Go back");

            switch (sc.scannerNumber()) {
                case 1 -> fightLog(player, sc, db);
                case 2 -> killLog(player, sc);
                case 0 -> isChoosing = false;
                default -> System.out.println(RED + BOLD + "Invalid input." + RESET);
            }
        }


    }

    private void fightLog(Player player, Scanners sc, DBConnection db){
        db.showFightLog(player);
        chillForASecond(1000);
        sleepThread("Press enter to go back");
        sc.pressEnterNoText();
    }

    private void killLog(Player player, Scanners sc) {
        Fury fury = new Fury();
        Siren siren = new Siren();
        Medusa medusa = new Medusa();
        Minotaur minotaur = new Minotaur();
        Cerberus cerberus = new Cerberus();
        Typhon typhon = new Typhon();

        boolean isChoosing = true;
        while (isChoosing) {

            System.out.println(PINK + BLACK_BACKGROUND + BOLD + "       " + UNDERLINED + "Kill log" + RESET + BLACK_BACKGROUND + "       " + RESET);

            if (player.getFuriesSlayed() >= 1) {
                System.out.println(PINK_LIGHT + ITALIC + "1. Fury" + RESET);
            } else if (player.getFuriesSlayed() == 0 && player.getAvailableLevels() >= 2) {
                System.out.println(GRAY + ITALIC + "1. Fury" + RESET);
            } else {
                System.out.println(GRAY + "???" + RESET);
            }

            if (player.getSirensSlayed() >= 1) {
                System.out.println(PINK_LIGHT + ITALIC + "2. Siren" + RESET);
            } else if (player.getSirensSlayed() == 0 && player.getAvailableLevels() >= 2) {
                System.out.println(GRAY + ITALIC + "2. Siren" + RESET);
            } else {
                System.out.println(GRAY + "???" + RESET);
            }

            if (player.getMedusasSlayed() >= 1) {
                System.out.println(PINK_LIGHT + ITALIC + "3. Medusa" + RESET);
            } else if (player.getMedusasSlayed() == 0 && player.getAvailableLevels() >= 3) {
                System.out.println(GRAY + ITALIC + "3. Medusa" + RESET);
            } else {
                System.out.println(GRAY + "???" + RESET);
            }

            if (player.getMinotaursSlayed() >= 1) {
                System.out.println(PINK_LIGHT + ITALIC + "4. Minotaur" + RESET);
            } else if (player.getMinotaursSlayed() == 0 && player.getAvailableLevels() >= 4) {
                System.out.println(GRAY + ITALIC + "4. Minotaur" + RESET);
            } else {
                System.out.println(GRAY + "???" + RESET);
            }

            if (player.getCerberusSlayed() >= 1) {
                System.out.println(PINK_LIGHT + ITALIC + "5. Cerberus" + RESET);
            } else if (player.getCerberusSlayed() == 0 && player.getAvailableLevels() >= 5) {
                System.out.println(GRAY + ITALIC + "5. Cerberus" + RESET);
            } else {
                System.out.println(GRAY + "???" + RESET);
            }

            if (player.getTyphonSlayed() >= 1) {
                System.out.println(PINK_LIGHT + ITALIC + "6. Typhon" + RESET);
            } else if (player.getTyphonSlayed() == 0 && player.getAvailableLevels() >= 6) {
                System.out.println(GRAY + ITALIC + "6. Typhon" + RESET);
            } else {
                System.out.println(GRAY + "???" + RESET);
            }

            System.out.println(PINK_LIGHT + BOLD + "0. Go back" + RESET);

            switch (sc.scannerNumber()) {
                case 1 -> {
                    if (player.getFuriesSlayed() >= 1 || player.getAvailableLevels() >= 2) {
                        furyStats(player, fury, sc);
                    } else {
                        printRed("Invalid input");
                    }
                }
                case 2 -> {
                    if (player.getSirensSlayed() >= 1 || player.getAvailableLevels() >= 2) {
                        sirensStats(player, siren, sc);
                    } else {
                        printRed("Invalid input");
                    }
                }
                case 3 -> {
                    if (player.getMedusasSlayed() >= 1 || player.getAvailableLevels() >= 3) {
                        medusaStats(player, medusa, sc);
                    } else {
                        printRed("Invalid input");
                    }
                }
                case 4 -> {
                    if (player.getMinotaursSlayed() >= 1 || player.getAvailableLevels() >= 4) {
                        minotaurStats(player, minotaur, sc);
                    } else {
                        printRed("Invalid input");
                    }
                }
                case 5 -> {
                    if (player.getCerberusSlayed() >= 1 || player.getAvailableLevels() >= 5) {
                        cerberusStats(player, cerberus, sc);
                    } else {
                        printRed("Invalid input");
                    }
                }
                case 6 -> {
                    if (player.getTyphonSlayed() >= 1 || player.getAvailableLevels() >= 6) {
                        typhonStats(player, typhon, sc);
                    } else {
                        printRed("Invalid input");
                    }
                }
                case 0 -> isChoosing = false;
                default -> printRed("Invalid input");
            }
        }
    }


    private void furyStats(Player player, ACharacters monster, Scanners sc) {

        if (player.getFuriesSlayed() >= 1) {
            System.out.println(BLACK_BACKGROUND + "            " + RED + BOLD + UNDERLINED + "Fury" + RESET + BLACK_BACKGROUND + "            " + RESET
                    + RED + ITALIC + "\nFuries killed: " + player.getFuriesSlayed() + RESET
                    + RED_LILDARKER + "\nHealth: " + monster.getHealth()
                    + "\nMin Damage: " + monster.getBaseDamage()
                    + "\nMax Damage: " + monster.getBaseDamage() * monster.getStrength()
                    + "\nGold Gained: " + monster.getGold()
                    + "\nXP Gained: " + monster.getExperience());

            chillForASecond(1500);

            sleepThread(YELLOW + "Would you like to read more about " + monster.getName() + "?" + RESET);

            if (Objects.equals(sc.scannerYesOrNo(), "yes")) {
                furyBackstory();
                chillForASecond(1000);
                sc.pressEnter();
            } else {
                chillForASecond(1000);
            }

        } else {
            sleepThread(GRAY + "You have not defeated this monster yet" + RESET);
            chillForASecond(1000);
        }
        sc.pressEnter();
    }

    private void sirensStats(Player player, ACharacters monster, Scanners sc) {

        if (player.getSirensSlayed() >= 1) {
            System.out.println(BLACK_BACKGROUND + "            " + CYAN_BOLD_BRIGHT + CYAN_UNDERLINED + "Siren" + RESET + BLACK_BACKGROUND + "            " + RESET
                    + CYAN + ITALIC + "\nSirens killed: " + player.getSirensSlayed() + RESET
                    + CYAN + "\nHealth: " + monster.getHealth()
                    + "\nMin Damage: " + monster.getBaseDamage()
                    + "\nMax Damage: " + monster.getBaseDamage() * monster.getStrength()
                    + "\nGold Gained: " + monster.getGold()
                    + "\nXP Gained: " + monster.getExperience());

            chillForASecond(1500);

            sleepThread(YELLOW + "Would you like to read more about " + monster.getName() + "?" + RESET);

            if (Objects.equals(sc.scannerYesOrNo(), "yes")) {
                sirenBackstory();
                chillForASecond(1000);
                sc.pressEnter();
            } else {
                chillForASecond(1000);
            }

        } else {
            sleepThread(GRAY + "You have not defeated this monster yet" + RESET);
            chillForASecond(1000);
        }
        sc.pressEnter();
    }


    private void medusaStats(Player player, ACharacters monster, Scanners sc) {
        if (player.getMedusasSlayed() >= 1) {
            System.out.println(BLACK_BACKGROUND + "            " + GREEN_DARK + BOLD + UNDERLINED + "Medusa" + RESET + BLACK_BACKGROUND + "            " + RESET
                    + GREEN_DARK + ITALIC + "\nMedusa's killed: " + player.getMedusasSlayed() + RESET
                    + GREEN_DARK + "\nHealth: " + monster.getHealth()
                    + "\nMin Damage: " + monster.getBaseDamage()
                    + "\nMax Damage: " + monster.getBaseDamage() * monster.getStrength()
                    + "\nGold Gained: " + monster.getGold()
                    + "\nXP Gained: " + monster.getExperience());

            chillForASecond(1500);

            sleepThread(YELLOW + "Would you like to read more about " + monster.getName() + "?" + RESET);

            if (Objects.equals(sc.scannerYesOrNo(), "yes")) {
                medusaBackstory();
                chillForASecond(1000);
                sc.pressEnter();
            } else {
                chillForASecond(1000);
            }

        } else {
            sleepThread(GRAY + "You have not defeated this monster yet" + RESET);
            chillForASecond(1000);
        }
        sc.pressEnter();
    }

    private void minotaurStats(Player player, ACharacters monster, Scanners sc) {
        if (player.getMinotaursSlayed() >= 1) {
            System.out.println(BLACK_BACKGROUND + "           " + ORANGE + BOLD + UNDERLINED + "Minotaur" + RESET + BLACK_BACKGROUND + "           " + RESET
                    + ORANGE + ITALIC + "\nMinotaur's killed: " + player.getMinotaursSlayed() + RESET
                    + ORANGE + "\nHealth: " + monster.getHealth()
                    + "\nMin Damage: " + monster.getBaseDamage()
                    + "\nMax Damage: " + monster.getBaseDamage() * monster.getStrength()
                    + "\nGold Gained: " + monster.getGold()
                    + "\nXP Gained: " + monster.getExperience());

            chillForASecond(1500);

            sleepThread(YELLOW + "Would you like to read more about " + monster.getName() + "?" + RESET);

            if (Objects.equals(sc.scannerYesOrNo(), "yes")) {
                minotaurBackstory();
                chillForASecond(1000);
                sc.pressEnter();
            } else {
                chillForASecond(1000);
            }

        } else {
            sleepThread(GRAY + "You have not defeated this monster yet" + RESET);
            chillForASecond(1000);
        }
        sc.pressEnter();
    }

    private void cerberusStats(Player player, ACharacters monster, Scanners sc) {
        if (player.getCerberusSlayed() >= 1) {
            System.out.println(BLACK_BACKGROUND + "           " + RED + BOLD + UNDERLINED + "Cerberus" + RESET + BLACK_BACKGROUND + "           " + RESET
                    + RED_LILDARKER + ITALIC + "\nCerberus killed: " + player.getCerberusSlayed() + RESET
                    + RED + "\nHealth: " + monster.getHealth()
                    + "\nMin Damage: " + monster.getBaseDamage()
                    + "\nMax Damage: " + monster.getBaseDamage() * monster.getStrength()
                    + "\nGold Gained: " + monster.getGold()
                    + "\nXP Gained: " + monster.getExperience());

            chillForASecond(1500);

            sleepThread(YELLOW + "Would you like to read more about " + monster.getName() + "?" + RESET);

            if (Objects.equals(sc.scannerYesOrNo(), "yes")) {
                cerberusBackstory();
                chillForASecond(1000);
                sc.pressEnter();
            } else {
                chillForASecond(1000);
            }

        } else {
            sleepThread(GRAY + "You have not defeated this monster yet" + RESET);
            chillForASecond(1000);
        }
        sc.pressEnter();
    }

    private void typhonStats(Player player, ACharacters monster, Scanners sc) {
        if (player.getTyphonSlayed() >= 1) {
            System.out.println(BLACK_BACKGROUND + "           " + RED + BOLD + UNDERLINED + "Typhon" + RESET + BLACK_BACKGROUND + "           " + RESET
                    + RED_LILDARKER + ITALIC + "\nTyphon's killed: " + player.getTyphonSlayed() + RESET
                    + RED + "\nHealth: " + monster.getHealth()
                    + "\nMin Damage: " + monster.getBaseDamage()
                    + "\nMax Damage: " + monster.getBaseDamage() * monster.getStrength()
                    + "\nGold Gained: " + monster.getGold()
                    + "\nXP Gained: " + monster.getExperience());

            chillForASecond(1500);

            sleepThread(YELLOW + "Would you like to read more about " + monster.getName() + "?" + RESET);

            if (Objects.equals(sc.scannerYesOrNo(), "yes")) {
                typhonBackstory();
                chillForASecond(1000);
                sc.pressEnter();
            } else {
                chillForASecond(1000);
            }

        } else {
            sleepThread(GRAY + "You have not defeated this monster yet" + RESET);
            chillForASecond(1000);
        }
        sc.pressEnter();
    }


}
