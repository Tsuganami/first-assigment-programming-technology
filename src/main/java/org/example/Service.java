package org.example;

public class Service extends AbstractTile {
    int amount = 0;
    /**
     *Constructor it's been called from TileFactory to create a Service tile
     */
    public Service(int amount) {
        this.amount = amount;
        this.setTileType(TileType.SERVICE);
    }

    @Override
    public int  getMoney() {
        return amount;
    }
    /**
     *Method for printing out the tile in a pretty way
     */
    @Override
    public String toString() {
        String result = "Service{amount=" + amount ;
        if (players != null && !players.isEmpty()) {
            result += ", players=" + players;
        }

        return result + '}';
    }

}
