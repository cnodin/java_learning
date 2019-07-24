package com.wwyl.swan.common.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName: ThreadPoolUtils
 * @Description: 线程池，随着应用启动而启动
 * @author: miyi.tang
 */
@Slf4j
@Setter
@Getter
public class ThreadPoolUtils {

    private static ThreadPoolUtils instance = null;

    // 线程池对象
    private ThreadPoolExecutor threadPoolExecutor;

    private ThreadPoolUtils() {

    }

    public static synchronized ThreadPoolUtils getInstance() {
        if (instance == null) {
            instance = new ThreadPoolUtils();
        }
        return instance;
    }

    /**
     * 打印线程池信息
     */
    public void getThreadPoolInfo() {
        log.info("核心线程数:{}",threadPoolExecutor.getCorePoolSize());
        log.info("线程池数:{}",threadPoolExecutor.getPoolSize());
        log.info("队列任务数:{}",threadPoolExecutor.getQueue().size());
    }

}
