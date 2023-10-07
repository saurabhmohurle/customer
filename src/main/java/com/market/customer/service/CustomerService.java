package com.market.customer.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.market.customer.entity.CustomerEntity;
import com.market.customer.entity.CustomerRepository;
import com.market.customer.model.Product;

@Service
public class CustomerService {

	private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);
	
	@Autowired
	CustomerRepository repository;
	
	@Autowired
	RestTemplate template;

	//private CustomerEntity entity;
	String response;
	public String addCustomer(CustomerEntity entity) {
		// TODO Auto-generated method stub
		System.out.println(entity.toString());
		logger.info(entity.toString());
		repository.save(entity);
		logger.info(response);
		response = "Customer added successfully";
		return response;
	}

	public List<CustomerEntity> getCustomersDetails() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}
	
	public Optional<CustomerEntity> getCustomerDetailsById(int id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

	public String updateCustomerDetails(CustomerEntity entity) {
		// TODO Auto-generated method stub
		System.out.println(entity.toString());
		logger.info(entity.toString());
		repository.save(entity);
		logger.info(response);
		response = "Details updated successfully";
		return response;
	}

	public String deleteCustomerById(@RequestParam int id) {
		// TODO Auto-generated method stub
		String message;
		try {
		repository.deleteById(id);
		message = "Customer deleted successfully";
		logger.info(message);
		}catch (Exception e) {
			
			// TODO: handle exception
			message = "Invalid Id: "+id;
			logger.error(message);
		}
		return message;
	}

	//@SuppressWarnings("unchecked")
	public Product[] getProductDetails() {
		// TODO Auto-generated method stub
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity <String> entity = new HttpEntity<String>(headers);
		String url="http://localhost:8082/getProductDetails";
		Product[] products = template.exchange(url, HttpMethod.GET, entity, Product[].class).getBody();
		return products;
	}

	public Product getProductById(int id) {
		// TODO Auto-generated method stub
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity <String> entity = new HttpEntity<String>(headers);
		String url="http://localhost:8082/getProductDetailsById?id="+id;
		Product products = template.exchange(url, HttpMethod.GET, entity, Product.class).getBody();
		return products;
	}

	public String addProduct(Product product) {
		// TODO Auto-generated method stub
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity <Product> entity = new HttpEntity<Product>(product,headers);
		String url="http://localhost:8082/addProduct";
		String result = template.exchange(url, HttpMethod.POST, entity, String.class).getBody();
		return result;
	}

	public String updateProduct(Product product) {
		// TODO Auto-generated method stub
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Product> entity = new HttpEntity<Product>(product,headers);
		String url="http://localhost:8082/updateProduct";
		String result = template.exchange(url, HttpMethod.PUT, entity, String.class).getBody();
		return result;
		
	}

	public String deleteProductById(int id) {
		// TODO Auto-generated method stub
		HttpHeaders header = new HttpHeaders();
		header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(header);
		String url = "http://localhost:8082/deleteProductById?id="+id;
		String result = template.exchange(url, HttpMethod.DELETE, entity, String.class).getBody();
		return result;
	}
}

	
	
	
