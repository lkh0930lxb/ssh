package cn.itcast.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.domain.Customer;

public interface CustomerService {
	void save(Customer customer);

	List<Customer> findAll(DetachedCriteria dc);

	Customer findById(Long custId);

	void update(Customer customer);

	void delete(Customer customer);
}
