package com.shopping.backend.backend;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.shopping.backend.config.HibernateConfiguration;
import com.shopping.backend.dao.ProductDAO;
import com.shopping.backend.daoimpl.ProductDAOImpl;

public class App 
{

	 public static void main( String[] args )
	    {
    System.out.println( "Hello World!" );      
    ApplicationContext context=
    	      new AnnotationConfigApplicationContext(HibernateConfiguration.class,ProductDAOImpl.class);
    	      ProductDAO productDao=(ProductDAO)context.getBean("productDaoImpl");
    	    
	    }
}
