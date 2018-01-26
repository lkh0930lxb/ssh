package cn.itcast.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.BaseDictDao;
import cn.itcast.domain.BaseDict;
import cn.itcast.service.BaseDictService;
@Service("baseDictService")
@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.SUPPORTS,readOnly=true,timeout=-1)
public class BaseDictServiceImpl implements BaseDictService {
	
	@Autowired
	BaseDictDao  baseDictDao;
	
	@Override
	//根据typecode查询字典数据
	public List<BaseDict> findByDictTypeCode(String code) {
		return baseDictDao.findByDictTypeCode(code);
	}

}
