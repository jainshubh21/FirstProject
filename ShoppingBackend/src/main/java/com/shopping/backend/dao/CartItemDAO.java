package com.shopping.backend.dao;

import java.util.List;

import com.shopping.backend.model.CartItem;
import com.shopping.backend.model.CustomerOrder;
import com.shopping.backend.model.User;

public interface CartItemDAO 
{
	void addToCart(CartItem cartItem);
	User getUser(String email);
	List<CartItem> getCartItems(String email);
	void removeCartItem(int cartItemId);
	void updateCartItem(int cartItemId,int requestedQuantity);
	CustomerOrder createCustomerOrder(CustomerOrder customerorder);

}
