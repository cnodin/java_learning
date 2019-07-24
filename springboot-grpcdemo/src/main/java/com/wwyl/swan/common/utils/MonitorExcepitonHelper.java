package com.wwyl.swan.common.utils;

import com.wwyl.lark.model.MonitorExceptionDTO;
import com.wwyl.lark.util.monitor.MonitorExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class MonitorExcepitonHelper {

    @Autowired
    private MonitorExceptionUtil monitorExceptionUtil;

    @Async
    public void sendAsyncExceptionAlarm(String alarmName, MonitorExceptionDTO monitorExceptionDTO) {
        monitorExceptionUtil.sendSyncExceptionAlarm(alarmName, monitorExceptionDTO);
    }

}
