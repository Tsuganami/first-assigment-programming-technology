package org.example.players;

import org.example.Strategy;

public final class Player {
    private String name;
    private Strategy strategy = null;
    private int money = 10000;
    private int buy_counter = 0;
    public Player(String Name, Strategy strategy){
        this.name = Name;
        this.strategy =  strategy;

    }
    @Override
    public String toString(){
        return "Player{name=" + name + ", strategy=" + strategy + ", money=" + money + "}";
    }





}
