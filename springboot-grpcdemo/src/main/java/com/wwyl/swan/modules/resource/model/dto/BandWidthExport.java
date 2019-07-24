package com.wwyl.swan.modules.resource.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.jeecgframework.poi.excel.annotation.Excel;

@Getter
@Setter
public class BandWidthExport implements Comparable<BandWidthExport>{

    @Excel(name = "时间", orderNum = "0", width = 25.0d)
    private String time;

    @Excel(name = "带宽值(Mpbs)", orderNum = "1", width = 25.0d)
    private String bandWidth;

    public int compareTo(BandWidthExport bandWidthExport) {
        return this.getTime().compareTo(bandWidthExport.getTime());
    }
}
