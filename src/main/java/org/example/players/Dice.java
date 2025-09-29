package org.example.players;
import java.util.ArrayList;
import java.util.Random;

import static org.example.GameConfig.DICE_RANGE;

public class Dice {
    private static ArrayList<Short> diceRolls = new ArrayList<>();
    private static int rollIndex = 0;
    private static boolean useFileRolls = false;
    
    public static void setDiceRolls(ArrayList<Short> rolls) {
        diceRolls = rolls;
        rollIndex = 0;
        useFileRolls = !rolls.isEmpty();
    }

    public static int ThrowDice(int position, int GameBoardSize) {
        int diceValue;
        
        if (useFileRolls && rollIndex < diceRolls.size()) {
            diceValue = diceRolls.get(rollIndex);
            rollIndex++;
        } else {
            Random rand = new Random();
            diceValue = rand.nextInt(DICE_RANGE) + 1;
        }
        
        return (position + diceValue) % GameBoardSize;
    }
}
