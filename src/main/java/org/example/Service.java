package org.example;

public class Service extends AbstractTile {
    int amount = 0;
    public Service(int amount) {
        this.amount = amount;
        this.setTileType(TileType.SERVICE);
    }

    @Override
    public int  getMoney() {
        return amount;
    }
    @Override
    public String toString() {
        String result = "Service{amount=" + amount ;
        if (players != null && !players.isEmpty()) {
            result += ", players=" + players;
        }

        return result + '}';
    }

}
