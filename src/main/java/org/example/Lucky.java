package org.example;

public class Lucky extends AbstractTile {
    private int amount;
    /**
     *Constructor it's been called from TileFactory to create a Lucky tile
     */
    public Lucky(int amount) {
        this.setTileType(TileType.LUCKY);
        this.amount = amount;
    }
    @Override
    public int getMoney() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     *Method for printing out the tile in a pretty way
     */
    @Override
    public String toString() {
        String result = "Lucky{amount=" + amount;

        if (players != null && !players.isEmpty()) {
            result += ", players=" + players;
        }

        return result + '}';
    }
}
