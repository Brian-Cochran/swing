package com.eonsahead.swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SwingPanel extends JPanel implements ActionListener {

    private double centerX = 0.0;
    private double centerY = 0.0;
    private final double minorRadius = 0.2;
    private final double majorRadius = 0.3;

    private double phase = 0.0;
    private Shape shape1;
    private Shape shape2;

    private Color color = Color.red;
    private Polygon3D poly1;
    private Polygon3D poly2;
    private Matrix4x4 spinner;
    private List<Shape> shapes;
    private Shape rect;

    public SwingPanel() {
        Timer timer = new Timer(20, this);
        timer.start();

//        int p = this.points;
//        double x = this.centerX;
//        double y = this.centerY;
//        double r0 = this.minorRadius;
//        double r1 = this.majorRadius;
//        this.shape = makeStar(p, x, y, r0, r1);
        this.poly1 = new Polygon3D(9, 0.6, 0);
        this.poly2 = new Polygon3D(9, 0.6, 0.5);
        
        Matrix4x4 a = new Matrix4x4();
        a.rotationX(Math.PI / 100);

        Matrix4x4 b = new Matrix4x4();
        b.rotationY(Math.PI / 100);

        Matrix4x4 c = new Matrix4x4();
        c.rotationZ(Math.PI / 100);

        this.spinner = a.multiply(b).multiply(c);
    } // SwingPanel()

    public Color getColor() {
        return this.color;
    } // getColor()

    public void setColor(Color c) {
        this.color = c;
    } // setColor( Color )

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;

        int w = this.getWidth();
        int h = this.getHeight();

        AffineTransform transform1 = new AffineTransform();
        AffineTransform transform2 = new AffineTransform();

        AffineTransform rotation = new AffineTransform();
        rotation.setToRotation(this.phase);

        AffineTransform scaling = new AffineTransform();
        scaling.setToScale(w / 2, h / 2);

        AffineTransform translation1 = new AffineTransform();
        double cx1 = 1.0;
        double cy1 = 1.0;
        translation1.setToTranslation(cx1, cy1);
        AffineTransform translation2 = new AffineTransform();
        double cx2 = 1.0;
        double cy2 = 1.0;
        translation2.setToTranslation(cx2, cy2);

        transform1.concatenate(scaling);
        transform1.concatenate(translation1);
        transform1.concatenate(rotation);
        
        transform2.concatenate(scaling);
        transform2.concatenate(translation2);
        transform2.concatenate(rotation);
        
        this.shape1 = poly1.getShape();
        this.shape2 = poly2.getShape();

        Shape s1 = transform1.createTransformedShape(this.shape1);
        Shape s2 = transform2.createTransformedShape(this.shape2);
        
        //Rectangle r = new Rectangle(poly2, poly1, 0);
        //Shape shape3 = r.getShape();
        //Shape s3 = transform1.createTransformedShape(shape3);
        
        g2D.setColor(this.getColor());
        g2D.fill(s1);
        g2D.fill(s2);
        //g2D.draw(s3);
        
        for (int side = 0; side < poly1.getSize(); side++) {
            Rectangle r = new Rectangle(poly1, poly2, side);
            Shape rectShape = r.getShape();
            g2D.fill(transform1.createTransformedShape(rectShape));
        } // for
        
    } // paintComponent( Graphics )

    @Override
    public void actionPerformed(ActionEvent event) {
        this.poly1.transform(spinner);
        this.poly2.transform(spinner);
        this.repaint();
    } // actionPerformed( ActionEvent )
} // SwingPanel