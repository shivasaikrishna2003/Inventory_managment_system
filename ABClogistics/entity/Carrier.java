package com.alpha.ABClogistics.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Carrier {
    @Id
	private int id;
	private String name;
	private String mail;
	private int contact;
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
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getContact() {
		return contact;
	}
	public void setContact(int contact) {
		this.contact = contact;
	}
	public Carrier(int id, String name, String mail, int contact) {
		super();
		this.id = id;
		this.name = name;
		this.mail = mail;
		this.contact = contact;
	}
	public Carrier() {
		// TODO Auto-generated constructor stub
	}
	
}
