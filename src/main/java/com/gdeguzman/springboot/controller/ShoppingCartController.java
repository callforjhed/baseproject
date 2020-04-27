package com.gdeguzman.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gdeguzman.springboot.entity.Basket;
import com.gdeguzman.springboot.entity.BasketItem;
import com.gdeguzman.springboot.entity.Customer;
import com.gdeguzman.springboot.service.BasketItemService;
import com.gdeguzman.springboot.service.BasketService;
import com.gdeguzman.springboot.service.CustomerService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cust")
public class ShoppingCartController {

	@Autowired
	CustomerService custService;

	@Autowired
	BasketService basketService;

	@Autowired
	BasketItemService itemService;

	
	@GetMapping("/hello")
	public ResponseEntity<String>greet()
	{
		return new ResponseEntity("Hello",HttpStatus.OK);
	}
	
	@RequestMapping("/{id}")
	public Customer getCutomerByid(@PathVariable("id") int id) {
		return custService.findById(id);
	}

	@RequestMapping(value = "/{custid}/addbasket", method = RequestMethod.POST)
	public ResponseEntity<String> createBasket(@PathVariable("custid") int custid, @RequestBody Basket basket) {

		Basket _basket = basketService.findByBasketname(basket.getBasketname());
		if (_basket != null) {
			return new ResponseEntity<String>("Basket name already exists.", HttpStatus.OK);
		} else {
			Customer cust = custService.findById(custid);
			basket.setCustomer(cust);
			cust.getBaskets().add(basket);

			try {
				custService.save(cust);
				return new ResponseEntity<String>("Basket created.", HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<String>("Basket not created.", HttpStatus.OK);
			}

		}

	}

	@RequestMapping(value = "/{custid}/{basketid}/additem", method = RequestMethod.POST)
	public ResponseEntity<String> addItem(@PathVariable("custid") int custid, @PathVariable("basketid") int basketid,
			@RequestBody BasketItem item) {

		boolean hasItem = false;
		Customer cust = custService.findById(custid);
		Basket basket = basketService.findById(basketid);
		List<BasketItem> items = basket.getBasketitems();

		for (BasketItem i : items) {
			if (i.getProductid() == item.getProductid()) {
				i.setQuantity(i.getQuantity() + 1);				
				hasItem = true;
				break;
			}
		}

		//if item not found then add new
		if(!hasItem) {
			item.setBasket(basket);
			items.add(item);
		}
		
		try {
			custService.save(cust);
			return new ResponseEntity<String>("Basket item added.", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Basket item not added.", HttpStatus.OK);
		}

	}
	
	@RequestMapping(value = "/{custid}/{basketid}/removeitem", method = RequestMethod.POST)
	public ResponseEntity<String> removeItem(@PathVariable("custid") int custid, @PathVariable("basketid") int basketid,
			@RequestBody BasketItem item) {
		
		Customer cust = custService.findById(custid);
		Basket basket = basketService.findById(basketid);
		List<BasketItem> items = basket.getBasketitems();

		for (BasketItem i : items) {
			if (i.getProductid() == item.getProductid()) {
				items.remove(i);
				break;
			}
		}		
		
		try {
			basket.setBasketitems(items);			
			custService.save(cust);
			return new ResponseEntity<String>("Basket item removed.", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Basket item not removed.", HttpStatus.OK);
		}

	}	

}
