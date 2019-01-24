package com.shopping.backend.dao;

import java.util.List;

import com.shopping.backend.model.Category;
import com.shopping.backend.model.Product;

public interface ProductDAO 
{
	public void addProduct(Product product);
	public List<Product> getAllProducts();
	public Product getProduct(int productId);
	public void updateProduct(Product product);
	public void deleteProduct(int productId);
	public List<Product> getCategoryProducts(String category);
	public void saveOrUpdate(Product product);
	public List<Category> getAllCategories();
	public void addCategory(Category category);
}