/*
 * TriangleDrawer.java
 *
 * @author Gill Windall
 *
 * This class can be given a SimpleTriangle object and draw it using a 
 * Graphics2D object.
 * 
 */
package uk.ac.gre.comp1549.simpledrawer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class TriangleDrawer implements DrawShape {

    private SimpleTriangle triangle; // the triangle to be drawn

    public TriangleDrawer(SimpleTriangle triangle) {
        this.triangle = triangle;
    }

    public void drawShape(Graphics2D g2d, float currentBrightness) {
        // scale the brightness of the colour
        Color c = scaleColour(triangle.getColour(), currentBrightness);
        g2d.setColor(c);
        DrawerMain d = new DrawerMain();
        int decSelection = d.getDecoratorSelection();
        if (decSelection == 0) {
            g2d.setStroke(new BasicStroke(triangle.getThickness()));
        }
        g2d.drawLine(triangle.getVertices().get(0).x, triangle.getVertices().get(0).y, triangle.getVertices().get(1).x, triangle.getVertices().get(1).y);
        g2d.drawLine(triangle.getVertices().get(2).x, triangle.getVertices().get(2).y, triangle.getVertices().get(1).x, triangle.getVertices().get(1).y);
        g2d.drawLine(triangle.getVertices().get(0).x, triangle.getVertices().get(0).y, triangle.getVertices().get(2).x, triangle.getVertices().get(2).y);

        
    }
    
    @Override
    public void fillShape(Graphics2D g2d, float currentBrightness){
        int [] xes = {triangle.getVertices().get(0).x, triangle.getVertices().get(1).x, triangle.getVertices().get(2).x};
        int [] yes = {triangle.getVertices().get(0).y, triangle.getVertices().get(1).y, triangle.getVertices().get(2).y};
        g2d.fillPolygon(xes, yes, 3);
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
