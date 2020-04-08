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
 *
 * @author Brian Cochran
 */
public class Polygon3D {
    private final List<Vector4D> vertices = new ArrayList<>();

    public Polygon3D(int numberOfSides, double radius) {
        for (int i = 0; i < numberOfSides; i++) {
            double fraction = ((double) i) / numberOfSides;
            double angle = fraction * 2.0 * Math.PI;
            double x = radius * Math.cos(angle);
            double y = radius * Math.sin(angle);
            Vector4D v = new Vector4D(x, y, 0.0);
            this.vertices.add(v);
        } // for
    } // Polygon3D( int, double )

    public void transform(Matrix4x4 m) {
        for (Vector4D u : this.vertices) {
            u = m.multiply(u);
        } // for 
    } // transform( Matrix )

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
}
