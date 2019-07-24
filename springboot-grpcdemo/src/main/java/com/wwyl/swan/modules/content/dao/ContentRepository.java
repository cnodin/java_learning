package com.wwyl.swan.modules.content.dao;

import com.wwyl.swan.modules.content.model.ContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 02/12/2017
 * Time: 15:58
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public interface ContentRepository extends JpaRepository<ContentEntity, Long> {

    ContentEntity findFirstByTaskId(String taskId);

    ContentEntity findByTaskIdAndAndContent(Long taskId, String content);

    List<ContentEntity> findByIdIn(List<Long> ids);
}
