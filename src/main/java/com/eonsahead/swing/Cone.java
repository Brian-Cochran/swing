/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eonsahead.swing;

import java.util.ArrayList;
import java.util.List;

/**
 * The Cone class creates and updates a list of shapes to be used in the creation of a cone.
 * <p>
 * This class contains three lists. The first is a list which contains the top 
 * and bottom polygons(although the bottom polygon always has a radius of 0). 
 * The second contains the sides of the cone, and the third contains the vertices 
 * of the top and bottom polygons.
 * 
 * @author Brian Cochran
 * @version 4/10/2020
 */
public class Cone {
    public List<Polygon3D> faces = new ArrayList<>();
    public List<Rectangle> sides = new ArrayList<>();
    public List<Vector4D> vertices = new ArrayList<>();
    private int numSides = 30;
    
    /**
     * Cone constructor
     * <p>
     * This constructor creates Polygon3D and Rectangle objects and updates the 
     * three lists in this class.
     * 
     * @param radius radius of base of cone
     * @param height height of cone
     */
    public Cone(double radius, double height) {
        Polygon3D polyTop = new Polygon3D(numSides, radius, height /  2, 0);
        this.faces.add(polyTop);
        this.vertices.addAll(polyTop.getVertices());
        
        Polygon3D polyBottom = new Polygon3D(numSides, 0.0, (-height) / 2, 1);
        this.faces.add(polyBottom);
        this.vertices.addAll(polyBottom.getVertices());
        
        Rectangle r;
        for (int side = 0; side < numSides; side++) {
            r = new Rectangle(polyTop, polyBottom, side);
            sides.add(r);
        } // for
    } // Cone(double, double)
    
    /**
     * Transforms cone according to 4x4 matrix
     * 
     * @param m 4x4 matrix
     */
    public void transform(Matrix4x4 m) {
        for (Vector4D v : this.vertices) {
            v.set(m.multiply(v));
        } // for
    } // transform(Matrix4x4)
} // Cone