
package com.eonsahead.swing;

import java.util.ArrayList;
import java.util.List;

/**
 * The AntiPrism class constructs a list of shapes to be used in the drawing of an antiprism.
 * <p>
 * This class creates and fills four lists. The first is the faces list, which 
 * stores the top and bottom polygons. The second is the sides0 list, which 
 * stores the sides connected by two vertices to the bottom polygon. The third is 
 * the sides1 list, which stores the sides connected by two vertices to the top 
 * polygon. The fourth list is a list of vertices of the top and bottom polygons.
 * 
 * @author Brian Cochran
 * @version 4/12/2020
 */
public class AntiPrism {
    
    public List<Polygon3D> faces = new ArrayList<>();
    public List<Triangle> sides0 = new ArrayList<>();
    public List<Triangle> sides1 = new ArrayList<>();
    public List<Vector4D> vertices = new ArrayList<>();
    
    /**
     * AntiPrism constructor
     * <p>
     * This class creates Polygon3D and Triangle objects and updates the four 
     * lists in this class.
     * 
     * @param numSides number of sides
     * @param radiusTop radius of top polygon
     * @param radiusBottom radius of bottom polygon
     * @param height height of prism
     */
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
    
    /**
     * Transforms antiprism according to 4x4 matrix
     * 
     * @param m 4x4 matrix
     */
    public void transform(Matrix4x4 m) {
        for (Vector4D v : this.vertices) {
            v.set(m.multiply(v));
        } // for
    } // transform(Matrix4x4)
} // AntiPrism
