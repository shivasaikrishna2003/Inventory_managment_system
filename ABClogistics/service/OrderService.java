package com.alpha.ABClogistics.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.ABClogistics.DTO.LoadingDto;
import com.alpha.ABClogistics.DTO.OrderDto;
import com.alpha.ABClogistics.DTO.ResponseStructure;
import com.alpha.ABClogistics.DTO.UnloadingDto;
import com.alpha.ABClogistics.entity.Address;
import com.alpha.ABClogistics.entity.Cargo;
import com.alpha.ABClogistics.entity.Driver;
import com.alpha.ABClogistics.entity.Loading;
import com.alpha.ABClogistics.entity.Order;
import com.alpha.ABClogistics.entity.Truck;
import com.alpha.ABClogistics.entity.Unloading;
import com.alpha.ABClogistics.exception.CarrierNotAssignedException;
import com.alpha.ABClogistics.exception.LoadingAddressIdNotFoundException;
import com.alpha.ABClogistics.exception.OrderNotPlacedException;
import com.alpha.ABClogistics.exception.OrderNotPresentException;
import com.alpha.ABClogistics.exception.OrderUpdateFailedException;
import com.alpha.ABClogistics.exception.TruckNotPresentException;
import com.alpha.ABClogistics.exception.UnloadingAddressIdNotFoundException;
import com.alpha.ABClogistics.repository.AddressRepository;
import com.alpha.ABClogistics.repository.OrderRepository;
import com.alpha.ABClogistics.repository.TruckRepository;

@Service
public class OrderService {
   
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	TruckRepository truckRepository;
	
	@Autowired
	MailService mailService;
	
	ResponseStructure<Order> rs=new ResponseStructure<Order>();
	public ResponseEntity<ResponseStructure<Order>> placeUserOrder(OrderDto orderDto){
		Order order=new Order();
		order.setId(orderDto.getId());
		order.setOrderdate(orderDto.getOrderdate());
		int cost=10*(orderDto.getCargoWeight()*orderDto.getCargoCount());
		order.setCost(cost);
		order.setEmail(orderDto.getEmail());
		Cargo cargo=new Cargo();
		cargo.setId(orderDto.getCargoId());
		cargo.setName(orderDto.getCargoName());
		cargo.setDescription(orderDto.getCargoDescription());
		cargo.setWeight(orderDto.getCargoWeight());
		cargo.setCount(orderDto.getCargoCount());
		
		order.setCargo(cargo);
		
		Loading load=new Loading();
		Address loadadd=addressRepository.findById(orderDto.getLoadingId()).orElseThrow(()->new LoadingAddressIdNotFoundException());
		load.setAddress(loadadd);
		order.setLoading(load);
		
		Unloading unload=new Unloading();
		
//		System.err.println(orderDto.getUnloadingId());
		
		Address unloadadd=addressRepository.findById(orderDto.getUnloadingId()).orElseThrow(()->new UnloadingAddressIdNotFoundException());
		
		unload.setAddress(unloadadd);
		order.setUnloading(unload);
		
	    Order ord=orderRepository.save(order);
		
		rs.setData(ord);
		rs.setMessage("order placed succesfully");
		rs.setStatuscode(HttpStatus.CREATED.value());
		
		String subject="ORDER PLACED";
		String content="Your order placed From "+ ord.getLoading().getAddress().getCity()+" to "+ord.getUnloading().getAddress().getCity()+" For the cost of $ "+ord.getCost();
		mailService.sendmail(ord.getEmail(), subject, content);
		return new ResponseEntity<ResponseStructure<Order>>(rs,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Order>> UpdateOrder(int orderid, int truckid) {
	
		  ResponseStructure<Order> rs=new ResponseStructure<Order>();
	   Order ord=orderRepository.findById(orderid).orElseThrow(()-> new OrderNotPresentException());
	   if(ord.getStatus().equals("placed")) {
	   Truck tru= truckRepository.findById(truckid).orElseThrow(()-> new TruckNotPresentException());
	   int totalWtofOrd=(ord.getCargo().getWeight()*ord.getCargo().getCount());
	   int totalcapacity=tru.getCapacity();
	   if(totalcapacity>=totalWtofOrd) {
		   ord.setCarrier(tru.getCarrier());
		   tru.setCapacity(tru.getCapacity()-totalWtofOrd);
		   truckRepository.save(tru);
		  Order ord1=orderRepository.save(ord);
		   rs.setData(ord1);
		   rs.setMessage("order updated succesfully");
		   rs.setStatuscode(HttpStatus.OK.value());
			
			String subject="ORDER Assigned For Order";
			String content="Your order is succesfully assigned to a carrier name"+ord.getCarrier().getName();
			mailService.sendmail(ord.getEmail(), subject, content);
		   return new ResponseEntity<ResponseStructure<Order>>(rs,HttpStatus.OK);
	   }else {
		   rs.setMessage("Truck capacity is not sufficient for this order");
		   rs.setStatuscode(HttpStatus.BAD_REQUEST.value());
		   
		   return new ResponseEntity<ResponseStructure<Order>>(rs,HttpStatus.BAD_REQUEST);
	   }
	 }else {
		 throw new OrderNotPlacedException();
	 }
	}
	
	public ResponseEntity<ResponseStructure<Order>> UpdateOrderUpdateLoading(int orderid,LoadingDto loadingDao) {
		
		Order o=orderRepository.findById(orderid).orElseThrow(()-> new OrderNotPresentException());
		if(o.getStatus().equals("cancelled")) {
			throw new OrderUpdateFailedException();
		}else {
				 o.getLoading().setDate(loadingDao.getDate());
		           o.getLoading().setTime(loadingDao.getTime());
		           o.setStatus("pending");
		           orderRepository.save(o);
		           rs.setData(o);
				   rs.setMessage("order and loading  updated succesfully ");
				   rs.setStatuscode(HttpStatus.OK.value());
				   
				   String subject=" Your ORDER is succesfully loaded and  Shipped ";
					String content="Your order is succesfully loaded and shipped at "+o.getLoading().getDate()+" this time  " +" at " +o.getLoading().getTime();
					mailService.sendmail(o.getEmail(), subject, content);
				   return new ResponseEntity<ResponseStructure<Order>>(rs,HttpStatus.OK);
			}
		}
          
	

	public ResponseEntity<ResponseStructure<Order>> UpdateOrderUpdateUnloading(int orderid, UnloadingDto unloadingDto) {
		 
	   Order o=orderRepository.findById(orderid).orElseThrow(()-> new OrderNotPresentException());
	   if(o.getStatus().equals("pending")) {
	   o.getUnloading().setDate(unloadingDto.getDate());
	   o.getUnloading().setTime(unloadingDto.getTime());
	   o.setStatus("completed");
	   orderRepository.save(o);
	   }
	   rs.setData(o);
	   rs.setMessage("order and unloading upadted succesfully");
	   rs.setStatuscode(HttpStatus.ACCEPTED.value());
	   
	   String subject=" Your ORDER is succesfully unloaded and  delivered";
		String content="Your order is succesfully unloaded and delivered at "+o.getUnloading().getDate()+" this time  " +" at " +o.getUnloading().getTime();
		mailService.sendmail(o.getEmail(), subject, content);
		return new ResponseEntity<ResponseStructure<Order>>(rs, HttpStatus.ACCEPTED);
	   
	}
	
	public ResponseEntity<ResponseStructure<Order>> cancelOrder(int orderid){
	  Order ord=orderRepository.findById(orderid).orElseThrow(()-> new OrderNotPresentException());
	   if(ord.getStatus().equals("placed"))
	    {
		 ord.setStatus("cancelled");
		 orderRepository.save(ord);
	 }else {
	        rs.setData(ord);
	        rs.setMessage("Order cannot be cancelled as it is already " + ord.getStatus());
	        rs.setStatuscode(HttpStatus.BAD_REQUEST.value());
	        return new ResponseEntity<>(rs, HttpStatus.ALREADY_REPORTED);
	}
	    rs.setData(ord);
	    rs.setMessage("Order is succesfully canelled");
	    rs.setStatuscode(HttpStatus.ACCEPTED.value());
	    String subject=" Your ORDER is succesfully cancelled";
		String content="Your order is succesfully cancelled "+ord.getUnloading().getDate()+" this time  " +" at " +ord.getUnloading().getTime();
		mailService.sendmail(ord.getEmail(), subject, content);
	 return new ResponseEntity<ResponseStructure<Order>>(rs, HttpStatus.ACCEPTED);
  }
}
