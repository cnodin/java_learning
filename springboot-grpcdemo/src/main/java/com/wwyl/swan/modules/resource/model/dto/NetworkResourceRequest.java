package com.wwyl.swan.modules.resource.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 25/11/2017
 * Time: 09:48
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Getter
@Setter
public class NetworkResourceRequest {

    @NotNull
    private String domains;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    /**
     * 产品类型cdn or una
     */
    private String type;

    /**
     * 产品类型code
     */
    private String productLineCode;

    /**
     * 产品线id
     */
    private Long productLineId;

    /**
     * 时间粒度
     */
    private String timeGranularity;
}
