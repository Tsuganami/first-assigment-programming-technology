package org.example;

public class Lucky extends AbstractTile {
    private int amount;
    public Lucky(int amount) {
        this.setTileType(TileType.LUCKY);
        this.amount = amount;
    }
    int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }


    @Override
    public String toString() {
        String result = "Lucky{amount=" + amount;

        if (players != null && !players.isEmpty()) {
            result += ", players=" + players;
        }

        return result + '}';
    }
}
