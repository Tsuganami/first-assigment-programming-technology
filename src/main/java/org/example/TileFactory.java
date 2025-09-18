package org.example;

class TileFactory {
    static AbstractTile createTile(String type, int amount) {
        switch (type) {
            case "LUCKY":
                return new Lucky(amount);
            case "SERVICE":
                return new Service(amount);
            case "PROPERTY":
                return new Property();

        }
        return null;


    }
}
