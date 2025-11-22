package com.alpha.ABClogistics.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alpha.ABClogistics.DTO.ResponseStructure;
import com.alpha.ABClogistics.entity.Order;

   @RestControllerAdvice
   public class GlobalExceptionHandler {

	   ResponseStructure<String> rs=new ResponseStructure<String>();
	   
	   @ExceptionHandler(AddressAlreadyPresentException.class)
	   public ResponseEntity<ResponseStructure<String>> AddressAlreadyPresentException(AddressAlreadyPresentException ex) {
		rs.setData("Address Id Already Present in DB");
		rs.setMessage("Address with this id is present");
		rs.setStatuscode(HttpStatus.ALREADY_REPORTED.value());
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.ALREADY_REPORTED);
	}
	   @ExceptionHandler(AddressIdNotPresentException.class)
	   public ResponseEntity<ResponseStructure<String>> AddressIdNotPresentException(AddressIdNotPresentException ex){
		   rs.setData("Address Id Not Present in DB");
			rs.setMessage("Address with this id is not present");
			rs.setStatuscode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_FOUND);
	   }
	   @ExceptionHandler(CarrierAlreadyPresentException.class)
	   public ResponseEntity<ResponseStructure<String>> CarrierAlreadyPresentException(CarrierAlreadyPresentException ex){
		   rs.setData("Carrier Id Already Present in DB");
			rs.setMessage("Carrier with this id is Already present");
			rs.setStatuscode(HttpStatus.ALREADY_REPORTED.value());
			return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.ALREADY_REPORTED);
	   }
	   
	   @ExceptionHandler(CarrierNotPresentException.class)
	   public ResponseEntity<ResponseStructure<String>> CarrierNotPresentException(CarrierNotPresentException ex){
		   rs.setData("Carrier Id Not Present in DB");
			rs.setMessage("Carrier with this id is not present");
			rs.setStatuscode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_FOUND);
	   }
	   
	   @ExceptionHandler(DriverAlreadyPresentException.class)
	   public ResponseEntity<ResponseStructure<String>> DriverAlreadyPresentException(DriverAlreadyPresentException ex){
		   rs.setData("Driver Id Already Present in DB");
			rs.setMessage("Driver with this id is Already present");
			rs.setStatuscode(HttpStatus.ALREADY_REPORTED.value());
			return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.ALREADY_REPORTED);
	   }
	   
	   @ExceptionHandler(DriverNotPresentException.class)
	   public ResponseEntity<ResponseStructure<String>> DriverNotPresentException(DriverNotPresentException ex){
		   rs.setData("Driver Id Not Present in DB");
			rs.setMessage("Driver with this id is not present");
			rs.setStatuscode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_FOUND);
	   }
	   
	   @ExceptionHandler(LoadingAddressIdNotFoundException.class)
	   public ResponseEntity<ResponseStructure<String>> LoadingAddressIdNotFoundException(LoadingAddressIdNotFoundException ex){
		   rs.setData("Loading Address Id Not Present in DB");
			rs.setMessage("Loading Address with this id is not present");
			rs.setStatuscode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_FOUND);
	   }
	   
	   @ExceptionHandler(OrderNotPresentException.class)
	   public ResponseEntity<ResponseStructure<String>> OrderNotPresentException(OrderNotPresentException ex){
		   rs.setData("Order Id Not Present in DB");
			rs.setMessage("Order with this id is not present");
			rs.setStatuscode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_FOUND);
	   }
	   
	   @ExceptionHandler(TruckAlreadyPresentException.class)
	   public ResponseEntity<ResponseStructure<String>> TruckAlreadyPresentException(TruckAlreadyPresentException ex){
		   rs.setData("Truck Id Already Present in DB");
			rs.setMessage("Truck with this id is Already present");
			rs.setStatuscode(HttpStatus.ALREADY_REPORTED.value());
			return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.ALREADY_REPORTED);
	   }
	   
	   @ExceptionHandler(TruckNotPresentException.class)
	   public ResponseEntity<ResponseStructure<String>> TruckNotPresentException(TruckNotPresentException ex){
		   rs.setData("Truck Id Not Present in DB");
			rs.setMessage("Truck with this id is not present");
			rs.setStatuscode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_FOUND);
	   }
	   
	   @ExceptionHandler(UnloadingAddressIdNotFoundException.class)
	   public ResponseEntity<ResponseStructure<String>> UnloadingAddressIdNotFoundException(UnloadingAddressIdNotFoundException ex){
		   rs.setData("Unloading Address Id Not Present in DB");
			rs.setMessage("Unloading Address with this id is not present");
			rs.setStatuscode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_FOUND);
	   }
	   
	   @ExceptionHandler(OrderNotGetPlacedException.class)
	   public ResponseEntity<ResponseStructure<String>> OrderNotGetPlacedException(){
		   rs.setData("Order is not get placed at");
			rs.setMessage("Order is not get placed so you can't assing carrier");
			rs.setStatuscode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_FOUND);
	   }
	   
	   @ExceptionHandler(OrderUpdateFailedException.class)
	   public ResponseEntity<ResponseStructure<String>> OrderUpdateFailedException(){
		   rs.setData("Order Update failed");
		   rs.setMessage("Order Updated cannot be done because order is cancelled");
		   rs.setStatuscode(HttpStatus.BAD_REQUEST.value());
		   
		   return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.BAD_REQUEST);
	   }
	   
	   @ExceptionHandler(CarrierNotAssignedException.class)
	   public ResponseEntity<ResponseStructure<String>> CarrierNotAssignedException(){
		   
		   rs.setData("Carrier not assinged ");
		   rs.setMessage("Carrier not asssinged to order");
		   rs.setStatuscode(HttpStatus.NOT_FOUND.value());
		   
		   return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_FOUND);
		   
	   }
  }
