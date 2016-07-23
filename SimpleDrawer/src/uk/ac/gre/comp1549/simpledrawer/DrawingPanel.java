/*
 * DrawingPanel.java
 *
 * @author Gill Windall
 *
 * A specialised JPanel used as the canvas for simple drawings.
 *
 * NOT created using the NetBeans GUI builder
 */
package uk.ac.gre.comp1549.simpledrawer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;

public class DrawingPanel extends JPanel {

    // current settings used when drawing
    private int currentThickness;
    private Color currentColor;
    private ShapeType currentShapeType;
    private float currentBrightness;
    private int currentRotation;
    private boolean animate;
    private int i = 0;
    private Color animColor;
    private Graphics2D g2 ;



    private List<Point> currentPoints; // x and y points for shape being drawn

    // position of the latest click
    private int x, y;

    // A List that stores the shapes that appear on the JPanel
    private List shapes;  // using a raw type - dangerous !!

    /* Default constructor.  Sets default values for line colour, thickness 
     * and shape type.
     */
    public DrawingPanel() {
        this(Color.BLACK, 5, ShapeType.LINE);
    }

    /* Constructor used to create a DrawingPanel with a
     * specified line colour, thickness and shape type
     */
    public DrawingPanel(Color c, int t, ShapeType st) {
        this.addMouseListener(new MouseWatcher());
        this.setBorder(BorderFactory.createLoweredBevelBorder());
        x = -1;
        y = -1;
        currentColor = c;
//        currentThickness = t;
        currentShapeType = st;
        currentRotation = 0;
        currentBrightness = 1;
        // instantiate the ArrayList to store shapes
        shapes = new ArrayList<>();
    }

    /*
     * paint the drawing including all shapes drawn so far
     *
     * paintComponent() is invoked when repaint() is called and
     * when the GUI needs redrawing e.g. because it has been covered
     * by another window
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Graphics2D needed to set line thickness
        Graphics2D g2d = (Graphics2D) g;
        g2 = g2d;
        Stroke s = g2d.getStroke(); // save stroke to restore later

        // rotate the drawing by the current rotation amount
        double rotateTheta;
        rotateTheta = currentRotation * Math.PI / 180;
        g2d.rotate(rotateTheta, this.getWidth() / 2, this.getHeight() / 2);
        DrawerMain d = new DrawerMain();
        // Loop though the ArrayList drawing
        // all the shapes stored in it
        for (Object aShape : shapes) {

            int decSelection = d.getDecoratorSelection();

            if (decSelection == 0) {
                if (aShape instanceof SimpleLine) {
                    LineDrawer ld = new LineDrawer((SimpleLine) aShape);
                    ld.drawShape(g2d, currentBrightness);
                } else {
                    if (aShape instanceof SimpleOval) {
                        OvalDrawer od = new OvalDrawer((SimpleOval) aShape);
                        od.drawShape(g2d, currentBrightness);
                    } else {
                        if (aShape instanceof SimpleTriangle) {
                            TriangleDrawer td = new TriangleDrawer((SimpleTriangle) aShape);
                            td.drawShape(g2d, currentBrightness);
                        } else {
                            if (aShape instanceof SimpleSquare) {
                                SquareDrawer sd = new SquareDrawer((SimpleSquare) aShape);
                                sd.drawShape(g2d, currentBrightness);
                            }
                        }
                    }
                }
            } else if (decSelection == 1) {
                if (aShape instanceof SimpleLine) {
                    DrawShape ld = new DottyFilledDrawer(new LineDrawer((SimpleLine) aShape));
                    ld.drawShape(g2d, currentBrightness);
                } else {
                    if (aShape instanceof SimpleOval) {
                        DrawShape od = new DottyFilledDrawer(new OvalDrawer((SimpleOval) aShape));
                        od.drawShape(g2d, currentBrightness);
                    } else {
                        if (aShape instanceof SimpleTriangle) {
                            DrawShape td = new DottyFilledDrawer(new TriangleDrawer((SimpleTriangle) aShape));
                            td.drawShape(g2d, currentBrightness);
                        } else {
                            if (aShape instanceof SimpleSquare) {
                                DrawShape sd = new DottyFilledDrawer(new SquareDrawer((SimpleSquare) aShape));
                                sd.drawShape(g2d, currentBrightness);
                            }
                        }
                    }
                }
            } else if (decSelection == 2) {
                if (aShape instanceof SimpleLine) {
                    LineDrawer ld = new LineDrawer((SimpleLine) aShape);
                    ld.drawShape(g2d, currentBrightness);
                } else {
                    if (aShape instanceof SimpleOval) {
                        DrawShape od = new DottyFilledDrawer(new OvalDrawer((SimpleOval) aShape));
                        od.fillShape(g2d, currentBrightness);
                    } else {
                        if (aShape instanceof SimpleTriangle) {
                            DrawShape td = new DottyFilledDrawer(new TriangleDrawer((SimpleTriangle) aShape));
                            td.fillShape(g2d, currentBrightness);
                        } else {
                            if (aShape instanceof SimpleSquare) {
                                DrawShape sd = new DottyFilledDrawer(new SquareDrawer((SimpleSquare) aShape));
                                sd.fillShape(g2d, currentBrightness);
                            }
                        }
                    }
                }
            }
        }

        g2d.setStroke(s);  // restore saved stroke

        if (currentPoints != null && currentPoints.size() == 1) { // draw dot where line started
            g2d.setColor(currentColor);
            g2d.fillOval(currentPoints.get(0).x, currentPoints.get(0).y, 3, 3);
        } else if (currentPoints != null && currentPoints.size() == 2) { // draw dot where line started
            g2d.setColor(currentColor);
            g2d.fillOval(currentPoints.get(1).x, currentPoints.get(1).y, 3, 3);
        } else if (currentPoints != null && currentPoints.size() == 3) { // draw dot where line started
            g2d.setColor(currentColor);
            g2d.fillOval(currentPoints.get(2).x, currentPoints.get(2).y, 3, 3);
        }
    }

    /**
     * @return the currentShapeType
     */
    public ShapeType getCurrentShapeType() {
        return currentShapeType;
    }

    /**
     * @param currentShapeType the currentShapeType to set
     */
    public void setCurrentShapeType(ShapeType currentShapeType) {
        this.currentShapeType = currentShapeType;
    }

    /**
     * @return the currentBrightness
     */
    public float getCurrentBrightness() {
        return currentBrightness;
    }

    /*
     * currentBrightness is passed in as a number in the range
     * 0 to 1.  In this class it needs to be in the range 0.75 to
     * 1.25 which is what the division by 2 and addition of
     * 0.75 achieves.
     */
    public void setCurrentBrightness(float currentBrightness) {
        this.currentBrightness = (currentBrightness / 2) + 0.75F;
        repaint();
    }


    /* MouseWatcher is an inner class used to handle the
     * mouse events generated by the user clicking on the drawing panel
     */
    private class MouseWatcher extends MouseAdapter {

        public void mousePressed(MouseEvent e) {
            // reset the rotation to 0 otherwise things get messy.
            currentRotation = 0;

            if (currentPoints == null) { // must be starting a new shape
                currentPoints = new ArrayList<>();
                Point firstPoint = new Point();
                firstPoint.x = e.getX();
                firstPoint.y = e.getY();
                currentPoints.add(firstPoint);
            } else { // shape must have already been started
                // decide what to do based on the current shape

                Point xPoint = new Point();
                xPoint.x = e.getX();
                xPoint.y = e.getY();
                currentPoints.add(xPoint);

                if (currentShapeType == ShapeType.LINE && currentPoints.size() == 2 || currentShapeType == ShapeType.OVAL && currentPoints.size() == 2) {
                    SimpleShape n = ShapeFactory.createShape(currentPoints, currentColor, currentThickness, currentShapeType);
                    shapes.add(n);
                    currentPoints = null;
                } else if (currentShapeType == ShapeType.TRIANGLE && currentPoints.size() == 3) {
                    SimpleShape n = ShapeFactory.createShape(currentPoints, currentColor, currentThickness, currentShapeType);
                    shapes.add(n);
                    currentPoints = null;
                } else if (currentShapeType == ShapeType.SQUARE && currentPoints.size() == 4) {
                    SimpleShape n = ShapeFactory.createShape(currentPoints, currentColor, currentThickness, currentShapeType);
                    shapes.add(n);
                    currentPoints = null;
                }

            }
            repaint(); // causes paintComponent() to be called
        }
    }

    public void setCurrentThickness(int currentThickness) {
        this.currentThickness = currentThickness;
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }

    public void clearDisplay() {
        // Empty the ArrayList and clear the display.
        shapes.clear();
        repaint();
    }

    public void createAnimation() {
        Timer t = new Timer(10, new AbstractAction() {
            int count = 0;
            int j = 0;
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (count < 30) {
                    List<Point> x;
                    x = new ArrayList<>();
                    x.add(new Point(j, 0));
                    x.add(new Point(j, 600));
                    SimpleLine ln1 = new SimpleLine(x, currentColor, 25, ShapeType.LINE);
                    shapes.add(ln1);
                    g2.setColor(animColor);
                    LineDrawer ld1 = new LineDrawer(ln1);
                    ld1.drawShape(g2, currentBrightness);
                    
                    List<Point> y;
                    y = new ArrayList<>();
                    y.add(new Point(0, j));
                    y.add(new Point(600, j));
                    SimpleLine ln2 = new SimpleLine(y, Color.WHITE, 25, ShapeType.LINE);
                    shapes.add(ln2);
                    g2.setColor(animColor);
                    LineDrawer ld2 = new LineDrawer(ln2);
                    ld2.drawShape(g2, currentBrightness);
                   
                    count++;
                    j+=25;
                    repaint();
                } else {//counter is at 1000 stop the timer
                    ((Timer) ae.getSource()).stop();
                    animate = false;
                    clearDisplay();
                }
            }
        });
        t.start();
    }


    /* The whole drawing area can be rotated left or right.
     * The amount passed in is the amount in degrees to rotate.
     * A negative number roates to the left and a positive number to
     * the right
     */
    public void rotate(int amount) {
        currentRotation += amount;
        repaint();
    }

    public void setShapes(List shapes) {
        this.shapes = shapes;
    }

}
