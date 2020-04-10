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
    private final int mode;
    public static final int CW = 0;
    public static final int CCW = 1;

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

    public Polygon3D(Vector4D v0, Vector4D v1, Vector4D v2) {
        this.vertices.add(v0);
        this.vertices.add(v1);
        this.vertices.add(v2);
        this.mode = Polygon3D.CCW;
    } // Polygon3D(Vector4D, Vector4D, Vector4D)

    public int getSize() {
        return this.vertices.size() - 1;
    } // getSize()

    public List<Vector4D> getVertices() {
        return this.vertices;
    } // getVertices()

    public Vector4D getVertex(int vertex) {
        return this.vertices.get(vertex);
    } // getVertex(int)

    public List<Polygon3D> makeSleeve(Polygon3D poly) {
        List<Polygon3D> faces = new ArrayList<>();
        int numSides = this.getVertices().size();

        if (numSides == poly.getVertices().size()) {
            Vector4D v0;
            Vector4D v1;
            Vector4D v2;
            Polygon3D p;

            List<Vector4D> vertexList1 = this.getVertices();
            List<Vector4D> vertexList2 = poly.getVertices();

            for (int i = 0; i < numSides - 1; i++) {
                v0 = vertexList1.get(i);
                v1 = vertexList2.get(i);
                v2 = vertexList1.get(i + 1);
                p = new Polygon3D(v0, v1, v2);
                faces.add(p);

                v0 = vertexList1.get(i + 1);
                v1 = vertexList2.get(i);
                v2 = vertexList2.get(i + 1);
                p = new Polygon3D(v0, v1, v2);
                faces.add(p);
            } // for

            v0 = vertexList1.get(numSides - 1);
            v1 = vertexList2.get(numSides - 1);
            v2 = vertexList1.get(0);
            p = new Polygon3D(v0, v1, v2);
            faces.add(p);

            v0 = vertexList1.get(0);
            v1 = vertexList2.get(numSides - 1);
            v2 = vertexList2.get(0);
            p = new Polygon3D(v0, v1, v2);
            faces.add(p);
        } // if
        return faces;
    } // makeSleeve(Polygon3D)

    public void transform(Matrix4x4 m) {
        for (Vector4D u : this.vertices) {
            u.set(m.multiply(u));
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

    public double getMinZ() {
        double minZ = this.getVertex(0).get(2);
        for (int vertex = 1; vertex < this.vertices.size(); vertex++) {
            if (this.getVertex(vertex).get(2) < minZ) {
                minZ = this.getVertex(vertex).get(2);
            } // if
        } // for
        return minZ;
    } // getMinZ()
    
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
