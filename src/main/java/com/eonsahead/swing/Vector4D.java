
package com.eonsahead.swing;


public class Vector4D {
    private double[] elements = new double[4];
    
    public Vector4D() {
        for (int i = 0; i < 4; i++) {
            this.set(i, 0);
        } // for
    } // Vector4D()
    
    public Vector4D(double x, double y, double z) {
        this.set(0, x);
        this.set(1, y);
        this.set(2, z);
        this.set(3, 1);
    } // Vector4D()
    
    public double get(int row) {
        return this.elements[row];
    } // get(int)
    
    public void set(int row, double value) {
        this.elements[row] = value;
    } // set(int, double)
    
    public double dotProduct(Vector4D v) {
        double product = 0.0;
        for (int i = 0; i < 4; i++) {
            product += this.get(i) * v.get(i);
        } // for
        return product;
    } // dotProduct(Vector4D)
    
    public double magnitude() {
        return Math.sqrt(this.dotProduct(this));
    } // magnitude()
    
    public Vector4D normalize() {
        double length = this.magnitude();
        double newX = this.get(0) / length;
        double newY = this.get(1) / length;
        double newZ = this.get(2) / length;
        return new Vector4D(newX, newY, newZ);
    } // normalize()
    
    public Vector4D crossProduct(Vector4D v) {
        double newX = this.get(1) * v.get(2) - this.get(2) * v.get(1);
        double newY = this.get(2) * v.get(0) - this.get(0) * v.get(2);
        double newZ = this.get(0) * v.get(1) - this.get(1) * v.get(0);
        return new Vector4D(newX, newY, newZ);
    } // crossProduct(Vector4D)
    
//    public Vector4D matrixVectorMultiplication(Matrix4x4 m) {
//        double value = 0.0;
//        for (int row = 0; row < 4; row++) {
//            for (int column = 0; column < 4; column++) {
//                value+= this.get(column) * m.get(row, column);
//            } // for
//            this.set(row, value);
//        } // for
//        return this;
//    } // matrixVectorMultiplication(Vector4D, Matrix4x4)
    
    @Override
    public String toString() {
        return "(" + get(0) + ", " + get(1) + ", " + get(2) + ", " + get(3) + ")";
    } // toString()
    
    public static void main(String[] args) {
        Matrix4x4 a = new Matrix4x4();
        System.out.println(a);
        
        Vector4D b = new Vector4D(3, 3, 3);
        System.out.println(b);
        
        Vector4D c = new Vector4D(4, 4, 4);
        System.out.println(c);
        
        System.out.println(b.crossProduct(c));
    } // main
}