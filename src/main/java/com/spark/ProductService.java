package com.spark;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.*;
import com.mongodb.MongoClient;

public class ProductService {

	
	MongoClient client = new MongoClient("localhost", 27017); 
	Datastore datastore = new Morphia().createDatastore(client, "product"); 
	
	public String addProduct(Product product){
//		product.setPrice(81.0f);
//		product.setName("Product1");
	      
		datastore.save(product);
		return "product added";
	}
	
	public List<Product> getAllProduct(){
		List<Product> list = datastore.find(Product.class).asList();
		if(list != null){
			return list;
		}
		return null;
	}
	
	public String deleteProductByCriteria(String name){
	
		Query<Product> query = datastore.createQuery(Product.class);
	    query.and(      
	      query.criteria("name").equal(name)
	    ); 
	    datastore.findAndDelete(query); 
	    return "product deleted";
	}	
}
