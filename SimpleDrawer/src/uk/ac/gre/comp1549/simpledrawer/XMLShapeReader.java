/*
 * XMLShapeREader.java
 *
 * @author Gill Windall
 *
 * Used to read shapes from a file stored in XML format using 
 * the class ShapeEventGeneratorFromXML
 *
 */
package uk.ac.gre.comp1549.simpledrawer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;


public class XMLShapeReader {

    private ShapeEventGeneratorFromXML segfx; // used to read the data

    private List<SimpleShape> slList; // list of lines
    private List<SimpleShape> olList; // list of ovals
    private List<SimpleShape> tlList; // list of triangles
    private List<SimpleShape> sqlList; // list of triangles

    public XMLShapeReader() throws ParserConfigurationException, SAXException {

        try {
            segfx = new ShapeEventGeneratorFromXML();
        } catch (ParserConfigurationException | SAXException ex) {
            Logger.getLogger(XMLShapeReader.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }

        // Create a listerner to receive callbacks from the
        // ShapeEventGeneratorFromXML object each time a Shape has
        // been read from file.
        ShapeEventListener sel = new ShapeEventListener() {
            /**
             *
             * @param originator unused
             * @param se the shape event object containing details of the shape
             * that has just been read from file.
             */
            @Override
            public void processShapeEvent(Object originator, ShapeEvent se) {
                storeShape(se);
            }
        };
        segfx.registerShapeEventListener("SHAPE", sel);
        slList = new ArrayList<>();
        olList = new ArrayList<>();
        tlList = new ArrayList<>();
        sqlList = new ArrayList<>();
    }

    /**
     * Called to initiate the processing of the XML file containing shapes.
     *
     * @param file the name of the XML file to process
     * @throws IOException
     * @throws SAXException
     */
    public void getShapesFromFile(String file) throws IOException, SAXException {
        segfx.processScriptFile(file);
    }

    /**
     * Called each time a shape has been read. The shape is stored in the
     * appropriate list according to type.
     *
     * @param se the ShapeEvent object containing details of the shape to be
     * stored.
     */
    private void storeShape(ShapeEvent se) {

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
     * Main method is just for testing in standalone mode
     *
     * @param args unused
     */
    public static void main(String[] args) {
        try {
            XMLShapeReader me = new XMLShapeReader();

            me.getShapesFromFile("stored_shapes.xml");
            System.out.println("number of lines: " + me.slList.size());
            System.out.println("number of ovals: " + me.olList.size());
            System.out.println("number of triangles: " + me.tlList.size());
            System.out.println("number of triangles: " + me.sqlList.size());

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(XMLShapeReader.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }
}
