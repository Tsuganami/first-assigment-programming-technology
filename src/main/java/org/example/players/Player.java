package org.example.players;

import org.example.AbstractTile;
import org.example.Strategy;
import org.example.PropertyState;
import org.example.TileType;

public final class Player {
    public int position = 0;
    private String name;
    private Strategy strategy = null;
    private int money = 10000;
    private int BuyCounter = 0;
    public Player(String Name, String strategy){
        this.name = Name;
        this.strategy =  Strategy.valueOf(strategy);

    }
    public int getMoney() {
        return money;
    }
    public void setMoney(int money) {
        this.money = money;
    }
    public void AddMoney(int money){
        if (money < 0){
            throw new  IllegalArgumentException("Cannot add negative amount of money:" + money);
        }
        this.money += money;

    }
    public void SubtractMoney(int money){
        if (money < 0){
            throw new  IllegalArgumentException("Cannot subtract negative amount of money:" + money);
        }

        this.money -= money;

    }
    public Boolean BuyingDesicion(AbstractTile tile){
        switch (this.strategy){
            case GREEDY:
                if (tile.getTileType() == TileType.PROPERTY) {
                    if (tile.getOwner() == null && this.money >= 1000) {
                        return true;
                    }
                    if (tile.getOwner() == this && tile.getPropertyState() == PropertyState.EMPTY && this.money >= 4000) {
                        return true;
                    }
                }
                return false;
            case CAREFUL:
                if (tile.getTileType() == TileType.PROPERTY) {
                    if (tile.getOwner() == null && this.money >= 2000) {
                        return true;
                    }
                    if (tile.getOwner() == this && tile.getPropertyState() == PropertyState.EMPTY && this.money >= 8000) {
                        return true;
                    }
                }
                return false;
            case TACTICAL:
                if (tile.getTileType() == TileType.PROPERTY) {
                    this.BuyCounter++;
                    if (this.BuyCounter % 2 == 0) {
                        return false;
                    }
                    if (tile.getOwner() == null && this.money >= 1000) {
                        return true;
                    }
                    if (tile.getOwner() == this && tile.getPropertyState() == PropertyState.EMPTY && this.money >= 4000) {
                        return true;
                    }
                }
                return false;
        }
        return false;
    }


    public int getBuyCounter() {
        return BuyCounter;
    }
    public void setBuyCounter(int buyCounter) {
        BuyCounter = buyCounter;
    }

    @Override
    public String toString(){
        return "Player{name=" + name + ", strategy=" + strategy + ", money=" + money + "}";
    }





}
