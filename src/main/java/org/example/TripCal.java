package org.example;

import java.util.Scanner;

class TripCal {
    int kilometer;
    double fuelConsume;
    double fuelPrice;
    double totalCost;

    public static void main(String[] args) {
        System.out.println("How many kilometers you will travel?");
        Scanner scanner = new Scanner(System.in);
        int kilometer = scanner.nextInt();
        System.out.println("How many liters of fuel does your car consume per 100 km?");
        double fuelConsume = scanner.nextDouble();
        System.out.println("How much does a liter of fuel cost?");
        double fuelPrice = scanner.nextDouble();
        double totalCost = calculateCost(kilometer, fuelConsume, fuelPrice);
        System.out.println("The total cost of the trip is: " + totalCost + " euros.");
    }

    public static double calculateCost(int kilometer, double fuelConsume, double fuelPrice) {
        double fuelNeeded = kilometer * fuelConsume / 100;
        double totalCost = fuelNeeded * fuelPrice;
        return totalCost;
    }
}