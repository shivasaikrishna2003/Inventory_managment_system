package com.alpha.ABClogistics.service;

import java.security.interfaces.RSAKey;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.ABClogistics.DTO.ResponseStructure;
import com.alpha.ABClogistics.entity.Address;
import com.alpha.ABClogistics.exception.AddressAlreadyPresentException;
import com.alpha.ABClogistics.exception.AddressIdNotPresentException;
import com.alpha.ABClogistics.repository.AddressRepository;

@Service
public class AddressService {
    @Autowired
	AddressRepository addressRepository;
    
  public ResponseEntity<ResponseStructure<Address>> saveAddress(Address address){
	  Optional<Address> addOptional=addressRepository.findById(address.getId());
	  
	  if(addOptional.isPresent()) {
		  throw new AddressAlreadyPresentException();
	  }
	  
	  Address saveAddress=addressRepository.save(address);
	  
	  ResponseStructure<Address> responseStructure= new ResponseStructure<Address>();
	  responseStructure.setData(saveAddress);
	  responseStructure.setMessage("Address saved");
	  responseStructure.setStatuscode(HttpStatus.CREATED.value());
	  return new ResponseEntity<ResponseStructure<Address>>(responseStructure,  HttpStatus.CREATED);
  }
  
  public ResponseEntity<ResponseStructure<Address>> findAddressById(int id){
	  
	  Optional<Address> addOptional=addressRepository.findById(id);
	  if(!addOptional.isPresent()) {
		  throw new AddressIdNotPresentException();
	  }
	  ResponseStructure<Address> responseStructure= new ResponseStructure<Address>();
	  responseStructure.setData(addOptional.get());
	  responseStructure.setMessage("Address found");
	  responseStructure.setStatuscode(HttpStatus.FOUND.value());
	  return new ResponseEntity<ResponseStructure<Address>>(responseStructure,  HttpStatus.FOUND);
  }
  
  public ResponseEntity<ResponseStructure<Address>> deleteAddressById(int id){
	 Optional<Address> address=addressRepository.findById(id);
	 if(!address.isPresent()) {
		 throw new AddressIdNotPresentException();
	 }
	 addressRepository.delete(address.get());
	 ResponseStructure<Address> rs= new ResponseStructure<Address>();
	 rs.setData(address.get());
	 rs.setMessage("address deleted succesfully");
	 rs.setStatuscode(HttpStatus.ACCEPTED.value());
	 return new ResponseEntity<ResponseStructure<Address>>(rs,HttpStatus.ACCEPTED);
  }
}
