/*
* @ClassName:WechatDao.java
* @Description: TODO desc 
* @author: Lijunfeng
* @date 2017-05-11
*/
package com.web.wechat.dao;

import com.web.commons.dao.BaseDao;
import com.web.wechat.pojo.Wechat;

public interface WechatDao extends BaseDao<Wechat> {
	
	void deleteByUserId(String userid);
}