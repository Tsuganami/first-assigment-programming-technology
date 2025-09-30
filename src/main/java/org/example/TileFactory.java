package org.example;

class TileFactory {
    /**
     *Method for creating Tiles based on the provided tiletype in the textfile
     *
     *
     *
     */
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
