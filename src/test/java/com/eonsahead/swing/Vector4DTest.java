/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eonsahead.swing;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Brian Cochran
 */
public class Vector4DTest {
    
    private final static double EPSILON = 1E-8;
    
    public Vector4DTest() {
    }

    /**
     * Test of get method, of class Vector4D.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        Vector4D instance = new Vector4D(4, 5, 6);
        double expResult = 4;
        double result = instance.get(0);
        assertEquals(expResult, result, EPSILON);
    } // testGet()

    /**
     * Test of set method, of class Vector4D.
     */
    @Test
    public void testSet() {
        System.out.println("set");
        Vector4D instance = new Vector4D();
        instance.set(0, 4);
        assertEquals(instance.get(0), 4, EPSILON);
    } // testSet()

    /**
     * Test of dotProduct method, of class Vector4D.
     */
    @Test
    public void testDotProduct() {
        System.out.println("dotProduct");
        Vector4D v = new Vector4D(1, 2, 3);
        Vector4D instance = new Vector4D(3, 2, 1);
        double expResult = 11.0;
        double result = instance.dotProduct(v);
        assertEquals(expResult, result, EPSILON);
    } // testDotProduct()

    /**
     * Test of magnitude method, of class Vector4D.
     */
    @Test
    public void testMagnitude() {
        System.out.println("magnitude");
        Vector4D instance = new Vector4D(4, 2, 2);
        double expResult = 5.0;
        double result = instance.magnitude();
        assertEquals(expResult, result, EPSILON);
    } // testMagnitude()

    /**
     * Test of normalize method, of class Vector4D.
     */
    @Test
    public void testNormalize() {
        System.out.println("normalize");
        Vector4D instance = new Vector4D(4, 2, 2);
        Vector4D result = instance.normalize();
        assertEquals(result.magnitude(), 1, EPSILON);
    } // testNormalize()

    /**
     * Test of crossProduct method, of class Vector4D.
     */
    @Test
    public void testCrossProduct() {
        System.out.println("crossProduct");
        Vector4D v = new Vector4D(1, 2, 3);
        Vector4D instance = new Vector4D(3, 2, 1);
        Vector4D expResult = new Vector4D(4, -8, 4);
        Vector4D result = instance.crossProduct(v);
        for (int i = 0; i < 4; i++) {
            assertEquals(expResult.get(i), result.get(i), EPSILON);
        } // for
    } // testCrossProduct()

    /**
     * Test of toString method, of class Vector4D.
     */
//    @Test
//    public void testToString() {
//        System.out.println("toString");
//        Vector4D instance = new Vector4D();
//        String expResult = "";
//        String result = instance.toString();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of main method, of class Vector4D.
     */
//    @Test
//    public void testMain() {
//        System.out.println("main");
//        String[] args = null;
//        Vector4D.main(args);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
} //Vector4DTest
