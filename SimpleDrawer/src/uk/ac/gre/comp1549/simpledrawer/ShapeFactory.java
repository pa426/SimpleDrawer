/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.gre.comp1549.simpledrawer;

import java.awt.Color;
import java.awt.Point;
import java.util.List;

/**
 *
 * @author Alexandru
 */
public class ShapeFactory {

    public static SimpleShape createShape(List<Point> v, Color c, int t, ShapeType st) {

        if (st == ShapeType.LINE) {
            return new SimpleLine(v, c, t, st);
        } else if (st == ShapeType.OVAL) {
            return new SimpleOval(v, c, t, st);
        } else if (st == ShapeType.TRIANGLE) {
            return new SimpleTriangle(v, c, t, st);
        } else if (st == ShapeType.SQUARE) {
            return new SimpleSquare(v, c, t, st);
        }
        return null;
    }

}
