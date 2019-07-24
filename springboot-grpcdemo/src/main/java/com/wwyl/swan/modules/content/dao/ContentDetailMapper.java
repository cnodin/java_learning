package com.wwyl.swan.modules.content.dao;

import com.wwyl.lark.dao.mapper.SuperMapper;
import com.wwyl.swan.modules.content.model.ContentDetailEntity;
import com.wwyl.swan.modules.content.model.ContentEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface ContentDetailMapper extends SuperMapper<ContentDetailEntity> {

    /**
     * 更新内容详细的状态<br/>
     * Date：2018-5-8 15:08:07<br/>
     * @param map 更新的map。map传更新的条件和要更新的值，自定义。
     * */
    void updateContentDetailStatus(@Param("map") Map<String, Object> map);

    /**
     * 获取结束的内容任务<br/>
     * Date：2018-8-28 17:26:53<br/>
     * @return {List<ContentEntity>}
     * */
    List<ContentEntity> getFinishedContentList();

    /**
     * 根据ids组更新数据状态为结束<br/>
     * Date：2018-8-28 17:27:26<br/>
     * @param ids 要更新的id组
     * */
    void updateContentFinished(@Param("ids") List<Long> ids);
}