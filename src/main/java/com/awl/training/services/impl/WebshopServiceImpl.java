package com.awl.training.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awl.training.beans.Basket;
import com.awl.training.beans.Product;
import com.awl.training.services.BasketService;
import com.awl.training.services.ProductService;
import com.awl.training.services.WebShopService;

/**
 * The Class WebshopServiceImpl is the default implemenatation of WebShopService.
 */
@Service
public class WebshopServiceImpl implements WebShopService {

	@Autowired
	private ProductService productService;

	@Autowired
	private BasketService basketService;

	@Override
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	@Override
	public Product getProduct(long productId) {
		return productService.getProduct(productId);
	}

	@Override
	public boolean isProductAvailable(long productId, int quantity) {
		if (productId < 0 || quantity < 0) {
			return false;
		}
		return productService.isProductAvailable(productId, quantity);
	}

	@Override
	public Basket getBasket(String sessionId) {
		return basketService.getBasket(sessionId);
	}

	@Override
	public String addItemToBasket(String sessionId, long productId, int quantity) throws IllegalArgumentException {

		Product product = productService.getProduct(productId);
		if (product == null) {
			throw new IllegalArgumentException("The product [" + productId + "] does not exist");
		}

		if (!productService.isProductAvailable(productId, quantity)) {
			throw new IllegalArgumentException("The product [" + productId + "] is not available");
		}

		return basketService.addItemToBasket(sessionId, product, quantity);
	}

}
