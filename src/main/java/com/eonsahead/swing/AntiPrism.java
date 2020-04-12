
package com.eonsahead.swing;

import java.util.ArrayList;
import java.util.List;

public class AntiPrism {
    
    public List<Polygon3D> faces = new ArrayList<>();
    public List<Triangle> sides0 = new ArrayList<>();
    public List<Triangle> sides1 = new ArrayList<>();
    public List<Vector4D> vertices = new ArrayList<>();
    
    public AntiPrism(int numSides, double radiusTop, double radiusBottom, double height) {
        Polygon3D polyTop = new Polygon3D(numSides, radiusTop, height / 2, 1);
        this.faces.add(polyTop);
        this.vertices.addAll(polyTop.getVertices());
        
        Polygon3D polyBottom = new Polygon3D(numSides, radiusBottom, (-height) / 2, 0);
        this.faces.add(polyBottom);
        this.vertices.addAll(polyBottom.getVertices());
        
        Triangle t;
        for (int side = 0; side < numSides; side++) {
            t = new Triangle(polyTop, polyBottom, side, 0);
            sides0.add(t);
            t = new Triangle(polyTop, polyBottom, side, 1);
            sides1.add(t);
        } // for
    } // AntiPrism(int, double, double, double)
    
    public void transform(Matrix4x4 m) {
        for (Vector4D v : this.vertices) {
            v.set(m.multiply(v));
        } // for
    } // transform(Matrix4x4)
} // AntiPrism
