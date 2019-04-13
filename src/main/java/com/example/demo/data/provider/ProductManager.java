/**
 * 
 */
package com.example.demo.data.provider;

import com.example.demo.exceptions.ProductDuplicateItemException;
import com.example.demo.model.Geolocation;
import com.example.demo.model.Product;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import org.bson.types.ObjectId;


/**
 * @author johnhalowang, Lisa Cheng, Gina 
 *
 */

public interface ProductManager {
	
	public List<Product>getProductsInRadius(String productName, Geolocation geo, double miles, double mpg) throws IOException;
	public List<Product>getAvailableProductsInRadius(String productName, Geolocation geo, double miles, double mpg) throws IOException;
	public Collection<Product> getProductByName(String name);
	public void updateProduct(Product product) throws ProductDuplicateItemException;
	public void deleteProduct(ObjectId id);
	public List<Product> listAllProducts();
	public void addProduct(Product product) throws ProductDuplicateItemException;
	public Product findProductById(ObjectId id);
}
