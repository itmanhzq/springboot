package com.chp.modules.mybatis.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.chp.modules.mybatis.dao.entity.AudioClip;

public interface AudioClipMapper{

    /**
     * 通过音频ID查询音频
     * @param audioIds
     * @return List<AudioClip>
     */
  List<AudioClip> qryAudioClipByIds(List<String> audioIds);
  
  /**
   * 查询所有音频名称
   * @return List<String>
   */
  List<String> qryAudioClipName();

  List<AudioClip> qryByMapParam(Map<String, Object> map);

  int qryTotalByKey(@Param("searchKey")String searchKey);
  
}
