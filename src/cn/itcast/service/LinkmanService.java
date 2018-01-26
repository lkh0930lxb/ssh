package cn.itcast.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.domain.Linkman;
import cn.itcast.utils.PageBean;

public interface LinkmanService {
	
	void save(Linkman linkman);
	void update(Linkman linkman);
	void delete(Linkman linkman);
	
	Linkman findById(Long id);
	List<Linkman> findAll(DetachedCriteria dc);
	PageBean<Linkman> findByPage(DetachedCriteria dc, Integer pageNumber, Integer pageSize);
}
