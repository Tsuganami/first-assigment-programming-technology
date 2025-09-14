package org.example.players;

final class Player {
    private String name;
    private final Buyingpolicy;
    private int money = 10000;
    public Player(String Name, BuyingPolicy Buyingpolicy){
        this.name = Name;
        this.Buyingpolicy =  Buyingpolicy;

    }




}
