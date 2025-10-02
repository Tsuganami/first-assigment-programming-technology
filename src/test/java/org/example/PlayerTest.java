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
        //Black box testing
        @Test
        @DisplayName("Player should start with 10000 money")
        void testInitialMoney() {
            assertEquals(10000, greedyPlayer.getMoney());
            assertEquals(10000, carefulPlayer.getMoney());
            assertEquals(10000, tacticalPlayer.getMoney());
        }
        //Black box testing
        @Test
        @DisplayName("Adding money should increase balance")
        void testAddMoney() {
            greedyPlayer.AddMoney(500);
            assertEquals(10500, greedyPlayer.getMoney());
        }
        //Black box testing
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
        //whitebox testing
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

        
        @Test
        @DisplayName("Greedy player - unowned property with sufficient money")
        void testGreedyUnownedPropertySufficientMoney() {
            greedyPlayer.setMoney(2000);
            Property property = new Property();
            property.setOwner(null);

            assertTrue(greedyPlayer.BuyingDesicion(property));
        }

        @Test
        @DisplayName("Greedy player - unowned property with insufficient money")
        void testGreedyUnownedPropertyInsufficientMoney() {
            greedyPlayer.setMoney(500);
            Property property = new Property();
            property.setOwner(null);

            assertFalse(greedyPlayer.BuyingDesicion(property));
        }

        @Test
        @DisplayName("Greedy player - owned property with EMPTY state and sufficient money")
        void testGreedyOwnedPropertyEmptySufficientMoney() {
            greedyPlayer.setMoney(5000);
            Property property = new Property();
            property.setOwner(greedyPlayer);
            property.setPropertyState(PropertyState.EMPTY);

            assertTrue(greedyPlayer.BuyingDesicion(property));
        }

        @Test
        @DisplayName("Greedy player - owned property with EMPTY state and insufficient money")
        void testGreedyOwnedPropertyEmptyInsufficientMoney() {
            greedyPlayer.setMoney(3000);
            Property property = new Property();
            property.setOwner(greedyPlayer);
            property.setPropertyState(PropertyState.EMPTY);

            assertFalse(greedyPlayer.BuyingDesicion(property));
        }

        @Test
        @DisplayName("Greedy player - owned property with HOUSE state")
        void testGreedyOwnedPropertyWithHouse() {
            greedyPlayer.setMoney(10000);
            Property property = new Property();
            property.setOwner(greedyPlayer);
            property.setPropertyState(PropertyState.HOUSE);

            assertFalse(greedyPlayer.BuyingDesicion(property));
        }

        @Test
        @DisplayName("Greedy player - non-property tile (Service)")
        void testGreedyNonPropertyTile() {
            Service service = new Service(100);

            assertFalse(greedyPlayer.BuyingDesicion(service));
        }

        
        @Test
        @DisplayName("Careful player - unowned property with money >= 2000")
        void testCarefulUnownedPropertySufficientMoney() {
            carefulPlayer.setMoney(2500);
            Property property = new Property();
            property.setOwner(null);

            assertTrue(carefulPlayer.BuyingDesicion(property));
        }

        @Test
        @DisplayName("Careful player - unowned property with money < 2000")
        void testCarefulUnownedPropertyInsufficientMoney() {
            carefulPlayer.setMoney(1500);
            Property property = new Property();
            property.setOwner(null);

            assertFalse(carefulPlayer.BuyingDesicion(property));
        }

        @Test
        @DisplayName("Careful player - owned property with EMPTY state and money >= 8000")
        void testCarefulOwnedPropertyEmptySufficientMoney() {
            carefulPlayer.setMoney(9000);
            Property property = new Property();
            property.setOwner(carefulPlayer);
            property.setPropertyState(PropertyState.EMPTY);

            assertTrue(carefulPlayer.BuyingDesicion(property));
        }

        @Test
        @DisplayName("Careful player - owned property with EMPTY state and money < 8000")
        void testCarefulOwnedPropertyEmptyInsufficientMoney() {
            carefulPlayer.setMoney(7000);
            Property property = new Property();
            property.setOwner(carefulPlayer);
            property.setPropertyState(PropertyState.EMPTY);

            assertFalse(carefulPlayer.BuyingDesicion(property));
        }

        @Test
        @DisplayName("Careful player - owned property with HOUSE state")
        void testCarefulOwnedPropertyWithHouse() {
            carefulPlayer.setMoney(10000);
            Property property = new Property();
            property.setOwner(carefulPlayer);
            property.setPropertyState(PropertyState.HOUSE);

            assertFalse(carefulPlayer.BuyingDesicion(property));
        }

        @Test
        @DisplayName("Careful player - non-property tile (Service)")
        void testCarefulNonPropertyTile() {
            Service service = new Service(100);

            assertFalse(carefulPlayer.BuyingDesicion(service));
        }

        
        @Test
        @DisplayName("Tactical player - odd counter (should consider buying), unowned property, sufficient money")
        void testTacticalOddCounterUnownedSufficientMoney() {
            tacticalPlayer.setBuyCounter(0); 
            tacticalPlayer.setMoney(2000);
            Property property = new Property();
            property.setOwner(null);

            assertTrue(tacticalPlayer.BuyingDesicion(property));
            assertEquals(1, tacticalPlayer.getBuyCounter());
        }

        @Test
        @DisplayName("Tactical player - even counter (should skip)")
        void testTacticalEvenCounterSkip() {
            tacticalPlayer.setBuyCounter(1); 
            tacticalPlayer.setMoney(10000);
            Property property = new Property();
            property.setOwner(null);

            assertFalse(tacticalPlayer.BuyingDesicion(property));
            assertEquals(2, tacticalPlayer.getBuyCounter());
        }

        @Test
        @DisplayName("Tactical player - odd counter, unowned property, insufficient money")
        void testTacticalOddCounterUnownedInsufficientMoney() {
            tacticalPlayer.setBuyCounter(0); 
            tacticalPlayer.setMoney(500);
            Property property = new Property();
            property.setOwner(null);

            assertFalse(tacticalPlayer.BuyingDesicion(property));
            assertEquals(1, tacticalPlayer.getBuyCounter());
        }

        @Test
        @DisplayName("Tactical player - odd counter, owned property with EMPTY state, sufficient money")
        void testTacticalOddCounterOwnedEmptySufficientMoney() {
            tacticalPlayer.setBuyCounter(0); 
            tacticalPlayer.setMoney(5000);
            Property property = new Property();
            property.setOwner(tacticalPlayer);
            property.setPropertyState(PropertyState.EMPTY);

            assertTrue(tacticalPlayer.BuyingDesicion(property));
            assertEquals(1, tacticalPlayer.getBuyCounter());
        }

        @Test
        @DisplayName("Tactical player - odd counter, owned property with EMPTY state, insufficient money")
        void testTacticalOddCounterOwnedEmptyInsufficientMoney() {
            tacticalPlayer.setBuyCounter(0); 
            tacticalPlayer.setMoney(3000);
            Property property = new Property();
            property.setOwner(tacticalPlayer);
            property.setPropertyState(PropertyState.EMPTY);

            assertFalse(tacticalPlayer.BuyingDesicion(property));
            assertEquals(1, tacticalPlayer.getBuyCounter());
        }

        @Test
        @DisplayName("Tactical player - non-property tile (Service)")
        void testTacticalNonPropertyTile() {
            int initialCounter = tacticalPlayer.getBuyCounter();
            Service service = new Service(100);

            assertFalse(tacticalPlayer.BuyingDesicion(service));
            assertEquals(initialCounter, tacticalPlayer.getBuyCounter());
        }

       
    }

