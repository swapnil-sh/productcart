package com.order.model;

import java.util.Date;
import java.util.List;

public class OrderResponse {

	private int id;
	private long totalAmount;
	private int userId;
	private Date date;
	private List<String> productsPurchased;
	
	public OrderResponse(int id, long totalAmount, int userId, Date date, List<String> productsPurchased) {
		super();
		this.id = id;
		this.totalAmount = totalAmount;
		this.userId = userId;
		this.date = date;
		this.productsPurchased = productsPurchased;
	}
	public OrderResponse() {
		
	}
	
	public List<String> getProductsPurchased() {
		return productsPurchased;
	}
	public void setProductsPurchased(List<String> productsPurchased) {
		this.productsPurchased = productsPurchased;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(long totalAmount) {
		this.totalAmount = totalAmount;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
