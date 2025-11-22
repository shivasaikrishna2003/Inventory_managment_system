package com.alpha.ABClogistics.entity;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="\"order\"")
public class Order {
    @Id
	private int id;
    private String orderdate;
    private String status = "placed";
    private int cost;
    @Autowired
    @OneToOne(cascade = CascadeType.ALL)
    private Cargo cargo;
    @Autowired
    @OneToOne(cascade = CascadeType.ALL)
    private Loading loading;
    @Autowired
    @OneToOne(cascade = CascadeType.ALL)
    private Unloading unloading;
    
    @Autowired
    @OneToOne
    Carrier carrier;
    
    private String email;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public Cargo getCargo() {
		return cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
	
	public Loading getLoading() {
		return loading;
	}
	public void setLoading(Loading loading) {
		this.loading = loading;
	}
	
	public Unloading getUnloading() {
		return unloading;
	}
	public void setUnloading(Unloading unloading) {
		this.unloading = unloading;
	}
	
	public Carrier getCarrier() {
		return carrier;
	}
	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Order(int id, String orderdate, String status, int cost, Cargo cargo, Loading loading, Unloading unloading,
			Carrier carrier, String email) {
		super();
		this.id = id;
		this.orderdate = orderdate;
		this.status = status;
		this.cost = cost;
		this.cargo = cargo;
		this.loading = loading;
		this.unloading = unloading;
		this.carrier = carrier;
		this.email = email;
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	
    
	
}
