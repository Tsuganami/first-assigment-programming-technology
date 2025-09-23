package org.example.players;
import java.util.Random;

import static org.example.GameConfig.DICE_RANGE;

public class Dice {
    int GameBoardSize = 0;
    int range  = DICE_RANGE;

    public static int ThrowDice(int position,int GameBoardSize ){
        Random rand = new Random();
        return (position + rand.nextInt(DICE_RANGE) + 1) % GameBoardSize;
    }


}
