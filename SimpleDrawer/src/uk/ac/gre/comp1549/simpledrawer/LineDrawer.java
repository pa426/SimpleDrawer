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

public class LineDrawer implements DrawShape{

    private final SimpleShape line; // the line to be drawn

    public LineDrawer(SimpleLine line) {
        this.line = line;
    }

    @Override
    public void drawShape(Graphics2D g2d, float currentBrightness) {
        // scale the brightness of the colour
        Color c = scaleColour(line.getColour(), currentBrightness);
        g2d.setColor(c);
        DrawerMain d = new DrawerMain();
        int decSelection = d.getDecoratorSelection();
        if (decSelection == 0) {
            g2d.setStroke(new BasicStroke(line.getThickness()));
        }
        int xs = line.getVertices().get(0).x;
        int ys = line.getVertices().get(0).y;

        // draw the line
        g2d.drawLine(xs, ys, line.getVertices().get(1).x, line.getVertices().get(1).y);
    }

    @Override
    public void fillShape(Graphics2D g2d, float currentBrightness){
        
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
