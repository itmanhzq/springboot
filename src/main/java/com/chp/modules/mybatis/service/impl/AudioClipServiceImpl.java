package com.chp.modules.mybatis.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.chp.comm.exception.BusinessException;
import com.chp.comm.utils.SqlUtil;
import com.chp.modules.mybatis.dao.entity.AudioClip;
import com.chp.modules.mybatis.dao.mapper.AudioClipMapper;
import com.chp.modules.mybatis.service.AudioClipService;
import com.chp.modules.mybatis.service.dto.AudioClipDto;
import com.chp.modules.mybatis.service.dto.AudioClipPageDto;

/**
 * 音频素材管理相关接口实现
 * 
 * @Class Name AudioClipServiceImpl
 * @Create In 2018年2月24日
 */
@Service
public class AudioClipServiceImpl implements AudioClipService {

  protected final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private AudioClipMapper audioClipMapper;

  @Transactional
  @Override
  public int batchInsert(AudioClipDto dto) throws BusinessException {
    return 0;
  }

  @Transactional
  @Override
  public int batchDelete(AudioClipDto dto) throws BusinessException {
      return 0;
    }

  @Override
  public AudioClipPageDto qryPageAudioClip(int pageSize, int pageNo, String searchKey)
      throws BusinessException {
    logger.info(" query searchKey: {}, pageSize: {}, pageNo: {}", searchKey, pageSize, pageNo);
    AudioClipPageDto pageInfo = new AudioClipPageDto();
    pageSize = pageSize > 0 ? pageSize : 20;
    pageNo = pageNo > 0 ? pageNo : 1;
    searchKey = SqlUtil.likeAround(searchKey);
    Map<String, Object> map = new HashMap<>();
    int start = (pageNo - 1) * pageSize;//offset指定要返回的第一行的偏移量:初始行的偏移量是0(不是1)
    map.put("start", start);
    map.put("searchKey", searchKey);
    map.put("pageSize", pageSize);
    // 按条件分页查询
    List<AudioClip> audioClipList = audioClipMapper.qryByMapParam(map);
    //int total2=audioClipList.size();//这样也可以查询总条数
    // 查询总条数
    int total = audioClipMapper.qryTotalByKey(searchKey);
    // 分页信息封装
    int pageCount = total / pageSize;
    if (total % pageSize > 0) {
      pageCount = pageCount + 1;
    }
    pageInfo.setPageCount(pageCount);
    pageInfo.setAudioClip(audioClipList);
    pageInfo.setTotal(total);
    logger.info("AudioClip pageInfo: {}" + JSON.toJSONString(pageInfo));
    return pageInfo;
  }

  @Override
  public List<String> qryAudioClipName() {
    return audioClipMapper.qryAudioClipName();
  }

  @Override
  public List<AudioClip> qryAudioClipByIds(List<String> auList) {
    return audioClipMapper.qryAudioClipByIds(auList);
  }


}
