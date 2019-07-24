package com.wwyl.swan.modules.content.service;

import com.wwyl.lark.web.model.PageDto;
import com.wwyl.swan.modules.content.model.*;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 02/12/2017
 * Time: 16:04
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public interface ContentService {

    ContentEntity save(ContentEntity contentEntity);

    /**
     * 保存预取内容反馈结果<br/>
     * 保存之前将对反馈结果的md5与原md5进行比对，并将比对结果存储<br/>
     * 如果反馈的md5为null或空串，则标记为检测成功。<br/>
     * 只有当反馈的md5与用户输入的md5不匹配时，才标记为检测失败<br/>
     * Date：2018-4-25 15:08:41<br/>
     * @param contentFeedBackEntity 反馈内容对象
     * @return {ContentFeedBackEntity}
     * */
    ContentFeedBackEntity saveContentFeedback(ContentFeedBackEntity contentFeedBackEntity);

    /**
     * 获取预取结果<br/>
     * 如果反馈的md5为null或空串，则标记为检测成功。<br/>
     * 只有当反馈的md5与用户输入的md5不匹配时，才标记为检测失败<br/>
     * Date：2018-4-25 15:10:29
     * @return {ContentResultDto} 预取结果dto
     * */
    PageDto getContentResult (ContentResultRequestDto contentResultRequestDto);

    List<ContentEntity> findByIdIn(List<Long> ids);

    /**
     * 发送内容任务到kiwi
     */
    void sendContentKiwi(ContentEntity contentEntity);

    /**
     * 处理ftp内容接口，生成content，content_detail，返回不存在的域名
     */
    Map<String, Object> ContentActionFtp(ContentDto contentFtpDto);

    /**
     * 根据一级域名从turkey获取对应二级域名和主机数量
     */
    SecondDomainDto loadSecondDomainDto(SwanTaskFirstDomainRequest swanTaskFirstDomainRequest);

    /**
     * 对外客户内容预取
     */
    List<ContentEntity> cdnContentFetch(CdnContentFetchDto cdnContentFetchDto);

    /**
     * 对外客户内容推送
     */
    ContentEntity cdnContentPush(CdnContentPushDto cdnContentPushDto);
}
