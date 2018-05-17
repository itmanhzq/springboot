package com.chp.comm.utils;

import org.springframework.beans.BeanUtils;

/**
 * @Class Name BaseConvert
 * @Author zhangli
 * @Create In 2017年12月14日
 */
public class BaseConvert {

  protected BaseConvert() {}

  public static void convertPOToVO(Object poObj, Object voObj) {
    BeanUtils.copyProperties(poObj, voObj);
  }

  public static void convertPOToVO(Object poObj, Object voObj, String... ignoreProperties) {
    BeanUtils.copyProperties(poObj, voObj, ignoreProperties);
  }

  public static void convertVOToPO(Object voObj, Object poObj) {
    BeanUtils.copyProperties(voObj, poObj);
  }

  public static void convertVOToPO(Object voObj, Object poObj, String... ignoreProperties) {
    BeanUtils.copyProperties(voObj, poObj, ignoreProperties);
  }

}
