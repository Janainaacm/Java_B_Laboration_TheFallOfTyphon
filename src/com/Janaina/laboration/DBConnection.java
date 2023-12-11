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
                    amountBought INT NOT NULL,
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
                       
                       INSERT INTO weapons (name, attackName, animation, price, strength, bought)
                       VALUES ('Glock-19', 'Kurdiska räven', 'ᡕᠵ᠊ᡃ࡚ࠢ࠘ ⸝່ࠡࠣ᠊߯᠆ࠣ࠘ᡁࠣ࠘᠊᠊ࠢ࠘𐡏  𖣓 𖣨', 1000, 100, 0);
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
                   IF NOT EXISTS (SELECT name FROM inventory
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

    public void executeQuery(String query) {
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(query);
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
                } else if (choice == 0){
                    return createNewPlayer();
                } else {
                    return chosenPlayer(choice);
                }

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        return player;
    }


    public String showcaseFromTable(String table, Scanners sc) {
        String selectedName = null;

        try {
            Statement statement = connection.createStatement();

            String query = "select id, name from " + table;
            ResultSet resultSet = statement.executeQuery(query);

            int columnCount = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                StringBuilder row = new StringBuilder();
                for (int i = 1; i <= columnCount; i++) {
                    if (i > 1) {
                        row.append(i).append(". ");
                    }
                    row.append(resultSet.getString(i)).append(" ");
                }
                System.out.println(PURPLE + row + "\n0. Go back" + RESET);
                int choice = sc.scannerNumber();

                if (choice == 0) {
                    return null;
                } else if (choice > 0 && choice <= columnCount) {
                    selectedName = resultSet.getString("name");
                    break;
                } else {
                    System.out.println(BLACK + "Invalid choice, please try again" + RESET);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return selectedName;
    }


    public int getIdFromChoice(String table, Scanners sc) {
        String name = showcaseFromTable(table, sc);
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

