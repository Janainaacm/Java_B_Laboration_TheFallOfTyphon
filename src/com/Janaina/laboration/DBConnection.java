package com.Janaina.laboration;

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
                         	typhonsSlayed INT NOT NULL,
                         	weaponsInventoryId INT NOT NULL,
                         	potionsInventoryId INT NOT NULL
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
                            playerId INT NOT NULL,
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
                            playerId INT NOT NULL,
                            CONSTRAINT fk_playerPotionsInventory
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
                            strength TINYINT NOT NULL,
                            bought BIT NOT NULL,
                            hidden BIT,
                            weaponsInventoryId INT NOT NULL,
                            CONSTRAINT fk_gameWeapons
                                FOREIGN KEY (weaponsInventoryId) REFERENCES weaponsInventory(id)
                                ON DELETE CASCADE ON UPDATE RESTRICT
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
                            intelligence TINYINT,
                            potionsInventoryId INT NOT NULL,
                            CONSTRAINT fk_gamePotions
                                FOREIGN KEY (potionsInventoryId) REFERENCES potionsInventory (id)
                                ON DELETE CASCADE ON UPDATE RESTRICT
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

        String addPlayerFk = """
                ALTER TABLE player
                ADD CONSTRAINT fk_weaponsInventory
                FOREIGN KEY IF NOT EXISTS (weaponsInventoryId) REFERENCES weaponsInventory(id)
                ON DELETE CASCADE ON UPDATE RESTRICT,
                ADD CONSTRAINT fk_potionsInventory
                FOREIGN KEY IF NOT EXISTS (potionsInventoryId) REFERENCES potionsInventory(id)
                ON DELETE CASCADE ON UPDATE RESTRICT;
                """;

        try {
            Statement statement = connection.createStatement();

            // Create database
            statement.executeUpdate(createDatabaseQuery);

            // Use database
            statement.executeUpdate(useDatabaseQuery);

            // Create tables
            statement.executeUpdate(createTablePlayer);  // Use execute() instead of executeUpdate()
            statement.executeUpdate(createTablePotionsInventory);
            statement.executeUpdate(createTableWeaponsInventory);
            statement.executeUpdate(createTableWeapons);
            statement.executeUpdate(createTablePotions);
            statement.executeUpdate(createTableSpecialAttacks);
            statement.executeUpdate(createTableFight);

            if (checkFk()){
                statement.executeUpdate(addPlayerFk);
            }

            System.out.println("Tables created successfully.");

        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    private boolean checkFk() throws SQLException {
        Statement statement = connection.createStatement();
        String query = """
            SELECT COUNT(*)
            FROM information_schema.REFERENTIAL_CONSTRAINTS
            WHERE CONSTRAINT_NAME = 'fk_weaponsInventory'
            AND TABLE_NAME = 'player';
            """;

        try (ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count == 0;
            } else {
                throw new SQLException("Error executing the query");
            }
        }
    }

    public void addItemsToTables(Player player) {
        String queryW1 = """
                INSERT INTO weapons (name, attackName, animation, price, strength, bought, weaponsInventoryId)
                SELECT 'Frostbite Dagger', 'Frostbite Strike', '+—⟪═════>', 150, 2, 0, ?
                WHERE NOT EXISTS (
                SELECT 1
                FROM weapons
                WHERE name = 'Frostbite Dagger' AND weaponsInventoryId = ?);
                """;

        String queryW2 = """
                INSERT INTO weapons (name, attackName, animation, price, strength, bought, weaponsInventoryId)
                SELECT 'Shadowfang Blade', 'Dark Eclipse', '▭▭ι═══════>', 160, 3, 0, ?
                WHERE NOT EXISTS (
                SELECT 1
                FROM weapons
                WHERE name = 'Shadowfang Blade' AND weaponsInventoryId = ?);
                """;

        String queryW3 = """
                INSERT INTO weapons (name, attackName, animation, price, strength, bought, weaponsInventoryId)
                SELECT 'Cursed Scythe', 'Reaper's Grasp', '▬ι══════>', 170, 4, 0, ?
                WHERE NOT EXISTS (
                SELECT 1
                FROM weapons
                WHERE name = 'Cursed Scythe' AND weaponsInventoryId = ?);
                """;

        String queryW4 = """
                INSERT INTO weapons (name, attackName, animation, price, strength, bought, weaponsInventoryId)
                SELECT 'Oceanic Trident', 'Abyssal Torrent', '——————∈ ࿐ ࿔', 200, 5, 0, ?
                WHERE NOT EXISTS (
                SELECT 1
                FROM weapons
                WHERE name = 'Oceanic Trident' AND weaponsInventoryId = ?);
                """;

        String queryW5 = """
                INSERT INTO weapons (name, attackName, animation, price, strength, bought, weaponsInventoryId)
                SELECT 'Phoenix Bow','Flaming Arrow Barrage', 'ˎ-·˚ ༘₊· ͟͟͞͞➳', 250, 7, 0, ?
                WHERE NOT EXISTS (
                SELECT 1
                FROM weapons
                WHERE name = 'Phoenix Bow' AND weaponsInventoryId = ?);
                """;

        String queryW6 = """
                INSERT INTO weapons (name, attackName, animation, price, strength, bought, weaponsInventoryId)
                SELECT 'Thunderstrike Hammer', 'Lightning Hammerblow', '⌁˚⊹｡ﾟϟﾟ.｡⊹˚⌁', 300, 8, 0, ?
                WHERE NOT EXISTS (
                SELECT 1
                FROM weapons
                WHERE name = 'Thunderstrike Hammer' AND weaponsInventoryId = ?);
                """;

        String queryW7 = """
                INSERT INTO weapons (name, attackName, animation, price, strength, bought, weaponsInventoryId)
                SELECT 'Glock-19', 'Kurdiska räven', 'ᡕᠵ᠊ᡃ࡚ࠢ࠘ ⸝່ࠡࠣ᠊߯᠆ࠣ࠘ᡁࠣ࠘᠊᠊ࠢ࠘𐡏  𖣓 𖣨', 1000, 100, 0, 1, ?
                WHERE NOT EXISTS (
                SELECT 1
                FROM weapons
                WHERE name = 'Glock-19' AND weaponsInventoryId = ?);
                """;

        String queryP1 = """
                INSERT INTO potions (name, animation, price, health, potionsInventoryId)
                SELECT 'Intelligence Potion', '❥⁺⋆༺.*₊˚࿐༅', 50, 5, ?
                WHERE NOT EXISTS (
                SELECT 1
                FROM weapons
                WHERE name = 'Intelligence Potion' AND potionsInventoryId = ?);
                """;

        String queryP2 = """
                INSERT INTO potions (name, animation, price, health, potionsInventoryId)
                SELECT 'Strength Potion', '❥⁺⋆༺.*₊˚࿐༅', 50, 1, ?
                WHERE NOT EXISTS (
                SELECT 1
                FROM weapons
                WHERE name = 'Strength Potion' AND potionsInventoryId = ?);
                """;

        String queryP3 = """
                INSERT INTO potions (name, animation, price, health, potionsInventoryId)
                SELECT 'Flexibility Potion', '❥⁺⋆༺.*₊˚࿐༅', 50, 2, ?
                WHERE NOT EXISTS (
                SELECT 1
                FROM weapons
                WHERE name = 'Flexibility Potion' AND potionsInventoryId = ?);
                """;

        String queryP4 = """
                INSERT INTO potions (name, animation, price, health, potionsInventoryId)
                SELECT 'Large Health Potion', '⋆｡୭⋆⁺.⋆｡˙⊹༺⋆｡˙⊹⋆', 50, 100, ?
                WHERE NOT EXISTS (
                SELECT 1
                FROM weapons
                WHERE name = 'Large Health Potion' AND potionsInventoryId = ?);
                """;

        String queryP5 = """
                INSERT INTO potions (name, animation, price, health, potionsInventoryId)
                SELECT 'Small Health Potion', '⋆｡୭⋆⁺.⋆｡˙⊹༺⋆｡˙⊹⋆', 30, 50, ?
                WHERE NOT EXISTS (
                SELECT 1
                FROM weapons
                WHERE name = 'Small Health Potion' AND potionsInventoryId = ?);
                """;

        String queryI = """
                INSERT INTO weaponsInventory (name, attackName, animation, strength, equipped, playerId)
                SELECT 'knife', 'Lethal Lunge', '▬ι=>', 1, 1, ?
                WHERE NOT EXISTS (
                SELECT 1
                FROM weaponsInventory
                WHERE name = 'knife' AND playerId = ?);
                """;


        try {
            PreparedStatement preparedStatementW1 = connection.prepareStatement(queryW1);
            preparedStatementW1.setInt(1, player.getWeaponsInventoryId());
            preparedStatementW1.setInt(2, player.getWeaponsInventoryId());

            PreparedStatement preparedStatementW2 = connection.prepareStatement(queryW2);
            preparedStatementW2.setInt(1, player.getWeaponsInventoryId());
            preparedStatementW2.setInt(2, player.getWeaponsInventoryId());

            PreparedStatement preparedStatementW3 = connection.prepareStatement(queryW3);
            preparedStatementW3.setInt(1, player.getWeaponsInventoryId());
            preparedStatementW3.setInt(2, player.getWeaponsInventoryId());

            PreparedStatement preparedStatementW4 = connection.prepareStatement(queryW4);
            preparedStatementW4.setInt(1, player.getWeaponsInventoryId());
            preparedStatementW4.setInt(2, player.getWeaponsInventoryId());

            PreparedStatement preparedStatementW5 = connection.prepareStatement(queryW5);
            preparedStatementW5.setInt(1, player.getWeaponsInventoryId());
            preparedStatementW5.setInt(2, player.getWeaponsInventoryId());

            PreparedStatement preparedStatementW6 = connection.prepareStatement(queryW6);
            preparedStatementW6.setInt(1, player.getWeaponsInventoryId());
            preparedStatementW6.setInt(2, player.getWeaponsInventoryId());

            PreparedStatement preparedStatementW7 = connection.prepareStatement(queryW7);
            preparedStatementW7.setInt(1, player.getWeaponsInventoryId());
            preparedStatementW7.setInt(2, player.getWeaponsInventoryId());

            PreparedStatement preparedStatementP1 = connection.prepareStatement(queryP1);
            preparedStatementP1.setInt(1, player.getPotionsInventoryId());
            preparedStatementP1.setInt(2, player.getPotionsInventoryId());

            PreparedStatement preparedStatementP2 = connection.prepareStatement(queryP2);
            preparedStatementP2.setInt(1, player.getPotionsInventoryId());
            preparedStatementP2.setInt(2, player.getPotionsInventoryId());

            PreparedStatement preparedStatementP3 = connection.prepareStatement(queryP3);
            preparedStatementP3.setInt(1, player.getPotionsInventoryId());
            preparedStatementP3.setInt(2, player.getPotionsInventoryId());

            PreparedStatement preparedStatementP4 = connection.prepareStatement(queryP4);
            preparedStatementP4.setInt(1, player.getPotionsInventoryId());
            preparedStatementP4.setInt(2, player.getPotionsInventoryId());

            PreparedStatement preparedStatementP5 = connection.prepareStatement(queryP5);
            preparedStatementP5.setInt(1, player.getPotionsInventoryId());
            preparedStatementP5.setInt(2, player.getPotionsInventoryId());

            PreparedStatement preparedStatementI = connection.prepareStatement(queryI);
            preparedStatementI.setInt(1, player.getId());
            preparedStatementI.setInt(2, player.getId());

            preparedStatementW1.executeUpdate();
            preparedStatementW2.executeUpdate();
            preparedStatementW3.executeUpdate();
            preparedStatementW4.executeUpdate();
            preparedStatementW5.executeUpdate();
            preparedStatementW6.executeUpdate();
            preparedStatementW7.executeUpdate();
            preparedStatementW1.executeUpdate();
            preparedStatementP2.executeUpdate();
            preparedStatementP3.executeUpdate();
            preparedStatementP4.executeUpdate();
            preparedStatementP5.executeUpdate();
            preparedStatementI.executeUpdate();


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
        String query = "SELECT " + columnName + " FROM " + tableName + " WHERE " + conditionColumn + " = ? AND playerId = ?";
        String text = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, conditionValue);
            preparedStatement.setInt(2, player.getId());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                text = resultSet.getString(columnName);
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return text;
    }

    public void addToWeaponsInventory(int id, Player player) {
        try {
            // Get weapon details
            String queryGet = "SELECT * FROM weapons WHERE id = ? AND playerId = ?";
            try (PreparedStatement selectStatement = connection.prepareStatement(queryGet)) {
                selectStatement.setInt(1, id);
                selectStatement.setInt(2, player.getId());

                try (ResultSet resultSet = selectStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Add weapon to weaponsInventory
                        addWeaponToInventory(resultSet, player);

                        // Update 'bought' status in weapons table
                        updateWeaponBoughtStatus(id, player);
                    }
                }
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    private void addWeaponToInventory(ResultSet resultSet, Player player) throws SQLException {
        String queryAdd = "INSERT INTO weaponsInventory (name, attackName, animation, strength, equipped, playerId) VALUES (?, ?, ?, ?, 0, ?)";
        try (PreparedStatement insertStatement = connection.prepareStatement(queryAdd)) {
            insertStatement.setString(1, resultSet.getString("name"));
            insertStatement.setString(2, resultSet.getString("attackName"));
            insertStatement.setString(3, resultSet.getString("animation"));
            insertStatement.setInt(4, resultSet.getInt("strength"));
            insertStatement.setInt(5, player.getId());

            insertStatement.executeUpdate();
        }
    }

    private void updateWeaponBoughtStatus(int id, Player player) throws SQLException {
        String queryUpdate = "UPDATE weapons SET bought = 1 WHERE id = ? AND playerId = ?";
        try (PreparedStatement updateStatement = connection.prepareStatement(queryUpdate)) {
            updateStatement.setInt(1, id);
            updateStatement.setInt(2, player.getId());

            updateStatement.executeUpdate();
        }
    }

    public void addToPotionsInventory(int id, Player player) {
        try {
            String checkQuery = "SELECT * FROM potionsInventory WHERE id = ? AND playerId = ?";
            try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {
                checkStatement.setInt(1, id);
                checkStatement.setInt(2, player.getId());

                try (ResultSet checkResultSet = checkStatement.executeQuery()) {
                    if (checkResultSet.next()) {
                        updateExistingPotionAmount(id, player);
                    } else {
                        addNewPotionToInventory(id, player);
                    }
                }
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    private void updateExistingPotionAmount(int id, Player player) throws SQLException {
        String updateQuery = "UPDATE potionsInventory SET amountBought = amountBought + 1 WHERE id = ? AND playerId = ?";
        try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
            updateStatement.setInt(1, id);
            updateStatement.setInt(2, player.getId());
            updateStatement.executeUpdate();
        }
    }

    private void addNewPotionToInventory(int id, Player player) throws SQLException {
        String queryGet = "SELECT * FROM potions WHERE id = ? AND potionsInventoryId = ?";
        try (PreparedStatement selectStatement = connection.prepareStatement(queryGet)) {
            selectStatement.setInt(1, id);
            selectStatement.setInt(2, player.getPotionsInventoryId());

            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (resultSet.next()) {
                    String queryAdd = "INSERT INTO potionsInventory (name, animation, strength, health, agility, intelligence, playerId, amountBought) VALUES (?, ?, ?, ?, ?, ?, ?, 1)";
                    try (PreparedStatement insertStatement = connection.prepareStatement(queryAdd)) {
                        insertStatement.setString(1, resultSet.getString("name"));
                        insertStatement.setString(2, resultSet.getString("animation"));
                        insertStatement.setInt(3, resultSet.getInt("strength"));
                        insertStatement.setInt(4, resultSet.getInt("health"));
                        insertStatement.setInt(5, resultSet.getInt("agility"));
                        insertStatement.setInt(6, resultSet.getInt("intelligence"));
                        insertStatement.setInt(7, player.getId());

                        insertStatement.executeUpdate();
                    }
                }
            }
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

    public void equipWeapon(int equippedWeaponId, int wantToEquipId, Player player) {
        try {
            String queryUpdate = "UPDATE weaponsInventory SET equipped = 0 WHERE id = ? AND playerId = ?";
            PreparedStatement updateStatement = connection.prepareStatement(queryUpdate);
            updateStatement.setInt(1, equippedWeaponId);
            updateStatement.setInt(2, player.getId());

            updateStatement.executeUpdate();

            String queryUpdate2 = "UPDATE weaponsInventory SET equipped = 1 WHERE id = ? AND playerId = ?";
            PreparedStatement updateStatement2 = connection.prepareStatement(queryUpdate2);
            updateStatement2.setInt(1, wantToEquipId);
            updateStatement2.setInt(2, player.getId());

            updateStatement2.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    public int getEquippedWeaponStrength(Player player) {
        return getIntFromDb("strength", "weaponsInventory", "equipped", 1, player);
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

    public String getEquippedWeaponString(String what, Player player) {
        return getStringFromDb(what, "weaponsInventory", "equipped", 1, player);
    }

    public void unlockGlock(Player player) {
        try {
            int id = getIdFromName("weaponsInventory", "Glock-19", "playerId", player);
            String query = "UPDATE weaponsInventory SET hidden = 0 WHERE id = ?";
            PreparedStatement updateStatement = connection.prepareStatement(query);
            updateStatement.setInt(1, id);
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    public void weaponsInventory(Player player, Scanners sc) {
        System.out.println(BLACK_BACKGROUND + RED + BOLD + "       Weapons:       " + RESET);

        while (true) {

            String equippedWeaponName = getStringFromDb("name", "weaponsInventory", "equipped", 1, player);
            int equippedWeaponId = getIdFromName("weaponsInventory", equippedWeaponName, "playerId", player);

            System.out.println(RED_DARK + "Equipped weapon: " + equippedWeaponName + RESET);

            int count = getCount("id", "weaponsInventory", player);

            if (count == 0) {
                System.out.println(GRAY + ITALIC + "This inventory is empty" + RESET);
            }

            String weaponName = selectFromWeaponsInventory(sc, player);

            if (Objects.equals(weaponName, "exit")) {
                break;
            }

            int weaponId = getIdFromName("weaponsInventory", weaponName, "playerId", player);
            int weaponStrength = getIntFromDb("strength", "weaponsInventory", "id", weaponId, player);

            equipWeapon(equippedWeaponId, weaponId, player);

            String defaultAttack = getStringFromDb("attackName", "weaponsInventory", "id", weaponId, player);


            player.setDefaultAttack(defaultAttack);
            sleepThread(player.getName() + " equipped " + weaponName + "!");
            chillForASecond(500);
            System.out.println(GRAY + ITALIC + "Strength +" + weaponStrength + RESET);

        }
    }

    public String selectFromWeaponsInventory(Scanners sc, Player player) {
        String selectedName = null;

        try {
            String query = "SELECT name, strength FROM weaponsInventory WHERE playerId = ? AND equipped = 0 AND hidden != 1";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, player.getId());
                try (ResultSet resultSet = statement.executeQuery()) {
                    int counterMax = displayWeaponList(resultSet);

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

    private int displayWeaponList(ResultSet resultSet) throws SQLException {
        System.out.println(WHITE + "Enter the number of the weapon you would like to equip:" + RESET);

        int counter = 1;
        while (resultSet.next()) {
            System.out.println(RED + ITALIC + counter + ". " + resultSet.getString("name") + " - " + resultSet.getInt("strength") + RESET);
            counter++;
        }
        System.out.println(GRAY + ITALIC + "0. Go back" + RESET);

        return counter - 1;
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

    public String selectFromWeaponsShop(Scanners sc, Player player) {
        String selectedName = null;

        try {
            String query = "SELECT name, price, strength FROM weapons WHERE weaponsInventoryId = ? AND bought = 0";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, player.getWeaponsInventoryId());
                try (ResultSet resultSet = statement.executeQuery()) {
                    int counterMax = displayShopWeaponList(resultSet);

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

    private int displayShopWeaponList(ResultSet resultSet) throws SQLException {

        int counter = 1;
        while (resultSet.next()) {
            System.out.println(RED + BOLD + counter + ". " + resultSet.getString("name") +
                    YELLOW_LIGHT + " - $" + resultSet.getInt("price") +
                    "\n" + BLACK + ITALIC + "Strength: +" + resultSet.getInt("strength") + RESET);
            counter++;
        }
        int counterMax = counter;
        System.out.println(BLACK_BACKGROUND + RED_DARK + BOLD + "0. Go back" + RESET);

        return counterMax;
    }

    public void potionsInventory(Scanners sc, Player player) {
        System.out.println(BLACK_BACKGROUND + PINK_LIGHT + BOLD + "    Potions:    " + RESET);

        while (true) {
            try {
                String query = "SELECT * FROM potionsInventory WHERE playerId = ? AND amountBought > 0";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setInt(1, player.getId());

                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            String potionName = selectPotionFromInventory(sc, player);
                            int potionId = getIdFromName("potionsInventory", potionName, "playerId", player);

                            if (Objects.equals(potionName, "exit")) {
                                break;
                            }

                            System.out.println(PINK + ITALIC + player.getName() + " drank " + potionName + "!");
                            drinkPotion(potionId, player);

                        } else {
                            System.out.println(GRAY + ITALIC + "This inventory is empty" + RESET);
                            sc.pressEnter();
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
                        if (resultSet.getInt("strength") > 1) {
                            System.out.println(PINK + ITALIC + counter + ". " + resultSet.getString("name") + PINK_PASTEL + " - " + resultSet.getInt("strength") + " Strength\nAmount: " + resultSet.getInt("amountBought") + RESET);
                        } else if (resultSet.getInt("intelligence") > 1) {
                            System.out.println(PINK + ITALIC + counter + ". " + resultSet.getString("name") + PINK_PASTEL + " - " + resultSet.getInt("intelligence") + " Intelligence\nAmount: " + resultSet.getInt("amountBought") + RESET);
                        } else if (resultSet.getInt("agility") > 1) {
                            System.out.println(PINK + ITALIC + counter + ". " + resultSet.getString("name") + PINK_PASTEL + " - " + resultSet.getInt("agility") + " Agility\nAmount: " + resultSet.getInt("amountBought") + RESET);
                        } else if (resultSet.getInt("health") > 1) {
                            System.out.println(PINK + ITALIC + counter + ". " + resultSet.getString("name") + PINK_PASTEL + " - " + resultSet.getInt("health") + " Health\nAmount: " + resultSet.getInt("amountBought") + RESET);
                        }
                        counter++;
                    }
                    System.out.println(GRAY + ITALIC + "0. Go back" + RESET);

                    System.out.println(WHITE + "Enter the number of the potion you would like to drink:" + RESET);

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

    public String selectFromPotionsShop(Scanners sc, Player player) {
        String selectedName = null;
        int counter = 1;

        try {
            Statement statement = connection.createStatement();

            String query = "SELECT name, price FROM potions WHERE potionInventoryId = " + player.getPotionsInventoryId();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                System.out.println(PINK + ITALIC + counter + ". " + resultSet.getString("name") + YELLOW_LIGHT + " - $" + resultSet.getInt("price") + "\n" + BLACK + ITALIC + "Strength: +" + resultSet.getInt("strength") + RESET);
                counter++;
            }

            System.out.println(BLACK_BACKGROUND + PINK_DARK + BOLD + "0. Go back" + RESET);

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

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return selectedName;
    }

    public int getIdFromName(String table, String name, String what, Player player) {
        int id = 0;

        try {
            String query = "SELECT id FROM " + table + " WHERE name = ? AND " + what + " = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, player.getId());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getInt("id");
            } else {
                System.out.println("No matching record found for name: " + name);
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return id;
    }


    private void players(Scanners sc, Player player){
        try (Statement statement = connection.createStatement()) {
            String query = "select id, name from player";
            try (ResultSet resultSet = statement.executeQuery(query)) {

                while (resultSet.next()) {
                System.out.println(PURPLE + "Select your player:" + RESET);
                System.out.println(PURPLE_LIGHT + resultSet.getInt("id") + ". " + resultSet.getString("name"));
                System.out.println("\n0. New Player" + RESET);
                int columnCount = resultSet.getMetaData().getColumnCount();

                    int choice = sc.scannerNumber();

                    if (choice > (columnCount + 1) && choice < 1) {
                        System.out.println(BLACK + "Invalid choice, please try again" + RESET);
                    } else if (choice == 0) {
                        createNewPlayer(sc, player);
                    } else {
                        chosenPlayer(choice, player);
                    }

                }
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
            PreparedStatement preparedStatement = connection.prepareStatement(query);
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

            String setId = "SELECT id, weaponsInventoryId, potionsInventoryId FROM player WHERE name = ?";
            PreparedStatement preparedStatement1 = connection.prepareStatement(setId);
            preparedStatement1.setString(1, player.getName());
            ResultSet resultSet = preparedStatement1.executeQuery();

            player.setId(resultSet.getInt("id"));
            player.setWeaponsInventoryId(resultSet.getInt("weaponsInventoryId"));
            player.setPotionsInventoryId(resultSet.getInt("potionsInventoryId"));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

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
                player.setWeaponsInventoryId(resultSet.getInt("weaponsInventoryId"));
                player.setPotionsInventoryId(resultSet.getInt("potionsInventoryId"));
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
            String query = "INSERT INTO fight (winner, timeOfFight, monsterName, playerId) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, winner); //Victory, Defeat, Fled
            preparedStatement.setString(2, "CONCAT(DATE_FORMAT(NOW(), '%b %d %Y %l:%i%p'))");
            preparedStatement.setString(3, monster.getName());
            preparedStatement.setInt(4, player.getId());

            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showFightLog(Player player) {
        try {
            String query = "SELECT * FROM fight WHERE playerId = ?";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                if (Objects.equals(resultSet.getString("name"), "Victory")) {
                    System.out.println(GREEN + ITALIC + resultSet.getString("winner") + RESET + WHITE + ITALIC + " against " + getStringFromDb("name", "monsters", "monsterId", resultSet.getInt("monsterId"), player) + " - " + resultSet.getString("timeOfFight") + RESET);

                } else if (Objects.equals(resultSet.getString("name"), "Defeat")) {
                    System.out.println(RED + ITALIC + resultSet.getString("winner") + RESET + WHITE + ITALIC + " against " + getStringFromDb("name", "monsters", "monsterId", resultSet.getInt("monsterId"), player) + " - " + resultSet.getString("timeOfFight") + RESET);

                } else if (Objects.equals(resultSet.getString("name"), "Fled")) {
                    System.out.println(YELLOW + ITALIC + resultSet.getString("winner") + RESET + WHITE + ITALIC + " against " + getStringFromDb("name", "monsters", "monsterId", resultSet.getInt("monsterId"), player) + " - " + resultSet.getString("timeOfFight") + RESET);

                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

    public void specialAttackList(Player player) {
        int counter = 1;
        System.out.println(LILAC + ITALIC + "✧ Special Attacks:");

        try {
            String query = "SELECT id, name, strength FROM specialAttacks WHERE playerId = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, player.getId());

            ResultSet resultSet = preparedStatement.executeQuery(query);

            System.out.println(ORANGE + BOLD + "Special Attacks:" + RESET);
            while (resultSet.next()) {
                System.out.println(YELLOW + BOLD + counter + ". " + resultSet.getString("name") + YELLOW_DARK + ITALIC + "\nDamage: " + resultSet.getInt("strength") + RESET);
                counter++;
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
                System.out.println(YELLOW + BOLD + "0. Use " + player.equippedWeaponName() + RESET);

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



            int rowsAffected = preparedStatement.executeUpdate(query);

            if (rowsAffected > 0) {
                chillForASecond(1500);
                sleepThread(PURPLE_ISH + "You have unlocked a new special attack." + RESET);
                chillForASecond(1500);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getAttackDamage(Scanners sc, Player player) {
        Random random = new Random();
        int id = showSpecialAttacks(sc, player);

        if (id == 0) {
            return random.nextInt(player.getBaseDamage(), (player.getStrength() + player.equippedWeaponStrength()) * 10);
        } else {
            try (PreparedStatement updateStatement = connection.prepareStatement("SELECT strength FROM specialAttacks WHERE id = ? AND playerId = ?;")) {
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
            preparedStatement.setInt(14, player.getId());

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

