package cn.itcast.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.itcast.dao.BaseDictDao;
import cn.itcast.domain.BaseDict;
@Repository("baseDictDao")
public class BaseDictDaoImpl implements BaseDictDao {
	
	@Autowired
	HibernateTemplate hibernateTemplate;

	@Override
	//根据typecode查询字典数据
	public List<BaseDict> findByDictTypeCode(String code) {
		return (List<BaseDict>) hibernateTemplate.find("from BaseDict where dictTypeCode = ?", code);
	}

}
