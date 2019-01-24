package com.shopping.backend.daoimpl;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.backend.dao.ProductDAO;
import com.shopping.backend.model.Category;
import com.shopping.backend.model.Product;

@Repository
@Transactional
public class ProductDAOImpl implements ProductDAO
{
    @Autowired
	private SessionFactory sessionfactory;
	
    public ProductDAOImpl()
    {
		System.out.println("ProductDaoImpl bean is created..");
	}
    
	public void addProduct(Product product) 
	{
		Session session= sessionfactory.getCurrentSession();
	       session.save(product);
		
	}


	public List<Product> getAllProducts() 
	{
		Session session=sessionfactory.getCurrentSession();
		Query query=session.createQuery("from Product");
		List<Product> products =query.list();
		return products;
	
	}

	public Product getProduct(int productId)
	{
			Session session=sessionfactory.getCurrentSession();
			Product product=(Product)session.get(Product.class, productId);
			return product;
	}

	public void updateProduct(Product product) 
	{
		Session session=sessionfactory.getCurrentSession();
		session.update(product);
		
	}

	public void deleteProduct(int productId)
	{
		Session session=sessionfactory.getCurrentSession();
		Product product =new Product();
		product.setProductId(productId);
		session.delete(product);
		
		
	}
	
	public void saveOrUpdate(Product product)
	{
		Session session = sessionfactory.getCurrentSession();
		session.saveOrUpdate(product);
	}
	
	public List<Product> getCategoryProducts(String category)
	{
		Session session = sessionfactory.getCurrentSession();
		String s1="'"+category+"'";
		Query query = session.createQuery("from Product where category.categoryName="+s1);
		List<Product> products = query.list();
		return products;
	}
	
	public List<Category> getAllCategories()
	{
		Session session=sessionfactory.getCurrentSession();
		Query query= session.createQuery("from Category");
		List<Category> categories = query.list();
		return categories;
	}
	
	public void addCategory(Category category)
	{
		Session session = sessionfactory.getCurrentSession();
		session.save(category);
	}

	
}
