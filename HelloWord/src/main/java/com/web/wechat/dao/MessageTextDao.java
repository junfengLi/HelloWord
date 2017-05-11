/*
* @ClassName:MessageTextDao.java
* @Description: TODO desc 
* @author: Lijunfeng
* @date 2017-05-11
*/
package com.web.wechat.dao;

import com.web.commons.dao.BaseDao;
import com.web.wechat.pojo.MessageText;
import java.util.List;

public interface MessageTextDao extends BaseDao<MessageText> {
	void deleteByPid(String pid);
}