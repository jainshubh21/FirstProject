package com.shopping.frontend.controllers;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shopping.backend.dao.CartItemDAO;
import com.shopping.backend.dao.ProductDAO;
import com.shopping.backend.model.CartItem;
import com.shopping.backend.model.Customer;
import com.shopping.backend.model.CustomerOrder;
import com.shopping.backend.model.Product;
import com.shopping.backend.model.ShippingAddress;
import com.shopping.backend.model.User;



@Controller
public class CartItemController {
	@Autowired
	private CartItemDAO cartItemDao;
	@Autowired
	private ProductDAO productDao;
	public CartItemController() {
		System.out.println("CartItemController bean is Created");
	}
	@RequestMapping(value="/cart/addtocart/{productId}")
	public String addToCart(@RequestParam int requestedQuantity, @PathVariable int productId, @AuthenticationPrincipal Principal principal) {
		String email="";
		if(principal!=null)
	       email = principal.getName();
		Product product = productDao.getProduct(productId);
		email = principal.getName();
		User user = cartItemDao.getUser(email);
		
		CartItem cartItem =new CartItem();
		cartItem.setUser(user);
		cartItem.setProduct(product);
		cartItem.setQuantity(requestedQuantity);
		cartItem.setTotalPrice(requestedQuantity*product.getPrice());
		cartItemDao.addToCart(cartItem);
		return "redirect:/cart/getcartitems";
	}
	
	@RequestMapping(value="/cart/updatecartitem")
	public String updateCartItem(@RequestParam int cartItemId,@RequestParam int requestedQuantity){
		cartItemDao.updateCartItem(cartItemId, requestedQuantity);
		return "redirect:/cart/getcartitems";
	}
	@RequestMapping(value="/cart/removecartitem/{cartItemId}")
	public String removeCartItem(@PathVariable int cartItemId) {
		cartItemDao.removeCartItem(cartItemId);
		return "redirect:/cart/getcartitems";
	}
	@RequestMapping(value="/cart/clearcart")
	public String clearCart(@AuthenticationPrincipal Principal principal) {
		
		List<CartItem> cartItems = cartItemDao.getCartItems(principal.getName());
		for (CartItem cartItem:cartItems)
		{
			cartItemDao.removeCartItem(cartItem.getCartitemId());
		}
		return "redirect:/cart/getcartitems";
		
	}
	@RequestMapping(value="/cart/getshippingaddressform")
	public String getShippingAddressForm(@AuthenticationPrincipal Principal principal,Model model){
	    String email=principal.getName();
	    //GET SHIPPING ADDRESS
	    User user=cartItemDao.getUser(email);
	    Customer customer=user.getCustomer();
	    ShippingAddress shippingAddress=customer.getShippingAddress();
	    model.addAttribute("shippingaddress",shippingAddress);
		return "shippingaddressform";
	}
	@RequestMapping(value="/cart/createorder")
	public String createCustomerOrder(@ModelAttribute ShippingAddress shippingAddress,
			Model model,
			@AuthenticationPrincipal Principal principal,HttpSession session){
		//set updated shippingaddress in customer object 
		
		String email=principal.getName();
		User user=cartItemDao.getUser(email);
		Customer customer=user.getCustomer();
		customer.setShippingAddress(shippingAddress);
		
		user.setCustomer(customer);
		customer.setUser(user);
		
		CustomerOrder customerOrder=new CustomerOrder();
		customerOrder.setPurchaseDate(new Date());
		customerOrder.setUser(user);
		
		List<CartItem> cartItems=cartItemDao.getCartItems(email);
		double grandTotal=0.0;
		for(CartItem cartItem:cartItems){
			grandTotal=cartItem.getTotalPrice()+grandTotal;
		}
		customerOrder.setGrandTotal(grandTotal);
		
		return "orderdetails";
	}
	

}
