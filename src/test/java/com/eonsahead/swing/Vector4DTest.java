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
    
    public Vector4DTest() {
    }

    /**
     * Test of get method, of class Vector4D.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        int row = 0;
        Vector4D instance = new Vector4D();
        double expResult = 0.0;
        double result = instance.get(row);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of set method, of class Vector4D.
     */
    @Test
    public void testSet() {
        System.out.println("set");
        int row = 0;
        double value = 0.0;
        Vector4D instance = new Vector4D();
        instance.set(row, value);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dotProduct method, of class Vector4D.
     */
    @Test
    public void testDotProduct() {
        System.out.println("dotProduct");
        Vector4D v = null;
        Vector4D instance = new Vector4D();
        double expResult = 0.0;
        double result = instance.dotProduct(v);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of magnitude method, of class Vector4D.
     */
    @Test
    public void testMagnitude() {
        System.out.println("magnitude");
        Vector4D instance = new Vector4D();
        double expResult = 0.0;
        double result = instance.magnitude();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of normalize method, of class Vector4D.
     */
    @Test
    public void testNormalize() {
        System.out.println("normalize");
        Vector4D instance = new Vector4D();
        Vector4D expResult = null;
        Vector4D result = instance.normalize();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of crossProduct method, of class Vector4D.
     */
    @Test
    public void testCrossProduct() {
        System.out.println("crossProduct");
        Vector4D v = null;
        Vector4D instance = new Vector4D();
        Vector4D expResult = null;
        Vector4D result = instance.crossProduct(v);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

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
    
}
