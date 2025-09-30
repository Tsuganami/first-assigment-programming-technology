package org.example;

import org.example.players.Player;

import static org.example.GameConfig.HOUSE_PRICE;
import static org.example.GameConfig.PROPERTY_PRICE;

public class Property extends AbstractTile {
    PropertyState propertyState = PropertyState.EMPTY;
    Player owner = null;
    int Price = PROPERTY_PRICE;
    int HousePrice = HOUSE_PRICE;
    /**
     *Constructor it's been called from TileFactory to create a Property tile in case there is no owner
     */
    public Property() {
        this.owner = null;
        this.setTileType(TileType.PROPERTY);
    }
    /**
     *Constructor it's been called from TileFactory to create a Property tile in case there is owner
     */
    public Property(Player owner) {
        this.owner = owner;
        this.setTileType(TileType.PROPERTY);


    }

    @Override
    public int getMoney() {
        if (propertyState.equals(PropertyState.EMPTY)) {
            return Price;
        }
        else if (propertyState.equals(PropertyState.HOUSE)) {
            return HousePrice;
        }
        return Price;
    }

    @Override
    public Player getOwner() {
        return owner;
    }
    
    public void setOwner(Player owner) {
        this.owner = owner;
    }
    
    @Override
    public PropertyState getPropertyState() {
        return propertyState;
    }
    
    public void setPropertyState(PropertyState propertyState) {
        this.propertyState = propertyState;
    }
    /**
     *Method for printing out the tile in a pretty way
     */
    @Override
    public String toString() {
        String result = "PROPERTY{owner=" + (owner != null ? owner.toString() : "none") + ", HOUSE=" + propertyState;
        if (players != null && !players.isEmpty()) {
            result += ", players=" + players;
        }

        return result + '}';
    }


}
