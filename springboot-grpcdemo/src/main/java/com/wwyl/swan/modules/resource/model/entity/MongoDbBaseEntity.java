package com.wwyl.swan.modules.resource.model.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MongoDbBaseEntity {
    String id;
    String tp;  // 0边缘流量，1中间缓存流量
    Long total;
}
