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
 * @version 4/7/2020
 */
public class Matrix4x4Test {
    
    private static final double EPSILON = 1E-8;
    
    public Matrix4x4Test() {
    }

    /**
     * Test of get method, of class Matrix4x4.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        Matrix4x4 instance = new Matrix4x4();
        double expResult = 1.0;
        double result = instance.get(0, 0);
        assertEquals(expResult, result, EPSILON);
    } // testGet()

    /**
     * Test of set method, of class Matrix4x4.
     */
    @Test
    public void testSet() {
        System.out.println("set");
        double value = 5.0;
        Matrix4x4 instance = new Matrix4x4();
        instance.set(0, 0, value);
        assertEquals(instance.get(0, 0), value, EPSILON);
    } // testSet()

    /**
     * Test of identity method, of class Matrix4x4.
     */
    @Test
    public void testIdentity() {
        System.out.println("identity");
        Matrix4x4 instance = new Matrix4x4();
        instance.identity();
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                if (row == column) {
                    assertEquals(instance.get(row, column), 1, EPSILON);
                } // if
                else {
                    assertEquals(instance.get(row, column), 0, EPSILON);
                } // else
            } // for
        } // for
    } // testIdentity()

    /**
     * Test of multiply method, of class Matrix4x4.
     */
    @Test
    public void testMultiply_Matrix4x4() {
        System.out.println("multiply");
        Matrix4x4 m = new Matrix4x4();
        Matrix4x4 instance = new Matrix4x4();
        Matrix4x4 expResult = new Matrix4x4();
        Matrix4x4 result = instance.multiply(m);
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                assertEquals(expResult.get(row, column), result.get(row, column), EPSILON);
            } // for
        } // for       
    } // testMultiply_Matrix4x4()

    /**
     * Test of multiply method, of class Matrix4x4.
     */
    @Test
    public void testMultiply_Vector4D() {
        System.out.println("multiply");
        Vector4D v = new Vector4D(4, 5, 6);
        Matrix4x4 instance = new Matrix4x4();
        Vector4D expResult = new Vector4D(4, 5, 6);
        Vector4D result = instance.multiply(v);
        for (int i = 0; i < 4; i++) {
            assertEquals(expResult.get(i), result.get(i), EPSILON);
        } // for
    } // testMultiply_Vector4D

    /**
     * Test of rotationX method, of class Matrix4x4.
     */
    @Test
    public void testRotationX() {
        System.out.println("rotationX");
        double angle = Math.PI;
        Matrix4x4 instance = new Matrix4x4();
        instance.rotationX(angle);
        assertEquals(instance.get(1, 1), Math.cos(angle), EPSILON);
        assertEquals(instance.get(1, 2), -Math.sin(angle), EPSILON);
        assertEquals(instance.get(2, 1), Math.sin(angle), EPSILON);
        assertEquals(instance.get(2, 2), Math.cos(angle), EPSILON);
    } // testRotationX()

    /**
     * Test of rotationY method, of class Matrix4x4.
     */
    @Test
    public void testRotationY() {
        System.out.println("rotationY");
        double angle = Math.PI;
        Matrix4x4 instance = new Matrix4x4();
        instance.rotationY(angle);
        assertEquals(instance.get(0, 0), Math.cos(angle), EPSILON);
        assertEquals(instance.get(0, 2), Math.sin(angle), EPSILON);
        assertEquals(instance.get(2, 0), -Math.sin(angle), EPSILON);
        assertEquals(instance.get(2, 2), Math.cos(angle), EPSILON);
    } // testRotationY()

    /**
     * Test of rotationZ method, of class Matrix4x4.
     */
    @Test
    public void testRotationZ() {
        System.out.println("rotationZ");
        double angle = Math.PI;
        Matrix4x4 instance = new Matrix4x4();
        instance.rotationZ(angle);
        assertEquals(instance.get(0, 0), Math.cos(angle), EPSILON);
        assertEquals(instance.get(0, 1), -Math.sin(angle), EPSILON);
        assertEquals(instance.get(1, 0), Math.sin(angle), EPSILON);
        assertEquals(instance.get(1, 1), Math.cos(angle), EPSILON);
    } // testRotationZ()

    /**
     * Test of scale method, of class Matrix4x4.
     */
    @Test
    public void testScale() {
        System.out.println("scale");
        double scaleX = 5;
        double scaleY = 5;
        double scaleZ = 5;
        Matrix4x4 instance = new Matrix4x4();
        instance.scale(scaleX, scaleY, scaleZ);
        assertEquals(instance.get(0, 0), scaleX, EPSILON);
        assertEquals(instance.get(1, 1), scaleY, EPSILON);
        assertEquals(instance.get(2, 2), scaleZ, EPSILON);
    } // testScale()

    /**
     * Test of translate method, of class Matrix4x4.
     */
    @Test
    public void testTranslate() {
        System.out.println("translate");
        double changeX = 1;
        double changeY = 2;
        double changeZ = 3;
        Matrix4x4 instance = new Matrix4x4();
        instance.translate(changeX, changeY, changeZ);
        assertEquals(instance.get(0, 3), changeX, EPSILON);
        assertEquals(instance.get(1, 3), changeY, EPSILON);
        assertEquals(instance.get(2, 3), changeZ, EPSILON);
    } // testTranslate

    /**
     * Test of addMatrix method, of class Matrix4x4.
     */
    @Test
    public void testAddMatrix() {
        System.out.println("addMatrix");
        Matrix4x4 m = new Matrix4x4();
        Matrix4x4 instance = new Matrix4x4();
        Matrix4x4 result = instance.addMatrix(m);
        assertEquals(result.get(0, 0), 2, EPSILON);
        assertEquals(result.get(1, 1), 2, EPSILON);
        assertEquals(result.get(2, 2), 2, EPSILON);
        assertEquals(result.get(3, 3), 2, EPSILON);
    } // testAddMatrix()
} //Matrix4x4Test
