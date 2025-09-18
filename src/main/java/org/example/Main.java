package org.example;
import org.example.players.Player;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) throws IOException {
        int Tiles = 0;
        int counter = 0;
        ArrayList<AbstractTile> GameBoard = new ArrayList<>();
        ArrayList<Player> Players = new ArrayList<>();
        //used in case if we pass the dice as the file
        ArrayList<Short> DiceRolls = new ArrayList<>();
        boolean tile_creation = false;
        boolean player_creation = false;
        boolean dicerolls_creation = false;

        for(String line : Files.readAllLines(Path.of("src/game_1.txt"))){
            if (counter == 0){
                Tiles = Integer.parseInt(line.trim());
            }

            if (tile_creation){
                System.out.println(line);
                GameBoard.add(TileFactory.createTile(line.split(" ")[0],Integer.parseInt(line.split(" ")[1])));
            }




            if (line.trim().equals("TILES")){
                tile_creation = true;

            }
            else if (line.trim().equals("PLAYERS")){
                tile_creation = false;
                player_creation = true;

            }
            else if (line.trim().equals("DICEROLLS NONE")){
                break;
            }
            else if  (line.trim().equals("DICEROLLS")){
                tile_creation = false;
                player_creation = false;
                dicerolls_creation = false;
            }






            if (tile_creation){
                GameBoard.add(null);
            }
            if (player_creation){
                Players.add(null);
            }
            if (dicerolls_creation){
                DiceRolls.add(null);
            }




        counter++;
        }
        for (AbstractTile tile : GameBoard){
            System.out.println("test");
            System.out.println(tile.toString());
        }
        }



    }


