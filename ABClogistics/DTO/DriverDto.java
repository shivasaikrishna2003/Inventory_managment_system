package com.alpha.ABClogistics.DTO;

public class DriverDto {
	
	private int id;
	private String name;
	private double contact;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getContact() {
		return contact;
	}
	public void setContact(double contact) {
		this.contact = contact;
	}
	public DriverDto(int id, String name, double contact) {
		super();
		this.id = id;
		this.name = name;
		this.contact = contact;
	}
	public DriverDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
