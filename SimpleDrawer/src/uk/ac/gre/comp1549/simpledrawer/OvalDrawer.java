/*
 * OvalDrawer.java
 *
 * @author Gill Windall
 *
 * This class can be given a SimpleOval object and draw it using a 
 * Graphics2D object.
 * 
 */
package uk.ac.gre.comp1549.simpledrawer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class OvalDrawer implements DrawShape {

    private int xs, ys, xb, yb;
    private SimpleOval oval; // the oval to be drawn

    public OvalDrawer(SimpleOval oval) {
        this.oval = oval;
    }

    public void drawShape(Graphics2D g2d, float currentBrightness) {
        // scale the brightness of the colour
        Color c = scaleColour(oval.getColour(), currentBrightness);
        g2d.setColor(c);
        // set the thickness of the line
        DrawerMain d = new DrawerMain();
        int decSelection = d.getDecoratorSelection();
        if (decSelection == 0) {
            g2d.setStroke(new BasicStroke(oval.getThickness()));
        }

        xs = oval.getVertices().get(0).x;
        ys = oval.getVertices().get(0).y;
        xb = oval.getVertices().get(1).x;
        yb = oval.getVertices().get(1).y;

        if (xb < xs && yb < ys) {
            g2d.drawOval(xb, yb, xb - xs, yb - ys);
        } else if (xb > xs && yb > ys) {
            g2d.drawOval(xs, ys, xb - xs, yb - ys);
        } else if (xb < xs) {
            g2d.drawOval(xb, ys, xs - xb, yb - ys);
        } else if (yb < ys) {
            g2d.drawOval(xs, yb, xb - xs, ys - yb);
        } else if (xb > xs) {
            g2d.drawOval(xs, yb, xs - xb, ys - yb);
        } else if (yb > ys) {
            g2d.drawOval(xb, ys, xb - xs, yb - ys);
        }

    }

    @Override
    public void fillShape(Graphics2D g2d, float currentBrightness) {
        Color c = scaleColour(oval.getColour(), currentBrightness);
        g2d.setColor(c);

        xs = oval.getVertices().get(0).x;
        ys = oval.getVertices().get(0).y;
        xb = oval.getVertices().get(1).x;
        yb = oval.getVertices().get(1).y;

        if (xb < xs && yb < ys) {
            g2d.fillOval(xb, yb, xb - xs, yb - ys);
        } else if (xb > xs && yb > ys) {
            g2d.fillOval(xs, ys, xb - xs, yb - ys);
        } else if (xb < xs) {
            g2d.fillOval(xb, ys, xs - xb, yb - ys);
        } else if (yb < ys) {
            g2d.fillOval(xs, yb, xb - xs, ys - yb);
        } else if (xb > xs) {
            g2d.fillOval(xs, yb, xs - xb, ys - yb);
        } else if (yb > ys) {
            g2d.fillOval(xb, ys, xb - xs, yb - ys);
        }
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
