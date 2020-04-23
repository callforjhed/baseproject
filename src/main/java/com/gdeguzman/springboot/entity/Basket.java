package com.gdeguzman.springboot.entity;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "basket")
@AllArgsConstructor @Getter @Setter @NoArgsConstructor
public class Basket{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int basketid;
	
	@Column(name = "basketname")
	private String basketname;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_customerid")
	private Customer customer;
	
	@JsonProperty(value = "items")
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "basket",orphanRemoval = true)
	private List<BasketItem> basketitems;  
	
}
