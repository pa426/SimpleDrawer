/*
 * JSONShapeREader.java
 *
 * @author Gill Windall
 *
 * Used to read shapes from a file stored in JSON format.  Uses the Gson
 * library to convert the JSON from the file into Java objects in memory.
 * You can read more about Gson at https://code.google.com/p/google-gson/
 *
 */
package uk.ac.gre.comp1549.simpledrawer;

import com.google.gson.*;
import java.awt.Color;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONShapeReader {

    // ListOfShapeEvents is an inner class used to wrap a list of 
    // ShapeEvent objects which hold shape details
    static class ListOfShapeEvents {

        List<ShapeEvent> listOfShapeEvents;
    }

    private ListOfShapeEvents los; // list of all the shapes
    private List<SimpleShape> slList; // list of lines
    private List<SimpleShape> olList; // list of ovals
    private List<SimpleShape> tlList; // list of triangles
    private List<SimpleShape> sqlList; // list of triangles

    private Gson gson; // gson object used to "parse" the JSON

    public JSONShapeReader() {
        gson = new Gson();
        slList = new ArrayList<>();
        olList = new ArrayList<>();
        tlList = new ArrayList<>();
        sqlList = new ArrayList<>();
    }

    /**
     * Read the shapes in JSON format from file. The shapes are initially read
     * into a single list (listOfShapeEvents) and then split into separate lists
     * according to type of shape.
     *
     * @param file the file from which to read the JSON
     * @throws FileNotFoundException
     */
    public void getShapesFromFile(String file) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(
                new FileReader(file));
        los = gson.fromJson(br, ListOfShapeEvents.class); // load the shapes
        storeShapes(); // store in separate lists according to type
    }

    /**
     * Read through the list of shapes that have been loaded from file and
     * create an appropriate shape object according to type and store it in the
     * relevant list.
     */
    private void storeShapes() {

        for (ShapeEvent se : los.listOfShapeEvents) {

            SimpleShape n = ShapeFactory.createShape(se.getVertices(), se.getColour(), se.getThickness(), se.getShapeType());
            if (se.getShapeType() == ShapeType.LINE) {
                slList.add(n);
            } else if (se.getShapeType() == ShapeType.OVAL) {
                olList.add(n);
            } else if (se.getShapeType() == ShapeType.TRIANGLE) {
                tlList.add(n);
            } else if (se.getShapeType() == ShapeType.SQUARE) {
                sqlList.add(n);
            }
        }
    }

    /**
     *
     * @return the list of line shapes
     */
    public List<SimpleShape> getSlList() {
        return slList;
    }

    public List<SimpleShape> getOlList() {
        return olList;
    }

    public List<SimpleShape> getTlList() {
        return tlList;
    }

    public List<SimpleShape> getSqlList() {
        return sqlList;
    }
    

    /**
     * This method is used to create some initial data in the JSON file which
     * can then be loaded later
     *
     * @param file the file into which to write the JSON
     */
    private static void generateTestJSON(String file) {
        List<ShapeEvent> list = new ArrayList<>();
        List<Point> x = new ArrayList<>();
        // load in some hard-coded shapes
        list.add(new ShapeEvent(x, Color.red, 5, ShapeType.LINE, "SHAPE"));
        list.add(new ShapeEvent(x, Color.blue, 5, ShapeType.OVAL, "SHAPE"));
        list.add(new ShapeEvent(x, Color.green, 5, ShapeType.LINE, "SHAPE"));
        ListOfShapeEvents los = new ListOfShapeEvents();
        los.listOfShapeEvents = list;
        Gson gson = new Gson();
        String json = gson.toJson(los); // convert the object to a JSON string

        try {
            FileWriter writer = new FileWriter(file);
            writer.write(json); // write the JSON string to file
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Main method is just for testing in standalone mode
     *
     * @param args unused
     */
    public static void main(String[] args) throws FileNotFoundException {

//        generateTestJSON("stored_shapes.json"); // uncomment if you wish to 
        // create a file of JSON
        // Read the JSON from file and output number of lines and number
        // of ovals read.
        JSONShapeReader me = new JSONShapeReader();
        me.getShapesFromFile("stored_shapes.json");
        System.out.println("Lines loaded = " + me.slList.size());
        System.out.println("Ovals loaded = " + me.olList.size());
        System.out.println("Triangles loaded = " + me.tlList.size());
        System.out.println("Squares loaded = " + me.sqlList.size());

    }
}
