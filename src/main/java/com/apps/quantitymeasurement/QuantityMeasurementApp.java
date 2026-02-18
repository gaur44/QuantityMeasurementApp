package com.apps.quantitymeasurement;


public final class QuantityMeasurementApp {
    public static void main(String[] args) {
        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(1.0);

        System.out.println("Input: " + f1 + " and " + f2);
        System.out.println("Equal: " + f1.equals(f2));
    }
}
