package com.eonsahead.swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SwingPanel extends JPanel implements ActionListener {

    private final double ambient = 0.5;
    private Color color = new Color(0, 255, 100);
    private final Matrix4x4 spinner;
    private final Vector4D illumination;
    private final Polygon3D polyTop;
    private final Polygon3D polyBottom;
    private final int NUM_SIDES = 50;

    public SwingPanel() {
        Timer timer = new Timer(20, this);
        timer.start();
        
        this.polyTop = new Polygon3D(NUM_SIDES, 0.8, 0.5, 0);
        this.polyBottom = new Polygon3D(NUM_SIDES, 0.8, -0.5, 1);

        Matrix4x4 a = new Matrix4x4();
        a.rotationX(Math.PI / 100 * 0);

        Matrix4x4 b = new Matrix4x4();
        b.rotationY(Math.PI / 100);

        Matrix4x4 c = new Matrix4x4();
        c.rotationZ(Math.PI / 100 * 0);

        this.spinner = a.multiply(b).multiply(c);
        this.illumination = (new Vector4D(0, 0, 3)).normalize();
    } // SwingPanel()

    public Color getColor() {
        return this.color;
    } // getColor()

    public void setColor(Color c) {
        this.color = c;
    } // setColor( Color )

    public Color chooseColor(double brightness, double ambient) {
        Color c = this.getColor();
        int red;
        int green;
        int blue;
        if (brightness > 0) {
            red = (int) (brightness * c.getRed());
            green = (int) (brightness * c.getGreen());
            blue = (int) (brightness * c.getBlue());
        } // if
        else {
            red = (int) (ambient * c.getRed());
            green = (int) (ambient * c.getGreen());
            blue = (int) (ambient * c.getBlue());
        } // else
        return new Color(red, green, blue);
    } // chooseColor(double, double)

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        int w = this.getWidth();
        int h = this.getHeight();
        AffineTransform transform = new AffineTransform();
        AffineTransform scaling = new AffineTransform();
        scaling.setToScale(w / 2, h / 2);
        AffineTransform translation = new AffineTransform();
        double cx1 = 1.0;
        double cy1 = 1.0;
        translation.setToTranslation(cx1, cy1);
        transform.concatenate(scaling);
        transform.concatenate(translation);

        Vector4D normal;
        Shape shape;

        shape = transform.createTransformedShape(polyTop.getShape());
        normal = polyTop.getNormal();
        if (normal.get(2) > 0) {
            double brightness = normal.dotProduct(illumination);
            g2D.setColor(chooseColor(brightness, ambient));
            g2D.fill(shape);
        } // if
        shape = transform.createTransformedShape(polyBottom.getShape());
        normal = polyBottom.getNormal();
        if (normal.get(2) > 0) {
            double brightness = normal.dotProduct(illumination);
            g2D.setColor(chooseColor(brightness, ambient));
            g2D.fill(shape);
        } // if
        for (int side = 0; side < polyTop.getSize() + 1; side++) {
            Rectangle r = new Rectangle(polyTop, polyBottom, side);
            shape = transform.createTransformedShape(r.getShape());
            normal = r.getNormal();
            if (normal.get(2) > 0) {
                double brightness = normal.dotProduct(illumination);
                g2D.setColor(chooseColor(brightness, ambient));
                g2D.fill(shape);
            } // if
        } // for
    } // paintComponent( Graphics )

    @Override
    public void actionPerformed(ActionEvent event) {
        this.polyTop.transform(spinner);
        this.polyBottom.transform(spinner);
        this.repaint();
    } // actionPerformed( ActionEvent )
} // SwingPanel
