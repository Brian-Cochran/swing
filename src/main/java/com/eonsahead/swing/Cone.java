/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eonsahead.swing;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Brian Cochran
 */
public class Cone {
    public List<Polygon3D> faces = new ArrayList<>();
    public List<Rectangle> sides = new ArrayList<>();
    public List<Vector4D> vertices = new ArrayList<>();
    
    public Cone(double radius, double height) {
        Polygon3D polyTop = new Polygon3D(100, radius, height /  2, 0);
        this.faces.add(polyTop);
        this.vertices.addAll(polyTop.getVertices());
        
        Polygon3D polyBottom = new Polygon3D(100, 0.0, (-height) / 2, 1);
        this.faces.add(polyBottom);
        this.vertices.addAll(polyBottom.getVertices());
        
        Rectangle r;
        for (int side = 0; side < 100; side++) {
            r = new Rectangle(polyTop, polyBottom, side);
            sides.add(r);
        } // for
    } // Cone(double, double)
    
    public void transform(Matrix4x4 m) {
        for (Vector4D v : this.vertices) {
            v.set(m.multiply(v));
        } // for
    } // transform(Matrix4x4)
} // Cone