/*
 * SimpleLine.java
 *
 * @author Gill Windall
 *
 * Represents a line that can be drawn on a drawing area
 *
 */
package uk.ac.gre.comp1549.simpledrawer;

import java.awt.Color;
import java.awt.Point;
import java.util.List;

public class SimpleLine extends SimpleShape{
    
    public SimpleLine(List<Point> v, Color c, int t, ShapeType st) {
        super(v, c, t, st);
    }
    
}
