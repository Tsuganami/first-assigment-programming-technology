
package org.example;
import org.example.players.Player;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.example.players.GameCycle.GameCycle;
import static org.example.players.GameCycle.printGameBoard;
import org.example.players.Dice;

public class Main {

    public static class GameValidationException extends Exception {
        public GameValidationException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        String gameFile = "src/game_1.txt";
        if (args.length > 0) {
            gameFile = args[0];
        }
        
        try {
            runGame(gameFile);
        } catch (GameValidationException e) {
            System.err.println("Game validation error: " + e.getMessage());
            System.exit(1);
        } catch (FileNotFoundException e) {
            System.err.println("Configuration file not found: " + e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Error reading configuration file: " + e.getMessage());
            System.exit(1);
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format in configuration file: " + e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void runGame(String gameFile) throws IOException, GameValidationException {
        int tiles = 0;
        int counter = 0;
        ArrayList<AbstractTile> gameBoard = new ArrayList<>();
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Short> diceRolls = new ArrayList<>();
        boolean tileCreation = false;
        boolean playerCreation = false;
        boolean diceRollsCreation = false;
        Set<String> playerNames = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(gameFile))) {
            System.out.println("Loading game configuration from: " + gameFile);
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (counter == 0) {
                    try {
                        tiles = Integer.parseInt(line);
                        validateTileCount(tiles);
                    } catch (NumberFormatException e) {
                        throw new GameValidationException("Invalid tile count format: " + line);
                    }
                    counter++;
                    continue;
                }

                if (line.equals("TILES")) {
                    tileCreation = true;
                    playerCreation = false;
                    diceRollsCreation = false;
                    counter++;
                    continue;
                } else if (line.equals("PLAYERS")) {
                    validateGameBoardSize(gameBoard, tiles);
                    tileCreation = false;
                    playerCreation = true;
                    diceRollsCreation = false;
                    counter++;
                    continue;
                } else if (line.equals("DICEROLLS NONE")) {
                    System.out.println("Using random dice rolls");
                    break;
                } else if (line.equals("DICEROLLS")) {
                    System.out.println("Reading dice rolls from file");
                    validatePlayersCount(players);
                    tileCreation = false;
                    playerCreation = false;
                    diceRollsCreation = true;
                    counter++;
                    continue;
                }

                if (tileCreation && !line.isEmpty()) {
                    processTileLine(line, gameBoard);
                } else if (playerCreation && !line.isEmpty()) {
                    processPlayerLine(line, players, playerNames);
                } else if (diceRollsCreation && !line.isEmpty()) {
                    processDiceRollLine(line, diceRolls);
                }

                counter++;
            }
        }

        validateFinalGameState(gameBoard, players, tiles);
        
        if (diceRolls.isEmpty()) {
            System.out.println("No dice rolls provided - using random generation");
        } else {
            System.out.println("Using " + diceRolls.size() + " dice rolls from file");
        }
        
        Dice.setDiceRolls(diceRolls);
        GameCycle(players, gameBoard);
    }

    private static void validateTileCount(int tiles) throws GameValidationException {
        if (tiles <= 0) {
            throw new GameValidationException("Tile count must be positive, got: " + tiles);
        }
        if (tiles > 100) {
            throw new GameValidationException("Tile count too large (max 100), got: " + tiles);
        }
        if (tiles < 3) {
            throw new GameValidationException("Minimum 3 tiles required for a game, got: " + tiles);
        }
    }

    private static void validateGameBoardSize(ArrayList<AbstractTile> gameBoard, int expectedTiles) throws GameValidationException {
        if (gameBoard.size() != expectedTiles) {
            throw new GameValidationException("Expected " + expectedTiles + " tiles, but created " + gameBoard.size());
        }
    }

    private static void validatePlayersCount(ArrayList<Player> players) throws GameValidationException {
        if (players.isEmpty()) {
            throw new GameValidationException("At least one player is required");
        }
        if (players.size() > 8) {
            throw new GameValidationException("Maximum 8 players allowed, got: " + players.size());
        }
    }

    private static void processTileLine(String line, ArrayList<AbstractTile> gameBoard) throws GameValidationException {
        String[] parts = line.split(" ");
        if (parts.length < 2) {
            throw new GameValidationException("Invalid tile format: " + line + ". Expected: TileType Value");
        }

        String tileType = parts[0];
        validateTileType(tileType);

        try {
            int value = Integer.parseInt(parts[1]);
            validateTileValue(tileType, value);
            
            System.out.println("Creating tile: " + line);
            AbstractTile tile = TileFactory.createTile(tileType, value);
            if (tile == null) {
                throw new GameValidationException("Failed to create tile of type: " + tileType);
            }
            gameBoard.add(tile);
        } catch (NumberFormatException e) {
            throw new GameValidationException("Invalid tile value format: " + parts[1]);
        }
    }

    private static void validateTileType(String tileType) throws GameValidationException {
        if (!tileType.equals("Property") && !tileType.equals("Service") && !tileType.equals("Lucky")) {
            throw new GameValidationException("Invalid tile type: " + tileType + ". Must be Property, Service, or Lucky");
        }
    }

    private static void validateTileValue(String tileType, int value) throws GameValidationException {
        if (tileType.equals("Property") && value != 0) {
            throw new GameValidationException("Property tiles must have value 0, got: " + value);
        }
        if (tileType.equals("Service") && value <= 0) {
            throw new GameValidationException("Service tiles must have positive cost, got: " + value);
        }
        if (tileType.equals("Service") && value > 5000) {
            throw new GameValidationException("Service cost too high (max 5000), got: " + value);
        }
        if (tileType.equals("Lucky") && value <= 0) {
            throw new GameValidationException("Lucky tiles must have positive reward, got: " + value);
        }
        if (tileType.equals("Lucky") && value > 10000) {
            throw new GameValidationException("Lucky reward too high (max 10000), got: " + value);
        }
    }

    private static void processPlayerLine(String line, ArrayList<Player> players, Set<String> playerNames) throws GameValidationException {
        String[] parts = line.split(":");
        if (parts.length < 2) {
            throw new GameValidationException("Invalid player format: " + line + ". Expected: PlayerName:Strategy");
        }

        String playerName = parts[0].trim();
        String playerType = parts[1].trim();

        validatePlayerName(playerName, playerNames);
        validatePlayerStrategy(playerType);

        System.out.println("Creating player: " + playerName + " (" + playerType + ")");
        players.add(new Player(playerName, playerType));
        playerNames.add(playerName);
    }

    private static void validatePlayerName(String playerName, Set<String> existingNames) throws GameValidationException {
        if (playerName.isEmpty()) {
            throw new GameValidationException("Player name cannot be empty");
        }
        if (playerName.length() > 20) {
            throw new GameValidationException("Player name too long (max 20 characters): " + playerName);
        }
        if (existingNames.contains(playerName)) {
            throw new GameValidationException("Duplicate player name: " + playerName);
        }
        if (!playerName.matches("[a-zA-Z0-9_\\s]+")) {
            throw new GameValidationException("Player name contains invalid characters: " + playerName);
        }
    }

    private static void validatePlayerStrategy(String strategy) throws GameValidationException {
        if (!strategy.equals("GREEDY") && !strategy.equals("CAREFUL") && !strategy.equals("TACTICAL")) {
            throw new GameValidationException("Invalid player strategy: " + strategy + ". Must be GREEDY, CAREFUL, or TACTICAL");
        }
    }

    private static void processDiceRollLine(String line, ArrayList<Short> diceRolls) throws GameValidationException {
        try {
            short diceValue = Short.parseShort(line);
            validateDiceValue(diceValue);
            diceRolls.add(diceValue);
        } catch (NumberFormatException e) {
            throw new GameValidationException("Invalid dice roll value: " + line);
        }
    }

    private static void validateDiceValue(short diceValue) throws GameValidationException {
        if (diceValue < 1 || diceValue > 6) {
            throw new GameValidationException("Dice value must be between 1 and 6, got: " + diceValue);
        }
    }

    private static void validateFinalGameState(ArrayList<AbstractTile> gameBoard, ArrayList<Player> players, int expectedTiles) throws GameValidationException {
        if (gameBoard.size() != expectedTiles) {
            throw new GameValidationException("Final validation failed: Expected " + expectedTiles + " tiles, got " + gameBoard.size());
        }
        
        if (players.isEmpty()) {
            throw new GameValidationException("No players created");
        }

        boolean hasProperty = false;
        for (AbstractTile tile : gameBoard) {
            if (tile.getTileType() == TileType.PROPERTY) {
                hasProperty = true;
                break;
            }
        }
        if (!hasProperty) {
            throw new GameValidationException("Game board must contain at least one property tile");
        }

        System.out.println("Validation successful: " + gameBoard.size() + " tiles, " + players.size() + " players");
    }
}