package com.gdeguzman.springboot.entity;

import javax.persistence.*;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "basketitem")
@AllArgsConstructor @Getter @Setter @NoArgsConstructor
public class BasketItem{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int itemid;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "basket_basketid")
	private Basket basket;
	
	@Column(name = "productid")
	private int productid;
	
	@Column(name = "productname")
	private String productname;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "itemprice")
	private double itemprice;
	
}
