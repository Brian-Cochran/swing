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

    private double phase = 0.0;
    private double ambient = 0.4;
    private Color color = new Color(255, 1, 1);
    private final Matrix4x4 spinner;
    private final Vector4D illumination;
    private final Prism prism;
    private Polygon3D poly1;
    private Polygon3D poly2;
    
    public SwingPanel() {
        Timer timer = new Timer(20, this);
        timer.start();

        this.prism = new Prism(9, 0.8, 0.6);

        Matrix4x4 a = new Matrix4x4();
        a.rotationX(Math.PI / 100);

        Matrix4x4 b = new Matrix4x4();
        b.rotationY(Math.PI / 100);

        Matrix4x4 c = new Matrix4x4();
        c.rotationZ(Math.PI / 100);

        this.spinner = a.multiply(b).multiply(c);
        this.illumination = (new Vector4D(1, 2, 4)).normalize();
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
       
        List<Polygon3D> faces = this.prism.getFaces();
        for (Polygon3D poly : faces) {
            Shape shape = transform.createTransformedShape(poly.getShape());
            
            Vector4D normal = poly.getNormal();
            
            if (normal.get(2) > 0) {
                double brightness = normal.dotProduct(illumination);
                g2D.setColor(chooseColor(brightness, ambient));
                g2D.fill(shape);
            } // if
        } // for
    } // paintComponent( Graphics )

    @Override
    public void actionPerformed(ActionEvent event) {
        this.prism.transform(spinner);
        this.repaint();
    } // actionPerformed( ActionEvent )
} // SwingPanel
