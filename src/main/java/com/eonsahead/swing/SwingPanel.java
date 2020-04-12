package com.eonsahead.swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * The SwingPanel class contains methods for drawing on and updating the Swing
 * window.
 *
 * This class creates a panel to be used by the Swing class window. Shapes are
 * drawn and transformed on this panel, which is displayed on the window.
 *
 * @author Brian Cochran
 * @version 4/10/2020
 */
public class SwingPanel extends JPanel implements ActionListener {

    private final double ambient = 0.1;
    private Color color = new Color(0, 0, 255);
    private final Matrix4x4 spinner;
    private final Vector4D illumination;
    private final Prism prism;
    private final Cone cone;
    private final AntiPrism antiprism;
    private final int NUM_SIDES = 5;
    private final Matrix4x4 initialSpin;
    private boolean decideTransform = true;

    // set to 1 for a prism, 2 for a cone, and 3 for an antiprism
    private final int shape = 3;

    /**
     * SwingPanel constructor.
     * <p>
     * This constructor starts a timer and sets the rotation and illumination
     * vectors.
     */
    public SwingPanel() {
        Timer timer = new Timer(20, this);
        timer.start();

        ///this.polyTop = new Polygon3D(NUM_SIDES, 0.8, 0.5, 0);
        //this.polyBottom = new Polygon3D(NUM_SIDES, 0.0, -0.5, 1);
        this.prism = new Prism(NUM_SIDES, 0.8, 0.8, 1);
        this.cone = new Cone(0.8, 1);
        this.antiprism = new AntiPrism(NUM_SIDES, 0.8, 0.8, 1);

        Matrix4x4 a = new Matrix4x4();
        a.rotationX(Math.PI / 400);

        Matrix4x4 b = new Matrix4x4();
        b.rotationY(Math.PI / 400);

        Matrix4x4 c = new Matrix4x4();
        c.rotationZ(Math.PI / 400);

        Matrix4x4 d = new Matrix4x4();
        d.rotationZ(Math.PI / NUM_SIDES);

        this.initialSpin = d;
        this.spinner = a.multiply(b).multiply(c);
        this.illumination = (new Vector4D(0, -1, 1)).normalize();
    } // SwingPanel()

    /**
     * Retrieves the color stored in the color variable.
     *
     * @return Color
     */
    public Color getColor() {
        return this.color;
    } // getColor()

    /**
     * Sets the color variable to a specified Color.
     *
     * @param c Color
     */
    public void setColor(Color c) {
        this.color = c;
    } // setColor( Color )

    /**
     * Calculates the red, green, and blue values of a color to be applied to a
     * face of a prism according brightness and ambient light.
     * <p>
     * The brightness of the color is determined by the normal and illumination
     * vectors. If the brightness is less than 0 for any face of the prism, that
     * face has the red, green, and blue values of its color multiplied by the
     * value of the ambient light instead of the brightness.
     *
     * @param brightness amount of light hitting the surface of a polygon
     * @param ambient amount of ambient light hitting surfaces not facing light
     * source
     * @return Color to be applied to face of prism
     */
    public Color chooseColor(double brightness, double ambient) {
        Color c = this.color;
        int red;
        int green;
        int blue;
        if (brightness > ambient) {
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

    /**
     * Draws and scales shapes on Swing window.
     * <p>
     * Depending on the shape variable, this method draws a prism or cone.
     *
     * @param g Graphics object
     */
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

        if (this.shape == 1) {
            for (Polygon3D face : this.prism.faces) {
                shape = transform.createTransformedShape(face.getShape());
                normal = face.getNormal();
                if (normal.get(2) > 0) {
                    double brightness = normal.dotProduct(illumination);
                    g2D.setColor(chooseColor(brightness, ambient));
                    g2D.fill(shape);
                } // if
            } // for
            for (Rectangle side : this.prism.sides) {
                shape = transform.createTransformedShape(side.getShape());
                normal = side.getNormal();
                if (normal.get(2) > 0) {
                    double brightness = normal.dotProduct(illumination);
                    g2D.setColor(chooseColor(brightness, ambient));
                    g2D.fill(shape);
                } // if
            } // for
        } // if
        else if (this.shape == 2) {
            for (Polygon3D face : this.cone.faces) {
                shape = transform.createTransformedShape(face.getShape());
                normal = face.getNormal();
                if (normal.get(2) > 0) {
                    double brightness = normal.dotProduct(illumination);
                    g2D.setColor(chooseColor(brightness, ambient));
                    g2D.fill(shape);
                } // if
            } // for
            for (Rectangle side : this.cone.sides) {
                shape = transform.createTransformedShape(side.getShape());
                normal = side.getNormal();
                if (normal.get(2) > 0) {
                    double brightness = normal.dotProduct(illumination);
                    g2D.setColor(chooseColor(brightness, ambient));
                    g2D.fill(shape);
                } // if
            } // for
        } // else if
        else if (this.shape == 3) {
            for (Polygon3D face : this.antiprism.faces) {
                if (this.decideTransform == true) {
                    face.transform(initialSpin);
                    this.decideTransform = false;
                    shape = transform.createTransformedShape(face.getShape());
                    normal = face.getNormal();
                    if (normal.get(2) > 0) {
                        double brightness = normal.dotProduct(illumination);
                        g2D.setColor(chooseColor(brightness, ambient));
                        g2D.fill(shape);
                    } // if
                } // if
                else {
                    shape = transform.createTransformedShape(face.getShape());
                    normal = face.getNormal();
                    if (normal.get(2) > 0) {
                        double brightness = normal.dotProduct(illumination);
                        g2D.setColor(chooseColor(brightness, ambient));
                        g2D.fill(shape);
                    } // if
                } // else
            } // for
            for (Triangle side : this.antiprism.sides0) {
                shape = transform.createTransformedShape(side.getShape());
                normal = side.getNormal();
                if (normal.get(2) > 0) {
                    double brightness = normal.dotProduct(illumination);
                    g2D.setColor(chooseColor(brightness, ambient));
                    g2D.fill(shape);
                } // if
            } // for
            for (Triangle side : this.antiprism.sides1) {
                shape = transform.createTransformedShape(side.getShape());
                normal = side.getNormal();
                if (normal.get(2) > 0) {
                    double brightness = normal.dotProduct(illumination);
                    g2D.setColor(chooseColor(brightness, ambient));
                    g2D.fill(shape);
                } // if
            } // for
        } // else if
    } // paintComponent( Graphics )

    /**
     * Updates shapes on screen according to spinner matrix.
     * <p>
     * This method allows the shapes on screen to appear animated and 3D.
     *
     * @param event ActionEvent object
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        //this.polyTop.transform(spinner);
        //this.polyBottom.transform(spinner);
        this.prism.transform(spinner);
        this.cone.transform(spinner);
        this.antiprism.transform(spinner);
        this.repaint();
    } // actionPerformed( ActionEvent )
} // SwingPanel
