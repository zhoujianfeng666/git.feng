package product.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import product.dao.productDao;
import product.entity.Product;
import product.service.productService;
@Component
public class productServiceImpl implements productService{
	@Autowired
	private productDao productdao;
	@Override
	public List<Product> getAll() {
		
		return productdao.getAll();
	}
	@Override
	public Product selectOne(String productName) {
		// TODO Auto-generated method stub
		return productdao.selectOne(productName);
	}
	
}
