package cn.itcast.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.domain.Linkman;

public interface LinkmanDao {
	void save(Linkman linkman);
	void update(Linkman linkman);
	void delete(Linkman linkman);
	
	Linkman findById(Long id);
	List<Linkman> findAll(DetachedCriteria dc);
	int findTotalRecord(DetachedCriteria dc);
	List<Linkman> findByPage(DetachedCriteria dc, int firstResulte, int maxResults);
}	
