/**
 * 
 */
package com.example.demo.data.provider;

import com.example.demo.data.Product;
import com.example.demo.data.Geolocation;
import java.util.Collection;
import java.util.List;
import org.bson.types.ObjectId;


/**
 * @author johnhalowang, Lisa Cheng, Gina 
 *
 */

public interface ProductManager {
	
	public List<Product>getProductRadius(String productName, Geolocation geo, double miles);
	public Collection<Product> getProductByName(String name);
	public void updateProduct(Product product);
	public boolean deleteProduct(ObjectId id);
	public List<Product> listAllProducts();
	public void addProduct(Product product);
	public Product findProductById(ObjectId id);
}
