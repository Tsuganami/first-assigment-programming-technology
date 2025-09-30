package org.example.players;

import org.example.AbstractTile;
import org.example.PropertyState;
import org.example.TileType;

import java.util.ArrayList;



public class GameCycle {
    /**
     *Method for printing out the Gameboard in a pretty way
     */
    public static void printGameBoard(ArrayList<AbstractTile> gameBoard) {
        System.out.println("\nGameBoard contents:");
        for (int i = 0; i < gameBoard.size(); i++) {
            AbstractTile tile = gameBoard.get(i);
            if (tile != null) {
                System.out.println("Tile " + i + ": " + tile.toString());
            } else {
                System.out.println("Tile " + i + ": null");
            }
        }
    }
    /**
     *Very important fucntion, in which the whole game loops
     */
    public static Boolean GameCycle(ArrayList<Player> players, ArrayList<AbstractTile> GameBoard){
        Boolean win = false;
        int rounds = 0;
        for (Player player : players){
            GameBoard.get(0).players.add(player);
            player.position = 0;
        }
        printGameBoard(GameBoard);

        while (!win && rounds < 100){
            rounds++;
            System.out.println("Round " + rounds);
            
            ArrayList<Player> activePlayers = new ArrayList<>(players);
            
            for (int i = 0; i < activePlayers.size(); i++){
                Player player = activePlayers.get(i);
                
                if (player.getMoney() < 0) continue;
                
                GameBoard.get(player.position).players.remove(player);
                player.position = Dice.ThrowDice(player.position, GameBoard.size());
                GameBoard.get(player.position).players.add(player);
                
                AbstractTile currentTile = GameBoard.get(player.position);
                
                switch (currentTile.getTileType()){
                    case LUCKY:
                        player.AddMoney(currentTile.getMoney());
                        break;
                    case SERVICE:
                        player.SubtractMoney(currentTile.getMoney());
                        if (player.getMoney() < 0) {
                            eliminatePlayer(player, GameBoard);
                            continue;
                        }
                        break;
                    case PROPERTY:
                        if (currentTile.getOwner() == null) {
                            if (player.BuyingDesicion(currentTile)) {
                                player.SubtractMoney(1000);
                                currentTile.setOwner(player);
                            }
                        } else if (currentTile.getOwner() == player) {
                            if (currentTile.getPropertyState() == PropertyState.EMPTY && player.BuyingDesicion(currentTile)) {
                                player.SubtractMoney(4000);
                                currentTile.setPropertyState(PropertyState.HOUSE);
                            }
                        } else {
                            int rent = currentTile.getPropertyState() == PropertyState.EMPTY ? 500 : 2000;
                            player.SubtractMoney(rent);
                            currentTile.getOwner().AddMoney(rent);
                            if (player.getMoney() < 0) {
                                eliminatePlayer(player, GameBoard);
                                continue;
                            }
                        }
                        break;
                }
            }
            
            activePlayers.removeIf(player -> player.getMoney() < 0);
            
            if (activePlayers.size() == 1) {
                System.out.println("Game Over! Winner: " + activePlayers.get(0));
                System.out.println("Winner's money: " + activePlayers.get(0).getMoney());
                printPlayerProperties(activePlayers.get(0), GameBoard);
                win = true;
            }
            
            if (rounds % 10 == 0) {
                System.out.println("Status after " + rounds + " rounds:");
                for (Player player : activePlayers) {
                    System.out.println(player + " owns " + countProperties(player, GameBoard) + " properties");
                }
            }
        }
        
        if (!win) {
            System.out.println("Game ended after 100 rounds without a clear winner");
            Player richest = findRichestPlayer(players);
            System.out.println("Richest player: " + richest);
            printPlayerProperties(richest, GameBoard);
        }

        return win;
    }
    /**
     *This function is needed to remove the player who already lost the game
     */
    private static void eliminatePlayer(Player player, ArrayList<AbstractTile> GameBoard) {
        System.out.println(player + " is eliminated!");
        for (AbstractTile tile : GameBoard) {
            if (tile.getOwner() == player) {
                tile.setOwner(null);
                tile.setPropertyState(PropertyState.EMPTY);
            }
        }
        player.setMoney(-1);
    }
    /**
     *This function is needed for convinience, to make it easier to print winning character
     */
    private static int countProperties(Player player, ArrayList<AbstractTile> GameBoard) {
        int count = 0;
        for (AbstractTile tile : GameBoard) {
            if (tile.getOwner() == player) {
                count++;
            }
        }
        return count;
    }
    /**
     *This function is needed for convinience, to make it easier to print winning character
     */
    private static void printPlayerProperties(Player player, ArrayList<AbstractTile> GameBoard) {
        System.out.println("Properties owned by " + player + ":");
        for (int i = 0; i < GameBoard.size(); i++) {
            if (GameBoard.get(i).getOwner() == player) {
                System.out.println("  Position " + i + ": " + GameBoard.get(i));
            }
        }
    }
    /**
     *This function is needed for convinience, to make it easier to find winning character
     */
    private static Player findRichestPlayer(ArrayList<Player> players) {
        Player richest = null;
        int maxWealth = -1;
        for (Player player : players) {
            if (player.getMoney() > maxWealth) {
                maxWealth = player.getMoney();
                richest = player;
            }
        }
        return richest;
    }
}
