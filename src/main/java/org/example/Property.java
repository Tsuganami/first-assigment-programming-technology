package org.example;

import org.example.players.Player;

import static org.example.GameConfig.HOUSE_PRICE;
import static org.example.GameConfig.PROPERTY_PRICE;

public class Property extends AbstractTile {
    private PropertyState propertyState = PropertyState.EMPTY;
    private Player owner = null;
    private final int Price = PROPERTY_PRICE;
    private final int HousePrice = HOUSE_PRICE;
    
    /**
     * Constructor called from TileFactory to create a Property tile with no owner
     */
    public Property() {
        this.owner = null;
        this.setTileType(TileType.PROPERTY);
    }
    
    /**
     * Constructor called from TileFactory to create a Property tile with an owner
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
    
    @Override
    public void setOwner(Player owner) {
        this.owner = owner;
    }
    
    @Override
    public PropertyState getPropertyState() {
        return propertyState;
    }
    
    @Override
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
