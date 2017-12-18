package com.itheima.crm.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itheima.crm.pojo.BaseDict;
import com.itheima.crm.pojo.Customer;
import com.itheima.crm.pojo.CustomerInfo;
import com.itheima.crm.service.BaseDictService;
import com.itheima.crm.service.CustomerService;
import com.itheima.crm.utils.Page;

@Controller
public class CustomerController {

	@Value("${customer.typecode.source}")
	private String typeCodeSource;
	@Value("${customer.typecode.industry}")
	private String typeCodeIndustry;
	@Value("${customer.typecode.level}")
	private String typeCodeLevel;
	@Autowired
	private BaseDictService baseDictService;
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/list.action")
	public String showCustomerList(Model model,CustomerInfo customerInfo, @RequestParam(defaultValue="1")int page) throws Exception{
		String custName = customerInfo.getCustName();
		if(custName!=null && !"".equals(custName)){
		    custName = new String(custName.getBytes("iso8859-1"),"utf-8");
			customerInfo.setCustName(custName);
		}
		//得到查询条件数据
		List<BaseDict> sourceList = baseDictService.getDictListByTypeCode(typeCodeSource);
	    List<BaseDict> industryList = baseDictService.getDictListByTypeCode(typeCodeIndustry);
	    List<BaseDict> levelList = baseDictService.getDictListByTypeCode(typeCodeLevel);
	    //把查询条件返回给jsp页面
	    model.addAttribute("fromType",sourceList);
		model.addAttribute("industryType",industryList);
		model.addAttribute("levelType",levelList);
		//查询数据的回显
		model.addAttribute("custName", customerInfo.getCustName());
		model.addAttribute("custSource", customerInfo.getCustSource());
		model.addAttribute("custIndustry", customerInfo.getCustIndustry());
		model.addAttribute("custLevel", customerInfo.getCustLevel());
		//
		
		Page<Customer> customerList = customerService.getCustomerList(customerInfo, page);
		model.addAttribute("page", customerList);
		return "customer";
	}

	@RequestMapping("customer/edit")
	@ResponseBody
	public Customer updateCustomerInfo(long id){
		Customer customer = customerService.getCustomerById(id);
		return customer;
	}
	
	@RequestMapping("customer/update")
	@ResponseBody
	public String updateCustomer(Customer customer){
		customerService.saveCustomer(customer);
		return "OK";
	}
	
	@RequestMapping("/customer/delete")
	@ResponseBody
	public String deleteCustomer(long id){
		customerService.deleteById(id);
		return "OK";
	}
	
	
	
}
