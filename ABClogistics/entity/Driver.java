package com.alpha.ABClogistics.entity;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Driver {
    @Id
	private int id;
    private String name;
    private double contact;
    @Autowired
    @OneToOne
    private Truck truck;
    @Autowired
    @OneToOne
    private Carrier carrier;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String string) {
		this.name = string;
	}
	public double getContact() {
		return contact;
	}
	public void setContact(double contact) {
		this.contact = contact;
	}
	public Truck getTruck() {
		return truck;
	}
	public void setTruck(Truck truck) {
		this.truck = truck;
	}
	public Carrier getCarrier() {
		return carrier;
	}
	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}
	public Driver(int id, String name, double contact, Truck truck, Carrier carrier) {
		super();
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.truck = truck;
		this.carrier = carrier;
	}
	public Driver() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
    
}
