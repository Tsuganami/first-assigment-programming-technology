package org.example;

import org.example.players.Player;

import java.util.ArrayList;

public class AbstractTile {
    private TileType tileType;
    public ArrayList<Player> players = new ArrayList<>();
    private PropertyState propertyState =  PropertyState.EMPTY;
    private Player owner = null;
    public TileType getTileType() {
        return tileType;
    }
    public  void setTileType(TileType tileType) {
        this.tileType = tileType;
    }

     public int getMoney(){
        return 0;
     }
     public PropertyState getPropertyState(){
        return propertyState;
     }
     public Player getOwner(){
        return owner;
     }

}
