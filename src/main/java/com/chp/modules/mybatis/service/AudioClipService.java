
package com.chp.modules.mybatis.service;

import java.util.List;

import com.chp.modules.mybatis.dao.entity.AudioClip;
import com.chp.modules.mybatis.service.dto.AudioClipDto;
import com.chp.modules.mybatis.service.dto.AudioClipPageDto;

/**
 * 音频素材管理相关接口
 * 
 * @Class Name IAudioClipService
 */
public interface AudioClipService {

  /**
   * 音频名称校验接口
   * 
   * @Methods Name qryAudioClipName
   * @return List<String>
   */
  List<String> qryAudioClipName();

  /**
   * 根据id查询音频信息接口
   * 
   * @Methods Name qryAudioClipByIds
   * @param auList
   * @return List<AudioClip>
   */
  List<AudioClip> qryAudioClipByIds(List<String> audioUuidList);

  /**
   * 音频创建接口
   * 
   * @Methods Name batchInsert
   * @param dto
   * @return int
   */
  int batchInsert(AudioClipDto dto);

  /**
   * 音频单条/批量删除接口
   * 
   * @Methods Name batchDelete
   * @param dto
   * @return int
   */
  int batchDelete(AudioClipDto dto);

  /***
   * 音频信息分页查询接口
   * 
   * @Methods Name qryAudioClip
   * @param pageSize
   * @param pageNo
   * @param searchKey
   * @return int
   */
  AudioClipPageDto qryPageAudioClip(int pageSize, int pageNo, String searchKey);



}
