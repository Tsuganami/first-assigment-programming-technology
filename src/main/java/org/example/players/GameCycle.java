package org.example.players;

import org.example.AbstractTile;
import org.example.TileType;

import java.util.ArrayList;



public class GameCycle {

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
    public static Boolean GameCycle(ArrayList<Player> players, ArrayList<AbstractTile> GameBoard){
        Boolean win =  false;
        //Initial positioning, put all of the players on the first square
        for (Player player : players){
            GameBoard.get(0).players.add(player);
            player.position = 0;
        }
        printGameBoard(GameBoard);

        while (!win){

            for (Player player : players){
                //Move
                player.position = Dice.ThrowDice(player.position,GameBoard.size());
                //Buying - Paying
                switch (GameBoard.get(player.position).getTileType()){
                    case LUCKY{
                        player.AddMoney(GameBoard.get(player.position).getMoney());

                    }




                }
            }



            //Buying and paying

            //Win lose check

        }

    return false;
    }
}
