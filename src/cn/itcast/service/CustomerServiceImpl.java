package cn.itcast.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.CustomerDao;
import cn.itcast.domain.Customer;
import cn.itcast.service.CustomerService;
@Service("customerService")
@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false,timeout=-1)
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	CustomerDao customerDao;
	
	@Override
	//保存
	public void save(Customer customer) {
		customerDao.save(customer);
	}
	
	@Override
	//更新
	public void update(Customer customer) {
		customerDao.update(customer);
	}

	@Override
	//查询
	public List<Customer> findAll(DetachedCriteria dc) {
		return customerDao.findAll(dc);
	}

	@Override
	//根据id查询一个对象
	public Customer findById(Long id) {
		return customerDao.findById(id);
	}

	@Override
	//删除
	public void delete(Customer customer) {
		Customer customer_ = customerDao.findById(customer.getCustId());
		customerDao.delete(customer_);
	}

}
