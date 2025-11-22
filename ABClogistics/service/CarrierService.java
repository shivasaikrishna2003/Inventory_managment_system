package com.alpha.ABClogistics.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.ABClogistics.DTO.ResponseStructure;
import com.alpha.ABClogistics.entity.Carrier;
import com.alpha.ABClogistics.exception.CarrierAlreadyPresentException;
import com.alpha.ABClogistics.exception.CarrierNotPresentException;
import com.alpha.ABClogistics.repository.CarrierRepository;

@Service
public class CarrierService {
     
	@Autowired
	CarrierRepository carrierRepository;
	
	public ResponseEntity<ResponseStructure<Carrier>> saveCarrier(Carrier carrier){
	Optional<Carrier> carrieroptional=carrierRepository.findById(carrier.getId());
	if(carrieroptional.isPresent()) {
		throw new CarrierAlreadyPresentException();
	 }
	Carrier savecarrier=carrierRepository.save(carrier);
	ResponseStructure<Carrier> rs=new ResponseStructure<Carrier>();
	rs.setData(savecarrier);
	rs.setMessage("carrier saved successfully");
	rs.setStatuscode(HttpStatus.CREATED.value());
	 return new ResponseEntity<ResponseStructure<Carrier>>(rs,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Carrier>> findCarrierById(int id){
	  Optional<Carrier>	carrier=carrierRepository.findById(id);
	  
	  if(!carrier.isPresent()) {
		  throw new CarrierNotPresentException();
	  }
	  
	  ResponseStructure<Carrier> rs=new ResponseStructure<Carrier>();
	  rs.setData(carrier.get());
	  rs.setMessage("Carrier fetched succesfully");
	  rs.setStatuscode(HttpStatus.FOUND.value());
	  
	  return new ResponseEntity<ResponseStructure<Carrier>>(rs,HttpStatus.FOUND);
	}
	  public ResponseEntity<ResponseStructure<Carrier>> deleteCarrierById(int id){
		  Optional<Carrier>	carrier=carrierRepository.findById(id);
		  
		  if(!carrier.isPresent()) {
			  throw new CarrierNotPresentException();
		  }
		  
		  carrierRepository.delete(carrier.get());
		  ResponseStructure<Carrier> rs=new ResponseStructure<Carrier>();
		  rs.setData(carrier.get());
		  rs.setMessage("Carrier deleted succesfully");
		  rs.setStatuscode(HttpStatus.ACCEPTED.value());
		  return new ResponseEntity<ResponseStructure<Carrier>>(rs,HttpStatus.ACCEPTED);
	  }
	
}
