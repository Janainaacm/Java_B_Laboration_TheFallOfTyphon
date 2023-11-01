package com.Janaina.laboration.Game.Variables.Hero;

import com.Janaina.laboration.Game.Shop.ShopCategories.Attacks;
import com.Janaina.laboration.Game.Shop.ShopCategories.Weapons;
import com.Janaina.laboration.Game.Shop.ShopProducts;
import com.Janaina.laboration.Game.Variables.Characters;

import java.util.*;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.Scanners.*;
import static com.Janaina.laboration.Resources.TextDelay.*;

public class Player extends Characters {

    public List<Attacks> specialAttackList;
    private final int resetHealth = 100;
    public ShopProducts equippedWeapon;

    public Player() {
        super("name", 2, 100, 10, 20, 20, 0, 0, 1, "Knife slash");
        specialAttackList = new ArrayList<>();
        this.equippedWeapon = new ShopProducts("knife", 0, 2, 0, 0, 0);

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

    public void displaySpecialAttacks() {
        System.out.println("Special Attacks:");
        for (int i = 0; i < specialAttackList.size(); i++) {
            System.out.println((i + 1) + ". " + specialAttackList.get(i).getName());
        }
    }

    public Attacks getSpecialAttack(int index) {
        if (index >= 0 && index < specialAttackList.size()) {
            return specialAttackList.get(index);
        }
        return null;
    }

    public void act(Characters monster, Inventory Inventory) {

        boolean monsterEncounter = true;

        do {
            System.out.println(monster.getStats());
            System.out.println(WHITE_BOLD_BRIGHT + "1. Attack\n2. Flee\n3. Get Stats\n4. Inventory" + RESET);

            switch (scannerNumber()) {

                case 1 -> attack(monster);
                case 2 -> {
                    if (flee(monster)){
                        monsterEncounter = false;
                    } else {
                        continue;
                    }
                }
                case 3 -> {
                    System.out.println(getStats());
                    pressEnter();
                }
                case 4 -> Inventory.playerInventory();

            }

            if (!monster.isAlive()) {
                playerWins(monster);
                monsterEncounter = false;
            } else if (!isAlive()) {
                System.out.println("Game over");
            }



        } while (monsterEncounter);

        pressEnter();
    }

    public void playerWins(Characters monster){
        System.out.println(PURPLE_BOLD_BRIGHT + "You managed to slay " + monster.getName() + "!" + RESET);
        chillForASecond(1000);
        System.out.println(YELLOW_BOLD + "+ " + monster.getGold() + "Gold");
        chillForASecond(1000);
        System.out.println("+ " + monster.getExperience() + " XP" + RESET);
        chillForASecond(1000);

        gainExperience(monster.getExperience());


    }


    @Override
    public void attack(Characters monster) {
        //if (!specialAttackList.isEmpty()) {
        //            //Create switch to chose from attacks
        //            System.out.println("poop");
        //        } else {
        //
        //
        //        }


        System.out.println(YELLOW_BOLD_BRIGHT + getName() + ", press enter to use " + getDefaultAttack() + "!" + RESET);
        pressEnterToAttack();
        sleepThread(YELLOW + "▭▭ι═══════ﺤ\n" + RESET);

        if (monster.dodge(this)){
            System.out.println(RED + monster.getName() + " dodged your attack!");
            chillForASecond(1000);
        } else {
            monster.receiveDamage(this);
        }

        if (monster.isAlive()) {
            monster.attack(this);
            if (!dodge(monster)) {
                receiveDamage(monster);
            }
            chillForASecond(200);
        }



    }

    @Override
    public boolean flee(Characters monster) {

        System.out.println(PURPLE + "Lets see if you are lucky enough to escape the " + monster.getName() + RESET);
        suspensefulDots(PURPLE + "." + RESET);

        if (canPlayerFlee(monster)){
            System.out.println(GREEN + "You managed to escape!" + RESET);
            return true;
        }else {
            System.out.println(RED + "Oh no, you were caught!" + RESET);
            dodge(monster);
            return false;
        }


    }

    public boolean canPlayerFlee(Characters monster) {
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
        if (getHealth() >50){
            healthStats = CYAN + "Health: " + GREEN + getHealth() + RESET;

        }else if (getHealth() <= 50 && getHealth() > 20){
            healthStats = CYAN + "Health: " + YELLOW + getHealth() + RESET;

        }else if (getHealth() <= 20 && getHealth() >= 1) {
            healthStats = CYAN + "Health: " + RED + getHealth() + RESET;

        }
        if (getStrength() >50){
            strengthStats = CYAN + "Strength: " + GREEN + getStrength() + RESET;

        }else if (getStrength() <= 50 && getStrength() > 20){
            strengthStats = CYAN + "Strength: " + YELLOW + getStrength() + RESET;

        }else if (getStrength() <= 20 && getStrength() >= 1) {
            strengthStats = CYAN + "Strength: " + RED + getStrength() + RESET;

        }

        return nameStats + "\n" + healthStats + "\n" + strengthStats;

    }

    @Override
    public boolean dodge(Characters monster) {
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
            System.out.println(RED + "You didn't dodge in time. The monster's attack hit you!" + RESET);
            chillForASecond(1000);
            System.out.println(GRAY + "Press enter to continue" + RESET);
            timeUp[0] = true;

        });


        Thread playerInputThread = new Thread(() -> {
            while (!successfulDodge[0] && !timeUp[0]) {
                if (scanner.hasNextLine()) {
                    if (Objects.equals(scanner.nextLine(), toDodge)) {
                        successfulDodge[0] = true;
                        timeUp[0] = true;
                        countdownThread.interrupt(); // Stop the countdown
                        System.out.println(GREEN + "You successfully dodged the monster's attack!" + RESET);
                        break; // Exit the loop
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
    public void receiveDamage(Characters monster) {
        Random random = new Random();
        int acquiredStrength = random.nextInt(1, monster.getStrength());
        int damageFromAttack = acquiredStrength * monster.getBaseDamage();

        setHealth(getHealth() - damageFromAttack);
        if (getHealth() >50){
            System.out.println(BLUE_BOLD_BRIGHT + "-" + damageFromAttack + " HP" + RESET);

        }else if (getHealth() <= 50 && getHealth() > 20){
            System.out.println(YELLOW_BOLD_BRIGHT + "-" + damageFromAttack + " HP" + RESET);

        }else if (getHealth() <= 20 && getHealth() >= 1) {
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
                "\n" + BLACK_BACKGROUND  + "                          " + RESET);

        setExperience(getExperience() - ((getLevel() - 1) * 100));
        setAgility(getAgility() + 10);
        setIntelligence(getIntelligence() + 10);
        setStrength(getStrength() + 10);
        setHealth(100 + (((getLevel() - 1) * 100) / 5));

        System.out.println(PURPLE + "Health: + 20\nAgility: + 10\nStrength: + 10\nIntelligence: + 10");
        pressEnter();
    }

    private int calculateExperienceRequiredForNextLevel() {
        return getLevel() * 100;
    }


}
