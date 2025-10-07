package org.example;

import org.example.players.Player;

import java.util.ArrayList;

public abstract class AbstractTile {
    private TileType tileType;
    public ArrayList<Player> players = new ArrayList<>();
    
    public TileType getTileType() {
        return tileType;
    }
    
    protected void setTileType(TileType tileType) {
        this.tileType = tileType;
    }

    /**
     * Abstract method to get money value associated with this tile.
     * Implementation varies by tile type.
     */
    public abstract int getMoney();
    
    /**
     * Get property state. Default implementation for non-property tiles.
     * Override in Property class for actual property state management.
     */
    public PropertyState getPropertyState() {
        return PropertyState.EMPTY; 
    }
    
    /**
     * Get owner. Default implementation for non-ownable tiles.
     * Override in Property class for actual ownership management.
     */
    public Player getOwner() {
        return null; 
    }

    /**
     * Set owner. Default implementation does nothing for non-ownable tiles.
     * Override in Property class for actual ownership management.
     */
    public void setOwner(Player owner) {

    }
    
    /**
     * Set property state. Default implementation does nothing for non-property tiles.
     * Override in Property class for actual property state management.
     */
    public void setPropertyState(PropertyState propertyState) {

    }

}
