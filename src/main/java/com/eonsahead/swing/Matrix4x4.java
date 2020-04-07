package com.eonsahead.swing;

/**
 * Model a matrix.
 *
 * @author Leon Tabak
 * @version 1 April 2020
 */
public class Matrix4x4 {
    
    private double[][] elements = new double[4][4];
    
    /**
     * Initializes a 4x4 matrix.
     * <p>
     * The diagonal of the matrix is filled with 1's and everything else is set to 0.
     */
    public Matrix4x4() {
        this.identity();
    } // Matrix4x4()
    
    /**
     * 
     * Retrieves the value at a specific location within a 4x4 matrix.
     * 
     * @param row row of value to be retrieved
     * @param column column of value to be retrieved
     * @return value retrieved (double)
     */
    public double get(int row, int column) {
        return this.elements[row][column];
    } // get(int, int)
    
    /**
     * 
     * Sets the value of a specific location within a 4x4 matrix.
     * 
     * @param row row of value to be set
     * @param column column of value to be set
     * @param value value to be set (double)
     */
    public void set(int row, int column, double value) {
        this.elements[row][column] = value;
    } // set(int, int, double)
    
    /**
     * Sets all elements in a 4x4 matrix to 0 except those located on the diagonal, which are set to 1.
     */
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
    
    /**
     * 
     * Multiplies two 4x4 matrices together.
     * 
     * @param m 4x4 matrix to be multiplied
     * @return new 4x4 matrix
     */
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
    
    /**
     * 
     * Multiplies a 4x4 matrix and a 4D vector together.
     * 
     * @param v 4D vector to be multiplied
     * @return new 4D vector
     */
    public Vector4D multiply(Vector4D v) {
        Vector4D product = new Vector4D();
        for (int row = 0; row < 4; row++) {
            double value = 0.0;
            for (int column = 0; column < 4; column++) {
                value += this.get(row, column) * v.get(column);
            } // for
            product.set(row, value);
        } // for
        return product;
    } // multiply(Vector4D)
    
    /**
     * 
     * Models a rotation about the x-axis.
     * 
     * @param angle angle to be rotated
     */
    public void rotationX(double angle) {
        this.identity();
        this.set(1, 1, Math.cos(angle));
        this.set(1, 2, -Math.sin(angle));
        this.set(2, 1, Math.sin(angle));
        this.set(2, 2, Math.cos(angle));
    } // rotationX(double)
    
    /**
     * 
     * Models a rotation about the y-axis.
     * 
     * @param angle angle to be rotated
     */
    public void rotationY(double angle) {
        this.identity();
        this.set(0, 0, Math.cos(angle));
        this.set(0, 2, Math.sin(angle));
        this.set(2, 0, -Math.sin(angle));
        this.set(2, 2, Math.cos(angle));
    } // rotationY(double)
    
    /**
     * Models a rotation about the z-axis.
     * 
     * @param angle angle to be rotated
     */
    public void rotationZ(double angle) {
        this.identity();
        this.set(0, 0, Math.cos(angle));
        this.set(0, 1, -Math.sin(angle));
        this.set(1, 0, Math.sin(angle));
        this.set(1, 1, Math.cos(angle));
    } // rotationZ(double)
    
    /**
     * Models a scaling of a geometric figure.
     * 
     * @param scaleX scale in x-direction
     * @param scaleY scale in y-direction
     * @param scaleZ scale in z-direction
     */
    public void scale(double scaleX, double scaleY, double scaleZ) {
        this.identity();
        this.set(0, 0, scaleX);
        this.set(1, 1, scaleY);
        this.set(2, 2, scaleZ);
    } // scale(double, double, double)
    
    /**
     * Models a translation of a geometric figure.
     * 
     * @param changeX change in x-direction
     * @param changeY change in y-direction
     * @param changeZ change in z-direction
     */
    public void translate(double changeX, double changeY, double changeZ) {
        this.identity();
        this.set(0, 3, changeX);
        this.set(1, 3, changeY);
        this.set(2, 3, changeZ);
    } // translate(double, double, double)
    
    /**
     * Calculates addition of two 4x4 matrices.
     * 
     * @param m 4x4 matrix to be added
     * @return 4x4 matrix
     */
    public Matrix4x4 addMatrix(Matrix4x4 m) {
        Matrix4x4 sum = new Matrix4x4();
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                sum.set(row, column, this.get(row, column) + m.get(row, column));
            } // for
        } // for
        return sum;
    } // addMatrix(Matrix4x4)
    
    /**
     * Converts a 4x4 matrix object into a printable string.
     * <p>
     * uses rowToString method.
     * 
     * @return string
     */
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
    
    /**
     * Converts rows of a 4x4 matrix into printable strings.
     * <p>
     * Used in toString method.
     * 
     * @param row row to be converted to a string
     * @return string
     */
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