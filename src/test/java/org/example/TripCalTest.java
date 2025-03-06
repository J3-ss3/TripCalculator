package org.example;

import static org.junit.jupiter.api.Assertions.*;

class TripCalTest {
    TripCal tripCal = new TripCal();

        @org.junit.jupiter.api.Test
        void calculateCost() {
            TripCal tripCal = new TripCal();
            double result = tripCal.calculateCost(100, 5, 1.5);
            assertEquals(7.5, result);
        }
}