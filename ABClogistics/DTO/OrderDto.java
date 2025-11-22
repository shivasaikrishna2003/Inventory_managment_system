package com.alpha.ABClogistics.DTO;

import org.hibernate.validator.constraints.UniqueElements;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class OrderDto {
    
	
	private int id;
	
	
	private String orderdate;
	
	private int cargoId;

	private String cargoName;
	private int cargoWeight;
	private int cargoCount;
	private String cargoDescription;
	
	private int loadingId;
	
	private int unloadingId;
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
	public int getCargoId() {
		return cargoId;
	}
	public void setCargoId(int cargoId) {
		this.cargoId = cargoId;
	}
	
	public String getCargoDescription() {
		return cargoDescription;
	}
	public void setCargoDescription(String cargoDescription) {
		this.cargoDescription = cargoDescription;
	}
	public String getCargoName() {
		return cargoName;
	}
	public void setCargoName(String cargoName) {
		this.cargoName = cargoName;
	}
	public int getCargoWeight() {
		return cargoWeight;
	}
	public void setCargoWeight(int cargoWeight) {
		this.cargoWeight = cargoWeight;
	}
	public int getCargoCount() {
		return cargoCount;
	}
	public void setCargoCount(int cargoCount) {
		this.cargoCount = cargoCount;
	}
	public int getLoadingId() {
		return loadingId;
	}
	public void setLoadingId(int loadingId) {
		this.loadingId = loadingId;
	}
	public int getUnloadingId() {
		return unloadingId;
	}
	public void setUnloadingId(int unloadingId) {
		this.unloadingId = unloadingId;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public OrderDto(int id, String orderdate, int cargoId, String cargoName, int cargoWeight, int cargoCount,
			String cargoDescription, int loadingId, int unloadingId, String email) {
		super();
		this.id = id;
		this.orderdate = orderdate;
		this.cargoId = cargoId;
		this.cargoName = cargoName;
		this.cargoWeight = cargoWeight;
		this.cargoCount = cargoCount;
		this.cargoDescription = cargoDescription;
		this.loadingId = loadingId;
		this.unloadingId = unloadingId;
		this.email = email;
	}
	public OrderDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
