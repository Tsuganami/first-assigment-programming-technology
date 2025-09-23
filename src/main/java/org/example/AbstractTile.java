package org.example;

import org.example.players.Player;

import java.util.ArrayList;

public class AbstractTile {
    private TileType tileType;
    public ArrayList<Player> players = new ArrayList<>();

    public TileType getTileType() {
        return tileType;
    }
    public  void setTileType(TileType tileType) {
        this.tileType = tileType;
    }
}
