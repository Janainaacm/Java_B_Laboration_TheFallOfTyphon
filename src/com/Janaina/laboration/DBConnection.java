package com.Janaina.laboration;

import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Resources.Scanners;

import java.sql.*;

import static com.Janaina.laboration.Resources.Colors.*;

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

    public void createTables() {
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
                    inventoryId INT NOT NULL,
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
                    inventoryId INT NOT NULL,
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
                                
                CREATE TABLE IF NOT EXISTS monster (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name TEXT NOT NULL,
                    health TEXT NOT NULL,
                    strength TINYINT NOT NULL,
                    agility SMALLINT NOT NULL,
                    intelligence SMALLINT NOT NULL,
                );
                                
                CREATE TABLE IF NOT EXISTS fight (
                    id INT AUTO_INCREMENT,
                    winner TEXT NOT NULL,
                    timeOfFight DATETIME NOT NULL,
                    playerId INT NOT NULL,
                    monsterId INT NOT NULL,
                    CONSTRAINT 'fk_playerFightLog'
                        FOREIGN KEY (playerId) REFERENCES player (id)
                        FOREIGN KEY (monsterId) REFERENCES monster (id)
                            ON DELETE CASCADE ON UPDATE RESTRICT
                );
                                
                """;

        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        addItemsToTables();

    }

    private void addItemsToTables() {
        String query = """
                BEGIN
                   IF NOT EXISTS (SELECT name FROM weapons
                        WHERE name IN ('Frostbite Dagger', 'Shadowfang Blade', 'Cursed Scythe', 'Oceanic Trident', 'Phoenix Bow', 'Thunderstrike Hammer', 'Glock-19'))
                    
                   BEGIN
                       INSERT INTO weapons (name, attackName, animation, price, strength, bought)
                       VALUES ('Frostbite Dagger', 'Frostbite Strike', '+—⟪═════>', 150, 2, 0);
                       
                       INSERT INTO weapons (name, attackName, animation, price, strength, bought)
                       VALUES ('Shadowfang Blade', 'Dark Eclipse', '▭▭ι═══════>', 160, 3, 0);
                       
                       INSERT INTO weapons (name, attackName, animation, price, strength, bought)
                       VALUES ('Cursed Scythe', 'Reaper's Grasp', '▬ι══════>', 170, 4, 0);
                       
                       INSERT INTO weapons (name, attackName, animation, price, strength, bought)
                       VALUES ('Oceanic Trident', 'Abyssal Torrent', '——————∈ ࿐ ࿔', 200, 5, 0);
                       
                       INSERT INTO weapons (name, attackName, animation, price, strength, bought)
                       VALUES ('Phoenix Bow','Flaming Arrow Barrage', 'ˎ-·˚ ༘₊· ͟͟͞͞➳', 250, 7, 0);
                       
                       INSERT INTO weapons (name, attackName, animation, price, strength, bought)
                       VALUES ("Thunderstrike Hammer", 'Lightning Hammerblow', '⌁˚⊹｡ﾟϟﾟ.｡⊹˚⌁', 300,8, 0);
                       
                       INSERT INTO weapons (name, attackName, animation, price, strength, bought, hidden)
                       VALUES ('Glock-19', 'Kurdiska räven', 'ᡕᠵ᠊ᡃ࡚ࠢ࠘ ⸝່ࠡࠣ᠊߯᠆ࠣ࠘ᡁࠣ࠘᠊᠊ࠢ࠘𐡏  𖣓 𖣨', 1000, 100, 0, 1);
                   END
                END
                                
                BEGIN
                   IF NOT EXISTS (SELECT name FROM potions
                        WHERE name IN ('Small Health Potion', 'Large Health Potion', 'Flexibility Potion', 'Strength Potion', 'Intelligence Potion'))
                    
                   BEGIN
                       INSERT INTO potions (name, animation, price, health)
                       VALUES ('Small Health Potion', '⋆｡୭⋆⁺.⋆｡˙⊹༺⋆｡˙⊹⋆', 30, 50);
                       
                       INSERT INTO potions (name, animation, price, health)
                       VALUES ('Large Health Potion', '⋆｡୭⋆⁺.⋆｡˙⊹༺⋆｡˙⊹⋆', 50, 100);
                       
                       INSERT INTO potions (name, animation, price, agility)
                       VALUES ('Flexibility Potion', '❥⁺⋆༺.*₊˚࿐༅', 50, 2);
                       
                       INSERT INTO potions (name, animation, price, strength)
                       VALUES ('Strength Potion', '❥⁺⋆༺.*₊˚࿐༅', 50, 1);
                       
                       INSERT INTO potions (name, animation, price, intelligence)
                       VALUES ('Intelligence Potion', '❥⁺⋆༺.*₊˚࿐༅', 50, 5);
                   END
                END
                                
                BEGIN
                   IF NOT EXISTS (SELECT name FROM weaponsInventory
                        WHERE name = 'knife'
                   BEGIN
                       INSERT INTO weaponsInventory (name, attackName, animation, price, strength, equipped)
                       VALUES ('knife', 'Lethal Lunge', '▬ι=>', 0, 1, 1);
                   END
                END
                """;

        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getIntFromDb(String object, String table, String where, int id) {
        String query = "SELECT " + object + " FROM " + table + " WHERE " + where + " = " + id;
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

    public String getStringFromDb(String object, String table, String where, int id) {
        String query = "SELECT " + object + " FROM " + table + " WHERE " + where + " = " + id;
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

    public void addToWeaponsInventory(int id) {
        try {
            String queryGet = "SELECT * FROM weapons WHERE id = ?";
            PreparedStatement selectStatement = connection.prepareStatement(queryGet);
            selectStatement.setInt(1, id);

            ResultSet resultSet = selectStatement.executeQuery();

            try {
                String queryAdd = "INSERT INTO weaponsInventory (name, attackName, animation, strength, equipped) VALUES (?, ?, ?, ?, 0)";
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

            String queryUpdate = "UPDATE weapons SET bought = 1 WHERE id = ?";
            PreparedStatement updateStatement = connection.prepareStatement(queryUpdate);
            updateStatement.setInt(1, id);
            updateStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addToPotionsInventory(int id) {
        try {
            String checkQuery = "SELECT * FROM potionsInventory WHERE id = 1";
            PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
            checkStatement.setInt(1, id);
            ResultSet checkResultSet = checkStatement.executeQuery();

            if (checkResultSet.next()) {
                String updateQuery = "UPDATE potionsInventory SET amountBought = amountBought + 1 WHERE id = ?";
                PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                updateStatement.setInt(1, id);
                updateStatement.executeUpdate();
            } else {
                String queryGet = "SELECT * FROM potions WHERE id = ?";
                PreparedStatement selectStatement = connection.prepareStatement(queryGet);
                selectStatement.setInt(1, id);
                ResultSet resultSet = selectStatement.executeQuery();

                try {
                    String queryAdd = "INSERT INTO potionsInventory (name, animation, strength, health, agility, intelligence, amountBought) VALUES (?, ?, ?, ?, ?, ?, 1)";
                    PreparedStatement insertStatement = connection.prepareStatement(queryAdd);

                    if (resultSet.next()) {
                        insertStatement.setString(1, resultSet.getString("name"));
                        insertStatement.setString(2, resultSet.getString("animation"));
                        insertStatement.setInt(3, resultSet.getInt("strength"));
                        insertStatement.setInt(4, resultSet.getInt("health"));
                        insertStatement.setInt(5, resultSet.getInt("agility"));
                        insertStatement.setInt(6, resultSet.getInt("intelligence"));

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

            System.out.println(PURPLE_LIGHT + "Select your player:" + RESET);
            int columnCount = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                StringBuilder row = new StringBuilder();
                for (int i = 1; i <= columnCount; i++) {
                    if (i > 1) {
                        row.append(". ");
                    }
                    row.append(resultSet.getString(i));
                }
                System.out.println(PURPLE + row + "\n0. New Player" + RESET);
                int choice = sc.scannerNumber();

                if (choice > (columnCount + 1) && choice < 1) {
                    System.out.println(BLACK + "Invalid choice, please try again" + RESET);
                } else if (choice == 0) {
                    player = createNewPlayer();
                } else {
                    player = chosenPlayer(choice);
                }

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        return player;
    }

    public int getCount(String column, String table) {
        int count = 0;

        try {
            Statement statement = connection.createStatement();

            String query = "SELECT COUNT(" + column + ") FROM " + table;
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }

    public void equipWeapon(int equippedWeaponId, int wantToEquipId) {
        try {
            String queryUpdate = "UPDATE weaponsInventory SET equipped = 0 WHERE id = ?";
            PreparedStatement updateStatement = connection.prepareStatement(queryUpdate);
            updateStatement.setInt(1, equippedWeaponId);
            updateStatement.executeUpdate();

            String queryUpdate2 = "UPDATE weaponsInventory SET equipped = 1 WHERE id = ?";
            PreparedStatement updateStatement2 = connection.prepareStatement(queryUpdate2);
            updateStatement2.setInt(1, wantToEquipId);
            updateStatement2.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void unlockGlock() {
        try {
            int id = getIdFromChoice("weapons", "Glock-19");
            String query = "UPDATE weapons SET hidden = 0 WHERE id = ?";
            PreparedStatement updateStatement = connection.prepareStatement(query);
            updateStatement.setInt(1, id);
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String selectFromWeaponsInventory(Scanners sc) {
        String selectedName = null;
        int counter = 1;

        try (Statement statement = connection.createStatement()) {
            String query = "SELECT name, strength FROM weaponsInventory WHERE equipped = 0 AND hidden != 1";
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

    public String selectFromWeaponsShop(Scanners sc) {
        String selectedName = null;
        int counter = 1;

        try {
            Statement statement = connection.createStatement();

            String query = "SELECT name, price FROM weapons WHERE bought = 0";
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

    public String selectFromPotionsInventory(Scanners sc) {
        String selectedName = null;
        int counter = 1;

        try (Statement statement = connection.createStatement()) {
            String query = "SELECT name, strength, agility, intelligence, health, amountBought FROM potionsInventory";
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

    public void drinkPotion(int drinkPotionId, Player player){
        try (Statement statement = connection.createStatement()){
            String queryUpdate = "UPDATE potionsInventory SET amountBought = amountBought + 1 WHERE id = ?";
            PreparedStatement updateStatement = connection.prepareStatement(queryUpdate);
            updateStatement.setInt(1, drinkPotionId);
            updateStatement.executeUpdate();

            String query = "SELECT * FROM potionsInventory WHERE id = " + drinkPotionId;
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.getInt("strength") > 1){
                player.setStrength(player.getStrength() + resultSet.getInt("strength"));
                System.out.println(GRAY + ITALIC + "Strength +" + resultSet.getInt("strength") + RESET);
            } else if (resultSet.getInt("intelligence") > 0){
                player.setIntelligence(player.getIntelligence() + resultSet.getInt("intelligence"));
                System.out.println(GRAY + ITALIC + "Intelligence +" + resultSet.getInt("intelligence") + RESET);
            } else if (resultSet.getInt("agility") > 0){
                player.setAgility(player.getAgility() + resultSet.getInt("agility"));
                System.out.println(GRAY + ITALIC + "Agility +" + resultSet.getInt("agility") + RESET);
            } else if (resultSet.getInt("health") > 0){
                player.setHealth(player.getHealth() + resultSet.getInt("health"));
                System.out.println(GRAY + ITALIC + "Health +" + resultSet.getInt("health") + RESET);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String selectFromPotionsShop(Scanners sc) {
        String selectedName = null;
        int counter = 1;

        try {
            Statement statement = connection.createStatement();

            String query = "SELECT name, price FROM potions";
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

    public int getIdFromChoice(String table, String name) {
        int id = 0;

        try {
            Statement statement = connection.createStatement();

            String query = "SELECT id FROM " + table + " WHERE name = '" + name + "'";
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

    private Player createNewPlayer() {
        Player player = new Player();
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

        return player;
    }


}

