package com.shopping.backend.dao;

import com.shopping.backend.model.Customer;

public interface CustomerDAO 
{
	void registerCustomer(Customer customer);
	boolean isEmailUnique(String email);	

}
