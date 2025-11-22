package com.alpha.ABClogistics.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.ABClogistics.DTO.ResponseStructure;
import com.alpha.ABClogistics.DTO.TruckDto;
import com.alpha.ABClogistics.entity.Truck;
import com.alpha.ABClogistics.exception.TruckAlreadyPresentException;
import com.alpha.ABClogistics.exception.TruckNotPresentException;
import com.alpha.ABClogistics.repository.TruckRepository;

@Service
public class TruckService {
    
	@Autowired
	TruckRepository truckRepository;
	
	public ResponseEntity<ResponseStructure<Truck>> savetruck(TruckDto truckDto){
		
		Optional<Truck> truckoptional=truckRepository.findById(truckDto.getId());
		
		if(truckoptional.isPresent()) {
			throw new TruckAlreadyPresentException();
		}
		Truck truck=new Truck();
		truck.setId(truckDto.getId());
		truck.setName(truckDto.getName());
		truck.setNumber(truckDto.getNumber());
		truck.setCapacity(truckDto.getCapacity());
		truck.setStatus(truckDto.getStatus());
		truckRepository.save(truck);
		
		
		ResponseStructure<Truck> rs=new ResponseStructure<Truck>();
		rs.setMessage("Truck Saved Successfully");
		rs.setData(truck);
		rs.setStatuscode(HttpStatus.CREATED.value());
		
		return new ResponseEntity<ResponseStructure<Truck>>(rs,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Truck>> findTruckById(int id){
		
		Optional<Truck> truck=truckRepository.findById(id);
		if(!truck.isPresent()) {
			throw new TruckNotPresentException();
		}
		
		ResponseStructure<Truck> rs=new ResponseStructure<Truck>();
		rs.setData(truck.get());
		rs.setMessage("Truck fetched succesfully");
		rs.setStatuscode(HttpStatus.FOUND.value());
		return new ResponseEntity<ResponseStructure<Truck>>(rs,HttpStatus.FOUND);
	}
	public ResponseEntity<ResponseStructure<Truck>> deleteTruckById(int id){
		Optional<Truck> truck=truckRepository.findById(id);
		if(!truck.isPresent()) {
			throw new TruckNotPresentException();
		}
		
		truckRepository.delete(truck.get());
		ResponseStructure<Truck> rs=new ResponseStructure<Truck>();
		rs.setData(truck.get());
		rs.setMessage("Truck deleted succesfully");
		rs.setStatuscode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<Truck>>(rs,HttpStatus.ACCEPTED);
		
	}
	
}
