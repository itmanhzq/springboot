package com.chp.comm.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @Class Name SqlUtil
 * @Author zhangli
 * @Create In 2017年12月14日
 */
public class SqlUtil {

    private SqlUtil() {
    }
    
    /**
     * 模糊查询：前后加通配符
     * @Methods Name likeAround
     * @Create In 2017年12月14日 By zhangli
     * @param field
     * @return String
     */
    public static String likeAround(String field) {
      if (StringUtils.isNotBlank(field)) {
          String str = field.replace("%", "\\%").replace("_", "\\_");
          return "%" + str + "%";
      }
      return null;
  }

}
