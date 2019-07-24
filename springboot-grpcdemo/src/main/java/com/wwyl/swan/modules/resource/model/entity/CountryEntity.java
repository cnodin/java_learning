package com.wwyl.swan.modules.resource.model.entity;

import com.wwyl.lark.core.entity.SimpleUUIDEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Getter
@Setter
@Table(name="data_country")
@Entity
public class CountryEntity extends SimpleUUIDEntity {
    private String name;
    //private String enName;
    @Column(name = "short_name")
    private String shortName;
    @Transient
    private String value = "0";
}
