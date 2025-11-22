package com.alpha.ABClogistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.ABClogistics.DTO.DriverDto;
import com.alpha.ABClogistics.DTO.LoadingDto;
import com.alpha.ABClogistics.DTO.ResponseStructure;
import com.alpha.ABClogistics.DTO.TruckDto;
import com.alpha.ABClogistics.DTO.UnloadingDto;
import com.alpha.ABClogistics.entity.Address;
import com.alpha.ABClogistics.entity.Carrier;
import com.alpha.ABClogistics.entity.Driver;
import com.alpha.ABClogistics.entity.Order;
import com.alpha.ABClogistics.entity.Truck;
import com.alpha.ABClogistics.service.AddressService;
import com.alpha.ABClogistics.service.CarrierService;
import com.alpha.ABClogistics.service.DriverService;
import com.alpha.ABClogistics.service.OrderService;
import com.alpha.ABClogistics.service.TruckService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
	AddressService addressService;
    
    @Autowired
    TruckService truckService;
    
    @Autowired
    CarrierService carrierService;
    
    @Autowired
    DriverService driverService;
    
    @Autowired
    OrderService orderService;
    @PostMapping("/saveAddress")
    public ResponseEntity<ResponseStructure<Address>> saveAddress( @RequestBody Address address){
    	return addressService.saveAddress(address);
    }
    
    @GetMapping("/findAddressById")
    public ResponseEntity<ResponseStructure<Address>> findAddressById(@RequestParam int id){
    	return addressService.findAddressById(id);
    }
    @DeleteMapping("/deleteaddressbyid")
    public ResponseEntity<ResponseStructure<Address>> deleteAddressById(@RequestParam int id){
    	return addressService.deleteAddressById(id);
    }
    
    @PostMapping("/savetruck")
    public ResponseEntity<ResponseStructure<Truck>> saveTruck(@RequestBody TruckDto truckDto){
    	return truckService.savetruck(truckDto);
    }
    
    @GetMapping("/findtruckbyid")
    public ResponseEntity<ResponseStructure<Truck>> findTruckById(@RequestParam int id){
    	return truckService.findTruckById(id);
    }
    
    @DeleteMapping("/deletetruckbyid")
    public ResponseEntity<ResponseStructure<Truck>> deleteTruckById(@RequestParam int id){
    	return truckService.deleteTruckById(id);
    }
    
    @PostMapping("/savecarrier")
    public ResponseEntity<ResponseStructure<Carrier>> saveCarrier(@RequestBody Carrier carrier){
    	return carrierService.saveCarrier(carrier);
    }
    @GetMapping("/findcarrierbyid")
    public ResponseEntity<ResponseStructure<Carrier>> findCarrierById(@RequestParam int id){
    	return carrierService.findCarrierById(id);
    }
    @DeleteMapping("/deletecarrierbyid")
    public ResponseEntity<ResponseStructure<Carrier>> deleteCarrierById(@RequestParam int id){
    	return carrierService.deleteCarrierById(id);
    }
    
    @PostMapping("/savedriver")
    public ResponseEntity<ResponseStructure<Driver>> saveDriver(@RequestBody DriverDto driverDto){
    	return driverService.saveDriver(driverDto);
    }
    @GetMapping("/finddriverbyid")
    public ResponseEntity<ResponseStructure<Driver>> findDriverById(@RequestParam int id){
    	return driverService.findDriverById(id);
    }
    @DeleteMapping("/deletedriverbyid")
    public ResponseEntity<ResponseStructure<Driver>> deleteDriverById(@RequestParam int id){
    	return driverService.deleteDriverById(id);
    }
    
    @PutMapping("/updateDriver/{driverid}/assingcarrier/{carrierid}/assignTruck/{truckid}")
    public ResponseEntity<ResponseStructure<Driver>> updateDriver(@PathVariable int driverid,@PathVariable int carrierid, @PathVariable int truckid){
    	return driverService.updateDriver(driverid, carrierid, truckid);
    }
    
    @PutMapping("/updateorder/{orderid}/assigncarrier/{truckid}")
    public ResponseEntity<ResponseStructure<Order>> updateOrder(@PathVariable int orderid,@PathVariable int truckid){
    	return orderService.UpdateOrder(orderid,truckid);
    }
    
    @PutMapping("/updateorder/{orderid}/updateloading")
    public ResponseEntity<ResponseStructure<Order>> updateOrderupdateLoading(@PathVariable int orderid, @RequestBody LoadingDto loadingDto){
    	return orderService.UpdateOrderUpdateLoading(orderid, loadingDto);
    }
    
    @PutMapping("/updateorder/{orderid}/updateunloading")
    public ResponseEntity<ResponseStructure<Order>> updateOrderupdateUnloading(@PathVariable int orderid, @RequestBody UnloadingDto unloadingDto){
    	return orderService.UpdateOrderUpdateUnloading(orderid,unloadingDto);
    }
}
