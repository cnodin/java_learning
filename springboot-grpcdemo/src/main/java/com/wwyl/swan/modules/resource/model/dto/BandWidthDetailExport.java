package com.wwyl.swan.modules.resource.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.jeecgframework.poi.excel.annotation.Excel;

@Getter
@Setter
public class BandWidthDetailExport implements Comparable<BandWidthDetailExport>{

    @Excel(name = "域名", orderNum = "0", width = 25.0d)
    private String domain;

    @Excel(name = "时间", orderNum = "1", width = 25.0d)
    private String time;

    @Excel(name = "流量(MB)", orderNum = "2", width = 25.0d)
    private String flows;

    @Excel(name = "带宽(Mpbs)", orderNum = "3", width = 25.0d)
    private String bandWidths;

    @Excel(name = "请求数", orderNum = "4", width = 25.0d)
    private String requests;

    public int compareTo(BandWidthDetailExport bandWidthExport) {
        return this.getTime().compareTo(bandWidthExport.getTime());
    }
}
