package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.converter.ConverterFacade;
import com.example.demo.data.provider.ProductManager;
import com.example.demo.exceptions.ProductDuplicateItemException;
import com.example.demo.model.AverageGasPriceCalculator;
import com.example.demo.model.Product;
import com.example.demo.dto.IndexDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.GeoRequestDTO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;

/**
 * @author johnhalowang, Lisa Chen
 *
 */
@RestController
@RequestMapping("/api/product")
public class ProductController {

	Logger logger = LogManager.getLogger(AuthenticationController.class);
	private final ConverterFacade converterFacade;

	@Autowired
	private ProductManager productManager;

	@Autowired
	public ProductController(final ConverterFacade converterFacade) {

		this.converterFacade = converterFacade;
	}

	
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public ResponseEntity<?> getProduct(@RequestBody final IndexDTO dto) {
		return new ResponseEntity<>(productManager.findProductById(dto.get_id()), HttpStatus.OK);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<?> updateProduct(@RequestBody final ProductDTO dto) throws ProductDuplicateItemException {

		Product product = converterFacade.convertProductTO(dto);
		productManager.updateProduct(product);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseEntity<?> deleteProduct(@RequestBody final IndexDTO dto) {

		productManager.deleteProduct(dto.get_id());
		return new ResponseEntity<>(dto.get_id().toHexString(), HttpStatus.OK);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	List<Product> listAllProducts() {

		return productManager.listAllProducts();
	}

	@RequestMapping(value = "/listGeo", method = RequestMethod.POST)
	public List<Product> listGeo(@RequestBody final GeoRequestDTO dto) throws IOException {
		return productManager.getProductsInRadius(dto.getName(), dto.getGeolocation(), dto.getRadius(), dto.getMpg());
	}
	
	@RequestMapping(value = "/getGasPrice", method = RequestMethod.POST)
	public double getGasPrice(@RequestBody final GeoRequestDTO dto) throws IOException {
		AverageGasPriceCalculator gasCalc = new AverageGasPriceCalculator(dto.getGeolocation());
		return gasCalc.getAveragePrice();
	}

	@RequestMapping(value = "/add", method = RequestMethod.PUT)
	public ResponseEntity<?> addProduct(@RequestBody final ProductDTO dto) throws ProductDuplicateItemException {

		Product product = converterFacade.convertProductTO(dto);
		product.set_id(ObjectId.get());
		productManager.addProduct(product);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}
}
