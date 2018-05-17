package com.chp.modules.mybatis.vo;

public interface BaseVo<T> {
  
	void convertPOToVO(T poObj);
	
	T convertVOToPO();
	
}

