package com.wwyl.swan.modules.content.dao;

import com.wwyl.lark.dao.mapper.SuperMapper;
import com.wwyl.swan.modules.content.model.ContentFeedBackEntity;
import com.wwyl.swan.modules.content.model.ContentResultDto;
import com.wwyl.swan.modules.content.model.ContentResultRequestDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * 公共view模版
 *
 * @author my.tang
 * @date 2018-04-11 20:50:02
 */
public interface ContentFeedbackMapper extends SuperMapper<ContentFeedBackEntity> {

    /**
     * 根据请求条件获取反馈结果列表，并返回结果dto
     * Date：2018-5-3 15:22:26
     * @param contentResultRequestDto 请求条件
     * @return {List<ContentResultDto>} 符合条件的统计结果列表
     * */
    List<ContentResultDto> getContentFeedbackList (@Param("dto") ContentResultRequestDto contentResultRequestDto);
    /**
     * 根据请求条件获取反馈结果总数
     * Date：2018-5-3 15:22:26
     * @param contentResultRequestDto 请求条件
     * @return {Long}
     * */
    Long countContentFeedback (@Param("dto") ContentResultRequestDto contentResultRequestDto);
}