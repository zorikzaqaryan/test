package com.awl.training.controllers;

import com.awl.training.services.WebShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.awl.training.beans.Basket;

@RestController
@RequestMapping(value = "/basket")
public class BasketController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private WebShopService webshopService;

	@RequestMapping(value = "/{sessionid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Basket getBasket(@PathVariable("sessionid") String sessionid) {
		return webshopService.getBasket(sessionid);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> addItemToBasket(@RequestParam(name = "sessionId") String sessionId, @RequestParam(name = "productId") long productId, @RequestParam(name = "quantity") int quantity) {

		try {
			String result = webshopService.addItemToBasket(sessionId, productId, quantity);
			return ResponseEntity.ok().body(result);
		} catch (IllegalArgumentException e) {
			logger.error("Error during add product [" + productId + "] in basket [" + sessionId + "] with the quantity [" + quantity + "]", e);
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}

	}
}
