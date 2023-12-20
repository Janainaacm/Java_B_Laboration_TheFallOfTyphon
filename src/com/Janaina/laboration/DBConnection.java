package com.Janaina.laboration;

import com.Janaina.laboration.Game.Introduction;
import com.Janaina.laboration.Game.Variables.ACharacters;
import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Resources.Scanners;


import java.sql.*;
import java.util.Objects;
import java.util.Random;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.PrintHandler.pythiaSpeaking;
import static com.Janaina.laboration.Resources.TextDelay.*;
import static com.Janaina.laboration.Resources.TextDelay.chillForASecond;

public class DBConnection {

    private static final String URL = "jdbc:mariadb://localhost:3306";
    private static final String USER = "janaina";
    private static final String PASSWORD = "q1W\"e3r4";
    private Connection connection;

    public void openConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connected");

        } catch (
                SQLException e) {
            handleSQLException(e);
        }
    }

    public void closeConnection() {

        try {
            if (connection != null) {
                connection.close();
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    public void createTables() {
        String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS thefalloftyphon;";

        String useDatabaseQuery = "USE thefalloftyphon;";

        String createTablePlayer = """
                CREATE TABLE IF NOT EXISTS player (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                        	name TEXT NOT NULL,
                        	lvl INT NOT NULL,
                         	experience INT NOT NULL,
                         	gold INT NOT NULL,
                         	health INT NOT NULL,
                         	agility INT NOT NULL,
                         	strength INT NOT NULL,
                         	intelligence INT NOT NULL,
                         	availableLevels INT NOT NULL,
                         	furiesSlayed INT NOT NULL,
                         	sirensSlayed INT NOT NULL,
                         	medusasSlayed INT NOT NULL,
                         	minotaursSlayed INT NOT NULL,
                         	cerberusSlayed INT NOT NULL,
                         	typhonsSlayed INT NOT NULL
                        );
                """;
        String createTableWeaponsInventory = """
                CREATE TABLE IF NOT EXISTS weaponsInventory (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            name TEXT NOT NULL,
                            attackName TEXT NOT NULL,
                            animation TEXT NOT NULL,
                            strength TINYINT,
                            equipped BIT,
                            playerId INT,
                            CONSTRAINT fk_playerWeaponsInventory
                                FOREIGN KEY (playerId) REFERENCES player(id)
                                    ON DELETE CASCADE ON UPDATE RESTRICT
                        );
                """;
        String createTablePotionsInventory = """
                CREATE TABLE IF NOT EXISTS potionsInventory (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            name TEXT NOT NULL,
                            animation TEXT NOT NULL,
                            strength TINYINT,
                            health TINYINT,
                            agility TINYINT,
                            intelligence TINYINT,
                            amountBought INT,
                            playerId INT,
                            CONSTRAINT fk_playerPotionsInventory
                                FOREIGN KEY (playerId) REFERENCES player (id)
                                    ON DELETE CASCADE ON UPDATE RESTRICT
                        );
                """;

        String createTableWeaponsShop = """
                CREATE TABLE IF NOT EXISTS weaponsShop (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            name TEXT NOT NULL,
                            strength TINYINT NOT NULL,
                            price SMALLINT NOT NULL,
                            bought BIT NOT NULL,
                            playerId INT,
                            hidden INT,
                            CONSTRAINT fk_playerWeaponsShop
                                FOREIGN KEY (playerId) REFERENCES player(id)
                                    ON DELETE CASCADE ON UPDATE RESTRICT
                        );
                """;

        String createTablePotionsShop = """
                CREATE TABLE IF NOT EXISTS potionsShop (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            name TEXT NOT NULL,
                            price INT NOT NULL,
                            strength TINYINT,
                            health TINYINT,
                            agility TINYINT,
                            intelligence TINYINT,
                            playerId INT,
                            CONSTRAINT fk_playerPotionsShop
                                FOREIGN KEY (playerId) REFERENCES player (id)
                                    ON DELETE CASCADE ON UPDATE RESTRICT
                        );
                """;

        String createTableWeapons = """
                CREATE TABLE IF NOT EXISTS weapons (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            name TEXT NOT NULL,
                            attackName TEXT NOT NULL,
                            animation TEXT NOT NULL,
                            price SMALLINT NOT NULL,
                            strength TINYINT NOT NULL
                        );
                """;
        String createTablePotions = """
                CREATE TABLE IF NOT EXISTS potions (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            name TEXT NOT NULL,
                            animation TEXT NOT NULL,
                            price SMALLINT NOT NULL,
                            strength TINYINT,
                            health TINYINT,
                            agility TINYINT,
                            intelligence TINYINT
                        );
                """;

        String createTableSpecialAttacks = """
                CREATE TABLE IF NOT EXISTS specialAttacks (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            name TEXT NOT NULL,
                            animation TEXT NOT NULL,
                            strength TINYINT NOT NULL,
                            playerId INT NOT NULL,
                            CONSTRAINT fk_playerSpecialAttacks
                                FOREIGN KEY (playerId) REFERENCES player (id)
                                    ON DELETE CASCADE ON UPDATE RESTRICT
                        );
                """;

        String createTableFight = """
                CREATE TABLE IF NOT EXISTS fight (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            winner TEXT NOT NULL,
                            timeOfFight nvarchar(50) NOT NULL,
                            monsterName TEXT NOT NULL,
                            playerId INT NOT NULL,
                            CONSTRAINT fk_playerFightLog
                                FOREIGN KEY (playerId) REFERENCES player (id)
                                    ON DELETE CASCADE ON UPDATE RESTRICT
                        );
                """;

        try {
            Statement statement = connection.createStatement();

            // Create database
            statement.executeUpdate(createDatabaseQuery);

            // Use database
            statement.executeUpdate(useDatabaseQuery);

            statement.executeUpdate(createTablePlayer);
            statement.executeUpdate(createTablePotionsInventory);
            statement.executeUpdate(createTableWeaponsInventory);
            statement.executeUpdate(createTableWeapons);
            statement.executeUpdate(createTablePotions);
            statement.executeUpdate(createTableSpecialAttacks);
            statement.executeUpdate(createTableFight);
            statement.executeUpdate(createTablePotionsShop);
            statement.executeUpdate(createTableWeaponsShop);


        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    public void addItemsToTables(Player player) {
        String q1 = """
                INSERT INTO weapons (name, attackName, animation, price, strength)
                SELECT 'Frostbite Dagger', 'Frostbite Strike', '+—⟪═════>', 150, 2
                WHERE NOT EXISTS (
                SELECT 1
                FROM weapons
                WHERE name = 'Frostbite Dagger');
                """;
        String q2 = """
                INSERT INTO weapons (name, attackName, animation, price, strength)
                SELECT 'Shadowfang Blade', 'Dark Eclipse', '▭▭ι═══════>', 160, 3
                WHERE NOT EXISTS (
                    SELECT 1
                    FROM weapons
                    WHERE name = 'Shadowfang Blade'
                );
                """;
        String q3 = """
                INSERT INTO weapons (name, attackName, animation, price, strength)
                SELECT 'Cursed Scythe', 'Reapers Grasp', '▬ι══════>', 170, 4
                WHERE NOT EXISTS (
                SELECT 1
                FROM weapons
                WHERE name = 'Cursed Scythe');
                """;
        String q4 = """
                INSERT INTO weapons (name, attackName, animation, price, strength)
                SELECT 'Oceanic Trident', 'Abyssal Torrent', '——————∈ ࿐ ࿔', 200, 5
                WHERE NOT EXISTS (
                SELECT 1
                FROM weapons
                WHERE name = 'Oceanic Trident');
                """;
        String q5 = """
                INSERT INTO weapons (name, attackName, animation, price, strength)
                SELECT 'Phoenix Bow','Flaming Arrow Barrage', 'ˎ-·˚ ༘₊· ͟͟͞͞➳', 250, 7
                WHERE NOT EXISTS (
                SELECT 1
                FROM weapons
                WHERE name = 'Phoenix Bow');
                """;
        String q6 = """
                INSERT INTO weapons (name, attackName, animation, price, strength)
                SELECT 'Thunderstrike Hammer', 'Lightning Hammerblow', '⌁˚⊹｡ﾟϟﾟ.｡⊹˚⌁', 300, 8
                WHERE NOT EXISTS (
                SELECT 1
                FROM weapons
                WHERE name = 'Thunderstrike Hammer');
                """;
        String q7 = """
                INSERT INTO weapons (name, attackName, animation, price, strength)
                SELECT 'Glock-19', 'Kurdiska räven', 'ᡕᠵ᠊ᡃ࡚ࠢ࠘ ⸝່ࠡࠣ᠊߯᠆ࠣ࠘ᡁࠣ࠘᠊᠊ࠢ࠘𐡏  𖣓 𖣨', 1000, 100
                WHERE NOT EXISTS (
                SELECT 1
                FROM weapons
                WHERE name = 'Glock-19');
                """;

        String q8 = """
                INSERT INTO weapons (name, attackName, animation, price, strength)
                SELECT 'knife', 'Lethal Lunge', '▬ι=>', 0, 1
                WHERE NOT EXISTS (
                SELECT 1
                FROM weapons
                WHERE name = 'knife');
                """;

        String p1 = """
                INSERT INTO potions (name, animation, price, intelligence)
                SELECT 'Intelligence Potion', '❥⁺⋆༺.*₊˚࿐༅', 50, 5
                WHERE NOT EXISTS (
                SELECT 1
                FROM potions
                WHERE name = 'Intelligence Potion');
                """;
        String p2 = """
                INSERT INTO potions (name, animation, price, strength)
                SELECT 'Strength Potion', '❥⁺⋆༺.*₊˚࿐༅', 50, 1
                WHERE NOT EXISTS (
                SELECT 1
                FROM potions
                WHERE name = 'Strength Potion');
                """;
        String p3 = """
                INSERT INTO potions (name, animation, price, agility)
                SELECT 'Flexibility Potion', '❥⁺⋆༺.*₊˚࿐༅', 50, 2
                WHERE NOT EXISTS (
                SELECT 1
                FROM potions
                WHERE name = 'Flexibility Potion');
                """;
        String p4 = """
                INSERT INTO potions (name, animation, price, health)
                SELECT 'Small Health Potion', '⋆｡୭⋆⁺.⋆｡˙⊹༺⋆｡˙⊹⋆', 30, 50
                WHERE NOT EXISTS (
                SELECT 1
                FROM potions
                WHERE name = 'Small Health Potion');
                """;
        String p5 = """
                INSERT INTO potions (name, animation, price, health)
                SELECT 'Large Health Potion', '⋆｡୭⋆⁺.⋆｡˙⊹༺⋆｡˙⊹⋆', 50, 100
                WHERE NOT EXISTS (
                SELECT 1
                FROM potions
                WHERE name = 'Large Health Potion');
                """;


        String queryWeapons = """
                INSERT INTO weaponsShop (name, strength, price, hidden, bought, playerId)
                        SELECT name, strength, price, 0, 0, ?
                        FROM weapons
                        WHERE NOT EXISTS (
                            SELECT 1
                            FROM weaponsShop
                            WHERE playerId = ?
                        );
                           
                """;

        String queryWeaponsInventory = """
                INSERT INTO weaponsInventory (name, attackName, animation, strength, equipped, playerId)
                SELECT name, attackName, animation, strength, 1, ?
                FROM weapons
                WHERE name = 'knife'
                AND NOT EXISTS (
                SELECT 1
                FROM weaponsInventory
                WHERE playerId = ?
                );
                           
                """;

        String queryUpdateWeaponsShopHidden = """
                UPDATE weaponsShop
                SET hidden = 1
                WHERE playerId = ?
                AND name = 'Glock-19'
                ;
                """;

        String queryUpdateWeaponsShopDelete = """
                DELETE FROM weaponsShop
                WHERE name = 'knife'
                AND playerId = 1
                ;
                """;

        String queryPotions = """
                INSERT INTO potionsShop (name, strength, agility, intelligence, health, price, playerId)
                       SELECT name, strength, agility, intelligence, health, price, ?
                       FROM potions
                       WHERE NOT EXISTS (
                       SELECT 1
                       FROM potionsShop
                       WHERE playerId = ?);
                """;

        try {

            Statement statement = connection.createStatement();
            statement.executeUpdate(q1);
            statement.executeUpdate(q2);
            statement.executeUpdate(q3);
            statement.executeUpdate(q4);
            statement.executeUpdate(q5);
            statement.executeUpdate(q6);
            statement.executeUpdate(q7);
            statement.executeUpdate(q8);
            statement.executeUpdate(p1);
            statement.executeUpdate(p2);
            statement.executeUpdate(p3);
            statement.executeUpdate(p4);
            statement.executeUpdate(p5);

            PreparedStatement preparedStatement1 = connection.prepareStatement(queryWeapons);
            preparedStatement1.setInt(1, player.getId());
            preparedStatement1.setInt(2, player.getId());
            preparedStatement1.executeUpdate();

            PreparedStatement preparedStatement2 = connection.prepareStatement(queryPotions);
            preparedStatement2.setInt(1, player.getId());
            preparedStatement2.setInt(2, player.getId());
            preparedStatement2.executeUpdate();

            PreparedStatement preparedStatement3 = connection.prepareStatement(queryWeaponsInventory);
            preparedStatement3.setInt(1, player.getId());
            preparedStatement3.setInt(2, player.getId());
            preparedStatement3.executeUpdate();

            PreparedStatement preparedStatement4 = connection.prepareStatement(queryUpdateWeaponsShopHidden);
            preparedStatement4.setInt(1, player.getId());
            preparedStatement4.executeUpdate();

            PreparedStatement preparedStatement5 = connection.prepareStatement(queryUpdateWeaponsShopDelete);
            preparedStatement5.setInt(1, player.getId());
            preparedStatement5.executeUpdate();


        } catch (SQLException e) {
            handleSQLException(e);
        }
    }


    public int getIntFromDb(String columnName, String tableName, String conditionColumn, int conditionValue, Player player) {
        String query = "SELECT " + columnName + " FROM " + tableName + " WHERE " + conditionColumn + " = ? AND playerId = ?";
        int number = 0;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, conditionValue);
            preparedStatement.setInt(2, player.getId());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                number = resultSet.getInt(columnName);
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return number;
    }

    public String getStringFromDb(String columnName, String tableName, String conditionColumn, int conditionValue, Player player) {
        String query = "SELECT ? FROM ? WHERE ? = ? AND playerId = ?";
        String text = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, columnName);
            preparedStatement.setString(2, tableName);
            preparedStatement.setString(3, conditionColumn);
            preparedStatement.setInt(4, conditionValue);
            preparedStatement.setInt(5, player.getId());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                text = resultSet.getString(columnName);
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return text;
    }

    public void addToWeaponsInventory(String weaponName, Player player) {

        String queryGet = """
                INSERT INTO weaponsInventory (name, attackName, animation, strength, equipped, playerId)
                SELECT name, attackName, animation, strength, 0, ?
                FROM weapons
                WHERE name = ?
                """;

        String queryDelete = """
                UPDATE weaponsShop
                SET bought = 1
                WHERE name = ?
                AND playerId = ?
                ;
                """;

        try (PreparedStatement selectStatement = connection.prepareStatement(queryGet)) {
            selectStatement.setInt(1, player.getId());
            selectStatement.setString(2, weaponName);

            selectStatement.executeUpdate();


            try (PreparedStatement deleteStatement = connection.prepareStatement(queryDelete)) {
                deleteStatement.setString(1, weaponName);
                deleteStatement.setInt(2, player.getId());

                deleteStatement.executeUpdate();
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    public void addToPotionsInventory(String potionName, Player player) {

        String queryCheckIfExists = """
                SELECT id FROM potionsInventory
                WHERE name = ?
                AND playerId = ?
                ;
                """;


        String queryAddToInventory = """
                INSERT INTO potionsInventory (name, animation, strength, health, agility, intelligence, amountBought, playerId)
                               SELECT name, animation, strength, health, agility, intelligence, 1, ?
                               FROM potions
                               WHERE name = ?
                               ;
                """;

        String queryAddToAmount = """
                UPDATE potionsInventory
                SET amountBought = amountBought + 1
                WHERE name = ?
                AND playerId = ?;
                """;

        try {
            PreparedStatement checkIfExists = connection.prepareStatement(queryCheckIfExists);
            checkIfExists.setString(1, potionName);
            checkIfExists.setInt(2, player.getId());

            ResultSet resultSet = checkIfExists.executeQuery();
            //int count = resultSet.getInt("doesPotionExist");

            if (resultSet.next()) {
                PreparedStatement preparedStatement = connection.prepareStatement(queryAddToAmount);
                preparedStatement.setString(1, potionName);
                preparedStatement.setInt(2, player.getId());

                preparedStatement.executeUpdate();

            } else {
                PreparedStatement checkStatement = connection.prepareStatement(queryAddToInventory);
                checkStatement.setInt(1, player.getId());
                checkStatement.setString(2, potionName);

                checkStatement.executeUpdate();

            }

        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    public int getCount(String column, String table, Player player) {
        int count = 0;

        try {
            String query = "SELECT COUNT(" + column + ") FROM " + table + " WHERE playerId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, player.getId());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }
        return count;
    }

    public void equipWeapon(String equippedWeaponName, String weaponName, Player player) {
        int equippedWeaponId = getIdFromWeaponsInventory(equippedWeaponName, player);
        int wantToEquipId = getIdFromWeaponsInventory(weaponName, player);
        String queryUpdate1 = "UPDATE weaponsInventory SET equipped = 0 WHERE id = ? AND playerId = ?";
        String queryUpdate2 = "UPDATE weaponsInventory SET equipped = 1 WHERE id = ? AND playerId = ?";

        try {
            PreparedStatement updateStatement = connection.prepareStatement(queryUpdate1);
            updateStatement.setInt(1, equippedWeaponId);
            updateStatement.setInt(2, player.getId());

            PreparedStatement updateStatement2 = connection.prepareStatement(queryUpdate2);
            updateStatement2.setInt(1, wantToEquipId);
            updateStatement2.setInt(2, player.getId());

            updateStatement.executeUpdate();
            updateStatement2.executeUpdate();

        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    public int getEquippedWeaponStrength(Player player) {
        int strength = 0;
        try {
            String query = "SELECT strength FROM weaponsInventory WHERE equipped = 1 AND playerId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, player.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                strength = resultSet.getInt("strength");
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return strength;
    }

    public void setEquippedWeaponStrength(Player player, int strength) {
        try {
            String queryUpdate = "UPDATE weaponsInventory SET strength = ? WHERE equipped = 1 AND playerId = ? ";
            PreparedStatement updateStatement = connection.prepareStatement(queryUpdate);
            updateStatement.setInt(1, strength);
            updateStatement.setInt(2, player.getId());

            updateStatement.executeUpdate();

        } catch (SQLException e) {
            handleSQLException(e);
        }

    }

    private String equippedWeaponName(Player player) {
        String name = null;
        try {
            String query = "SELECT name FROM weaponsInventory WHERE equipped = 1 AND playerId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, player.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                name = resultSet.getString("name");
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return name;
    }

    private String equippedWeaponAttackName(Player player) {
        String attackName = null;
        try {
            String query = "SELECT attackName FROM weaponsInventory WHERE equipped = 1 AND playerId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, player.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                attackName = resultSet.getString("attackName");
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return attackName;
    }

    private String equippedWeaponAnimation(Player player) {
        String animation = null;
        try {
            String query = "SELECT animation FROM weaponsInventory WHERE equipped = 1 AND playerId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, player.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                animation = resultSet.getString("animation");
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return animation;
    }

    public String getEquippedWeaponString(String what, Player player) {

        if (Objects.equals(what, "name")) {
            return equippedWeaponName(player);
        } else if (Objects.equals(what, "attackName")) {
            return equippedWeaponAttackName(player);
        } else if (Objects.equals(what, "animation")) {
            return equippedWeaponAnimation(player);
        } else {
            return null;
        }
    }

    public void updateGlock(Player player) {
        try {
            int id = getIdFromWeaponsShop("Glock-19", player);

            String queryCheck = "SELECT * FROM weaponsShop WHERE id = ? AND playerId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(queryCheck);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(1, player.getId());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String query = "UPDATE weaponsShop SET hidden = 0 WHERE id = ? AND playerId = ?";
                    PreparedStatement updateStatement = connection.prepareStatement(query);
                    updateStatement.setInt(1, id);
                    updateStatement.setInt(1, player.getId());

                    updateStatement.executeUpdate();
                }
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    public void weaponsInventory(Player player, Scanners sc) {

        while (true) {
            System.out.println(BLACK_BACKGROUND + RED + BOLD + "       Weapons:       " + RESET);
            String equippedWeaponName = equippedWeaponName(player);

            System.out.println(RED_DARK + "Equipped weapon: " + equippedWeaponName + RESET);

            String weaponName = selectFromWeaponsInventory(sc, player);

            if (Objects.equals(weaponName, "exit")) {
                break;
            }

            equipWeapon(equippedWeaponName, weaponName, player);

            String defaultAttack = equippedWeaponAttackName(player);

            player.setDefaultAttack(defaultAttack);
            sleepThread(player.getName() + " equipped " + weaponName + "!");
            chillForASecond(500);
            System.out.println(GRAY + ITALIC + "Strength + " + getEquippedWeaponStrength(player) + RESET);


        }
    }

    private String selectFromWeaponsInventory(Scanners sc, Player player) {

        try {
            int isInventoryEmpty = getCount("id", "weaponsInventory", player);
            if (isInventoryEmpty <= 1) {
                System.out.println(GRAY + ITALIC + "This inventory is empty" + RESET);
                return "exit";
            } else {

                return displayWeaponList(player, sc);

            }

        } catch (SQLException e) {
            handleSQLException(e);
            return null;
        }
    }

    private String displayWeaponList(Player player, Scanners sc) throws SQLException {
        int counter = 1;

        String query = "SELECT name, strength FROM weaponsInventory WHERE playerId = ? AND equipped = 0";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, player.getId());
        try (ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                System.out.println(RED + BOLD + counter + ". " + resultSet.getString("name") + RESET + YELLOW_DARK + ITALIC + " + " + resultSet.getInt("strength") + " Strength" + RESET);
                counter++;
            }
            System.out.println(RED_LILDARKER + BOLD + "0. Go back\n" + RESET);
            System.out.println(GRAY + ITALIC + "Enter a number:" + RESET);
            int counterMax = counter;

            int choice = sc.scannerNumber();

            if (choice == 0) {
                return "exit";
            } else if (choice >= 1 && choice <= counterMax) {
                return getSelectedName(resultSet, choice);
            } else {
                System.out.println(BLACK + "Invalid choice, please try again" + RESET);
                return displayWeaponList(player, sc);
            }
        }
    }

    private String getSelectedName(ResultSet resultSet, int choice) throws SQLException {
        resultSet.beforeFirst();
        int counter = 1;
        while (resultSet.next()) {
            if (counter == choice) {
                return resultSet.getString("name");
            }
            counter++;
        }
        return null;
    }

    public void shopWeapons(Player player, Scanners sc) {
        String query = "SELECT COUNT(id) AS weaponsCount FROM weaponsShop WHERE playerId = ? AND hidden = 0 AND bought = 0";
        int weaponsAvailableInShop = 0;

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, player.getId());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    weaponsAvailableInShop = resultSet.getInt("weaponsCount");
                }
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }


        if (weaponsAvailableInShop == 0) {
            System.out.println(GRAY + ITALIC + "You have bought all available weapons");
        } else {

            int total = 0;

            while (true) {
                System.out.println(BLACK_BACKGROUND + BOLD + RED + " Available weapons: " + RESET + "\n"
                        + BLACK_BACKGROUND + RED_LILDARKER + " Gold: " + YELLOW_DARK + player.getGold() + " " + RESET);

                String weaponName = chooseWeaponFromShop(sc, player);

                if (Objects.equals(weaponName, "exit")) {
                    break;
                }

                int id = getIdFromWeaponsShop(weaponName, player);
                int weaponPrice = getIntFromDb("price", "weaponsShop", "id", id, player);

                if (total + weaponPrice <= player.getGold()) {
                    total += weaponPrice;
                    player.setGold(player.getGold() - weaponPrice);
                    System.out.println(RED_DARK + BOLD + "-" + weaponPrice + " Gold");
                    System.out.println(WHITE + weaponName + " has been added to your inventory." + RESET);
                    addToWeaponsInventory(weaponName, player);
                    sc.pressEnter();
                } else {
                    System.out.println(RED + "Insufficient funds to buy " + weaponName + RESET);
                    sc.pressEnter();
                }
            }
        }
    }

    private String chooseWeaponFromShop(Scanners sc, Player player) {
        String selectedName = null;
        int counter = 1;

        try {
            String query = "SELECT name, price, strength FROM weaponsShop WHERE playerId = ? AND hidden = 0 AND bought = 0";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, player.getId());
                try (ResultSet resultSet = statement.executeQuery()) {

                    while (resultSet.next()) {
                        System.out.println(RED + BOLD + counter + ". " + resultSet.getString("name") +
                                YELLOW_LIGHT + " - $" + resultSet.getInt("price") +
                                "\n" + BLACK + ITALIC + "Strength: +" + resultSet.getInt("strength") + RESET);
                        counter++;
                    }
                    int counterMax = counter;
                    System.out.println(BLACK_BACKGROUND + RED_DARK + BOLD + "0. Go back" + RESET);
                    chillForASecond(500);
                    System.out.println(WHITE + ITALIC + "Enter the number of the weapon you would like to buy:" + RESET);


                    int choice = sc.scannerNumber();

                    if (choice == 0) {
                        return "exit";
                    } else if (choice >= 1 && choice <= counterMax) {
                        selectedName = getSelectedName(resultSet, choice);
                    } else {
                        System.out.println(BLACK + "Invalid choice, please try again" + RESET);
                    }
                }
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return selectedName;
    }

    private int getIdFromWeaponsShop(String weaponName, Player player) {
        int id = 0;
        try {
            String query = """
                    SELECT id FROM weaponsShop
                    WHERE name = ?
                    AND playerId = ?
                    """;

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, weaponName);
                preparedStatement.setInt(2, player.getId());

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        id = resultSet.getInt("id");
                    }
                }
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }
        return id;
    }

    private int getIdFromWeaponsInventory(String weaponName, Player player) {
        int id = 0;
        try {
            String query = """
                    SELECT id FROM weaponsInventory
                    WHERE name = ?
                    AND playerId = ?
                    """;

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, weaponName);
                preparedStatement.setInt(2, player.getId());


                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        id = resultSet.getInt("id");
                    }
                }
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }
        return id;
    }

    private int getIdFromPotionsInventory(String potionName, Player player) {
        int id = 0;
        try {
            String query = """
                    SELECT id FROM potionsInventory
                    WHERE name = ?
                    AND playerId = ?
                    """;

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, potionName);
                preparedStatement.setInt(2, player.getId());


                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        id = resultSet.getInt("id");
                    }
                }
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }
        return id;
    }

    private int getIdFromPotionShop(String potionName, Player player) {
        int id = 0;
        try {
            String query = """
                    SELECT id FROM potionsShop
                    WHERE name = ?
                    AND playerId = ?
                    """;

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, potionName);
                preparedStatement.setInt(2, player.getId());


                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        id = resultSet.getInt("id");
                    }
                }
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }
        return id;
    }

    public void potionsInventory(Scanners sc, Player player) {

        while (true) {
            System.out.println(BLACK_BACKGROUND + PINK_LIGHT + BOLD + "    Potions:    " + RESET);
            try {
                String query = "SELECT * FROM potionsInventory WHERE playerId = ? AND amountBought > 0";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setInt(1, player.getId());

                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            String potionName = selectPotionFromInventory(sc, player);

                            if (Objects.equals(potionName, "exit")) {
                                break;
                            } else {

                                int potionId = getIdFromPotionsInventory(potionName, player);

                                System.out.println(PINK + ITALIC + player.getName() + " drank " + potionName + "!");
                                drinkPotion(potionId, player);
                                sc.pressEnter();
                            }

                        } else {
                            System.out.println(GRAY + ITALIC + "This inventory is empty" + RESET);
                            break;
                        }
                    }
                }
            } catch (SQLException e) {
                handleSQLException(e);
            }
        }
    }

    private String selectPotionFromInventory(Scanners sc, Player player) {
        int counter = 1;
        String selectedName = null;

        try {
            String query = "SELECT * FROM potionsInventory WHERE amountBought > 0 AND playerId = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, player.getId());
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        if (resultSet.getInt("strength") > 0) {
                            System.out.println(PINK_PASTEL + BOLD + counter + ". " + resultSet.getString("name") + RESET + PINK_DARK + ITALIC + " - Strength: " + resultSet.getInt("strength") + "\nAmount: " + resultSet.getInt("amountBought") + RESET);
                        } else if (resultSet.getInt("intelligence") > 0) {
                            System.out.println(PINK_PASTEL + BOLD + counter + ". " + resultSet.getString("name") + RESET + PINK_DARK + ITALIC + " - Intelligence: " + resultSet.getInt("intelligence") + "\nAmount: " + resultSet.getInt("amountBought") + RESET);
                        } else if (resultSet.getInt("agility") > 0) {
                            System.out.println(PINK_PASTEL + BOLD + counter + ". " + resultSet.getString("name") + RESET + PINK_DARK + ITALIC + " - Agility: " + resultSet.getInt("agility") + "\nAmount: " + resultSet.getInt("amountBought") + RESET);
                        } else if (resultSet.getInt("health") > 0) {
                            System.out.println(PINK_PASTEL + BOLD + counter + ". " + resultSet.getString("name") + RESET + PINK_DARK + ITALIC + " - Health: " + resultSet.getInt("health") + "\nAmount: " + resultSet.getInt("amountBought") + RESET);
                        }
                        counter++;
                    }
                    System.out.println(PINK + ITALIC + "0. Go back" + RESET);

                    System.out.println(WHITE + "Enter number:" + RESET);

                    int choice = sc.scannerNumber();

                    if (choice == 0) {
                        return "exit";
                    } else if (choice > counter || choice < 0) {
                        System.out.println(BLACK + "Invalid choice, please try again" + RESET);
                    } else {
                        resultSet.beforeFirst();
                        counter = 1;

                        while (resultSet.next()) {
                            if (counter == choice) {
                                selectedName = resultSet.getString("name");
                                break;
                            }
                            counter++;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return selectedName;
    }

    private void drinkPotion(int drinkPotionId, Player player) {
        try {
            String queryUpdate = "UPDATE potionsInventory SET amountBought = amountBought - 1 WHERE id = ? AND playerId = ?";
            try (PreparedStatement updateStatement = connection.prepareStatement(queryUpdate)) {
                updateStatement.setInt(1, drinkPotionId);
                updateStatement.setInt(2, player.getId());
                updateStatement.executeUpdate();
            }

            String query = "SELECT * FROM potionsInventory WHERE id = ? AND playerId = ?";
            try (PreparedStatement selectStatement = connection.prepareStatement(query)) {
                selectStatement.setInt(1, drinkPotionId);
                selectStatement.setInt(2, player.getId());

                try (ResultSet resultSet = selectStatement.executeQuery()) {
                    if (resultSet.next()) {
                        if (resultSet.getInt("strength") > 1) {
                            player.setStrength(player.getStrength() + resultSet.getInt("strength"));
                            System.out.println(GRAY + ITALIC + "Strength +" + resultSet.getInt("strength") + RESET);
                        } else if (resultSet.getInt("intelligence") > 0) {
                            player.setIntelligence(player.getIntelligence() + resultSet.getInt("intelligence"));
                            System.out.println(GRAY + ITALIC + "Intelligence +" + resultSet.getInt("intelligence") + RESET);
                        } else if (resultSet.getInt("agility") > 0) {
                            player.setAgility(player.getAgility() + resultSet.getInt("agility"));
                            System.out.println(GRAY + ITALIC + "Agility +" + resultSet.getInt("agility") + RESET);
                        } else if (resultSet.getInt("health") > 0) {
                            player.setHealth(player.getHealth() + resultSet.getInt("health"));
                            System.out.println(GRAY + ITALIC + "Health +" + resultSet.getInt("health") + RESET);

                        }
                    }
                }
            }

            if (player.getHealth() > 100) {
                player.setHealth(100);
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    public void shopPotions(Player player, Scanners sc) {
        int total = 0;

        while (true) {
            System.out.println(BLACK_BACKGROUND + BOLD + PINK + "     Potions:     " + RESET + "\n"
                    + PINK + ITALIC + "Gold: " + YELLOW_DARK + player.getGold() + RESET);

            String potionName = choosePotionFromShop(sc, player);

            if (Objects.equals(potionName, "exit")) {
                break;
            }

            int id = getIdFromPotionShop(potionName, player);
            int potionPrice = getIntFromDb("price", "potionsShop", "id", id, player);

            if (total + potionPrice <= player.getGold()) {
                total += potionPrice;
                player.setGold(player.getGold() - potionPrice);
                System.out.println(RED_DARK + BOLD + "-" + potionPrice + " Gold");
                System.out.println(WHITE + potionName + " has been added to your inventory." + RESET);
                addToPotionsInventory(potionName, player);
                sc.pressEnter();
            } else {
                System.out.println(RED + "Insufficient funds to buy " + potionName + RESET);
                sc.pressEnter();
            }
        }
    }


    private String choosePotionFromShop(Scanners sc, Player player) {
        String selectedName = null;
        int counter = 1;

        try {
            String query = "SELECT * FROM potionsShop WHERE playerId = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, player.getId());
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        if (resultSet.getInt("strength") > 0) {
                            System.out.println(PINK_PASTEL + BOLD + counter + ". " + resultSet.getString("name") + RESET + YELLOW_LIGHT + ITALIC + " - " + resultSet.getInt("price") + "$" + PINK_DARK + "   \n\uD80C\uDFF2ּ Strength: +" + resultSet.getInt("strength") + RESET);
                        } else if (resultSet.getInt("intelligence") > 0) {
                            System.out.println(PINK_PASTEL + BOLD + counter + ". " + resultSet.getString("name") + RESET + YELLOW_LIGHT + ITALIC + " - " + resultSet.getInt("price") + "$" + PINK_DARK + "   \n\uD80C\uDFF2ּ Intelligence: +" + resultSet.getInt("intelligence") + RESET);
                        } else if (resultSet.getInt("agility") > 0) {
                            System.out.println(PINK_PASTEL + BOLD + counter + ". " + resultSet.getString("name") + RESET + YELLOW_LIGHT + ITALIC + " - " + resultSet.getInt("price") + "$" + PINK_DARK + "   \n\uD80C\uDFF2ּ Agility: +" + resultSet.getInt("agility") + RESET);
                        } else if (resultSet.getInt("health") > 0) {
                            System.out.println(PINK_PASTEL + BOLD + counter + ". " + resultSet.getString("name") + RESET + YELLOW_LIGHT + ITALIC + " - " + resultSet.getInt("price") + "$" + PINK_DARK + "   \n\uD80C\uDFF2ּ Health: +" + resultSet.getInt("health") + RESET);
                        }
                        counter++;
                    }
                    int counterMax = counter;
                    System.out.println(BLACK_BACKGROUND + BOLD + PINK + "0. Go back" + RESET);
                    chillForASecond(500);
                    System.out.println(GRAY + ITALIC + "\nEnter the number of the potion you would like to buy:" + RESET);

                    int choice = sc.scannerNumber();

                    if (choice == 0) {
                        return "exit";
                    } else if (choice >= 1 && choice <= counterMax) {
                        selectedName = getSelectedName(resultSet, choice);
                    } else {
                        System.out.println(BLACK + "Invalid choice, please try again" + RESET);
                    }
                }
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return selectedName;
    }


    private void players(Scanners sc, Player player) {
        int counter = 1;

        try (Statement statement = connection.createStatement()) {
            String query = "SELECT id, name FROM player";

            try (ResultSet resultSet = statement.executeQuery(query)) {
                System.out.println(PURPLE + "Select your player:" + RESET);

                while (resultSet.next()) {
                    System.out.println(PURPLE_LIGHT + counter + ". " + resultSet.getString("name"));
                    counter++;
                }

                System.out.println("0. New Player" + RESET);

                int choice = sc.scannerNumber();

                if (choice < 0 || choice > counter - 1) {
                    System.out.println("Invalid choice, please try again");
                    players(sc, player);
                    return;
                }

                if (choice == 0) {
                    createNewPlayer(sc, player);
                } else {
                    loadExistingPlayer(choice, player);
                }
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    private void loadExistingPlayer(int choice, Player player) {
        try (Statement statement = connection.createStatement()) {
            String query = "SELECT id FROM player";

            try (ResultSet resultSet = statement.executeQuery(query)) {
                resultSet.absolute(choice);

                int playerId = resultSet.getInt("id");

                chosenPlayer(playerId, player);
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    public void choosePlayer(Scanners sc, Player player) {
        while (true) {
            try {
                String checkIfExists = "SELECT * FROM player";
                try (PreparedStatement checkStatement = connection.prepareStatement(checkIfExists)) {
                    try (ResultSet resultSet = checkStatement.executeQuery()) {
                        if (resultSet.next()) {
                            players(sc, player);

                        } else {
                            createNewPlayer(sc, player);
                        }
                        break;
                    }
                }
            } catch (SQLException e) {
                handleSQLException(e);
            }
        }

    }

    private void createNewPlayer(Scanners sc, Player player) {
        Introduction intro = new Introduction();

        suspensefulDots(".");

        pythiaSpeaking("""
                Hello there,\s
                My name is Pythia, but you might formally know me as The Oracle of Delphi.\s
                What is your name?""");

        while (true) {
            String chosenName = sc.scannerText();
            if (checkIfNameExists(chosenName)) {
                System.out.println(RED_DARK + BOLD + "This name is already in use, try another." + RESET);
            } else {
                player.setName(chosenName);
                break;
            }
        }

        try {
            String query = "INSERT INTO player (name, lvl, experience, gold, health, agility, strength, intelligence, availableLevels, furiesSlayed, sirensSlayed, medusasSlayed, minotaursSlayed, cerberusSlayed, typhonsSlayed) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, player.getName());
                preparedStatement.setInt(2, player.getLevel());
                preparedStatement.setInt(3, player.getExperience());
                preparedStatement.setInt(4, player.getGold());
                preparedStatement.setInt(5, player.getHealth());
                preparedStatement.setInt(6, player.getAgility());
                preparedStatement.setInt(7, player.getStrength());
                preparedStatement.setInt(8, player.getIntelligence());
                preparedStatement.setInt(9, player.getAvailableLevels());
                preparedStatement.setInt(10, player.getFuriesSlayed());
                preparedStatement.setInt(11, player.getSirensSlayed());
                preparedStatement.setInt(12, player.getMedusasSlayed());
                preparedStatement.setInt(13, player.getMinotaursSlayed());
                preparedStatement.setInt(14, player.getCerberusSlayed());
                preparedStatement.setInt(15, player.getTyphonSlayed());

                preparedStatement.executeUpdate();

                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        player.setId(generatedKeys.getInt(1));
                    } else {
                        System.out.println("Error retrieving player ID");

                    }
                }

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        chillForASecond(100);
        intro.introduction(player, sc);
    }

    private boolean checkIfNameExists(String name) {
        try {
            String query = "SELECT * FROM player WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    private void chosenPlayer(int playerId, Player player) {

        try {
            String query = "SELECT * FROM player WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, playerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                player.setId(resultSet.getInt("id"));
                player.setName(resultSet.getString("name"));
                player.setLevel(resultSet.getInt("lvl"));
                player.setExperience(resultSet.getInt("experience"));
                player.setGold(resultSet.getInt("gold"));
                player.setHealth(resultSet.getInt("health"));
                player.setAgility(resultSet.getInt("agility"));
                player.setStrength(resultSet.getInt("strength"));
                player.setIntelligence(resultSet.getInt("intelligence"));
                player.setAvailableLevels(resultSet.getInt("availableLevels"));
                player.setFuriesSlayed(resultSet.getInt("furiesSlayed"));
                player.setSirensSlayed(resultSet.getInt("sirensSlayed"));
                player.setMedusasSlayed(resultSet.getInt("medusasSlayed"));
                player.setMinotaursSlayed(resultSet.getInt("minotaursSlayed"));
                player.setCerberusSlayed(resultSet.getInt("cerberusSlayed"));
                player.setTyphonSlayed(resultSet.getInt("typhonsSlayed"));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        pythiaSpeaking("Welcome back " + player.getName() + ", I am overjoyed for your return");

    }

    public void insertFightLog(Player player, String winner, ACharacters monster) {
        try {
            String query = "INSERT INTO fight (winner, timeOfFight, monsterName, playerId) VALUES (?, NOW(), ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, winner); //Victory, Defeat, Fled
                preparedStatement.setString(2, monster.getName());
                preparedStatement.setInt(3, player.getId());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showFightLog(Player player) {
        try {
            String query = "SELECT * FROM fight WHERE playerId = ? ORDER BY timeOfFight";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, player.getId());

                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    while (resultSet.next()) {
                        if (Objects.equals(resultSet.getString("winner"), "Victory")) {
                            System.out.println(GREEN_LIGHT + ITALIC + resultSet.getString("winner") + RESET + WHITE + ITALIC + " against " + resultSet.getString("monsterName") + " - " + GRAY + resultSet.getString("timeOfFight") + RESET);
                        } else if (Objects.equals(resultSet.getString("winner"), "Defeat")) {
                            System.out.println(RED + ITALIC + resultSet.getString("winner") + RESET + WHITE + ITALIC + " against " + resultSet.getString("monsterName") + " - " + GRAY + resultSet.getString("timeOfFight") + RESET);
                        } else if (Objects.equals(resultSet.getString("winner"), "Fled")) {
                            System.out.println(YELLOW + ITALIC + resultSet.getString("winner") + RESET + WHITE + ITALIC + " against " + resultSet.getString("monsterName") + " - " + GRAY + resultSet.getString("timeOfFight") + RESET);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void specialAttackList(Player player) {
        System.out.println(LILAC + ITALIC + "✧ Special Attacks:");

        try {
            String query = "SELECT name, strength FROM specialAttacks WHERE playerId = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, player.getId());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println(RESET + PINK_LIGHT + BOLD  + "  \uD80C\uDFF2 " + resultSet.getString("name") + RESET + PINK_PASTEL + ITALIC + "\n      Damage: " + RED + resultSet.getInt("strength") + RESET);
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    private int showSpecialAttacks(Scanners sc, Player player) {
        int id = 0;

        while (true) {
            int counter = 1;

            try {
                String query = "SELECT id, name, strength FROM specialAttacks WHERE playerId = ?;";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, player.getId());

                ResultSet resultSet = preparedStatement.executeQuery();

                System.out.println(ORANGE + BOLD + "Special Attacks:" + RESET);
                while (resultSet.next()) {
                    System.out.println(YELLOW + BOLD + counter + ". " + resultSet.getString("name") + YELLOW_DARK + ITALIC + "\nDamage: " + resultSet.getInt("strength") + RESET);
                    counter++;
                }
                System.out.println(YELLOW + BOLD + "0. Use " + player.equippedWeaponName(this) + RESET);

                int choice = sc.scannerNumber();

                if (choice == 0) {
                    return 0;
                } else if (choice > counter || choice < 0) {
                    System.out.println(BLACK + "Invalid choice, please try again" + RESET);
                    continue;
                } else {
                    resultSet.beforeFirst();
                    counter = 1;

                    while (resultSet.next()) {
                        if (counter == choice) {
                            id = resultSet.getInt("id");
                            break;
                        }
                        counter++;
                    }
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            return id;
        }
    }

    public boolean usedGlock(Player player) {
        return Objects.equals(getEquippedWeaponString("name", player), "Glock-19");
    }

    public void addSpecialAttack(Player player, String monster) {
        try {
            String query = null;

            if (Objects.equals(monster, "Siren")) {
                query = """
                        INSERT specialAttacks (name, animation, strength, playerId)
                        SELECT 'Sirens Song', '⋆｡୭⋆⁺.⋆｡˙⊹༺⋆｡˙⊹⋆', 20, ?
                        WHERE NOT EXISTS (
                        SELECT 1 FROM specialAttacks
                        WHERE name = 'Sirens Song'
                        AND playerId = ?);
                        """;
            } else if (Objects.equals(monster, "Medusa")) {
                query = """
                        INSERT specialAttacks (name, animation, strength, playerId)
                        SELECT 'Petrifying gaze', '⋆｡୭⋆⁺.⋆｡˙⊹༺⋆｡˙⊹⋆', 30, ?
                        WHERE NOT EXISTS (
                        SELECT 1 FROM specialAttacks
                        WHERE name = 'Petrifying gaze'
                        AND playerId = ?);
                        """;
            } else if (Objects.equals(monster, "Cerberus")) {
                query = """
                        INSERT specialAttacks (name, animation, strength, playerId)
                        SELECT 'Poisonous fang', '༺ ˖࣪ ∗ ਏਓ ∗ ˖࣪ ༻', 35, ?
                        WHERE NOT EXISTS (
                        SELECT 1 FROM specialAttacks
                        WHERE name = 'Poisonous fang'
                        AND playerId = ?);
                        """;
            } else if (Objects.equals(monster, "Typhon")) {
                query = """
                        INSERT specialAttacks (name, animation, strength, playerId)
                        SELECT 'Hellfire', '☄༄˚ ༘ ⋆｡☄༄', 40, ?
                        WHERE NOT EXISTS (
                        SELECT 1 FROM specialAttacks
                        WHERE name = 'Hellfire'
                        AND playerId = ?);
                        """;
            }

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, player.getId());
            preparedStatement.setInt(2, player.getId());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                chillForASecond(1500);
                sleepThread(PURPLE_ISH + "You have unlocked a new special attack." + RESET);
                chillForASecond(1500);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getAttackDamage(Scanners sc, Player player, DBConnection db) {
        Random random = new Random();
        int id = showSpecialAttacks(sc, player);

        if (id == 0) {
            return random.nextInt(player.getBaseDamage(), (player.getStrength() + player.equippedWeaponStrength(db)) * 10);
        } else {
            try (PreparedStatement updateStatement = connection.prepareStatement("SELECT strength, animation FROM specialAttacks WHERE id = ? AND playerId = ?;")) {
                updateStatement.setInt(1, id);
                updateStatement.setInt(2, player.getId());

                try (ResultSet resultSet = updateStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("strength");
                    }
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            return 0;
        }
    }

    public void updateGameProgress(Player player) {
        try {
            String query = "UPDATE player SET lvl = ?, experience = ?, gold = ?, health = ?, agility = ?, strength = ?, intelligence = ?, availableLevels = ?, furiesSlayed = ?, sirensSlayed = ?, medusasSlayed = ?, minotaursSlayed = ?, cerberusSlayed = ?, typhonsSlayed = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, player.getLevel());
            preparedStatement.setInt(2, player.getExperience());
            preparedStatement.setInt(3, player.getGold());
            preparedStatement.setInt(4, player.getHealth());
            preparedStatement.setInt(5, player.getAgility());
            preparedStatement.setInt(6, player.getStrength());
            preparedStatement.setInt(7, player.getIntelligence());
            preparedStatement.setInt(8, player.getAvailableLevels());
            preparedStatement.setInt(9, player.getFuriesSlayed());
            preparedStatement.setInt(10, player.getSirensSlayed());
            preparedStatement.setInt(11, player.getMedusasSlayed());
            preparedStatement.setInt(12, player.getMinotaursSlayed());
            preparedStatement.setInt(13, player.getCerberusSlayed());
            preparedStatement.setInt(14, player.getTyphonSlayed());
            preparedStatement.setInt(15, player.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleSQLException(SQLException e) {
        System.out.println("SQLException: " + e.getMessage());
        System.out.println("SQLState: " + e.getSQLState());
        System.out.println("VendorError: " + e.getErrorCode());

    }
}

