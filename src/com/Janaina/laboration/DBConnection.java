package com.Janaina.laboration;

import com.Janaina.laboration.Game.Variables.ACharacters;
import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Resources.Scanners;

import java.sql.*;
import java.util.Objects;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.PrintHandler.pythiaSpeaking;
import static com.Janaina.laboration.Resources.TextDelay.sleepThread;
import static com.Janaina.laboration.Resources.TextDelay.suspensefulDots;

public class DBConnection {

    private static final String URL = "jdbc:mariadb://localhost:3306/TheFallOfTyphon";
    private static final String USER = "janaina";
    private static final String PASSWORD = "q1W\"e3r4";
    Connection connection;

    public void openConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connected");

        } catch (
                SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void closeConnection() {

        try {
            if (connection != null) {
                connection.close();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createTables(Player player) {
        String query = """
                CREATE TABLE IF NOT EXISTS player (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                	name TEXT UNIQUE NOT NULL,
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
                 	lastPlay dateTime,
                 	inventoryId INT NOT NULL,
                 	CONSTRAINT 'fk_chosenPlayer'
                 	    FOREIGN KEY (inventoryId) REFERENCES inventory (id)
                      			ON DELETE CASCADE ON UPDATE RESTRICT
                );
                                
                CREATE TABLE IF NOT EXISTS weaponsInventory (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name TEXT NOT NULL,
                    attackName TEXT NOT NULL,
                    animation TEXT NOT NULL,
                    strength TINYINT,
                    equipped BIT,
                    hidden BIT,
                    playerId INT NOT NULL,
                    CONSTRAINT 'fk_playerWeaponsInventory'
                        FOREIGN KEY (playerId) REFERENCES player (id)
                            ON DELETE CASCADE ON UPDATE RESTRICT
                );
                                
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
                    CONSTRAINT 'fk_playerPotionsInventory'
                        FOREIGN KEY (playerId) REFERENCES player (id)
                            ON DELETE CASCADE ON UPDATE RESTRICT
                );
                             
                                
                CREATE TABLE IF NOT EXISTS weapons (
                    id INT AUTO_INCREMENT,
                    name TEXT NOT NULL,
                    attackName TEXT NOT NULL,
                    animation TEXT NOT NULL,
                    price SMALLINT NOT NULL,
                    strength TINYINT NOT NULL,
                    bought BIT NOT NULL,
                    weaponsInventoryId INT NOT NULL,
                    CONSTRAINT 'fk_gameWeapons'
                        FOREIGN KEY (inventoryId) REFERENCES inventory (id)
                            ON DELETE CASCADE ON UPDATE RESTRICT
                );
                                
                CREATE TABLE IF NOT EXISTS potions (
                    id INT AUTO_INCREMENT,
                    name TEXT NOT NULL,
                    animation TEXT NOT NULL,
                    price SMALLINT NOT NULL,
                    strength TINYINT,
                    health TINYINT,
                    agility TINYINT,
                    intelligence TINYINT,
                    potionsInventoryId INT NOT NULL,
                    CONSTRAINT 'fk_gamePotions'
                        FOREIGN KEY (inventoryId) REFERENCES inventory (id)
                            ON DELETE CASCADE ON UPDATE RESTRICT
                );
                                
                CREATE TABLE IF NOT EXISTS specialAttacks (
                    id INT AUTO_INCREMENT,
                    name TEXT NOT NULL,
                    animation TEXT NOT NULL,
                    strength TINYINT NOT NULL,
                    acquired BIT NOT NULL,
                    playerId INT NOT NULL,
                    CONSTRAINT 'fk_playerSpecialAttacks'
                        FOREIGN KEY (playerId) REFERENCES player (id)
                            ON DELETE CASCADE ON UPDATE RESTRICT
                );
                                
                CREATE TABLE IF NOT EXISTS monsters (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name TEXT NOT NULL,
                    strength TINYINT NOT NULL,
                    health TEXT NOT NULL,
                    baseDamage TINYINT NOT NULL,
                    agility SMALLINT NOT NULL,
                    intelligence SMALLINT NOT NULL,
                    gold SMALLINT NOT NULL,
                    experience SMALLINT NOT NULL,
                    defaultAttack TEXT NOT NULL,
                    amountKilled INT NOT NULL,
                    playerId INT NOT NULL,
                        CONSTRAINT 'fk_playerMonsters'
                        FOREIGN KEY (playerId) REFERENCES player (id)
                            ON DELETE CASCADE ON UPDATE RESTRICT
                );
                
                CREATE TABLE IF NOT EXISTS fight (
                    id INT AUTO_INCREMENT,
                    winner TEXT NOT NULL,
                    timeOfFight nvarchar(50) NOT NULL,
                    playerId INT NOT NULL,
                    monsterId INT NOT NULL,
                    CONSTRAINT 'fk_playerFightLog'
                        FOREIGN KEY (playerId) REFERENCES player (id)
                        FOREIGN KEY (monsterId) REFERENCES monsters (id)
                            ON DELETE CASCADE ON UPDATE RESTRICT
                );
                                
                """;

        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        addItemsToTables(player);

    }

    private void addItemsToTables(Player player) {
        String query =
                "INSERT INTO INTO weapons (name, attackName, animation, price, strength, bought, weaponsInventoryId) " +
                    "SELECT 'Frostbite Dagger', 'Frostbite Strike', '+—⟪═════>', 150, 2, 0, " + player.getWeaponsInventoryId() +
                    " WHERE NOT EXISTS (" +
                    " SELECT 1" +
                    " FROM weapons" +
                    " WHERE name = 'Frostbite Dagger' AND weaponsInventoryId = " + player.getWeaponsInventoryId() + ");" +

                "INSERT INTO INTO weapons (name, attackName, animation, price, strength, bought, weaponsInventoryId) " +
                    "SELECT 'Shadowfang Blade', 'Dark Eclipse', '▭▭ι═══════>', 160, 3, 0, " + player.getWeaponsInventoryId() +
                    " WHERE NOT EXISTS (" +
                    " SELECT 1" +
                    " FROM weapons" +
                    " WHERE name = 'Shadowfang Blade' AND weaponsInventoryId = " + player.getWeaponsInventoryId() + ");" +

                "INSERT INTO INTO weapons (name, attackName, animation, price, strength, bought, weaponsInventoryId) " +
                    "SELECT 'Cursed Scythe', 'Reaper's Grasp', '▬ι══════>', 170, 4, 0, " + player.getWeaponsInventoryId() +
                    " WHERE NOT EXISTS (" +
                    " SELECT 1" +
                    " FROM weapons" +
                    " WHERE name = 'Cursed Scythe' AND weaponsInventoryId = " + player.getWeaponsInventoryId() + ");" +

                "INSERT INTO INTO weapons (name, attackName, animation, price, strength, bought, weaponsInventoryId) " +
                    "SELECT 'Oceanic Trident', 'Abyssal Torrent', '——————∈ ࿐ ࿔', 200, 5, 0, " + player.getWeaponsInventoryId() +
                    " WHERE NOT EXISTS (" +
                    " SELECT 1" +
                    " FROM weapons" +
                    " WHERE name = 'Oceanic Trident' AND weaponsInventoryId = " + player.getWeaponsInventoryId() + ");" +

                "INSERT INTO INTO weapons (name, attackName, animation, price, strength, bought, weaponsInventoryId) " +
                    "SELECT 'Phoenix Bow','Flaming Arrow Barrage', 'ˎ-·˚ ༘₊· ͟͟͞͞➳', 250, 7, 0, " + player.getWeaponsInventoryId() +
                    " WHERE NOT EXISTS (" +
                    " SELECT 1" +
                    " FROM weapons" +
                    " WHERE name = 'Phoenix Bow' AND weaponsInventoryId = " + player.getWeaponsInventoryId() + ");" +

                "INSERT INTO INTO weapons (name, attackName, animation, price, strength, bought, weaponsInventoryId) " +
                    "SELECT 'Thunderstrike Hammer', 'Lightning Hammerblow', '⌁˚⊹｡ﾟϟﾟ.｡⊹˚⌁', 300, 8, 0, " + player.getWeaponsInventoryId() +
                    " WHERE NOT EXISTS (" +
                    " SELECT 1" +
                    " FROM weapons" +
                    " WHERE name = 'Thunderstrike Hammer' AND weaponsInventoryId = " + player.getWeaponsInventoryId() + ");" +

                "INSERT INTO INTO weapons (name, attackName, animation, price, strength, bought, weaponsInventoryId) " +
                    "SELECT 'Glock-19', 'Kurdiska räven', 'ᡕᠵ᠊ᡃ࡚ࠢ࠘ ⸝່ࠡࠣ᠊߯᠆ࠣ࠘ᡁࠣ࠘᠊᠊ࠢ࠘𐡏  𖣓 𖣨', 1000, 100, 0, 1, " + player.getWeaponsInventoryId() +
                    " WHERE NOT EXISTS (" +
                    " SELECT 1" +
                    " FROM weapons" +
                    " WHERE name = 'Glock-19' AND weaponsInventoryId = " + player.getWeaponsInventoryId() + ");" +


                "INSERT INTO INTO potions (name, animation, price, health, potionsInventoryId) " +
                    "SELECT 'Small Health Potion', '⋆｡୭⋆⁺.⋆｡˙⊹༺⋆｡˙⊹⋆', 30, 50, " + player.getPotionsInventoryId() +
                    " WHERE NOT EXISTS (" +
                    " SELECT 1" +
                    " FROM potions" +
                    " WHERE name = 'Small Health Potion' AND weaponsInventoryId = " + player.getPotionsInventoryId() + ");" +

                "INSERT INTO INTO potions (name, animation, price, health, potionsInventoryId) " +
                    "SELECT 'Large Health Potion', '⋆｡୭⋆⁺.⋆｡˙⊹༺⋆｡˙⊹⋆', 50, 100, " + player.getPotionsInventoryId() +
                    " WHERE NOT EXISTS (" +
                    " SELECT 1" +
                    " FROM potions" +
                    " WHERE name = 'Large Health Potion' AND weaponsInventoryId = " + player.getPotionsInventoryId() + ");" +

                "INSERT INTO INTO potions (name, animation, price, health, potionsInventoryId) " +
                    "SELECT 'Flexibility Potion', '❥⁺⋆༺.*₊˚࿐༅', 50, 2, " + player.getPotionsInventoryId() +
                    " WHERE NOT EXISTS (" +
                    " SELECT 1" +
                    " FROM potions" +
                    " WHERE name = 'Flexibility Potion' AND weaponsInventoryId = " + player.getPotionsInventoryId() + ");" +

                "INSERT INTO INTO potions (name, animation, price, health, potionsInventoryId) " +
                    "SELECT 'Strength Potion', '❥⁺⋆༺.*₊˚࿐༅', 50, 1, " + player.getPotionsInventoryId() +
                    " WHERE NOT EXISTS (" +
                    " SELECT 1" +
                    " FROM potions" +
                    " WHERE name = 'Strength Potion' AND weaponsInventoryId = " + player.getPotionsInventoryId() + ");" +

                "INSERT INTO INTO potions (name, animation, price, health, potionsInventoryId) " +
                    "SELECT 'Intelligence Potion', '❥⁺⋆༺.*₊˚࿐༅', 50, 5, " + player.getPotionsInventoryId() +
                    " WHERE NOT EXISTS (" +
                    " SELECT 1" +
                    " FROM potions" +
                    " WHERE name = 'Intelligence Potion' AND weaponsInventoryId = " + player.getPotionsInventoryId() + ");" +


                "INSERT INTO INTO monsters (name, strength, health, baseDamage, agility, intelligence, gold, experience, defaultAttack, amountKilled, playerId) " +
                    "SELECT 'Fury', 2, 50, 10, 10, 10, 15, 30, 'Rage of the Erinyes', 0, " + player.getId() +
                    " WHERE NOT EXISTS (" +
                    " SELECT 1" +
                    " FROM monsters" +
                    " WHERE name = 'Fury' AND playerId = " + player.getId() + ");" +

                "INSERT INTO INTO monsters (name, strength, health, baseDamage, agility, intelligence, gold, experience, defaultAttack, amountKilled, playerId) " +
                        "SELECT 'Siren', 2, 60, 10, 40, 15, 50, 40, 'Deadly Wail', 0, " + player.getId() +
                        " WHERE NOT EXISTS (" +
                        " SELECT 1" +
                        " FROM monsters" +
                        " WHERE name = 'Siren' AND playerId = " + player.getId() + ");" +

                "INSERT INTO INTO monsters (name, strength, health, baseDamage, agility, intelligence, gold, experience, defaultAttack, amountKilled, playerId) " +
                        "SELECT 'Medusa', 3, 100, 10, 10, 30, 100, 60, 'Serpents Curse', 0, " + player.getId() +
                        " WHERE NOT EXISTS (" +
                        " SELECT 1" +
                        " FROM monsters" +
                        " WHERE name = 'Medusa' AND playerId = " + player.getId() + ");" +

                "INSERT INTO INTO monsters (name, strength, health, baseDamage, agility, intelligence, gold, experience, defaultAttack, amountKilled, playerId) " +
                        "SELECT 'Minotaur', 3, 100, 10, 30, 30, 150, 70, 'Bestial Fury', 0, " + player.getId() +
                        " WHERE NOT EXISTS (" +
                        " SELECT 1" +
                        " FROM monsters" +
                        " WHERE name = 'Minotaur' AND playerId = " + player.getId() + ");" +

                "INSERT INTO INTO monsters (name, strength, health, baseDamage, agility, intelligence, gold, experience, defaultAttack, amountKilled, playerId) " +
                        "SELECT 'Cerberus', 5, 150, 10, 10, 40, 200, 70, 'Underworld Carnage', 0, " + player.getId() +
                        " WHERE NOT EXISTS (" +
                        " SELECT 1" +
                        " FROM monsters" +
                        " WHERE name = 'Cerberus' AND playerId = " + player.getId() + ");" +

                "INSERT INTO INTO monsters (name, strength, health, baseDamage, agility, intelligence, gold, experience, defaultAttack, amountKilled, playerId) " +
                        "SELECT 'Typhon', 7, 200, 10, 20, 60, 300, 100, 'Apocalyptic Surge', 0, " + player.getId() +
                        " WHERE NOT EXISTS (" +
                        " SELECT 1" +
                        " FROM monsters" +
                        " WHERE name = 'knife' AND playerId = " + player.getId() + ");" +

                "INSERT INTO INTO weaponsInventoryId (name, attackName, animation, strength, equipped, playerId) " +
                        "SELECT 'knife', 'Lethal Lunge', '▬ι=>', 1, 1, " + player.getId() +
                        " WHERE NOT EXISTS (" +
                        " SELECT 1" +
                        " FROM weaponsInventoryId" +
                        " WHERE name = 'knife' AND playerId = " + player.getId() + ");"
                ;


        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getIntFromDb(String object, String table, String where, int id, Player player) {
        String query = "SELECT " + object + " FROM " + table + " WHERE " + where + " = " + id + " AND playerId = " + player.getId();
        int number = 0;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            number = resultSet.getInt(object);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return number;
    }

    public String getStringFromDb(String object, String table, String where, int id, Player player) {
        String query = "SELECT " + object + " FROM " + table + " WHERE " + where + " = " + id + " AND playerId = " + player.getId();
        String text = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            text = resultSet.getString(object);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return text;
    }

    public void addToWeaponsInventory(int id, Player player) {
        try {
            String queryGet = "SELECT * FROM weapons WHERE id = ? AND playerId = " + player.getId();
            PreparedStatement selectStatement = connection.prepareStatement(queryGet);
            selectStatement.setInt(1, id);

            ResultSet resultSet = selectStatement.executeQuery();

            try {
                String queryAdd = "INSERT INTO weaponsInventory (name, attackName, animation, strength, equipped) VALUES (?, ?, ?, ?, 0) AND playerId = " + player.getId();
                PreparedStatement insertStatement = connection.prepareStatement(queryAdd);

                if (resultSet.next()) {
                    insertStatement.setString(1, resultSet.getString("name"));
                    insertStatement.setString(2, resultSet.getString("attackName"));
                    insertStatement.setString(3, resultSet.getString("animation"));
                    insertStatement.setInt(4, resultSet.getInt("strength"));
                    insertStatement.executeUpdate();
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            String queryUpdate = "UPDATE weapons SET bought = 1 WHERE id = ? AND playerId = " + player.getId();
            PreparedStatement updateStatement = connection.prepareStatement(queryUpdate);
            updateStatement.setInt(1, id);
            updateStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addToPotionsInventory(int id, Player player) {
        try {
            String checkQuery = "SELECT * FROM potionsInventory WHERE id = ? AND playerId = " + player.getId();
            PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
            checkStatement.setInt(1, id);
            ResultSet checkResultSet = checkStatement.executeQuery();

            if (checkResultSet.next()) {
                String updateQuery = "UPDATE potionsInventory SET amountBought = amountBought + 1 WHERE id = ? AND playerId = " + player.getId();
                PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                updateStatement.setInt(1, id);
                updateStatement.executeUpdate();
            } else {
                String queryGet = "SELECT * FROM potions WHERE id = ? AND potionsInventoryId = " + player.getPotionsInventoryId();
                PreparedStatement selectStatement = connection.prepareStatement(queryGet);
                selectStatement.setInt(1, id);
                ResultSet resultSet = selectStatement.executeQuery();

                try {
                    String queryAdd = "INSERT INTO potionsInventory (name, animation, strength, health, agility, intelligence, playerId, amountBought) VALUES (?, ?, ?, ?, ?, ?, ?, 1)";
                    PreparedStatement insertStatement = connection.prepareStatement(queryAdd);

                    if (resultSet.next()) {
                        insertStatement.setString(1, resultSet.getString("name"));
                        insertStatement.setString(2, resultSet.getString("animation"));
                        insertStatement.setInt(3, resultSet.getInt("strength"));
                        insertStatement.setInt(4, resultSet.getInt("health"));
                        insertStatement.setInt(5, resultSet.getInt("agility"));
                        insertStatement.setInt(6, resultSet.getInt("intelligence"));
                        insertStatement.setInt(7, player.getId());

                        insertStatement.executeUpdate();
                    }

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Player choosePlayer(Scanners sc) {
        Player player = null;

        try {
            Statement statement = connection.createStatement();

            String query = "select id, name from player";
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println(PURPLE + "Select your player:" + RESET);
            int columnCount = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                StringBuilder row = new StringBuilder();
                for (int i = 1; i <= columnCount; i++) {
                    if (i > 1) {
                        row.append(". ");
                    }
                    row.append(resultSet.getString(i));
                }
                System.out.println(PURPLE_LIGHT + row + "\n0. New Player" + RESET);
                int choice = sc.scannerNumber();

                if (choice > (columnCount + 1) && choice < 1) {
                    System.out.println(BLACK + "Invalid choice, please try again" + RESET);
                } else if (choice == 0) {
                    player = createNewPlayer(sc);
                } else {
                    player = chosenPlayer(choice);
                }

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        return player;
    }

    public int getCount(String column, String table, Player player) {
        int count = 0;

        try {
            Statement statement = connection.createStatement();

            String query = "SELECT COUNT(" + column + ") FROM " + table + "WHERE playerId = " + player.getId();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }

    public void equipWeapon(int equippedWeaponId, int wantToEquipId, Player player) {
        try {
            String queryUpdate = "UPDATE weaponsInventory SET equipped = 0 WHERE id = ? AND playerId = " + player.getId();
            PreparedStatement updateStatement = connection.prepareStatement(queryUpdate);
            updateStatement.setInt(1, equippedWeaponId);
            updateStatement.executeUpdate();

            String queryUpdate2 = "UPDATE weaponsInventory SET equipped = 1 WHERE id = ? AND playerId = " + player.getId();
            PreparedStatement updateStatement2 = connection.prepareStatement(queryUpdate2);
            updateStatement2.setInt(1, wantToEquipId);
            updateStatement2.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getEquippedWeaponStrength(Player player) {
        return getIntFromDb("strength", "weaponsInventory", "equipped", 1, player);
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
            System.out.println(e.getMessage());
        }
    }

    public String selectFromWeaponsInventory(Scanners sc, Player player) {
        String selectedName = null;
        int counter = 1;

        try (Statement statement = connection.createStatement()) {
            String query = "SELECT name, strength FROM weaponsInventory WHERE playerId = " + player.getId() + "AND equipped = 0 AND hidden != 1";
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println(WHITE + "Enter the number of the weapon you would like to equip:" + RESET);

            while (resultSet.next()) {
                System.out.println(RED + ITALIC + counter + ". " + resultSet.getString("name") + " - " + resultSet.getInt("strength") + RESET);
                counter++;
            }
            System.out.println(GRAY + ITALIC + "0. Go back" + RESET);

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

    public String selectFromWeaponsShop(Scanners sc, Player player) {
        String selectedName = null;
        int counter = 1;

        try {
            Statement statement = connection.createStatement();

            String query = "SELECT name, price FROM weapons WHERE weaponsInventoryId = " + player.getWeaponsInventoryId() + " AND bought = 0";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                System.out.println(RED + BOLD + counter + ". " + resultSet.getString("name") + YELLOW_LIGHT + " - $" + resultSet.getInt("price") + "\n" + BLACK + ITALIC + "Strength: +" + resultSet.getInt("strength") + RESET);
                counter++;
            }
            System.out.println(BLACK_BACKGROUND + RED_DARK + BOLD + "0. Go back" + RESET);

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

    public String selectFromPotionsInventory(Scanners sc, Player player) {
        String selectedName = null;
        int counter = 1;

        try (Statement statement = connection.createStatement()) {
            String query = "SELECT name, strength, agility, intelligence, health, amountBought FROM potionsInventory WHERE playerId = " + player.getId();
            ResultSet resultSet = statement.executeQuery(query);


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

            System.out.println(WHITE + "Enter the number of the potion you would like to use:" + RESET);

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

    public void drinkPotion(int drinkPotionId, Player player) {
        try (Statement statement = connection.createStatement()) {
            String queryUpdate = "UPDATE potionsInventory SET amountBought = amountBought + 1 WHERE id = ? AND playerId = " + player.getId();
            PreparedStatement updateStatement = connection.prepareStatement(queryUpdate);
            updateStatement.setInt(1, drinkPotionId);
            updateStatement.executeUpdate();

            String query = "SELECT * FROM potionsInventory WHERE id = " + drinkPotionId + " AND playerId = " + player.getId();
            ResultSet resultSet = statement.executeQuery(query);

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

        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
            Statement statement = connection.createStatement();

            String query = "SELECT id FROM " + table + " WHERE name = '" + name + "' AND " + what + " = " + player.getId();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                id = resultSet.getInt("id");
            } else {
                System.out.println("No matching record found for name: " + name);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return id;
    }

    private boolean checkIfNameExists(String name) {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM player WHERE name = '" + name + "'";
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    private Player createNewPlayer(Scanners sc) {
        Player player = new Player();
        suspensefulDots(".");

        pythiaSpeaking("""
                Hello there,\s
                My name is Pythia, but you might formally know me as The Oracle of Delphi.\s
                What is your name?""");

        while (true) {
            String chosenName = sc.scannerText();
            if (checkIfNameExists(chosenName)){
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

            String setId = "SELECT id FROM player WHERE name = ?";
            PreparedStatement preparedStatement1 = connection.prepareStatement(setId);
            preparedStatement1.setString(1, player.getName());
            ResultSet resultSet = preparedStatement1.executeQuery();

            player.setId(resultSet.getInt("id"));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return player;
    }

    private Player chosenPlayer(int playerId) {
        Player player = new Player();

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

        return player;
    }

    public void insertFightLog(Player player, String winner, ACharacters monster){
        try {
            String query = "INSERT INTO fight (winner, timeOfFight, playerId, monsterId) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "winner"); //Victory, Defeat, Fled
            preparedStatement.setString(2, "CONCAT(DATE_FORMAT(NOW(), '%b %d %Y %l:%i%p'))");
            preparedStatement.setInt(3, player.getId());
            preparedStatement.setInt(3, monster.getId());

            preparedStatement.executeUpdate();


        } catch (SQLException e){
            e.getMessage();
        }
    }

    public void showFightLog(Player player){
        try {
            String query = "SELECT * FROM fight WHERE playerId = ?";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                if (Objects.equals(resultSet.getString("name"), "Victory")){
                    System.out.println(resultSet.getString("winner") + " against " + getStringFromDb("name", "monsters", "monsterId", resultSet.getInt("monsterId"), player));

                } else if (Objects.equals(resultSet.getString("name"), "Defeat")){

                }else if (Objects.equals(resultSet.getString("name"), "Fled")){

                }


            }




        } catch (SQLException e){
            System.out.println(e.getMessage());
        }


    }

    public int getMonstersKilled(String monsterName, Player player){
        int killed = 0;

        try {
            String query = "SELECT amountKilled FROM monsters WHERE name = ? AND playerId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, monsterName);
            preparedStatement.setInt(2, player.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            killed = resultSet.getInt("amountKilled");

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return killed;
    }


}

