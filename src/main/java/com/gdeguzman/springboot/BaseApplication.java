package com.gdeguzman.springboot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.gdeguzman.springboot.entity.Basket;
import com.gdeguzman.springboot.entity.BasketItem;
import com.gdeguzman.springboot.entity.Customer;
import com.gdeguzman.springboot.entity.Product;
import com.gdeguzman.springboot.repository.CustomerRepository;
import com.gdeguzman.springboot.repository.ProductRepository;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class BaseApplication {

	@Autowired
	CustomerRepository custRepo;
	
	@Autowired
	ProductRepository productRepo;

	public static void main(String[] args) {
		SpringApplication.run(BaseApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void PostStartupProcess() {
		
		//create productList
		productRepo.save(new Product(1,"Palmolive","Hair Shampoo",25.00));
		productRepo.save(new Product(2,"Safeguard","Body Soap",45.00));
		productRepo.save(new Product(3,"Colgate","Toothpaste",100.00));
		productRepo.save(new Product(4,"Gardenia Bread","Tasty Bread",65.00));
		productRepo.save(new Product(5,"Greencross","Alcohol",50.00));
		
		
		//create customer
		Customer cust = new Customer("Gerald","de Guzman");
		
		List<Basket>basketList = new ArrayList<Basket>();
		Basket basket = new Basket();
		basket.setBasketname("BASKET1");
		basket.setCustomer(cust);
		
		/*
		 * List<BasketItem>basketItems = new ArrayList<BasketItem>(); BasketItem item =
		 * new BasketItem(); item.setBasket(basket); item.setProductid(1);
		 * item.setProductname("Palmolive"); item.setQuantity(11);
		 * item.setItemprice(item.getQuantity() * 25.00);
		 * 
		 * basketItems.add(item); basket.setBasketitem(basketItems);
		 * basketList.add(basket);
		 * 
		 * cust.setBaskets(basketList);
		 */
		custRepo.save(cust);
		

	}

}
