package com.cart.model;

public class AddItemRequest {

	private int productId;
	private int quantity;
	private int userId;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	public AddItemRequest(int productId, int quantity, int userId) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.userId = userId;
	}
	public AddItemRequest()
	{
		
	}
}
