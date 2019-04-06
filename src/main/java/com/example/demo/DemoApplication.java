package com.example.demo;

import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.example.demo.data.provider.StoreManager;
import com.example.demo.data.provider.impl.FS_StoreManager;
import com.example.demo.data.provider.ProductManager;
import com.example.demo.data.provider.impl.FS_ProductManager;


//@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class DemoApplication {

	
	/**
     * This is a good example of how Spring instantiates
     * objects. The instances generated from this method
     * will be used in this project, where the Autowired
     * annotation is applied.
     */
    @Bean
    public StoreManager storeManager() {
        StoreManager storeManager = new FS_StoreManager();
        return storeManager;
    }
    @Bean
    public ProductManager productManger() {
    	ProductManager productManager= new FS_ProductManager();
        return productManager;
    }

    
	
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
