package com.wwyl.swan.modules.content.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wwyl.swan.modules.content.dao.ContentServerMapper;
import com.wwyl.swan.modules.content.model.ContentServerEntity;
import com.wwyl.swan.modules.content.service.ContentServerService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 内容操作关联的服务器列表 服务实现类
 * </p>
 *
 * @author sh.zhang
 * @since 2018-08-28 10:29:13
 */
@Service
public class ContentServerServiceImpl extends ServiceImpl<ContentServerMapper, ContentServerEntity> implements ContentServerService {
	
}
