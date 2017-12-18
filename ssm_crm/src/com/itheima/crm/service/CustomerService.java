package com.itheima.crm.service;

import java.util.List;

import com.itheima.crm.pojo.BaseDict;
import com.itheima.crm.pojo.Customer;
import com.itheima.crm.pojo.CustomerInfo;
import com.itheima.crm.utils.Page;

public interface CustomerService {
	
	Page<Customer> getCustomerList(CustomerInfo customerInfo, int cutPage);

    Customer getCustomerById(long id);
	
	void saveCustomer(Customer customer);
	
	void deleteById(long id);
	
}
