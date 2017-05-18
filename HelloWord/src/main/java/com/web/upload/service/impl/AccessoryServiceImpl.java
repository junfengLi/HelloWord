package com.web.upload.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.upload.dao.AccessoryDao;
import com.web.upload.pojo.Accessory;
import com.web.upload.service.AccessoryService;

@Service("accessoryService")
@Transactional 
public class AccessoryServiceImpl implements AccessoryService {
	@Autowired
	private AccessoryDao accessoryDao;

	@Override
	public void add(Accessory accessory) {
		if (StringUtils.isBlank(accessory.getId())) {
			accessory.setCreatetime(System.currentTimeMillis());
			accessoryDao.insertSelective(accessory);
		} else {
			accessoryDao.updateByPrimaryKeySelective(accessory);
		}
	}

	@Override
	public boolean updateLinkId(String accessoryIds, String linkId) {
		if (StringUtils.isNotBlank(accessoryIds)&&StringUtils.isNotBlank(linkId)) {
			String[] accessoryIdList = accessoryIds.split(";");
			for (String id : accessoryIdList) {
				Accessory accessory = getAccessoryById(id);
				accessory.setLinkid(linkId);
				accessoryDao.updateByPrimaryKeySelective(accessory);
			}
		}
		return true;
	}

	@Override
	public Accessory getAccessoryById(String id) {
		return accessoryDao.selectByPrimaryKey(id);
	}

	@Override
	public List<Accessory> getByLinkIdModule(String linkId, String module) {
		Accessory accessory = new Accessory();
		accessory.setLinkid(linkId);
		accessory.setModule(module);
		return accessoryDao.selectByStatement(accessory);
	}

	@Override
	public void deleteById(String id) {
		accessoryDao.deleteByPrimaryKey(id);
	}

	@Override
	public boolean deleteByLinkId(String linkId) {
		Accessory accessory = new Accessory();
		accessory.setLinkid(linkId);
		List<Accessory> accessories = accessoryDao.selectByStatement(accessory);
		for (Accessory accessory2 : accessories) {
			accessoryDao.deleteByPrimaryKey(accessory2.getId());
		}
		return true;
	}

	@Override
	public Accessory getOneByLinkIdModule(String linkId, String module) {
		List<Accessory> accessories = getByLinkIdModule(linkId, module);
		if (CollectionUtils.isNotEmpty(accessories)) {
			return accessories.get(0);
		}
		return null;
	}

	@Override
	public void deleteByAccessoryNames(String deleteAccessoryNames, String linkId) {
		if (StringUtils.isNotBlank(deleteAccessoryNames)) {
			String[] accessoryIdList = deleteAccessoryNames.split(";");
			for (String name : accessoryIdList) {
				Accessory accessory = new Accessory();
				accessory.setLinkid(linkId);
				accessory.setName(name);
				List<Accessory> accessories = accessoryDao.selectByStatement(accessory);
				if (CollectionUtils.isNotEmpty(accessories)) {
					for (Accessory accessory2 : accessories) {
						accessoryDao.deleteByPrimaryKey(accessory2.getId());
					}
				}
			}
		}
	}

}
