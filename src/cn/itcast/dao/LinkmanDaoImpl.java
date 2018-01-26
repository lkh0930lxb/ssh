package cn.itcast.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.itcast.dao.LinkmanDao;
import cn.itcast.domain.Linkman;
@Repository("linkmanDao")
public class LinkmanDaoImpl implements LinkmanDao {

	@Autowired
	HibernateTemplate hibernateTemplate;
	
	@Override
	public void save(Linkman linkman) {
		hibernateTemplate.save(linkman);
	}

	@Override
	public void update(Linkman linkman) {
		hibernateTemplate.update(linkman);

	}

	@Override
	public void delete(Linkman linkman) {
		hibernateTemplate.delete(linkman);

	}

	@Override
	public Linkman findById(Long id) {
		
		return hibernateTemplate.get(Linkman.class, id);
	}

	@Override
	public List<Linkman> findAll(DetachedCriteria dc) {
		
		return (List<Linkman>) hibernateTemplate.findByCriteria(dc);
	}

	@Override
	//查询总记录数
	public int findTotalRecord(DetachedCriteria dc) {
		//设置count(*)
		dc.setProjection(Projections.rowCount());//select count(*) from .. where ....
		List<Long> list = (List<Long>) hibernateTemplate.findByCriteria(dc);
		return list.get(0).intValue();
	}

	@Override
	//查询当前页数据
	public List<Linkman> findByPage(DetachedCriteria dc, int firstResult, int maxResults) {
		//需要去掉 统计查询
		dc.setProjection(null);
		return (List<Linkman>) hibernateTemplate.findByCriteria(dc,firstResult,maxResults);
	}

}
