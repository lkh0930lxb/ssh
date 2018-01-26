package cn.itcast.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.itcast.dao.CustomerDao;
import cn.itcast.domain.Customer;
@Repository("customerDao")
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	HibernateTemplate hibernateTemplate;
	
	@Override
	//保存
	public void save(Customer customer) {
		hibernateTemplate.save(customer);
	}
	
	@Override
	//更新
	public void update(Customer customer) {
		hibernateTemplate.update(customer);
	}

	@Override
	//查询
	public List<Customer> findAll(DetachedCriteria dc) {
		return (List<Customer>) hibernateTemplate.findByCriteria(dc);
	}

	@Override
	//通过id查询一个对象
	public Customer findById(Long id) {
		//return hibernateTemplate.get(Customer.class, id);
		return hibernateTemplate.load(Customer.class, id);
	}

	@Override
	//删除
	public void delete(Customer customer_) {
		hibernateTemplate.delete(customer_);
	}

}
