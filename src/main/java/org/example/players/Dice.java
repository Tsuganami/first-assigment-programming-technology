package org.example.players;
import java.util.ArrayList;
import java.util.Random;

import static org.example.GameConfig.DICE_RANGE;

public class Dice {
    private static ArrayList<Short> diceRolls = new ArrayList<>();
    private static int rollIndex = 0;
    private static boolean useFileRolls = false;
    /**
     *This function is used to set dice rolls in, if you have them defined in the files
     */
    public static void setDiceRolls(ArrayList<Short> rolls) {
        diceRolls = rolls;
        rollIndex = 0;
        useFileRolls = rolls != null && !rolls.isEmpty();
        if (useFileRolls) {
            System.out.println("Dice configured to use " + rolls.size() + " predefined rolls");
        } else {
            System.out.println("Dice configured for random generation");
        }
    }
    /**
     *Random dice throws generations with protection from going over the size of the board
     */
    public static int ThrowDice(int position, int GameBoardSize) {
        int diceValue;
        
        if (useFileRolls && rollIndex < diceRolls.size()) {
            diceValue = diceRolls.get(rollIndex);
            rollIndex++;
            System.out.println("Using file dice roll: " + diceValue);
        } else {
            Random rand = new Random();
            diceValue = rand.nextInt(DICE_RANGE) + 1;
            if (useFileRolls && rollIndex >= diceRolls.size()) {
                System.out.println("File dice rolls exhausted, switching to random: " + diceValue);
            } else {
                System.out.println("Random dice roll: " + diceValue);
            }
        }
        
        return (position + diceValue) % GameBoardSize;
    }
}
