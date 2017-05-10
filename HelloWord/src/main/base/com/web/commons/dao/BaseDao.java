package com.web.commons.dao;

import java.util.List;

public interface BaseDao<T> {  
	int deleteByPrimaryKey(String id);

    //int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(String id);

    List<T> selectByStatement(T record);

    int selectByStatementCount(T record);

    int updateByPrimaryKeySelective(T record);
    
}  