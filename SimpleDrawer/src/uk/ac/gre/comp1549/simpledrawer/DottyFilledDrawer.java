/*
 * LineDrawer.java
 *
 * @author Gill Windall
 *
 * This class can be given a SimpleLine object and draw it using a 
 * Graphics2D object.
 * 
 */
package uk.ac.gre.comp1549.simpledrawer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class DottyFilledDrawer extends ShapeDecorator {

    public DottyFilledDrawer(DrawShape sh) {
        super(sh);
    }

    @Override
    public void drawShape(Graphics2D g2d, float currentBrightness) {
        myShape.drawShape(g2d, currentBrightness);
        float[] dashingPattern3 = {10f, 10f, 1f, 10f};
        Stroke stroke3 = new BasicStroke(4f, BasicStroke.CAP_SQUARE,
                BasicStroke.JOIN_MITER, 1.0f, dashingPattern3, 0.0f);

        g2d.setStroke(stroke3);
        
    }

    @Override
    public void fillShape(Graphics2D g2d, float currentBrightness) {
        myShape.fillShape(g2d, currentBrightness);
    }

    public Color scaleColour(Color c, float currentBrightness) {
        // get the red amount and scale by currentBrightness
        int red = (int) (c.getRed() * currentBrightness);
        // check we've not gone over 255
        red = red > 255 ? 255 : red;
        // do the same for green and blue
        int green = (int) (c.getGreen() * currentBrightness);
        green = green > 255 ? 255 : green;
        int blue = (int) (c.getBlue() * currentBrightness);
        blue = blue > 255 ? 255 : blue;
        Color scaledColour = new Color(red, green, blue);

        return scaledColour;
    }
}
