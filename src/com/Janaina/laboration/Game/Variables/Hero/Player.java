package com.Janaina.laboration.Game.Variables.Hero;

import com.Janaina.laboration.DBConnection;
import com.Janaina.laboration.Game.Variables.ACharacters;
import com.Janaina.laboration.Resources.Scanners;

import java.util.*;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.PrintHandler.playerSpeaking;
import static com.Janaina.laboration.Resources.PrintHandler.printRed;
import static com.Janaina.laboration.Resources.TextDelay.*;

public class Player extends ACharacters {

    private int availableLevels;
    private int furiesSlayed;
    private int sirensSlayed;
    private int medusasSlayed;
    private int minotaursSlayed;
    private int cerberusSlayed;
    private int typhonSlayed;
    private int roundsFightingTyphon;
    private int id;


    public Player() {
        super("name", 1, 100, 10, 20, 20, 0, 0, 1, "Knife slash", 100);
    }


    public String equippedWeaponName(DBConnection db){
        return db.getEquippedWeaponString("name", this);
    }
    public String equippedWeaponAttackName(DBConnection db){
        return db.getEquippedWeaponString("attackName", this);
    }
    public String equippedWeaponAnimation(DBConnection db){
        return db.getEquippedWeaponString("animation", this);
    }
    public int equippedWeaponStrength(DBConnection db){
        return db.getEquippedWeaponStrength(this);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFuriesSlayed() {
        return furiesSlayed;
    }

    public void setFuriesSlayed(int furiesSlayed) {
        this.furiesSlayed = furiesSlayed;
    }

    public int getSirensSlayed() {
        return sirensSlayed;
    }

    public void setSirensSlayed(int sirensSlayed) {
        this.sirensSlayed = sirensSlayed;
    }

    public int getMedusasSlayed() {
        return medusasSlayed;
    }

    public void setMedusasSlayed(int medusasSlayed) {
        this.medusasSlayed = medusasSlayed;
    }

    public int getMinotaursSlayed() {
        return minotaursSlayed;
    }

    public void setMinotaursSlayed(int minotaursSlayed) {
        this.minotaursSlayed = minotaursSlayed;
    }

    public int getCerberusSlayed() {
        return cerberusSlayed;
    }

    public void setCerberusSlayed(int cerberusSlayed) {
        this.cerberusSlayed = cerberusSlayed;
    }

    public int getTyphonSlayed() {
        return typhonSlayed;
    }

    public void setTyphonSlayed(int typhonSlayed) {
        this.typhonSlayed = typhonSlayed;
    }


    public void act(ACharacters monster, Inventory inventory, Scanners sc, DBConnection db) {

        boolean monsterEncounter = true;

        do {
            System.out.println(monster.getStats(db));
            System.out.println(WHITE_BOLD_BRIGHT + "1. Attack\n2. Flee\n3. Get Stats\n4. Inventory" + RESET);

            switch (sc.scannerNumber()) {

                case 1 -> attack(monster, sc, db);
                case 2 -> {
                    if (flee(monster)) {
                        sleepThread(PURPLE_LIGHT + "Better luck next time" + RESET);
                        suspensefulDots(PURPLE_LIGHT + "." + RESET);
                        db.insertFightLog(this, "Fled", monster);
                        monsterEncounter = false;
                    } else {
                        continue;
                    }
                }
                case 3 -> {
                    System.out.println(getStats(db));
                    sc.pressEnter();
                }
                case 4 -> inventory.playerInventory(this, sc, db);
                default -> printRed("Invalid input");

            }

            if (playerWins(monster, db)) {
                db.insertFightLog(this, "Victory", monster);
                monsterEncounter = false;
            }

            if (!isAlive()) {
                sleepThread(RED + ITALIC + "You were killed by " + monster.getName() + RESET);
                suspensefulDots(RED + "." + RESET);
                sleepThread(RED + BOLD + "Game Over." + RESET);
                db.insertFightLog(this, "Defeat", monster);
                db.updateGameProgress(this);
                db.closeConnection();
                System.exit(0);
                monsterEncounter = false;
            }

        } while (monsterEncounter);

    }

    public void actTyphon(ACharacters monster, Inventory inventory, Scanners sc, DBConnection db) {

        boolean monsterEncounter = true;
        if (Objects.equals(monster.getName(), "Typhon")) {

            roundsFightingTyphon++;
            System.out.println("plus");
        }

        do {
            System.out.println(monster.getStats(db));
            System.out.println(WHITE + BOLD + "1. Attack\n" +
                    GRAY + "2. Flee\n" +
                    WHITE + BOLD + "3. Get Stats\n4. Inventory" + RESET);

            switch (sc.scannerNumber()) {

                case 1 -> attack(monster, sc, db);
                case 2 -> {
                    System.out.println(WHITE + "Sike" + RESET);
                    sleepThread(GRAY + ITALIC + "Such a pussy" + RESET);
                    chillForASecond(1000);
                }
                case 3 -> {
                    System.out.println(getStats(db));
                    sc.pressEnter();
                }
                case 4 -> inventory.playerInventory(this, sc, db);
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


            if (playerWins(monster, db)) {
                db.insertFightLog(this, "Victory", monster);
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
                db.insertFightLog(this, "Defeat", monster);
                db.updateGameProgress(this);
                db.closeConnection();
                System.exit(0);
                monsterEncounter = false;
            }

        } while (monsterEncounter);

    }

    public boolean playerWins(ACharacters monster, DBConnection db) {
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
                case "Medusa" -> medusasSlayed++;
                case "Minotaur" -> minotaursSlayed++;
                case "Cerberus" -> cerberusSlayed ++;
                case "Typhon" -> typhonSlayed++;
            }

            return true;

        } else {
            return false;
        }

    }


    @Override
    public void attack(ACharacters monster, Scanners sc, DBConnection db) {
        boolean glock = false;
        int attack;
        if (db.getCount("id", "specialAttacks", this) >= 1) {
            attack = db.getAttackDamage(sc, this, db);
        } else {
            attack = attackWeapon(sc, db);
            glock = db.usedGlock(this);
        }

        if (glock){
            monster.receiveDamage(this, attack);
        } else if (monster.dodge(this)) {
            System.out.println(RED + monster.getName() + " dodged your attack!");
            chillForASecond(1000);
        } else {
            monster.receiveDamage(this, attack);
        }

        if (monster.isAlive()) {
            monster.attack(this, sc, db);
            if (!dodge(monster)) {
                receiveDamage(monster, 0);
            }
            chillForASecond(200);
        }

    }

    private int attackWeapon(Scanners sc, DBConnection db) {
        System.out.println(YELLOW_BOLD_BRIGHT + getName() + ", press enter to use " + equippedWeaponAttackName(db) + "!" + RESET);
        sc.pressEnterNoText();
        sleepThread(YELLOW + equippedWeaponAnimation(db) + RESET);
        Random random = new Random();

        if (Objects.equals(equippedWeaponName(db), "Glock-19")){
            playerSpeaking(ITALIC + BOLD + "INGEN rör Strängnäs. Strängnäs är MITT område!" + RESET, this);
            return getStrength() + equippedWeaponStrength(db) * 10;
        } else {
            return random.nextInt(getBaseDamage(), (getStrength() + equippedWeaponStrength(db)) * 10);
        }

    }

    private int useSpecialAttack(Scanners sc, DBConnection db) {
        System.out.println(YELLOW_BOLD_BRIGHT + getName() + ", press enter to use " + equippedWeaponAttackName(db) + "!" + RESET);
        sc.pressEnterNoText();
        sleepThread(YELLOW + equippedWeaponAnimation(db) + RESET);
        Random random = new Random();

        if (Objects.equals(equippedWeaponName(db), "Glock-19")){
            playerSpeaking(ITALIC + BOLD + "INGEN rör Strängnäs. Strängnäs är MITT område!" + RESET, this);
            return getStrength() + equippedWeaponStrength(db) * 10;
        } else {
            return random.nextInt(getBaseDamage(), (getStrength() + equippedWeaponStrength(db)) * 10);
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
    public String getStats(DBConnection db) {

        return LILAC + BOLD + UNDERLINED + getName().toUpperCase() + RESET +
                LILAC + ITALIC + "\n✧ Health: " + GREEN_LIGHT + getHealth() + LILAC + ITALIC +
                "\n✧ Equipped Weapon: " + ORANGE + equippedWeaponName(db) + LILAC + ITALIC +
                "\n✧ Min Damage: " + RED + getBaseDamage() + LILAC + ITALIC +
                "\n✧ Max Damage: " + RED + getBaseDamage() * (getStrength() + equippedWeaponStrength(db)) + RESET;

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
