package com.gdeguzman.springboot.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "customer")
@Getter @Setter @NoArgsConstructor
public class Customer{
	
	
	public Customer(String firstname, String lastname) {		
		this.firstname = firstname;
		this.lastname = lastname;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerid;
	
	@Column(name = "firstname")
	private String firstname;
		
	@Column(name = "lastname")
	private String lastname;
		
	@JsonProperty(value = "baskets")
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "customer")
	private List<Basket>baskets;
	
}
