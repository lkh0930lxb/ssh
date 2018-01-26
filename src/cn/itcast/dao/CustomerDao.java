package cn.itcast.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.domain.Customer;

public interface CustomerDao {
	void save(Customer customer);

	List<Customer> findAll(DetachedCriteria dc);

	Customer findById(Long id);

	void update(Customer customer);

	void delete(Customer customer_);
}
