package com.devs.payroll;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import com.gdeguzman.springboot.entity.Basket;
import com.gdeguzman.springboot.entity.BasketItem;
import com.gdeguzman.springboot.entity.Customer;
import com.gdeguzman.springboot.entity.Product;
import com.gdeguzman.springboot.repository.CustomerRepository;
import com.gdeguzman.springboot.repository.ProductRepository;
import com.gdeguzman.springboot.service.CustomerService;
import com.gdeguzman.springboot.service.ProductService;

@SpringBootTest
@SpringBootApplication
class PayrollApplicationTests {

	@Mock
	private ProductRepository prodRepo;

	@Mock
	private CustomerRepository custRepo;

	@InjectMocks
	private ProductService prodService = new ProductService();

	@InjectMocks
	private CustomerService custService = new CustomerService();

	@Test
	public void getProductListTest() {
		List<Product> prodList = new ArrayList<Product>();
		prodList.add(new Product(1, "Palmolive", "Hair Shampoo", 25.00));
		prodList.add(new Product(2, "Safeguard", "Body Soap", 45.00));
		prodList.add(new Product(3, "Colgate", "Toothpaste", 100.00));
		prodList.add(new Product(4, "Gardenia Bread", "Tasty Bread", 65.00));
		prodList.add(new Product(5, "Greencross", "Alcohol", 50.00));

		when(prodService.findAll()).thenReturn(prodList);

		assertNotNull(prodService.findAll());
		assertEquals(5, prodRepo.findAll().size());
	}

	@Test
	public void AddCustomer() {
		Customer cust = new Customer("gerald", "de guzman");
		when(custRepo.save(cust)).thenReturn(cust);

		assertEquals("gerald de guzman", String.format("%s %s", cust.getFirstname(), cust.getLastname()));

	}
	
	@Test
	public void AddBasket() {
		Customer cust = new Customer("gerald", "de guzman");
		List<Basket>baskets = new ArrayList<Basket>();
		baskets.add(new Basket(1, "basket1", cust, null));
		baskets.add(new Basket(2, "basket2", cust, null));
		cust.setBaskets(baskets);
		
		when(custRepo.save(cust)).thenReturn(cust);
		
		assertEquals("gerald de guzman", String.format("%s %s", cust.getFirstname(), cust.getLastname()));
		assertEquals(2, cust.getBaskets().size());
		
	}
	
	@Test
	public void AddBasketItem() {
		Customer cust = new Customer("gerald", "de guzman");
		
		List<Basket>baskets = new ArrayList<Basket>();
		Basket basket1 = new Basket();
		basket1.setBasketname("basket1");
		basket1.setCustomer(cust);
		
		List<BasketItem>basketItems = new ArrayList<BasketItem>();
		BasketItem item = new BasketItem(1, basket1, 1, "Palmolive", 4, 25.00);
		basketItems.add(item);
		item = new BasketItem(1, basket1, 1, "Safeguard", 10, 45.00);
		basketItems.add(item);
		
		basket1.setBasketitems(basketItems);
		baskets.add(basket1);
		cust.setBaskets(baskets);
		
		when(custRepo.save(cust)).thenReturn(cust);
		
		assertEquals("gerald de guzman", String.format("%s %s", cust.getFirstname(), cust.getLastname()));
		assertEquals(1, cust.getBaskets().size());
		assertEquals(2, cust.getBaskets().get(0).getBasketitems().size());
	}

}
