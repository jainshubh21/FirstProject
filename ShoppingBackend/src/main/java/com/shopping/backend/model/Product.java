package com.shopping.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class Product
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int productId;
	@Column(name="pname")
	@NotEmpty(message="Product Name Cannot be empty")
	private String productName;
	@Min(value=1,message="Price cannot be less than 1. ")
	private double price;
	@Min(value=0,message="Quantity cannot be less than 0. ")
	private int quantity;
	@NotEmpty(message="Desription Needed")
	private String description;
	@ManyToOne
	@JoinColumn(name="cid")
	private Category category;
	@Transient //image will not get persisted in the database
	private  MultipartFile  image;
	
	public int getProductId() 
	{
		return productId;
	}
	public void setProductId(int productId) 
	{
		this.productId = productId;
	}
	public String getProductName() 
	{
		return productName;
	}
	public void setProductName(String productName) 
	{
		this.productName = productName;
	}
	public double getPrice() 
	{
		return price;
	}
	public void setPrice(double price) 
	{
		this.price = price;
	}
	public int getQuantity() 
	{
		return quantity;
	}
	public void setQuantity(int quantity) 
	{
		this.quantity = quantity;
	}
	public String getDescription() 
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public Category getCategory() 
	{
		return category;
	}
	public void setCategory(Category category) 
	{
		this.category = category;
	}
    
	public MultipartFile getImage()
	{
		return image;
	}
	public void setImage(MultipartFile image)
	{
		this.image = image;
	}
}
