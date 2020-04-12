/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eonsahead.swing;

import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.List;

public class Triangle {
    
    private List<Vector4D> vertices = new ArrayList<>();
    
    public Triangle(Polygon3D p1, Polygon3D p2, int side, int mode) {
        if (p1.getSize() == p2.getSize()) {
            if (mode == 0) {
                if (side == p1.getSize()) {
                    this.vertices.add(p1.getVertex(side));
                    this.vertices.add(p2.getVertex(side));
                    this.vertices.add(p2.getVertex(0));
                } // if
                else {
                    this.vertices.add(p1.getVertex(side));
                    this.vertices.add(p2.getVertex(side));
                    this.vertices.add(p2.getVertex(side + 1));
                } //else
            } // if
            else {
                if (side == p1.getSize()) {
                    this.vertices.add(p1.getVertex(side));
                    this.vertices.add(p2.getVertex(0));
                    this.vertices.add(p1.getVertex(0));
                } // if
                else {
                    this.vertices.add(p1.getVertex(side));
                    this.vertices.add(p2.getVertex(side + 1));
                    this.vertices.add(p1.getVertex(side + 1));
                } // else
            } // else
        } // if
    } // Triangle(Polygon3D, Polygon3D, int)
    
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
    
    public List<Vector4D> getVertices() {
        return this.vertices;
    } // getVertices()
    
    public Vector4D getVertex(int vertex) {
        return this.vertices.get(vertex);
    } // getVertex(int)
     
    public Vector4D getNormal() {
        Vector4D v0 = this.getVertex(0);
        Vector4D v1 = this.getVertex(1);
        Vector4D v2 = this.getVertex(2);
        Vector4D v3 = v2.add(v1.getNegative());
        Vector4D v4 = v0.add(v1.getNegative());
        Vector4D result = v3.crossProduct(v4);
        return result.normalize();
    } //getNormal()
    
    public void transform(Matrix4x4 m) {
        for (Vector4D u : this.vertices) {
            u.set(m.multiply(u));
        } // for
    } // transform()
} // Triangle
