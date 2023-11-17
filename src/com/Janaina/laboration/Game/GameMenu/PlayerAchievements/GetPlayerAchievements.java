package com.Janaina.laboration.Game.GameMenu.PlayerAchievements;

import com.Janaina.laboration.Game.Variables.ACharacters;
import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Game.Variables.Monsters.*;

import java.util.Objects;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.PrintHandler.printRed;
import static com.Janaina.laboration.Resources.Scanners.*;
import static com.Janaina.laboration.Resources.Storyteller.*;
import static com.Janaina.laboration.Resources.TextDelay.chillForASecond;
import static com.Janaina.laboration.Resources.TextDelay.sleepThread;

public class GetPlayerAchievements {

    public void playerAchievements(Player player) {
        Fury fury = new Fury();
        Siren siren = new Siren();
        Medusa medusa = new Medusa();
        Minotaur minotaur = new Minotaur();
        Cerberus cerberus = new Cerberus();
        Typhon typhon = new Typhon();

        boolean isChoosing = true;
        while (isChoosing) {

            System.out.println(PINK + BLACK_BACKGROUND + BOLD + "       " + UNDERLINED + "Achievements" + RESET + BLACK_BACKGROUND + "       " + RESET);

            if (player.furiesSlayed >= 1) {
                System.out.println(PINK_LIGHT + ITALIC + "1. Fury" + RESET);
            } else if (player.furiesSlayed == 0 && player.getAvailableLevels() >= 2) {
                System.out.println(GRAY + ITALIC + "1. Fury" + RESET);
            } else {
                System.out.println(GRAY + "???" + RESET);
            }

            if (player.sirensSlayed >= 1) {
                System.out.println(PINK_LIGHT + ITALIC + "2. Siren" + RESET);
            } else if (player.sirensSlayed == 0 && player.getAvailableLevels() >= 2) {
                System.out.println(GRAY + ITALIC + "2. Siren" + RESET);
            } else {
                System.out.println(GRAY + "???" + RESET);
            }

            if (player.medusaSlayed >= 1) {
                System.out.println(PINK_LIGHT + ITALIC + "3. Medusa" + RESET);
            } else if (player.medusaSlayed == 0 && player.getAvailableLevels() >= 3) {
                System.out.println(GRAY + ITALIC + "3. Medusa" + RESET);
            } else {
                System.out.println(GRAY + "???" + RESET);
            }

            if (player.minotaurSlayed >= 1) {
                System.out.println(PINK_LIGHT + ITALIC + "4. Minotaur" + RESET);
            } else if (player.minotaurSlayed == 0 && player.getAvailableLevels() >= 4) {
                System.out.println(GRAY + ITALIC + "4. Minotaur" + RESET);
            } else {
                System.out.println(GRAY + "???" + RESET);
            }

            if (player.cerberusSlayed >= 1) {
                System.out.println(PINK_LIGHT + ITALIC + "5. Cerberus" + RESET);
            } else if (player.cerberusSlayed == 0 && player.getAvailableLevels() >= 5) {
                System.out.println(GRAY + ITALIC + "5. Cerberus" + RESET);
            } else {
                System.out.println(GRAY + "???" + RESET);
            }

            if (player.typhonSlayed >= 1) {
                System.out.println(PINK_LIGHT + ITALIC + "6. Typhon" + RESET);
            } else if (player.typhonSlayed == 0 && player.getAvailableLevels() >= 6) {
                System.out.println(GRAY + ITALIC + "6. Typhon" + RESET);
            } else {
                System.out.println(GRAY + "???" + RESET);
            }

            System.out.println(PINK_LIGHT + BOLD + "0. Go back" + RESET);

            switch (scannerNumber()) {
                case 1 -> {
                    if (player.furiesSlayed >= 1 || player.getAvailableLevels() >= 2) {
                        furyStats(player, fury);
                    } else {
                        printRed("Invalid input");
                    }
                }
                case 2 -> {
                    if (player.sirensSlayed >= 1 || player.getAvailableLevels() >= 2) {
                        sirensStats(player, siren);
                    } else {
                        printRed("Invalid input");
                    }
                }
                case 3 -> {
                    if (player.medusaSlayed >= 1 || player.getAvailableLevels() >= 3) {
                        medusaStats(player, medusa);
                    } else {
                        printRed("Invalid input");
                    }
                }
                case 4 -> {
                    if (player.minotaurSlayed >= 1 || player.getAvailableLevels() >= 4) {
                        minotaurStats(player, minotaur);
                    } else {
                        printRed("Invalid input");
                    }
                }
                case 5 -> {
                    if (player.cerberusSlayed >= 1 || player.getAvailableLevels() >= 5) {
                        cerberusStats(player, cerberus);
                    } else {
                        printRed("Invalid input");
                    }
                }
                case 6 -> {
                    if (player.typhonSlayed >= 1 || player.getAvailableLevels() >= 6) {
                        typhonStats(player, typhon);
                    } else {
                        printRed("Invalid input");
                    }
                }
                case 0 -> isChoosing = false;
                default -> printRed("Invalid input");
            }
        }
    }


    private void furyStats(Player player, ACharacters monster) {

        if (player.furiesSlayed >= 1) {
            System.out.println(BLACK_BACKGROUND + "            " + RED + BOLD + UNDERLINED + "Fury" + RESET + BLACK_BACKGROUND + "            " + RESET
                    + RED + ITALIC + "\nFuries killed: " + player.furiesSlayed + RESET
                    + RED_LILDARKER + "\nHealth: " + monster.getHealth()
                    + "\nMin Damage: " + monster.getBaseDamage()
                    + "\nMax Damage: " + monster.getBaseDamage() * monster.getStrength()
                    + "\nGold Gained: " + monster.getGold()
                    + "\nXP Gained: " + monster.getExperience());

            chillForASecond(1500);

            sleepThread(YELLOW + "Would you like to read more about " + monster.getName() + "?" + RESET);

            if (Objects.equals(scannerYesOrNo(), "yes")) {
                furyBackstory();
                chillForASecond(1000);
            } else {
                chillForASecond(1000);
            }

        } else {
            sleepThread(GRAY + "You have not defeated this monster yet" + RESET);
            chillForASecond(1000);
        }
        pressEnter();
    }

    private void sirensStats(Player player, ACharacters monster) {

        if (player.sirensSlayed >= 1) {
            System.out.println(BLACK_BACKGROUND + "            " + CYAN_BOLD_BRIGHT + CYAN_UNDERLINED + "Siren" + RESET + BLACK_BACKGROUND + "            " + RESET
                    + CYAN + ITALIC + "\nSirens killed: " + player.sirensSlayed + RESET
                    + CYAN + "\nHealth: " + monster.getHealth()
                    + "\nMin Damage: " + monster.getBaseDamage()
                    + "\nMax Damage: " + monster.getBaseDamage() * monster.getStrength()
                    + "\nGold Gained: " + monster.getGold()
                    + "\nXP Gained: " + monster.getExperience());

            chillForASecond(1500);

            sleepThread(YELLOW + "Would you like to read more about " + monster.getName() + "?" + RESET);

            if (Objects.equals(scannerYesOrNo(), "yes")) {
                sirenBackstory();
                chillForASecond(1000);
            } else {
                chillForASecond(1000);
            }

        } else {
            sleepThread(GRAY + "You have not defeated this monster yet" + RESET);
            chillForASecond(1000);
        }
        pressEnter();
    }


    private void medusaStats(Player player, ACharacters monster) {
        if (player.medusaSlayed >= 1) {
            System.out.println(BLACK_BACKGROUND + "            " + GREEN_DARK + BOLD + UNDERLINED + "Medusa" + RESET + BLACK_BACKGROUND + "            " + RESET
                    + GREEN_DARK + ITALIC + "\nMedusa's killed: " + player.medusaSlayed + RESET
                    + GREEN_DARK + "\nHealth: " + monster.getHealth()
                    + "\nMin Damage: " + monster.getBaseDamage()
                    + "\nMax Damage: " + monster.getBaseDamage() * monster.getStrength()
                    + "\nGold Gained: " + monster.getGold()
                    + "\nXP Gained: " + monster.getExperience());

            chillForASecond(1500);

            sleepThread(YELLOW + "Would you like to read more about " + monster.getName() + "?" + RESET);

            if (Objects.equals(scannerYesOrNo(), "yes")) {
                medusaBackstory();
                chillForASecond(1000);
            } else {
                chillForASecond(1000);
            }

        } else {
            sleepThread(GRAY + "You have not defeated this monster yet" + RESET);
            chillForASecond(1000);
        }
        pressEnter();
    }

    private void minotaurStats(Player player, ACharacters monster) {
        if (player.minotaurSlayed >= 1) {
            System.out.println(BLACK_BACKGROUND + "           " + ORANGE + BOLD + UNDERLINED + "Minotaur" + RESET + BLACK_BACKGROUND + "           " + RESET
                    + ORANGE + ITALIC + "\nMinotaur's killed: " + player.minotaurSlayed + RESET
                    + ORANGE + "\nHealth: " + monster.getHealth()
                    + "\nMin Damage: " + monster.getBaseDamage()
                    + "\nMax Damage: " + monster.getBaseDamage() * monster.getStrength()
                    + "\nGold Gained: " + monster.getGold()
                    + "\nXP Gained: " + monster.getExperience());

            chillForASecond(1500);

            sleepThread(YELLOW + "Would you like to read more about " + monster.getName() + "?" + RESET);

            if (Objects.equals(scannerYesOrNo(), "yes")) {
                minotaurBackstory();
                chillForASecond(1000);
            } else {
                chillForASecond(1000);
            }

        } else {
            sleepThread(GRAY + "You have not defeated this monster yet" + RESET);
            chillForASecond(1000);
        }
        pressEnter();
    }

    private void cerberusStats(Player player, ACharacters monster) {
        if (player.cerberusSlayed >= 1) {
            System.out.println(BLACK_BACKGROUND + "           " + RED + BOLD + UNDERLINED + "Cerberus" + RESET + BLACK_BACKGROUND + "           " + RESET
                    + RED_LILDARKER + ITALIC + "\nCerberus killed: " + player.cerberusSlayed + RESET
                    + RED + "\nHealth: " + monster.getHealth()
                    + "\nMin Damage: " + monster.getBaseDamage()
                    + "\nMax Damage: " + monster.getBaseDamage() * monster.getStrength()
                    + "\nGold Gained: " + monster.getGold()
                    + "\nXP Gained: " + monster.getExperience());

            chillForASecond(1500);

            sleepThread(YELLOW + "Would you like to read more about " + monster.getName() + "?" + RESET);

            if (Objects.equals(scannerYesOrNo(), "yes")) {
                cerberusBackstory();
                chillForASecond(1000);
            } else {
                chillForASecond(1000);
            }

        } else {
            sleepThread(GRAY + "You have not defeated this monster yet" + RESET);
            chillForASecond(1000);
        }
        pressEnter();
    }

    private void typhonStats(Player player, ACharacters monster) {
        if (player.typhonSlayed >= 1) {
            System.out.println(BLACK_BACKGROUND + "           " + RED + BOLD + UNDERLINED + "Typhon" + RESET + BLACK_BACKGROUND + "           " + RESET
                    + RED_LILDARKER + ITALIC + "\nTyphon's killed: " + player.typhonSlayed + RESET
                    + RED + "\nHealth: " + monster.getHealth()
                    + "\nMin Damage: " + monster.getBaseDamage()
                    + "\nMax Damage: " + monster.getBaseDamage() * monster.getStrength()
                    + "\nGold Gained: " + monster.getGold()
                    + "\nXP Gained: " + monster.getExperience());

            chillForASecond(1500);

            sleepThread(YELLOW + "Would you like to read more about " + monster.getName() + "?" + RESET);

            if (Objects.equals(scannerYesOrNo(), "yes")) {
                typhonBackstory();
                chillForASecond(1000);
            } else {
                chillForASecond(1000);
            }

        } else {
            sleepThread(GRAY + "You have not defeated this monster yet" + RESET);
            chillForASecond(1000);
        }
        pressEnter();
    }


}
