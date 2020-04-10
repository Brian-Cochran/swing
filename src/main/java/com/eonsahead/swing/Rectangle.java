package com.eonsahead.swing;

import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.List;

public class Rectangle {

    private List<Vector4D> vertices = new ArrayList<>();

    public Rectangle(Polygon3D p1, Polygon3D p2, int side) {
        if (p1.getSize() == p2.getSize()) {
            if (side == p1.getSize()) {
                this.vertices.add(p1.getVertex(side));
                this.vertices.add(p1.getVertex(0));
                this.vertices.add(p2.getVertex(0));
                this.vertices.add(p2.getVertex(side));
            } // if
            else {
                this.vertices.add(p1.getVertex(side));
                this.vertices.add(p1.getVertex(side + 1));
                this.vertices.add(p2.getVertex(side + 1));
                this.vertices.add(p2.getVertex(side));
            } // else
        } // if
    } // getPoints(Polygon3D, Polygon3D)
    
    public Shape getShape(){
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
    
    public Vector4D getVertex(int vertex) {
        return this.vertices.get(vertex);
    } // getVertex(int)
    
    public Vector4D getNormalFront() {
        Vector4D temp1 = (this.getVertex(2).add(this.getVertex(1).getNegative()));
        Vector4D temp2 = (this.getVertex(0).add(this.getVertex(1).getNegative()));
        return temp1.crossProduct(temp2);
    } // getNormalFront()
    
    public Vector4D getNormalBack() {
        Vector4D temp1 = (this.getVertex(2).add(this.getVertex(3).getNegative()));
        Vector4D temp2 = (this.getVertex(0).add(this.getVertex(3).getNegative()));
        return temp1.crossProduct(temp2);
    } // getNormalBack()
} // Rectangle