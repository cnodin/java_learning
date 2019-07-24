package com.wwyl.swan.modules.resource.model.entity;

import com.wwyl.lark.core.entity.SimpleUUIDEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Table(name="data_isp")
@Entity
public class IspEntity extends SimpleUUIDEntity{
    private String name;
    private String enName;
    private String shortName;
}
