package com.chp.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chp.comm.utils.GsonUtil;
import com.chp.comm.utils.redis.single.RedisSingleUtils;
import com.chp.modules.mybatis.dao.entity.UserInfo;
import com.chp.modules.mybatis.dao.mapper.UserInfoMapper;

import io.swagger.annotations.Api;


/**
 * Created by xiaour on 2017/4/19.
 */
@RestController
@RequestMapping(value = "/redis")
@Api(value = "api", description = "RedisController") // Swagger UI 对应api的标题描述
public class RedisController {

  @Autowired
  private RedisSingleUtils redisSingleUtils;

  @Autowired
  private UserInfoMapper userInfoMapper;
 
  /**
   * 向redis存储值
   * 
   * @param key
   * @param value
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/set", method = RequestMethod.POST)
  public String set(@RequestParam(name = "key")String key, @RequestParam(name = "value")String value) throws Exception {
	  redisSingleUtils.set(key, value);
    return "success";
  }
  
  
  @RequestMapping(value = "/index", method = RequestMethod.GET)
  public String index() {
    return "hello world";
  }

  /**
   * 获取redis中的值
   * 
   * @param key
   * @return
   */
  @RequestMapping(value = "/get", method = RequestMethod.GET)
  public String get(@RequestParam(name = "key")String key) {
    try {
    	String ret=(String) redisSingleUtils.get(key);
      return ret;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "";
  }

  /**
   * 获取数据库中的用户
   * 
   * @param id
   * @return
   */
  @RequestMapping(value = "/getUser/{id}", method = RequestMethod.GET)
  public String get(@PathVariable("id") int id) {
    try {
      UserInfo user = userInfoMapper.selectByPrimaryKey(id);
      return GsonUtil.getJsonString(user);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "";
  }


}
