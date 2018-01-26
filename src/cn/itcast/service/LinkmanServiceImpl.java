package cn.itcast.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.LinkmanDao;
import cn.itcast.domain.Linkman;
import cn.itcast.service.LinkmanService;
import cn.itcast.utils.PageBean;
@Service("linkmanService")
@Transactional
public class LinkmanServiceImpl implements LinkmanService {

	@Autowired
	LinkmanDao linkmanDao;
	
	@Override
	public void save(Linkman linkman) {
		linkmanDao.save(linkman);
	}

	@Override
	public void update(Linkman linkman) {
		linkmanDao.update(linkman);

	}

	@Override
	public void delete(Linkman linkman) {
		linkmanDao.delete(linkman);
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public Linkman findById(Long id) {
		
		return linkmanDao.findById(id);
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public List<Linkman> findAll(DetachedCriteria dc) {
		
		return linkmanDao.findAll(dc);
	}

	@Override
	//分页查询
	public PageBean<Linkman> findByPage(DetachedCriteria dc, Integer pageNumber, Integer pageSize) {
		//1.创建pagebean
		PageBean<Linkman> pb = new PageBean<>(pageNumber, pageSize);

		//2.设置总记录数
		pb.setTotalRecord(linkmanDao.findTotalRecord(dc));

		//3.设置当前页数据
		pb.setData(linkmanDao.findByPage(dc,pb.getStartIndex(),pb.getPageSize()));

		//4.返回pagebean
		return pb;
	}

}
