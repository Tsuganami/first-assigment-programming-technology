package org.example;

import org.example.players.Player;

import static org.example.GameConfig.HOUSE_PRICE;
import static org.example.GameConfig.PROPERTY_PRICE;

public class Property extends AbstractTile {
    Player owner;
    PropertyState propertyState;
    int Price = PROPERTY_PRICE;
    int HousePrice = HOUSE_PRICE;
    public Property() {
        this.owner = null;
        this.tileType = TileType.PROPERTY;
    }
    public Property(Player owner) {
        this.owner = owner;
        this.tileType = TileType.PROPERTY;


    }
    @Override
    public String toString() {
        return "PROPERTY{owner=" + (owner != null ? owner.toString() : "none") + ", HOUSE=" + propertyState + "}";
    }


}
