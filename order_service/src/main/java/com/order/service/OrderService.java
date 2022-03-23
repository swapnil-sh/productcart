package com.order.service;

import org.springframework.stereotype.Service;

import com.order.model.OrderResponse;;

public interface OrderService {
	
	OrderResponse checkout(int userId);

}
