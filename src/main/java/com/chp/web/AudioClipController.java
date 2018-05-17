package com.chp.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.chp.comm.ResultMessage;
import com.chp.comm.exception.BusinessException;
import com.chp.modules.mybatis.service.AudioClipService;
import com.chp.modules.mybatis.service.dto.AudioClipPageDto;
import com.chp.modules.mybatis.vo.AudioClipVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 音频管理接口
 * 
 * @Class Name AudioClipController
 * @Author chenpeng
 * @Create In 2018年2月27日
 */
@RestController
@RequestMapping(value = "/audioclip")
@Api(value = "api", description = "AudioClipController") // Swagger UI 对应api的标题描述
public class AudioClipController {

  protected final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private AudioClipService audioClipServiceImpl;

  /**
   * 
   * @Methods Name batchCreateAudioClip
   * @Create In 2018年3月29日 By chenpeng
   * @param audioClipVo
   * @return
   * @throws BusinessException ResultMessage
   */
  @ApiOperation("批量新增音频")
  @RequestMapping(value = "/batchCreate", method = RequestMethod.POST)
  public ResultMessage batchCreateAudioClip(@Valid AudioClipVo audioClipVo)
      throws BusinessException {
    audioClipServiceImpl.batchInsert(audioClipVo.convertVOToPO());
    return ResultMessage.createSuccessResult();
  }

  /**
   * 批量删除音频素材-单条删除共用
   * 
   * @Methods Name batchDeleteAudio
   * @param ids
   * @return ResultMessage
   */
  @ApiOperation("批量删除音频")
  @PostMapping(value = "/batchDelete")
  public ResultMessage batchDeleteAudio(@Valid @RequestBody AudioClipVo audiClipVo)
      throws BusinessException {
    logger.info("batchDeleteAudio begin {}", JSON.toJSONString(audiClipVo));
    audioClipServiceImpl.batchDelete(audiClipVo.convertVOToPO());
    return ResultMessage.createSuccessResult(null);
  }

  /**
   * 音频信息分页查询
   * 
   * @Methods Name queryAudioClip
   * @param pageSize
   * @param pageNo
   * @param searchKey
   * @return ResultMessage
   */
  @GetMapping(value = "/search")
  public ResultMessage queryAudioClip(@RequestParam(name = "pageSize") int pageSize,
      @RequestParam(name = "pageNo") int pageNo, @RequestParam(name = "searchKey") String searchKey)
      throws BusinessException {
    logger.info("queryAudioClip begin pageSize pageNo searchKey {}", pageSize, pageNo, searchKey);
    AudioClipPageDto pageInfo = audioClipServiceImpl.qryPageAudioClip(pageSize, pageNo, searchKey);
    return ResultMessage.createSuccessResult(pageInfo);
  }

}
