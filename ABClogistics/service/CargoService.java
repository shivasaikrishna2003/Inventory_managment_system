package com.alpha.ABClogistics.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.ABClogistics.DTO.CargoDto;
import com.alpha.ABClogistics.DTO.ResponseStructure;
import com.alpha.ABClogistics.entity.Cargo;
import com.alpha.ABClogistics.repository.CargoRespository;



@Service
public class CargoService {
   @Autowired
	CargoRespository cargoRespository;
   ResponseStructure<Cargo> rs=new ResponseStructure<Cargo>();
   public ResponseEntity<ResponseStructure<Cargo>> saveCargo(CargoDto cargoDto){
	   
	 Optional<Cargo> opcargo=cargoRespository.findById(cargoDto.getId());
	
	 Cargo cargo=new Cargo();
	 cargo.setId(cargoDto.getId());
	 cargo.setName(cargoDto.getName());
	 cargo.setDescription(cargoDto.getDescription());
	 cargo.setWeight(cargoDto.getWeight());
	 cargo.setCount(cargoDto.getCount());
	 cargoRespository.save(cargo);
	 rs.setData(cargo);
	 rs.setMessage("cargo saved succesfully");
	 rs.setStatuscode(HttpStatus.CREATED.value());
	 
	 return  new ResponseEntity<ResponseStructure<Cargo>>(rs,HttpStatus.CREATED);
	   
   }
 }

