package com.itheima.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itheima.crm.mapper.CustomerMapper;
import com.itheima.crm.pojo.Customer;
import com.itheima.crm.pojo.CustomerInfo;
import com.itheima.crm.service.CustomerService;
import com.itheima.crm.utils.Page;

@Service
public class CustomerServiceimpl implements CustomerService {

	@Autowired
	private CustomerMapper customerMapper;
	
	@Override
	public Page<Customer> getCustomerList(CustomerInfo customerInfo, int curPage) {
		// 1、根据curPage计算start。（curPage - 1）* rows
		int start = (curPage - 1)*customerInfo.getRows();
		// 2、把start设置到CustomerInfo对象中
		customerInfo.setStart(start);
		// 3、执行查询，得到一个客户列表
		List<Customer> list = customerMapper.getCustomerInfo(customerInfo);
		// 4、取查询结果总记录数。
		int count = customerMapper.getCustomerCount(customerInfo);
		// 5、把结果封装到Page对象中。
		Page<Customer> result = new Page<>();
		result.setPage(curPage);
		result.setRows(list);
		result.setSize(customerInfo.getRows());
		result.setTotal(count);
		return result;
	}

	@Override
	public Customer getCustomerById(long id) {
		return customerMapper.getCustomerById(id);
	}

	@Override
	public void saveCustomer(Customer customer) {
		customerMapper.saveCustomer(customer);
		
	}

	@Override
	public void deleteById(long id) {
		customerMapper.deleteById(id);
	}
	
}
