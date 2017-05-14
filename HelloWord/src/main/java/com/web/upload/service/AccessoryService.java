package com.web.upload.service;

import java.util.List;

import com.web.upload.pojo.Accessory;

/**
 * 成员信息操作接口
 * @author yang
 *
 */
public interface AccessoryService {
	/**
	 * 添加一个成员
	 * @param member
	 */
	void add(Accessory accessory);
	/**
	 * 根据ID获取一个成员
	 * @param id
	 * @return
	 */
	boolean updateLinkId(String accessoryIds,String linkId);
	Accessory getAccessoryById(String id);
	List<Accessory> getByLinkIdModule(String linkId,String module);
	Accessory getOneByLinkIdModule(String linkId,String module);
	/**
	 * 根据ID删除一个成员
	 * @param id
	 */
	void deleteById(String id);
	boolean deleteByLinkId(String linkId);
	void deleteByAccessoryNames(String deleteAccessoryNames, String id);
}
