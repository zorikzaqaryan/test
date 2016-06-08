package com.awl.training.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.awl.training.beans.Basket;
import com.awl.training.beans.OrderItem;
import com.awl.training.beans.Product;
import com.awl.training.services.BasketService;

/**
 * The Class BasketServiceImpl is the default implementation of BasketService.
 */
@Service
public class BasketServiceImpl implements BasketService {

	private Map<String, Basket> baskets = new HashMap<String, Basket>();

	@Override
	public Basket getBasket(String sessionid) {

		// Check if the basket already exist
		if (!baskets.containsKey(sessionid)) {
			// The basket does not exist : Create it
			baskets.put(sessionid, new Basket(sessionid));
		}

		// Return basket
		return baskets.get(sessionid);
	}

	@Override
	public String addItemToBasket(String sessionId, Product product, int quantity) throws IllegalArgumentException {

		if (product == null) {
			throw new IllegalArgumentException("The product is null");
		}

		if (quantity < 1) {
			throw new IllegalArgumentException("The quantity is wrong");
		}

		// Retrieve Basket
		Basket basket = getBasket(sessionId);

		// Create Item
		OrderItem item = new OrderItem(quantity, product);

		// Add item to the basket
		basket.getItems().add(item);

		return "The Item  [" + product.getId() + "] has been successfully added in basket [" + sessionId + "]";
	}
}
