package org.example;

public class Lucky extends AbstractTile {
    int amount;
    public Lucky(int amount) {
        this.tileType = TileType.LUCKY;
        this.amount = amount;
    }
    @Override
    public String toString() {
        return "Lucky{amount=" + amount + '}';
    }
}
