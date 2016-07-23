package uk.ac.gre.comp1549.simpledrawer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Holds a Map the keys of which are types of ShapeEvents (e.g type "SHAPE")
 * the values are the list of Listeners registered for that type of event.
 *
 * @author Gill
 */
public class ShapeEventList {

    // key is event type, value is list of listeners for that type
    Map<String, List<ShapeEventListener>> eventListeners;

    /**
     * default constructor
     */
    public ShapeEventList() {
        eventListeners = new HashMap<>();
    }

    /**
     * Add a ShapeEventListener
     *
     * @param type - the type of event listened for (e.g "SHAPE")
     * @param listener - reference to the listener object
     */
    public void addListener(String type, ShapeEventListener listener) {
        List<ShapeEventListener> dbl = eventListeners.get(type);
        if (dbl == null) { // if no listeners for this type already registered
            dbl = new ArrayList<>(); // create a new list
        }
        dbl.add(listener); // add the listener to the list
        eventListeners.put(type, dbl); // update the map
    }

    /**
     * Remove a ShapeEventListener
     *
     * @param type - the type of event listened for (e.g "SHAPE")
     * @param listener - reference to the listener object
     */
    public void removeListener(String type, ShapeEventListener listener) {
        List<ShapeEventListener> sel = eventListeners.get(type);
        if (sel != null) { // if there are any listeners for the specified event type
            while (sel.remove(listener)); // remove listener looping in case more than one
        }
    }

    /**
     * Return a list of ShapeEventListeners for a specified type of event
     * @param type - the type of event for which listeners are required (e.g. "SHAPE")
     * @return - the list of listeners of the specified type
     */
    public List<ShapeEventListener> getListeners(String type) {
        return eventListeners.get(type);
    }
}
