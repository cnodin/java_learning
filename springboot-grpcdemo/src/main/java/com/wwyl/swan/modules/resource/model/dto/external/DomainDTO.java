package com.wwyl.swan.modules.resource.model.dto.external;

import lombok.Getter;
import lombok.Setter;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 29/11/2017
 * Time: 16:44
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Getter
@Setter
public class DomainDTO {

    private String id;

    private String firstDomain;

    private String firstRootDomainId;

    private Long createdTime;

    private Long updatedTime;

}
