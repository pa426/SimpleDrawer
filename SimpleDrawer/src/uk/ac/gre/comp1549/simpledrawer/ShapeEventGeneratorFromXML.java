/**
 * ShapeEventGeneratorFromXML reads and parses an XML file which holds a script
 * of shapes. It generates ShapeEvents which listeners can register to receive.
 *
 * @author Gill Windall
 * @version 3.0
 */
package uk.ac.gre.comp1549.simpledrawer;

import java.awt.Point;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShapeEventGeneratorFromXML extends DefaultHandler {

    // constants used to control processing of the XML file
    /**
     * XML tag used to indicate a shape
     */
    public final static String SHAPE_TAG = "shape";

    /**
     * XML tag used to indicate the type of shape
     */
    public final static String TYPE_TAG = "type";

    /**
     * XML tag used to indicate start position of shape
     */
    public final static String START_TAG = "start";

    public final static String MIDDLE_TAG = "middle";
    /**
     * XML tag used to indicate end position of shape
     */
    public final static String END_TAG = "end";
    /**
     * XML tag used to indicate x coordinate
     */
    public final static String X_TAG = "x";

    /**
     * XML tag used to indicate y coordinate
     */
    public final static String Y_TAG = "y";

    /**
     * XML tag used to indicate colour of shape
     */
    public final static String COLOUR_TAG = "colour";

    /**
     * XML tag used to indicate thickness of line of a shape
     */
    public final static String THICK_TAG = "thickness";

    private String currentTag = ""; // current tag being processed

    // list of listeners registered to receive dashboard events
    private final ShapeEventList shapeListeners;

    // the xml parser object
    private final XMLReader xmlReader;

    // details of current shape being read
    private ShapeEvent currentShape;

    // to keep track of whether we are processing start position of the shape
    private boolean processingStartPos = false;

    private List<Point> currentPoints = new ArrayList<>();
   

    /**
     *
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public ShapeEventGeneratorFromXML() throws ParserConfigurationException, SAXException {
        shapeListeners = new ShapeEventList();

        // The following code configures and creates an object known as a SAXParser which is capable of
        // reading and interpreting an XML file.
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(true);
        SAXParser saxParser;
        try {
            saxParser = spf.newSAXParser();
            xmlReader = saxParser.getXMLReader();
        } catch (ParserConfigurationException | SAXException ex) {
            Logger.getLogger(ShapeEventGeneratorFromXML.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    /**
     *
     * @param filename - filename of the XML file to be processed
     * @throws IOException - problem reading the file
     * @throws SAXException - problem parsing the file
     */
    public void processScriptFile(String filename) throws IOException, SAXException {
        // register the current object to receive callbacks when elements are encountered in the XML file
        xmlReader.setContentHandler(this);
        // Start the parsing process.  As the file is processed methods in the startElement(), endElement() and
        // characters() methods in the current object will be called to handle the content of the XML file.
        xmlReader.parse(convertToFileURL(filename));
    }

    /**
     * startElement() is called by the parser whenever a start tag (tag =
     * element) is encountered in the XML file. Store the tag's name and if it
     * is an SHAPE_tag create a new ShapeEvent object that will be populated
     * from the data read from the file in the the character() method
     *
     * @param namespaceURI
     * @param localName - the name of the tag e.g. "shape"
     * @param atts
     * @throws SAXException - problem parsing the file
     */
    @Override
    public void startElement(String namespaceURI,
            String localName,
            String qName,
            Attributes atts)
            throws SAXException {

        currentTag = localName;

        switch (currentTag) {
            case SHAPE_TAG:
                currentShape = new ShapeEvent(); // starting a new shape so create object
                currentShape.setEventType("SHAPE");
                break;
            case START_TAG:
                processingStartPos = true;
                break;
            case MIDDLE_TAG:
                processingStartPos = true;
                break;
            case END_TAG:
                processingStartPos = false;
                break;
        }
    }

    /**
     * characters() is called by the parser when character content is to be
     * processed. Note that we need to check what type of tag is currently being
     * processed in order to know what to do with the characters. For instance
     * if we are processing a delay tag we want to pause processing for the
     * specified amount of time.
     *
     * @param ch - array holding the characters to be processed
     * @param start - start position of current characters within the array
     * @param length - number of characters to process
     * @throws SAXException
     */
    @Override
    public void characters(char ch[], int start, int length)
            throws SAXException {
        // get the characters into a String and lose any unwanted whitespace
        String val = new String(ch, start, length).trim();

        if (val.length() < 1) {  // if no characters to process (was all whitespace) just return
            return;
        }

        // process the characters based on what type of tag we are currently dealing with.
        switch (currentTag) {
            case TYPE_TAG:
                currentShape.setShapeType(ShapeType.valueOf(val.toUpperCase()));
                break;
            case X_TAG: // an x tag can either be for the start or end 
                if (processingStartPos) {
                    currentShape.setPointsX(Integer.parseInt(val)); 
                } else {
                    currentShape.setPointsX(Integer.parseInt(val)); 
                }
                break;
            case Y_TAG: // an y tag can either be for the start or end 
                if (processingStartPos) {
                     currentShape.setPointsY(Integer.parseInt(val)); 
                } else {
                     currentShape.setPointsY(Integer.parseInt(val)); 
                }
                break;
            case COLOUR_TAG:
                currentShape.setColourByString(val);
                break;
            case THICK_TAG:
                currentShape.setThickness(Integer.parseInt(val));
                break;
        }

        
    }

    /**
     * endElement() is called by the parser when the end tag of an element is
     * encountered. At that point know that we have finished processing that
     * tag. The only situation we are interested in is when the end "shape" tag
     * is found. At that point we know that we have all the data for the shape
     * and can passing the object to any listeners
     *
     * @param uri
     * @param localName - the name of the tag e.g. "dashboard_event"
     * @param qName
     * @throws SAXException
     */
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        if (localName.equals(SHAPE_TAG)) { // we are at the end of the SHAPE
            // get all listeners
            List<ShapeEventListener> listeners = shapeListeners.getListeners(currentShape.getEventType());
            if (listeners != null) {
                // loop through the listeners passing the event object to them - this is "firing" the event
                for (ShapeEventListener sel : listeners) {
                    sel.processShapeEvent(this, currentShape);
                }
            }
            currentShape = null;
        }
        currentTag = "";
    }

    /**
     * registerShapeEventListener() is called by objects that want to be
     * notified when an event occurs,
     *
     * @param type - type of the event listener is interested in (e.g. "speed")
     * @param dbel - reference to the listener object
     */
    public void registerShapeEventListener(String type, ShapeEventListener dbel) {
        shapeListeners.addListener(type, dbel);
    }

    /**
     * removeShapeEventListener() is called by objects that not longer want to
     * be notified when an event occurs,
     *
     * @param type - type of the event listener wishes to deregister for (e.g.
     * "SHAPE")
     * @param dbel - reference to the listener object
     */
    public void removeShapeEventListener(String type, ShapeEventListener dbel) {
        shapeListeners.removeListener(type, dbel);
    }

    /**
     * convertToFileURL() is a utility method just used if this class is run in
     * standalone mode to test the class
     *
     * @param filename - name of the xml file
     * @return filename in URL format e.g "file:///c:/files/file.xml"
     */
    private static String convertToFileURL(String filename) {
        String path = new File(filename).getAbsolutePath();
        if (File.separatorChar != '/') {
            path = path.replace(File.separatorChar, '/');
        }

        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        return "file:" + path;
    }

    /**
     * Output a help message to the user
     */
    private static void usage() {
        System.err.println("Usage:  ShapeEventGeneratorFromXML <file.xml>");
        System.err.println("       -usage or -help = this message");
        System.exit(1);
    }

    /**
     * main() method is only used if the class is run in standalone mode for
     * testing purposes.
     *
     * @param args - last argument (args[length-1]) is the name of the xml file
     * to process
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String filename = null;

        // get the filename if persent
        for (int i = 0; i < args.length; i++) {
            filename = args[i];
            if (i != args.length - 1) {
                usage();
            }
        }

        if (filename == null) {
            usage();
        }

        // Create an instance of DashboardEventGeneratorFromXML and test it
        ShapeEventGeneratorFromXML me = new ShapeEventGeneratorFromXML();
        ShapeEventListener sel = new ShapeEventListener() {
            @Override
            public void processShapeEvent(Object originator, ShapeEvent se) {
                System.out.println("***** " + se);
            }
        };
        me.registerShapeEventListener("SHAPE", sel);
        me.processScriptFile(filename);
        me.removeShapeEventListener("SHAPE", sel);
        me.processScriptFile(filename);
    }
}
