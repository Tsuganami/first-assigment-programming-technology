package org.example;

import org.example.players.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;




    public class PlayerTest {

        private Player greedyPlayer;
        private Player carefulPlayer;
        private Player tacticalPlayer;

        @BeforeEach
        void setUp() {
            greedyPlayer = new Player("Alice", "GREEDY");
            carefulPlayer = new Player("Bob", "CAREFUL");
            tacticalPlayer = new Player("Charlie", "TACTICAL");
        }

        @Test
        @DisplayName("Player should start with 10000 money")
        void testInitialMoney() {
            assertEquals(10000, greedyPlayer.getMoney());
            assertEquals(10000, carefulPlayer.getMoney());
            assertEquals(10000, tacticalPlayer.getMoney());
        }

        @Test
        @DisplayName("Adding money should increase balance")
        void testAddMoney() {
            greedyPlayer.AddMoney(500);
            assertEquals(10500, greedyPlayer.getMoney());
        }

        @Test
        @DisplayName("Subtracting money should decrease balance")
        void testSubtractMoney() {
            greedyPlayer.SubtractMoney(1000);
            assertEquals(9000, greedyPlayer.getMoney());
        }

        @Test
        @DisplayName("Adding negative money should throw exception")
        void testAddNegativeMoney() {
            assertThrows(IllegalArgumentException.class, () -> {
                greedyPlayer.AddMoney(-100);
            });
        }

        @Test
        @DisplayName("Greedy player should buy available property")
        void testGreedyBuyingDecision() {
            Property property = new Property();
            property.setOwner(null); // Unowned property

            assertTrue(greedyPlayer.BuyingDesicion(property));
        }

        @Test
        @DisplayName("Careful player should not buy when low on money")
        void testCarefulBuyingDecision() {
            carefulPlayer.setMoney(1500); // Less than 2000 required
            Property property = new Property();
            property.setOwner(null);

            assertFalse(carefulPlayer.BuyingDesicion(property));
        }

        @Test
        @DisplayName("Tactical player should skip every second opportunity")
        void testTacticalBuyingDecision() {
            Property property = new Property();
            property.setOwner(null);

            // First call - should buy (counter = 1)
            boolean firstDecision = tacticalPlayer.BuyingDesicion(property);
            // Second call - should skip (counter = 2)
            boolean secondDecision = tacticalPlayer.BuyingDesicion(property);

            assertTrue(firstDecision);
            assertFalse(secondDecision);
        }
    }

