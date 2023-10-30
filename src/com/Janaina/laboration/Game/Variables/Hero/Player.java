package com.Janaina.laboration.Game.Variables.Hero;

import com.Janaina.laboration.Game.Shop.ShopCategories.Armor.Armor;
import com.Janaina.laboration.Game.Shop.ShopCategories.Books.Attacks;
import com.Janaina.laboration.Game.Shop.ShopCategories.Weapons.Weapons;
import com.Janaina.laboration.Game.Variables.Characters;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.Scanners.*;
import static com.Janaina.laboration.Resources.TextDelay.*;

public class Player extends Characters {

    public List<Attacks> specialAttackList;
    public List<Armor> equipmentList;
    public List<Weapons> weaponsList;

    public Player(String name, int strength, int health, int baseDamage, int agility, int intelligence, int gold, int experience, int level) {
        super("name", 2, 100, 15, 20, 20, 0, 0, 1, "Lethal Lunge");
    }

    public Player() {
        super("name", 10, 100, 5, 20, 20, 0, 0, 1, "Lethal Lunge");
    }


    public List<Attacks> getSpecialAttackList() {
        return specialAttackList;
    }

    public void setSpecialAttackList(List<Attacks> specialAttackList) {
        this.specialAttackList = specialAttackList;
    }

    public List<Armor> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<Armor> equipmentList) {
        this.equipmentList = equipmentList;
    }

    public List<Weapons> getWeaponsList() {
        return weaponsList;
    }

    public void setWeaponsList(List<Weapons> weaponsList) {
        this.weaponsList = weaponsList;
    }

    public void act(Characters monster, Inventory Inventory) {

        System.out.println(PURPLE + "Oh no! a monster!!" + RESET);

        boolean monsterEncounter = true;
        do {
            System.out.println(monster.getStats());

            switch (scannerNumber()) {
                //1. Attack
                //2. Flee
                //3. Inventory

                case 1 -> attack(monster);
                case 2 -> {
                    if (flee(monster)){
                        monsterEncounter = false;
                    } else {
                        continue;
                    }
                }
                case 3 -> Inventory.playerInventory();
            }

            if (!monster.isAlive()) {
                System.out.println("you win slay");
            } else if (!isAlive()) {
                System.out.println("Game over");
            }


        } while (monsterEncounter);


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


        System.out.println(YELLOW + getName() + " used " + getDefaultAttack() + "!" + RESET);
        sleepThread(YELLOW + "▭▭ι═══════ﺤ" + RESET);
        pressEnterToAttack();
        monster.dodge(this);

    }

    @Override
    public boolean flee(Characters monster) {

        System.out.println(PURPLE + "Lets see if you are lucky enough to escape the " + monster.getName() + RESET);
        suspensefulDots(PURPLE + "." + RESET);

        if (canPlayerFlee(monster)){
            System.out.println(PURPLE + "You managed to escape!" + RESET);
            return true;
        }else {
            System.out.println(PURPLE + "Oh no, you were caught!" + RESET);
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
    public void dodge(Characters monster) {
        chillForASecond(200);
        System.out.println(RED_BOLD_BRIGHT + monster.getName() + " used " + monster.getDefaultAttack() + " on you!" + RESET);
        sleepThread(RED + "ﮩ٨ـﮩﮩ٨ـﮩ٨ـﮩﮩ٨" + RESET);
        
        if (didDodge()){

        } else {

            receiveDamage(monster);
        }

    }

    @Override
    public String getStats() {
        return null;
    }

    @Override
    public boolean didDodge() {
        Random random = new Random();

        int timeLimit = getAgility() / 10;
        int toDodge = random.nextInt(1, 9);

        System.out.println(PURPLE + "Type " + toDodge + " to dodge!" + RESET);

        return dodgeAttack(timeLimit, toDodge);
    }

    public static boolean dodgeAttack(int timeLimit, int toDodge) {
        final boolean[] successfulDodge = {false};
        final boolean[] timeUp = {false};

        Thread countdownThread = new Thread(() -> {
            System.out.println("Dodge in:");
            for (int i = timeLimit; i > 0; i--) {
                System.out.println(i);
                try {
                    Thread.sleep(1000); // Sleep for 1 second
                } catch (InterruptedException e) {
                    // Handle interruption gracefully
                    return;
                }
            }
            System.out.println(RED + "You didn't dodge in time. The monster's attack hit you!" + GRAY + "\n Enter 0 to continue" + RESET);
            timeUp[0] = true;

        });


        Thread playerInputThread = new Thread(() -> {
            while (!successfulDodge[0] && !timeUp[0]) {
                if (Objects.equals(scannerNumber(), toDodge)) {
                    successfulDodge[0] = true;
                    timeUp[0] = true;
                    countdownThread.interrupt(); // Stop the countdown
                    System.out.println("You successfully dodged the monster's attack!");
                    break; // Exit the loop
                }

            }
        });


        countdownThread.start();
        playerInputThread.start();


        // Wait for the playerInputThread to finish before exiting
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
            System.out.println(BLACK_BACKGROUND + " " + WHITE_BOLD_BRIGHT + "-" + damageFromAttack + " HP" + RESET + BLACK_BACKGROUND + " " + RESET);

        }else if (getHealth() <= 50 && getHealth() > 20){
            System.out.println(BLACK_BACKGROUND + " " + YELLOW_BOLD_BRIGHT + "-" + damageFromAttack + " HP" + RESET + BLACK_BACKGROUND + " " + RESET);

        }else if (getHealth() <= 20 && getHealth() >= 1) {
            System.out.println(BLACK_BACKGROUND + " " + RED_BOLD_BRIGHT + "-" + damageFromAttack + " HP" + RESET + BLACK_BACKGROUND + " " + RESET);

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
        setExperience(getExperience() - (getLevel() * 100));
        setLevel(getLevel() + 1);
        setAgility(getAgility() + 5);
        setIntelligence(getIntelligence() + 5);
        setStrength(getStrength() + 5);

    }

    private int calculateExperienceRequiredForNextLevel() {
        return getLevel() * 100;
    }


}
