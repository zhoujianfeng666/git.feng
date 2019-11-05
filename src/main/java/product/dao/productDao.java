package product.dao;

import java.util.List;


import product.entity.Product;

public interface productDao {
	public List<Product> getAll();
	
	public Product selectOne(String productName);
	
}
