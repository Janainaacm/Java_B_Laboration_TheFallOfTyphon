package com.Janaina.laboration.Game.Variables.Hero;

import com.Janaina.laboration.Game.Shop.ShopProducts;
import com.Janaina.laboration.Game.Variables.ACharacters;
import com.Janaina.laboration.Resources.Scanners;

import java.util.*;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.PrintHandler.printRed;
import static com.Janaina.laboration.Resources.TextDelay.*;

public class Player extends ACharacters {

    public List<Attacks> specialAttackList;
    public ShopProducts equippedWeapon;
    private int availableLevels;
    public int furiesSlayed = 0;
    public int sirensSlayed = 0;
    public int medusaSlayed = 0;
    public int minotaurSlayed = 0;
    public int cerberusSlayed = 0;
    public int typhonSlayed = 0;
    private int roundsFightingTyphon = 0;

    public Player() {
        super("name", 1, 100, 10, 20, 20, 0, 0, 1, "Knife slash", 100);
        specialAttackList = new ArrayList<>();
        this.equippedWeapon = new ShopProducts("knife", "Lethal Lunge", "▬ι=ﺤ", 0, 1, 0, 0, 0);
        this.availableLevels = 1;
    }


    public ShopProducts getEquippedWeapon() {
        return equippedWeapon;
    }

    public void setEquippedWeapon(ShopProducts equippedWeapon) {
        this.equippedWeapon = equippedWeapon;
    }


    private void addSpecialAttack(Attacks attack) {
        specialAttackList.add(attack);
    }

    public void specialAttackSirens(){
        addSpecialAttack(new Attacks("Sirens Song", 20));

    }

    public void specialAttackMedusa(){
        addSpecialAttack(new Attacks("Medusa's head", 30));

    }
    public void specialAttackCerberus(){
        addSpecialAttack(new Attacks("Poisonous Fang", 30));
    }


    public void act(ACharacters monster, Inventory inventory, Scanners sc) {

        boolean monsterEncounter = true;

        do {
            System.out.println(monster.getStats());
            System.out.println(WHITE_BOLD_BRIGHT + "1. Attack\n2. Flee\n3. Get Stats\n4. inventory" + RESET);

            switch (sc.scannerNumber()) {

                case 1 -> attack(monster, sc);
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
                    sc.pressEnter();
                }
                case 4 -> inventory.playerInventory(this, sc);
                default -> printRed("Invalid input");

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

    public void actTyphon(ACharacters monster, Inventory inventory, Scanners sc) {

        boolean monsterEncounter = true;
        if (Objects.equals(monster.getName(), "Typhon")) {

            roundsFightingTyphon++;
            System.out.println("plus");
        }

        do {
            System.out.println(monster.getStats());
            System.out.println(WHITE + BOLD + "1. Attack\n" +
                    GRAY + "2. Flee\n" +
                    WHITE + BOLD + "3. Get Stats\n4. inventory" + RESET);

            switch (sc.scannerNumber()) {

                case 1 -> attack(monster, sc);
                case 2 -> {
                    System.out.println(WHITE + "Sike" + RESET);
                    sleepThread(GRAY + ITALIC + "Such a pussy" + RESET);
                    chillForASecond(1000);
                }
                case 3 -> {
                    System.out.println(getStats());
                    sc.pressEnter();
                }
                case 4 -> inventory.playerInventory(this, sc);
                default -> printRed("Invalid input");


            }

            if (Objects.equals(monster.getName(), "Typhon")){

                if (roundsFightingTyphon == 1){
                    if (monster.getHealth() <= 150){
                        System.out.println("1");
                        monster.revive();
                        monsterEncounter = false;
                    }
                }

                if (roundsFightingTyphon == 2){
                    if (monster.getHealth() <= 100){
                        System.out.println("2");
                        monster.revive();
                        monsterEncounter = false;
                    }
                }
            }


            if (playerWins(monster)) {
                if (Objects.equals(monster.getName(), "Typhon")) {
                    roundsFightingTyphon = 0;
                }
                monster.revive();
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
            setGold(getGold() + monster.getGold());
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
    public void attack(ACharacters monster, Scanners sc) {

        int attack;
        if (!specialAttackList.isEmpty()) {
            attack = chosenAttack(sc);
        } else {
            attack = attackWeapon(sc);
        }

        if (monster.dodge(this)) {
            System.out.println(RED + monster.getName() + " dodged your attack!");
            chillForASecond(1000);
        } else {
            monster.receiveDamage(this, attack);
        }

        if (monster.isAlive()) {
            monster.attack(this, sc);
            if (!dodge(monster)) {
                receiveDamage(monster, 0);
            }
            chillForASecond(200);
        }

    }

    private int attackWeapon(Scanners sc) {
        System.out.println(YELLOW_BOLD_BRIGHT + getName() + ", press enter to use " + equippedWeapon.getAttackName() + "!" + RESET);
        sc.pressEnterNoText();
        sleepThread(YELLOW + equippedWeapon.getAnimation() + RESET);
        Random random = new Random();

        return random.nextInt(getBaseDamage(), (getStrength() + equippedWeapon.getStrength()) * 10);

    }


    private int chosenAttack(Scanners sc) {
        System.out.println(YELLOW + ITALIC + "0. Use " + equippedWeapon.getName() + RESET);
        System.out.println(ORANGE + BOLD + "Special Attacks:" + RESET);
        for (int i = 0; i < specialAttackList.size(); i++) {
            System.out.println(ORANGE + ITALIC + (i + 1) + ". " + specialAttackList.get(i).getName() + GRAY + ITALIC + "\nDamage: " + RED + specialAttackList.get(i).getDamage() + RESET);

        }
        int choice = sc.scannerNumber();

        if (choice == 0) {
            return attackWeapon(sc);
        }

        if (choice < 1 || choice > specialAttackList.size()) {
            System.out.println(BLACK + "Invalid choice, please try again" + RESET);
            return chosenAttack(sc);

        } else {

            Attacks selectedAttack = specialAttackList.get(choice - 1);
            System.out.println(YELLOW_BOLD_BRIGHT + getName() + ", press enter to use " + selectedAttack.getName() + "!" + RESET);
            sc.pressEnterNoText();
            sleepThread(YELLOW + "⋆｡୭⋆⁺.⋆｡˙⊹༺⋆｡˙⊹⋆\n" + RESET);
            return selectedAttack.getDamage();
        }

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
        int escapeChance = getIntelligence() - monster.getIntelligence();
        int randomValue = random.nextInt(1, 100);


        return randomValue < escapeChance;
    }


    @Override
    public String getStats() {

        return LILAC + BOLD + UNDERLINED + getName().toUpperCase() + RESET +
                LILAC + ITALIC + "\n✧ Health: " + GREEN_LIGHT + getHealth() + LILAC + ITALIC +
                "\n✧ Equipped Weapon: " + ORANGE + equippedWeapon.getName() + LILAC + ITALIC +
                "\n✧ Min Damage: " + RED + getBaseDamage() + LILAC + ITALIC +
                "\n✧ Max Damage: " + RED + getBaseDamage() * (getStrength() + equippedWeapon.getStrength()) + RESET;

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
        setStrength(getStrength() + 1);

        System.out.println(PURPLE + "Agility: + 10\nStrength: + 1\nBase Damage: + 10\nIntelligence: + 10");
    }

    private int calculateExperienceRequiredForNextLevel() {
        return getLevel() * 100;
    }

    public int getAvailableLevels() {
        return availableLevels;
    }

    public void setAvailableLevels(int availableLevels) {
        if (availableLevels > this.availableLevels) {
            this.availableLevels = availableLevels;
        }
    }




}
