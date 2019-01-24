package com.shopping.backend.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.backend.model.CartItem;
import com.shopping.backend.model.CustomerOrder;
import com.shopping.backend.model.User;

@Repository
@Transactional
public class CartItemDAOImpl 
{
	@Autowired
	private SessionFactory sessionfactory;
	
	public void addToCart(CartItem cartItem)
	{
		Session session = sessionfactory.getCurrentSession();
		session.save(cartItem);
		
	}
	public User getUser(String email)
	{
		Session session = sessionfactory.getCurrentSession();
		User user = (User)session.get(User.class, email);
		return user;
	}
	
	@SuppressWarnings("deprecation")
	public List<CartItem> getCartItems(String email)
	{
		Session session = sessionfactory.getCurrentSession();
		Query query=session.createQuery("from CartItem where user.email=:email ");
		query.setString("email", email);
		List<CartItem> cartItems=query.list();
		return cartItems;
	}
	public void removeCartItem(int cartItemId)
	{
		Session session = sessionfactory.getCurrentSession();
		CartItem cartItem = (CartItem)session.get(CartItem.class, cartItemId);
		session.delete(cartItem);
	}
	public void updateCartItem(int cartItemId,int requestedQuantity)
	{
		Session session = sessionfactory.getCurrentSession();
		CartItem cartItem = (CartItem)session.get(CartItem.class, cartItemId);
		cartItem.setQuantity(requestedQuantity);
		cartItem.setTotalPrice(requestedQuantity*cartItem.getProduct().getPrice());
		session.update(cartItem);
	}

	public CustomerOrder createCustomerOrder(CustomerOrder customerOrder)
	{
		Session session =sessionfactory.getCurrentSession();
		session.save(customerOrder);
		return customerOrder;
	}
}
