/**
 * 
 */
package com.example.demo.data.provider;

import com.example.demo.data.Product;
import com.example.demo.data.Geolocation;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * @author johnwang
 *
 */
public interface ProductManager {
	public List<Product>getProductRadius(String productName, Geolocation geo, double miles);
	public Collection<Product> getProductByName(String name);
}
