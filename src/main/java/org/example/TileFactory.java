package org.example;

class TileFactory {
    static AbstractTile createTile(String type, int amount) {
        switch (type) {
            case "Lucky":
                return new Lucky(amount);
            case "Service":
                return new Service(amount);
            case "Property":
                return new Property();

        }
        return null;


    }
}
