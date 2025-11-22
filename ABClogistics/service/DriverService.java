package com.alpha.ABClogistics.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.ABClogistics.DTO.DriverDto;
import com.alpha.ABClogistics.DTO.ResponseStructure;
import com.alpha.ABClogistics.entity.Carrier;
import com.alpha.ABClogistics.entity.Driver;
import com.alpha.ABClogistics.entity.Truck;
import com.alpha.ABClogistics.exception.CarrierNotPresentException;
import com.alpha.ABClogistics.exception.DriverAlreadyPresentException;
import com.alpha.ABClogistics.exception.DriverNotPresentException;
import com.alpha.ABClogistics.exception.TruckNotPresentException;
import com.alpha.ABClogistics.repository.CarrierRepository;
import com.alpha.ABClogistics.repository.DriverRepository;
import com.alpha.ABClogistics.repository.TruckRepository;

@Service
public class DriverService {
    @Autowired
	DriverRepository driverRepository;
    
    @Autowired
    TruckRepository truckRepository;
    
    @Autowired
    CarrierRepository carrierRepository;
    
    ResponseStructure<Driver> rs=new ResponseStructure<Driver>();
    public ResponseEntity<ResponseStructure<Driver>> saveDriver(DriverDto driverDto){
      Optional<Driver>	driver=driverRepository.findById(driverDto.getId());
      
      if(driver.isPresent()) {
    	  throw new DriverAlreadyPresentException();
      }
      Driver driver2=new Driver();
      
      driver2.setId(driverDto.getId());
      driver2.setName(driverDto.getName());
      driver2.setContact(driverDto.getContact());
      driverRepository.save(driver2);
      rs.setData(driver2);
      rs.setMessage("Driver saved succesfuly");
      rs.setStatuscode(HttpStatus.CREATED.value());
      return new ResponseEntity<ResponseStructure<Driver>>(rs,HttpStatus.CREATED);
    }
public ResponseEntity<ResponseStructure<Driver>> findDriverById(int id){
		
		Optional<Driver> driver=driverRepository.findById(id);
		if(!driver.isPresent()) {
			throw new DriverNotPresentException();
		}
		
		ResponseStructure<Driver> rs=new ResponseStructure<Driver>();
		rs.setData(driver.get());
		rs.setMessage("Driver fetched succesfully");
		rs.setStatuscode(HttpStatus.FOUND.value());
		return new ResponseEntity<ResponseStructure<Driver>>(rs,HttpStatus.FOUND);
	}
	public ResponseEntity<ResponseStructure<Driver>> deleteDriverById(int id){
		Optional<Driver> driver=driverRepository.findById(id);
		if(!driver.isPresent()) {
			throw new DriverNotPresentException();
		}
		
		driverRepository.delete(driver.get());
		ResponseStructure<Driver> rs=new ResponseStructure<Driver>();
		rs.setData(driver.get());
		rs.setMessage("Driver deleted succesfully");
		rs.setStatuscode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<Driver>>(rs,HttpStatus.ACCEPTED);
		
	}
	
	
	public ResponseEntity<ResponseStructure<Driver>> updateDriver(int driverid,int carrierid,int truckid){
		
	      Optional<Driver> oppDriver=driverRepository.findById(driverid);
	      Optional<Truck> opptruck=truckRepository.findById(truckid);
	      Optional<Carrier> oppcarrier=carrierRepository.findById(carrierid);
	 if(!oppDriver.isPresent()) {
		throw new DriverNotPresentException();
	 }
	 if(!oppcarrier.isPresent()) {
		 throw new CarrierNotPresentException();
	 }
	 if(!opptruck.isPresent()) {
		 throw new TruckNotPresentException();
	 }
	  Driver d= oppDriver.get();
      Truck	t= opptruck.get();
      Carrier c=oppcarrier.get();
      d.setCarrier(c);
      d.setTruck(t);
      t.setCarrier(c);
      driverRepository.save(d);
      truckRepository.save(t);
      rs.setData(d);
      rs.setMessage("driver updated succesfully");
      rs.setStatuscode(HttpStatus.OK.value());
      return new ResponseEntity<ResponseStructure<Driver>>(rs,HttpStatus.OK);
	}
}
