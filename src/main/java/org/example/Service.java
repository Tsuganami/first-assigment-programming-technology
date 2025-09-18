package org.example;

public class Service extends AbstractTile {
    int amount = 0;
    public Service(int amount) {
        this.amount = amount;
        tileType = TileType.SERVICE;
    }

    @Override
    public String toString() {
        return "Service{amount=" + amount + '}';
    }

}
