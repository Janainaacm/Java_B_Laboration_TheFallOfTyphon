package com.Janaina.laboration;

import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Resources.Scanners;

import java.sql.*;
import java.util.Scanner;

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

    public void executeQuery(String query) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("Select artist you want to play as:");
            int columnCount = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                StringBuilder row = new StringBuilder();
                for (int i = 1; i <= columnCount; i++) {
                    if (i > 1) {
                        row.append(". ");
                    }
                    row.append(resultSet.getString(i));
                }
                System.out.println(row);
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
                                
                CREATE TABLE IF NOT EXISTS inventory (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name TEXT NOT NULL,
                    attackName TEXT,
                    animation TEXT NOT NULL,
                    strength TINYINT,
                    health TINYINT,
                    agility TINYINT,
                    intelligence TINYINT,
                    equipped BIT,
                    playerId INT NOT NULL,
                    CONSTRAINT 'fk_playerInventory'
                        FOREIGN KEY (playerId) REFERENCES player (id)
                            ON DELETE CASCADE ON UPDATE RESTRICT
                );
                                
                CREATE TABLE IF NOT EXISTS shop (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name TEXT NOT NULL,
                    attackName TEXT,
                    animation TEXT NOT NULL,
                    price SMALLINT NOT NULL,
                    strength TINYINT,
                    health TINYINT,
                    agility TINYINT,
                    intelligence TINYINT,
                    playerId INT NOT NULL,
                    CONSTRAINT 'fk_playerShop'
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
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("Select artist you want to play as:");
            int columnCount = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                StringBuilder row = new StringBuilder();
                for (int i = 1; i <= columnCount; i++) {
                    if (i > 1) {
                        row.append(". ");
                    }
                    row.append(resultSet.getString(i));
                }
                System.out.println(row);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }



    public int getIdFromTable(String table, Scanners sc) {
        int playerId = 0;
        try {
            Statement statement = connection.createStatement();

            String query = "select id, name from" + table;
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println(PURPLE_LIGHT + "Select your " + table + ":" + RESET);
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
                playerId = sc.scannerNumber();

                if (playerId > (columnCount + 1) && playerId < 1) {
                    System.out.println(BLACK + "Invalid choice, please try again" + RESET);
                } else {
                    break;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return playerId;
    }

    public Player createNewPlayer() {
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

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        return player;
    }

    public Player chosenPlayer(int playerId) {
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

