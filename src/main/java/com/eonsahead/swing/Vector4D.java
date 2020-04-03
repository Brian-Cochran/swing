
package com.eonsahead.swing;


public class Vector4D {
    private double[][] elements = new double[4][1];
    
    public Vector4D() {
        for (int i = 0; i < 4; i++) {
            this.set(i, 0);
        } // for
    } // Vector4D()
    
    public Vector4D(double x, double y, double z, double q) {
        this.set(0, x);
        this.set(1, y);
        this.set(2, z);
        this.set(3, q);
    } // Vector4D()
    
    public double get(int row) {
        return this.elements[row][0];
    } // get(int)
    
    public void set(int row, double value) {
        this.elements[row][0] = value;
    } // set(int, double)
    
    public double dotProduct(Vector4D v) {
        double product = 0.0;
        for (int i = 0; i < 4; i++) {
            product += this.get(i) * v.get(i);
        } // for
        return product;
    } // dotProduct(Vector4D)
    
    public Vector4D matrixVectorMultiplication(Matrix4x4 m) {
        double value = 0.0;
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                value+= this.get(column) * m.get(row, column);
            } // for
            this.set(row, value);
        } // for
        return this;
    } // matrixVectorMultiplication(Vector4D, Matrix4x4)
    
    @Override
    public String toString() {
        return "(" + get(0) + ", " + get(1) + ", " + get(2) + ", " + get(3) + ")";
    } // toString()
}