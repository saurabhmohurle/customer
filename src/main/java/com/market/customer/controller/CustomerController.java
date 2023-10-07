package com.market.customer.controller;
//Server Post 8083
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.market.customer.entity.CustomerEntity;
import com.market.customer.model.Product;
import com.market.customer.service.CustomerService;

@RestController
public class CustomerController {
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	CustomerService service;
	private String response;
	
	@PostMapping("/addCustomer")
	public String addCustomer(@RequestBody CustomerEntity entity) {
		service.addCustomer(entity);
		response = "Customer added successfully";
		logger.info(response);
		return response; 
	}
	
	@GetMapping("/getCustomersDetails")
	public List<CustomerEntity> getCustomersDetails(){
		return service.getCustomersDetails();
	}
	
	@GetMapping("/getCustomerDetailsById")
	public Optional<CustomerEntity> getCustomerDetailsById(@RequestParam int id){
		return service.getCustomerDetailsById(id);
	}
	
	@PutMapping("/updateCustomerDetails")
	public String updateCustomerDetails(@RequestBody CustomerEntity entity) {
		service.updateCustomerDetails(entity);
		response = "Details updated successfully";
		logger.info(response);
		return response; 
	}
	
	@DeleteMapping("/deleteCustomerById")
	public String deleteCustomerById(@RequestParam int id) {
		service.deleteCustomerById(id);
		response = "Customer deleted successfully";
		logger.info(response);
		return response; 
	}
	
	@GetMapping("/getProductDetails")
	public Product[] getProductDetails(){
		return service.getProductDetails();
	}
	
	@GetMapping("/getProductById")
	public Product getProductById(@RequestParam int id){
		return service.getProductById(id);
	}
	
	@PostMapping("/addProduct")
	public String addProduct(@RequestBody Product product) {
		service.addProduct(product);
		response = "Product added successfully";
		logger.info(response);
		return response; 
	}
	
	@PutMapping("/updateProduct")
	public String updateProduct(@RequestBody Product product) {
		service.updateProduct(product);
		response = "Product updated successfully";
		logger.info(response);
		return response;
	}
	
	@DeleteMapping("/deleteProductById")
	public String deleteProductById(@RequestParam int id) {
		service.deleteProductById(id);
		response = "Product deleted successfully";
		logger.info(response);
		return response;				 
	}
}
