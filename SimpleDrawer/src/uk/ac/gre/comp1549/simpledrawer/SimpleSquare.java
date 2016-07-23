/*
 * SimpleTriangle.java
 *
 * @author Gill Windall
 *
 * Represents a triangle that can be drawn on a drawing area
 *
 */
package uk.ac.gre.comp1549.simpledrawer;

import java.awt.Color;
import java.awt.Point;
import java.util.List;

public class SimpleSquare extends SimpleShape implements Area {

    private List<Point> v;

    public SimpleSquare(List<Point> v, Color c, int t, ShapeType st) {
        super(v, c, t, st);
    }

    @Override
    public double getArea() {
        int term1 = v.get(0).x * (v.get(1).y - v.get(2).y);
        int term2 = v.get(1).x * (v.get(2).y - v.get(0).y);
        int term3 = v.get(2).x * (v.get(3).y - v.get(1).y);
        int term4 = v.get(3).x * (v.get(0).y - v.get(1).y);

        return Math.abs((term1 + term2 + term3 + term4) / 2.0);
    }
}
