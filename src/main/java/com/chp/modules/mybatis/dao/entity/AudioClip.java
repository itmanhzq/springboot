package com.chp.modules.mybatis.dao.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AudioClip implements Serializable {

  /**
   * @Field long serialVersionUID 
   */
  private static final long serialVersionUID = 6547804199171618578L;
  
  private String uuid;
  private String courtUuid;
  private int enableFlag;
  private String audioClipFormat;
  private String audioClipName;
  private String audioClipFileId;
  private String updateUser;
  private String createUser;
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",locale = "zh" , timezone="GMT+8")
  private Date createTime;
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",locale = "zh" , timezone="GMT+8")
  private Date updateTime;
  /**
   * @Return the String uuid
   */
  public String getUuid() {
    return uuid;
  }
  /**
   * @Param String uuid to set
   */
  public void setUuid(String uuid) {
    this.uuid = uuid;
  }
  
  /**
   * @Return the String courtUuid
   */
  public String getCourtUuid() {
    return courtUuid;
  }
  /**
   * @Param String courtUuid to set
   */
  public void setCourtUuid(String courtUuid) {
    this.courtUuid = courtUuid;
  }
  /**
   * @Return the int enableFlag
   */
  public int getEnableFlag() {
    return enableFlag;
  }
  /**
   * @Param int enableFlag to set
   */
  public void setEnableFlag(int enableFlag) {
    this.enableFlag = enableFlag;
  }
  /**
   * @Return the String audioClipFormat
   */
  public String getAudioClipFormat() {
    return audioClipFormat;
  }
  /**
   * @Param String audioClipFormat to set
   */
  public void setAudioClipFormat(String audioClipFormat) {
    this.audioClipFormat = audioClipFormat;
  }
  /**
   * @Return the String audioClipName
   */
  public String getAudioClipName() {
    return audioClipName;
  }
  /**
   * @Param String audioClipName to set
   */
  public void setAudioClipName(String audioClipName) {
    this.audioClipName = audioClipName;
  }
  /**
   * @Return the String audioClipFileId
   */
  public String getAudioClipFileId() {
    return audioClipFileId;
  }
  /**
   * @Param String audioClipFileId to set
   */
  public void setAudioClipFileId(String audioClipFileId) {
    this.audioClipFileId = audioClipFileId;
  }
  /**
   * @Return the String updateUser
   */
  public String getUpdateUser() {
    return updateUser;
  }
  /**
   * @Param String updateUser to set
   */
  public void setUpdateUser(String updateUser) {
    this.updateUser = updateUser;
  }
  /**
   * @Return the String createUser
   */
  public String getCreateUser() {
    return createUser;
  }
  /**
   * @Param String createUser to set
   */
  public void setCreateUser(String createUser) {
    this.createUser = createUser;
  }
  /**
   * @Return the Date createTime
   */
  public Date getCreateTime() {
    return createTime;
  }
  /**
   * @Param Date createTime to set
   */
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
  /**
   * @Return the Date updateTime
   */
  public Date getUpdateTime() {
    return updateTime;
  }
  /**
   * @Param Date updateTime to set
   */
  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }
  
  
}
