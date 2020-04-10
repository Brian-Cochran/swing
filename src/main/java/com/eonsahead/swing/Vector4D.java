
package com.eonsahead.swing;

/**
 * 
 * @author Brian Cochran
 * @version 4/6/2020
 */
public class Vector4D {
    
    private double[] elements = new double[4];
    
    /**
     * Initializes a 4D vector to filled with zeroes.
     */
    public Vector4D() {
        for (int i = 0; i < 4; i++) {
            this.set(i, 0);
        } // for
    } // Vector4D()
    
    /**
     * 
     * Initializes a 4D vector using parameters.
     * <p>
     * Sets x, y, and z according to parameters and sets last value to 1.
     * 
     * @param x value to be placed in first spot
     * @param y value to be placed in second spot
     * @param z value to be placed in third spot
     */
    public Vector4D(double x, double y, double z) {
        this.set(0, x);
        this.set(1, y);
        this.set(2, z);
        this.set(3, 1);
    } // Vector4D()
    
    /**
     * 
     * Retrieves the value at a specific location in a 4D vector.
     * 
     * @param row location of value to be retrieved
     * @return value at specified location (double)
     */
    public double get(int row) {
        return this.elements[row];
    } // get(int)
    
    /**
     * 
     * Sets the value at a specific location in a 4D vector according to value parameter.
     * 
     * @param row location of value to be replaced
     * @param value value to be set
     */
    public void set(int row, double value) {
        this.elements[row] = value;
    } // set(int, double)
    
    public void set(Vector4D v) {
        this.elements[0] = v.elements[0];
        this.elements[1] = v.elements[1];
        this.elements[2] = v.elements[2];
        this.elements[3] = v.elements[3];
    } // set(Vector4D)
    
    /**
     * 
     * Calculates dot product of two 4D vectors.
     * 
     * @param v Vector to be multiplied
     * @return dot product (double)
     */
    public double dotProduct(Vector4D v) {
        double product = 0.0;
        for (int i = 0; i < 4; i++) {
            product += this.get(i) * v.get(i);
        } // for
        return product;
    } // dotProduct(Vector4D)
    
    /**
     * 
     * Calculates magnitude of a 4D vector.
     * <p>
     * Uses dot product method to calculate the magnitude.
     * 
     * @return magnitude of 4D vector (double)
     */
    public double magnitude() {
        return Math.sqrt(this.dotProduct(this));
    } // magnitude()
    
    /**
     * 
     * Creates a new vector of magnitude 1 with a specific direction.
     * <p>
     * Takes direction of a 4D vector and sets the magnitude to 1 to create a new 4D vector.
     * 
     * @return 4D vector with magnitude of 1
     */
    public Vector4D normalize() {
        double length = this.magnitude();
        double newX = this.get(0) / length;
        double newY = this.get(1) / length;
        double newZ = this.get(2) / length;
        double newQ = this.get(3) / length;
        Vector4D result = new Vector4D(newX, newY, newZ);
        result.set(3, newQ);
        return result;
    } // normalize()
    
    /**
     * 
     * Calculates the cross product of two 4D vectors.
     * <p>
     * Only uses first three spots of both vectors.
     * 
     * @param v 4D vector to be multiplied
     * @return 4D vector
     */
    public Vector4D crossProduct(Vector4D v) {
        double newX = this.get(1) * v.get(2) - this.get(2) * v.get(1);
        double newY = this.get(2) * v.get(0) - this.get(0) * v.get(2);
        double newZ = this.get(0) * v.get(1) - this.get(1) * v.get(0);
        return new Vector4D(newX, newY, newZ);
    } // crossProduct(Vector4D)
    
    public Vector4D getNegative() {
        double newX = this.get(0) * -1;
        double newY = this.get(1) * -1;
        double newZ = this.get(2) * -1;
        return new Vector4D(newX, newY, newZ);
    } // getNegative()
    
    public Vector4D add(Vector4D v) {
        double newX = this.get(0) + v.get(0);
        double newY = this.get(1) + v.get(1);
        double newZ = this.get(2) + v.get(2);
        return new Vector4D(newX, newY, newZ);
    } // add(Vector4D)
    
    /**
     * 
     * Converts a 4D vector object into a printable string.
     * 
     * @return string
     */
    @Override
    public String toString() {
        return "(" + get(0) + ", " + get(1) + ", " + get(2) + ", " + get(3) + ")";
    } // toString()
} // Vector4D