/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eonsahead.swing;

import java.util.ArrayList;
import java.util.List;


/**
 * The Prism class constructs lists of shapes to be used in the drawing of a polyhedron.
 * <p>
 * This class creates and fills three lists. The first is the faces list, which 
 * stores the top and bottom polygons. The second is the sides list, which stores 
 * the rectangles which join the top and bottom polygons. The third is a list of 
 * the vertices of the top and bottom polygons.
 * 
 * @author Brian Cochran
 * @version 4/10/2020
 */
public class Prism {
    
    public List<Polygon3D> faces = new ArrayList<>();
    public List<Rectangle> sides = new ArrayList<>();
    public List<Vector4D> vertices = new ArrayList<>();
    
    /**
     * Prism constructor
     * <p>
     * This constructor creates Polygon3D and Rectangle objects and updates the 
     * three lists in this class.
     * 
     * @param numSides number of sides
     * @param radiusTop radius of top polygon
     * @param radiusBottom radius of bottom polygon
     * @param height height of prism
     */
    public Prism(int numSides, double radiusTop, double radiusBottom, double height) {
        Polygon3D polyTop = new Polygon3D(numSides, radiusTop, height / 2, 0);
        this.faces.add(polyTop);
        this.vertices.addAll(polyTop.getVertices());
        
        Polygon3D polyBottom = new Polygon3D(numSides, radiusBottom, (-height) / 2, 1);
        this.faces.add(polyBottom);
        this.vertices.addAll(polyBottom.getVertices());
        
        Rectangle r;
        for (int side = 0; side < numSides; side++) {
            r = new Rectangle(polyTop, polyBottom, side);
            sides.add(r);
        } // for
    } // Prism()
    
    /**
     * Transforms prism according to 4x4 matrix
     * 
     * @param m 4x4 matrix
     */
    public void transform(Matrix4x4 m) {
        for (Vector4D v : this.vertices) {
            v.set(m.multiply(v));
        } // for
    } // transform(Matrix4x4)
} // Prism
