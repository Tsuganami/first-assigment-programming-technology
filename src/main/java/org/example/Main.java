
package org.example;
import org.example.players.Player;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.example.players.GameCycle.GameCycle;
import static org.example.players.GameCycle.printGameBoard;
import org.example.players.Dice;


public class Main {


    public static void main(String[] args) throws IOException {
        int tiles = 0;
        int counter = 0;
        ArrayList<AbstractTile> gameBoard = new ArrayList<>();
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Short> diceRolls = new ArrayList<>();
        boolean tileCreation = false;
        boolean playerCreation = false;
        boolean diceRollsCreation = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("src/game_1.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (counter == 0) {
                    tiles = Integer.parseInt(line);
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
                    tileCreation = false;
                    playerCreation = true;
                    diceRollsCreation = false;
                    counter++;
                    continue;
                } else if (line.equals("DICEROLLS NONE")) {
                    break;
                } else if (line.equals("DICEROLLS")) {
                    tileCreation = false;
                    playerCreation = false;
                    diceRollsCreation = true;
                    counter++;
                    continue;
                }


                if (tileCreation && !line.isEmpty()) {
                    String[] parts = line.split(" ");
                    if (parts.length >= 2) {
                        String tileType = parts[0];
                        int value = Integer.parseInt(parts[1]);
                        System.out.println("Creating tile: " + line);
                        gameBoard.add(TileFactory.createTile(tileType, value));
                    }
                } else if (playerCreation && !line.isEmpty()) {
                    String[] parts = line.split(":");
                    if (parts.length >= 2) {
                        String playerName = parts[0].trim();
                        String playerType = parts[1].trim();
                        System.out.println("Creating player: " + playerName + " (" + playerType + ")");

                        players.add(new Player(playerName,playerType));

                    }
                } else if (diceRollsCreation && !line.isEmpty()) {
                    try {
                        short diceValue = Short.parseShort(line);
                        diceRolls.add(diceValue);
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid dice roll value: " + line);
                    }
                }

                counter++;
            }
        }
        
        Dice.setDiceRolls(diceRolls);
        GameCycle(players,gameBoard);


        // Print results
        //System.out.println("Total tiles expected: " + tiles);
        //System.out.println("Tiles created: " + gameBoard.size());
        //System.out.println("Players created: " + players.size());
        //System.out.println("Dice rolls: " + diceRolls.size());

        //System.out.println("\nGameBoard contents:");
        //for (int i = 0; i < gameBoard.size(); i++) {
        //    AbstractTile tile = gameBoard.get(i);
        //    if (tile != null) {
        //        System.out.println("Tile " + i + ": " + tile.toString());
        //    } else {
        //        System.out.println("Tile " + i + ": null");
        //    }
        //}
        //printGameBoard(gameBoard);



    }
}