package product.service;

import java.util.List;

import product.entity.Product;


public interface productService {
	public List<Product> getAll();
	public Product selectOne(String productName); 
}
