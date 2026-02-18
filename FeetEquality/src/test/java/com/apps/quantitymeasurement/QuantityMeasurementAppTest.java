package com.apps.quantitymeasurement;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class QuantityMeasurementAppTest {

    @Test
    void testFeetEquality_SameValue() {
        Feet feet1 = new Feet(5.0);
        Feet feet2 = new Feet(5.0);

        assertTrue(feet1.equals(feet2));
    }

    @Test
    void testFeetEquality_DifferentValue() {
        Feet feet1 = new Feet(5.0);
        Feet feet2 = new Feet(6.0);

        assertFalse(feet1.equals(feet2));
    }

    @Test
    void testFeetEquality_SameReference() {
        Feet feet1 = new Feet(5.0);

        assertTrue(feet1.equals(feet1));
    }

    @Test
    void testFeetEquality_NullComparison() {
        Feet feet1 = new Feet(5.0);

        assertFalse(feet1.equals(null));
    }

    @Test
    void testFeetEquality_DifferentObjectType() {
        Feet feet1 = new Feet(5.0);

        assertFalse(feet1.equals("5.0"));
    }
}
