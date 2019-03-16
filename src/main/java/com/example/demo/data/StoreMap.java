package com.example.demo.data;

import java.util.HashMap;

/**
 * This class is a HashMap, but we extend the HashMap
 * class so that we can rename it to something meaningful.
 * <p>
 * Basically, the key of the map is the store ID, and the
 * value is the actual Store object.
 * <p>
 * Using a HashMap allows us to quickly query the store
 * object.
 *
 * @author johnhalowang (chi-wei wang)
 *
 */

@SuppressWarnings("serial")
public class StoreMap extends HashMap<String, Store>{

}
