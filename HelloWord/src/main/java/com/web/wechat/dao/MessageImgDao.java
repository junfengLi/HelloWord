/*
* @ClassName:MessageImgDao.java
* @Description: TODO desc 
* @author: Lijunfeng
* @date 2017-05-11
*/
package com.web.wechat.dao;

import com.web.commons.dao.BaseDao;
import com.web.wechat.pojo.MessageImg;
import java.util.List;

public interface MessageImgDao extends BaseDao<MessageImg> {
	void deleteByPid(String pid);
	String selectMaxUid(MessageImg messageImg);
}