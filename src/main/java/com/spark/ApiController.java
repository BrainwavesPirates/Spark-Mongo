package com.spark;


import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.delete;
import com.google.gson.Gson;

import static com.spark.JsonUtil.*;

public class ApiController {
		
	public ApiController( ProductService productService) {
		
		Gson gson = new Gson();		
        post("/add", (req, res) -> {
        		res.type("application/json");
        		Product product = gson.fromJson(req.body(), Product.class);
        		return productService.addProduct(product);
        }, gson ::toJson);
        
        get("/", (req, res) -> {
    		res.type("application/json");
    		return productService.getAllProduct();
        }, gson ::toJson);
        
        delete("/:name", (req, res) -> {
    		res.type("application/json");
    		String productName = req.params(":name");    		
    		return productService.deleteProductByCriteria(productName);
        }, gson ::toJson);
        
        exception(IllegalArgumentException.class, (e, req, res) -> {
			res.status(400);
			res.body(toJson(new ResponseError(e)));
		});
	}
}
