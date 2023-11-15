package com.Janaina.laboration.Game.Variables.Hero;

import com.Janaina.laboration.Game.Shop.ShopProducts;
import com.Janaina.laboration.Game.Variables.ACharacters;

import java.util.*;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.Scanners.*;
import static com.Janaina.laboration.Resources.TextDelay.*;

public class Player extends ACharacters {

    public List<Attacks> specialAttackList;
    public ShopProducts equippedWeapon;
    private int availableLevels = 3;

    public int furiesSlayed = 1;
    public int sirensSlayed = 1;
    public int medusaSlayed = 0;
    public int minotaurSlayed = 0;
    public int cerberusSlayed = 0;
    public int typhonSlayed = 0;

    public Player() {
        super("name", 2, 100, 10, 20, 20, 0, 0, 1, "Knife slash", 100);
        specialAttackList = new ArrayList<>();
        this.equippedWeapon = new ShopProducts("knife", "Lethal Lunge", 0, 2, 0, 0, 0);

    }


    public ShopProducts getEquippedWeapon() {
        return equippedWeapon;
    }

    public void setEquippedWeapon(ShopProducts equippedWeapon) {
        this.equippedWeapon = equippedWeapon;
    }


    public void addSpecialAttack(Attacks attack) {
        specialAttackList.add(attack);
    }


    public void act(ACharacters monster, Inventory inventory) {

        boolean monsterEncounter = true;

        do {
            System.out.println(monster.getStats());
            System.out.println(WHITE_BOLD_BRIGHT + "1. Attack\n2. Flee\n3. Get Stats\n4. inventory" + RESET);

            switch (scannerNumber()) {

                case 1 -> attack(monster);
                case 2 -> {
                    if (flee(monster)) {
                        sleepThread(PURPLE_LIGHT + "Better luck next time" + RESET);
                        suspensefulDots(PURPLE_LIGHT + "." + RESET);
                        monsterEncounter = false;
                    } else {
                        continue;
                    }
                }
                case 3 -> {
                    System.out.println(getStats());
                    pressEnter();
                }
                case 4 -> inventory.playerInventory(this);

            }

            if (playerWins(monster)) {
                monsterEncounter = false;
            }

            if (!isAlive()) {
                sleepThread(RED + ITALIC + "You were killed by " + monster.getName() + RESET);
                suspensefulDots(RED + "." + RESET);
                System.out.println(RED + BOLD + "Game Over." + RESET);
                System.exit(0);
                monsterEncounter = false;
            }

        } while (monsterEncounter);

    }

    public boolean playerWins(ACharacters monster) {
        if (!monster.isAlive()) {
            System.out.println(PURPLE_BOLD_BRIGHT + "You managed to slay " + monster.getName() + "!" + RESET);
            chillForASecond(1000);
            System.out.println(YELLOW_BOLD + "+ " + monster.getGold() + " Gold");
            chillForASecond(1000);
            System.out.println("+ " + monster.getExperience() + " XP" + RESET);
            chillForASecond(1000);
            gainExperience(monster.getExperience());

            switch (monster.getName()){
                case "Fury" -> furiesSlayed ++;
                case "Siren" -> sirensSlayed ++;
                case "Medusa" -> medusaSlayed ++;
                case "Minotaur" -> minotaurSlayed ++;
                case "Cerberus" -> cerberusSlayed ++;
                case "Typhon" -> typhonSlayed++;
            }

            return true;

        } else {
            return false;
        }

    }


    @Override
    public void attack(ACharacters monster) {

        int attack;
        if (!specialAttackList.isEmpty()) {
            attack = chosenAttack();
        } else {
            attack = attackWeapon();
        }

        if (monster.dodge(this)) {
            System.out.println(RED + monster.getName() + " dodged your attack!");
            chillForASecond(1000);
        } else {
            monster.receiveDamage(this, attack);
        }

        if (monster.isAlive()) {
            monster.attack(this);
            if (!dodge(monster)) {
                receiveDamage(monster, 0);
            }
            chillForASecond(200);
        }

    }

    public int attackWeapon() {
        System.out.println(YELLOW_BOLD_BRIGHT + getName() + ", press enter to use " + getDefaultAttack() + "!" + RESET);
        pressEnterToAttack();
        sleepThread(YELLOW + "▭▭ι═══════ﺤ\n" + RESET);
        Random random = new Random();
        int acquiredStrength = random.nextInt(1, getStrength() + 1);

        return acquiredStrength * getBaseDamage();

    }


    public int chosenAttack() {
        System.out.println(YELLOW + ITALIC + "0. Use Weapon" + RESET);
        System.out.println(ORANGE + BOLD + "Special Attacks:" + RESET);
        for (int i = 0; i < specialAttackList.size(); i++) {
            System.out.println(ORANGE + ITALIC + (i + 1) + ". " + specialAttackList.get(i).getName() + GRAY + ITALIC + "\nDamage: " + RED + specialAttackList.get(i).getDamage() + RESET);

        }
        int choice = scannerNumber();

        if (choice == 0) {
            return attackWeapon();
        }

        if (choice < 1 || choice > specialAttackList.size()) {
            System.out.println(BLACK + "Invalid choice, please try again" + RESET);
        }

        Attacks selectedAttack = specialAttackList.get(choice - 1);
        System.out.println(YELLOW_BOLD_BRIGHT + getName() + ", press enter to use " + selectedAttack.getName() + "!" + RESET);
        pressEnterToAttack();
        sleepThread(YELLOW + "▭▭ι═══════ﺤ\n" + RESET);
        return selectedAttack.getDamage();

    }

    @Override
    public boolean flee(ACharacters monster) {

        System.out.println(PURPLE + "Lets see if you are lucky enough to escape the " + monster.getName() + RESET);
        suspensefulDots(PURPLE + "." + RESET);

        if (canPlayerFlee(monster)) {
            System.out.println(GREEN + "You managed to escape!" + RESET);
            return true;
        } else {
            System.out.println(RED + "Oh no, you were caught!" + RESET);
            dodge(monster);
            return false;
        }


    }

    public boolean canPlayerFlee(ACharacters monster) {
        Random random = new Random();
        int escapeChance = getAgility() - monster.getIntelligence();
        int randomValue = random.nextInt(1, 100);


        return randomValue < escapeChance;
    }


    @Override
    public String getStats() {
        String nameStats = CYAN_UNDERLINED + CYAN_BOLD_BRIGHT + getName() + RESET;
        String healthStats = null;
        String strengthStats = null;
        if (getHealth() > 50) {
            healthStats = CYAN + "Health: " + GREEN + getHealth() + RESET;

        } else if (getHealth() <= 50 && getHealth() > 20) {
            healthStats = CYAN + "Health: " + YELLOW + getHealth() + RESET;

        } else if (getHealth() <= 20 && getHealth() >= 1) {
            healthStats = CYAN + "Health: " + RED + getHealth() + RESET;

        }
        if (getStrength() > 50) {
            strengthStats = CYAN + "Strength: " + GREEN + getStrength() + RESET;

        } else if (getStrength() <= 50 && getStrength() > 20) {
            strengthStats = CYAN + "Strength: " + YELLOW + getStrength() + RESET;

        } else if (getStrength() <= 20 && getStrength() >= 1) {
            strengthStats = CYAN + "Strength: " + RED + getStrength() + RESET;

        }

        return nameStats + "\n" + healthStats + "\n" + strengthStats;

    }

    @Override
    public boolean dodge(ACharacters monster) {
        Random random = new Random();

        int timeLimit = getAgility() / 10;
        int toDodge = random.nextInt(1, 9);

        System.out.println(PURPLE_BOLD_BRIGHT + "Type " + toDodge + " to dodge!" + RESET);

        return dodgeAttack(timeLimit, String.valueOf(toDodge));
    }

    public static boolean dodgeAttack(int timeLimit, String toDodge) {
        Scanner scanner = new Scanner(System.in);

        final boolean[] successfulDodge = {false};
        final boolean[] timeUp = {false};

        Thread countdownThread = new Thread(() -> {
            System.out.println("Dodge in:");
            for (int i = timeLimit; i > 0; i--) {
                System.out.println(i);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                    return;
                }
            }
            timeUp[0] = true;
            System.out.println(RED + "You didn't dodge in time. The monster's attack hit you!" + RESET);
            chillForASecond(1000);
            System.out.println(GRAY + "Press enter to continue" + RESET);

        });


        Thread playerInputThread = new Thread(() -> {
            while (!successfulDodge[0] && !timeUp[0]) {
                if (scanner.hasNextLine()) {
                    if ((scanner.nextLine().contains(toDodge)) && !timeUp[0]) {
                        countdownThread.interrupt();
                        successfulDodge[0] = true;
                        timeUp[0] = true;
                        System.out.println(GREEN + "You successfully dodged the monster's attack!" + RESET);
                        break;
                    }
                }
            }
        });


        countdownThread.start();
        playerInputThread.start();


        try {
            playerInputThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }


        return successfulDodge[0];

    }

    @Override
    public void receiveDamage(ACharacters monster, int damage) {
        Random random = new Random();
        int acquiredStrength = random.nextInt(1, monster.getStrength());
        int damageFromAttack = acquiredStrength * monster.getBaseDamage();

        setHealth(getHealth() - damageFromAttack);
        if (getHealth() > 50) {
            System.out.println(BLUE_BOLD_BRIGHT + "-" + damageFromAttack + " HP" + RESET);

        } else if (getHealth() <= 50 && getHealth() > 20) {
            System.out.println(YELLOW_BOLD_BRIGHT + "-" + damageFromAttack + " HP" + RESET);

        } else if (getHealth() <= 20 && getHealth() >= 1) {
            System.out.println(RED_BOLD_BRIGHT + "-" + damageFromAttack + " HP" + RESET);

        }


    }

    public void gainExperience(int xp) {
        setExperience(getExperience() + xp);
        checkForLevelUp();
    }

    private void checkForLevelUp() {
        int experienceRequiredForNextLevel = calculateExperienceRequiredForNextLevel();

        if (getExperience() >= experienceRequiredForNextLevel) {
            levelUp();
        }
    }

    private void levelUp() {
        setLevel(getLevel() + 1);
        System.out.println(BLACK_BACKGROUND + "                          " + RESET +
                "\n" + BLACK_BACKGROUND + PURPLE_BOLD_BRIGHT + " " + PURPLE_UNDERLINED + "YOU HAVE REACHED LEVEL " + getLevel() + RESET + BLACK_BACKGROUND + " " + RESET +
                "\n" + BLACK_BACKGROUND + "                          " + RESET);

        setExperience(getExperience() - ((getLevel() - 1) * 100));
        setAgility(getAgility() + 10);
        setIntelligence(getIntelligence() + 10);
        setStrength(getStrength() + 10);
        setHealth(100 + (((getLevel() - 1) * 100) / 5));

        System.out.println(PURPLE + "Health: + 20\nAgility: + 10\nStrength: + 10\nIntelligence: + 10");
    }

    private int calculateExperienceRequiredForNextLevel() {
        return getLevel() * 100;
    }

    public int getAvailableLevels() {
        return availableLevels;
    }

    public void setAvailableLevels(int availableLevels) {
        this.availableLevels = availableLevels;
    }

    public void unlockNewLevel() {
        setAvailableLevels(getAvailableLevels() + 1);
    }


}
