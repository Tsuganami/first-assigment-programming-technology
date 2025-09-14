package org.example.players;

import org.example.Strategy;

final class PlayerFactory {
    public static Player create(String Name, Strategy strategy){
        switch(strategy){
            (strategy.GREEDY){
                return new Player(Name, Strategy.GREEDY);
            }
            (strategy.TACTICAL){
                return new Player(Name, Strategy.TACTICAL);
            }
            (strategy.CAREFUL){
                return new Player(Name, Strategy.CAREFUL);

            }
        }

    }

}
