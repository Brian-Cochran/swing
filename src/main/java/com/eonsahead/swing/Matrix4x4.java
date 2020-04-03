package com.eonsahead.swing;

/**
 * Model a matrix.
 *
 * @author Leon Tabak
 * @version 1 April 2020
 */
public class Matrix4x4 {
    
    private double[][] elements = new double[4][4];
    
    public Matrix4x4() {
        this.identity();
    } // Matrix4x4()
    
    public double get(int row, int column) {
        return this.elements[row][column];
    } // get(int, int)
    
    public void set(int row, int column, double value) {
        this.elements[row][column] = value;
    } // set(int, int, double)
    
    public void identity() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == j) {
                    this.set(i, j, 1.0);
                } // if
                else {
                    this.set(i, j, 0.0);
                } // else
            } // for
        } // for
    } // identity()
    
    public Matrix4x4 multiply(Matrix4x4 m) {
        Matrix4x4 product = new Matrix4x4();
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                double sum = 0.0;
                for (int k = 0; k < 4; k++) {
                    sum += this.get(row, k) * m.get(k, column);
                } // for
                product.set(row, column, sum);
            } // for
        } // for
        return product;
    } // multiply(Matrix4x4)
    
    public void rotationX(double angle) {
        this.identity();
        this.set(1, 1, Math.cos(angle));
        this.set(1, 2, -Math.sin(angle));
        this.set(2, 1, Math.sin(angle));
        this.set(2, 2, Math.cos(angle));
    } // rotationX(double)
    
    public void rotationY(double angle) {
        this.identity();
        this.set(0, 0, Math.cos(angle));
        this.set(0, 2, Math.sin(angle));
        this.set(2, 0, -Math.sin(angle));
        this.set(2, 2, Math.cos(angle));
    } // rotationY(double)
    
    public void rotationZ(double angle) {
        this.identity();
        this.set(0, 0, Math.cos(angle));
        this.set(0, 1, -Math.sin(angle));
        this.set(1, 0, Math.sin(angle));
        this.set(1, 1, Math.cos(angle));
    } // rotationZ(double)
    
    public void scale(double scaleX, double scaleY, double scaleZ) {
        this.identity();
        this.set(0, 0, scaleX);
        this.set(1, 1, scaleY);
        this.set(2, 2, scaleZ);
    } // scale(double, double, double)
    
    public void translate(double changeX, double changeY, double changeZ) {
        this.identity();
        this.set(0, 3, changeX);
        this.set(1, 3, changeY);
        this.set(2, 3, changeZ);
    } // translate(double, double, double)
    
    public Matrix4x4 addMatrix(Matrix4x4 m) {
        Matrix4x4 sum = new Matrix4x4();
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                sum.set(row, column, this.get(row, column) + m.get(row, column));
            } // for
        } // for
        return sum;
    } // addMatrix(Matrix4x4)
    
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        
        result.append("[");
        for (int i = 0; i < 3; i++) {
            result.append(rowToString(i));
            result.append(",");
        } // for
        result.append(rowToString(3));
        result.append("]");
        return result.toString();
    } // toString()
    
    public String rowToString(int row) {
        StringBuilder result = new StringBuilder();
        
        result.append("(");
        for (int i = 0; i < 3; i++) {
            result.append(this.get(row, i));
            result.append(", ");
        } // for
        result.append(this.get(row, 3));
        result.append(")");
        return result.toString();
    } // rowToString(int)
} // Matrix4x4