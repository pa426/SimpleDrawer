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

public class SquareDrawer implements DrawShape {

    private final SimpleSquare square; // the triangle to be drawn

    public SquareDrawer(SimpleSquare square) {
        this.square = square;
    }

    /**
     * Draw the shape using a Graphics2D object
     *
     * @param g2d Graphics2D object used for drawing
     * @param currentBrightness the current brightness being used to draw
     */
    public void drawShape(Graphics2D g2d, float currentBrightness) {
        // scale the brightness of the colour
        Color c = scaleColour(square.getColour(), currentBrightness);
        g2d.setColor(c);
        DrawerMain d = new DrawerMain();
        int decSelection = d.getDecoratorSelection();
        if (decSelection == 0) {
            g2d.setStroke(new BasicStroke(square.getThickness()));
        }
        g2d.drawLine(square.getVertices().get(0).x, square.getVertices().get(0).y, square.getVertices().get(1).x, square.getVertices().get(1).y);
        g2d.drawLine(square.getVertices().get(2).x, square.getVertices().get(2).y, square.getVertices().get(1).x, square.getVertices().get(1).y);
        g2d.drawLine(square.getVertices().get(3).x, square.getVertices().get(3).y, square.getVertices().get(2).x, square.getVertices().get(2).y);
        g2d.drawLine(square.getVertices().get(0).x, square.getVertices().get(0).y, square.getVertices().get(3).x, square.getVertices().get(3).y);

    }
    
    @Override
    public void fillShape(Graphics2D g2d, float currentBrightness){
        int [] xes = {square.getVertices().get(0).x, square.getVertices().get(1).x, square.getVertices().get(2).x, square.getVertices().get(3).x};
        int [] yes = {square.getVertices().get(0).y, square.getVertices().get(1).y, square.getVertices().get(2).y, square.getVertices().get(3).y};
        g2d.fillPolygon(xes, yes, 4);
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
