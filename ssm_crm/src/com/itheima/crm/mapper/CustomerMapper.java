package com.itheima.crm.mapper;

import java.util.List;

import com.itheima.crm.pojo.Customer;
import com.itheima.crm.pojo.CustomerInfo;

public interface CustomerMapper {
	
	List<Customer> getCustomerInfo(CustomerInfo customerInfo);
	
	int getCustomerCount(CustomerInfo customerInfo);
	
	Customer getCustomerById(long id);
	
	void saveCustomer(Customer customer);
	
	void deleteById(long id);
	
}