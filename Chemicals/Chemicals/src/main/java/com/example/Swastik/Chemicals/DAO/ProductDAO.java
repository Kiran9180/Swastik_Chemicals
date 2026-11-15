package com.example.Swastik.Chemicals.DAO;

import java.util.List;

import com.example.Swastik.Chemicals.Model.Product;

public interface ProductDAO {
	
	
	
	
	
	 void save(Product product);

	    void update(Product product);

	    void delete(Long productId);

	    Product getById(Long productId);

	    List<Product> getAll();

	    List<Product> getActiveProducts();
	
	

}
