package org.example;

import org.example.players.Dice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiceTest {

    @Test
    @DisplayName("Dice should return values between 1 and 6")
    void testRandomDiceRange() {
        for (int i = 0; i < 100; i++) {
            int result = Dice.ThrowDice(0, 10);
            assertTrue(result >= 0 && result < 10, "Position should be within board size");
        }
    }

    @Test
    @DisplayName("Dice should use predefined rolls when provided")
    void testPredefinedDiceRolls() {
        ArrayList<Short> testRolls = new ArrayList<>();
        testRolls.add((short) 3);
        testRolls.add((short) 5);

        Dice.setDiceRolls(testRolls);

        int firstResult = Dice.ThrowDice(0, 10);
        int secondResult = Dice.ThrowDice(0, 10);

        assertEquals(3, firstResult);
        assertEquals(5, secondResult);
    }
}