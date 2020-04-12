/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eonsahead.swing;

import java.util.ArrayList;
import java.util.List;
import com.eonsahead.swing.Matrix4x4;
import com.eonsahead.swing.Vector4D;
import java.awt.Shape;
import java.awt.geom.GeneralPath;

/**
 * The Polygon3D class contains methods for creating, transforming, and tracing 
 * a polygon to be a part of a prism.
 * 
 * @author Brian Cochran
 * @version 4/10/2020
 */
public class Polygon3D {

    private final List<Vector4D> vertices = new ArrayList<>();
    private final int mode;
    public static final int CW = 0;
    public static final int CCW = 1;
    
    /**
     * Polygon3D constructor.
     * <p>
     * This constructor creates a list of vertices to represent a polygon of any 
     * size.
     * 
     * @param numberOfSides number of sides of polygon
     * @param radius controls size of polygon
     * @param z z-coordinate of vertices in polygon
     * @param mode keeps track of clockwise or counterclockwise creation of normal vector
     */
    public Polygon3D(int numberOfSides, double radius, double z, int mode) {
        for (int i = 0; i < numberOfSides; i++) {
            double fraction = ((double) i) / numberOfSides;
            double angle = fraction * 2.0 * Math.PI;
            double x = radius * Math.cos(angle);
            double y = radius * Math.sin(angle);
            Vector4D v = new Vector4D(x, y, z);
            this.vertices.add(v);
        } // for
        this.mode = mode;
    } // Polygon3D( int, double, double, int )
    
    /**
     * Retrieves the size of a polygon according to how many sides it has.
     * 
     * @return (int) number of sides of polygon.
     */
    public int getSize() {
        return this.vertices.size() - 1;
    } // getSize()
    
    /**
     * Retrieves the coordinates of a specified vertex
     * 
     * @param vertex vertex to be retrieved
     * @return vector representation of vertex
     */
    public Vector4D getVertex(int vertex) {
        return this.vertices.get(vertex);
    } // getVertex(int)
    
    /**
     * Retrieves a polygon's list of vertices
     * 
     * @return list of vector representations of vertices
     */
    public List<Vector4D> getVertices() {
        return this.vertices;
    } // getVertices()
    
    /**
     * Transforms vertices according to a 4x4 matrix
     * 
     * @param m 4-dimensional matrix
     */
    public void transform(Matrix4x4 m) {
        for (Vector4D u : this.vertices) {
            u.set(m.multiply(u));
        } // for 
    } // transform( Matrix )
    
    /**
     * Draws path of polygon
     * 
     * @return polygon represented by a path
     */
    public Shape getShape() {
        GeneralPath path = new GeneralPath();
        Vector4D v = this.vertices.get(0);
        double x = v.get(0);
        double y = v.get(1);
        path.moveTo(x, y);

        for (int i = 1; i < this.vertices.size(); i++) {
            v = this.vertices.get(i);
            x = v.get(0);
            y = v.get(1);
            path.lineTo(x, y);
        } // for
        path.closePath();
        return path;
    } // getShape()
    
    /**
     * Calculates the normal vector of a polygon.
     * 
     * @return normal vector
     */
    public Vector4D getNormal() {
        Vector4D v0 = this.getVertex(0);
        Vector4D v1 = this.getVertex(1);
        Vector4D v2 = this.getVertex(2);
        Vector4D v3 = v2.add(v1.getNegative());
        Vector4D v4 = v0.add(v1.getNegative());
        Vector4D result = v3.crossProduct(v4);
        
        if (this.mode == Polygon3D.CW) {
            Matrix4x4 m = new Matrix4x4();
            m.scale(-1.0, -1.0, -1.0);
            result = m.multiply(result);
        } // if
        return result.normalize();
    } // getNormal()
} // Polygon3D
