package com.wwyl.swan.common.init;

import com.wwyl.swan.common.utils.ThreadPoolUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 初始化加载系统常用资源
 */
@Component
@Order(1)
@Slf4j
public class InitSourceRunner implements CommandLineRunner {

    @Value("${threadPoolExecutor.corePoolSize}")
    private Integer corePoolSize;

    @Value("${threadPoolExecutor.maximumPoolSize}")
    private Integer maximumPoolSize;

    @Value("${threadPoolExecutor.keepAliveTime}")
    private Integer keepAliveTime;


    @Override
    public void run(String... args) {
        // 初始线程池资源
        ThreadPoolUtils threadPoolUtils = ThreadPoolUtils.getInstance();
        ThreadPoolExecutor threadPoolExecutor = threadPoolUtils.getThreadPoolExecutor();
        if(threadPoolExecutor == null){
            // 线程队列使用SynchronousQueue
            threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime, TimeUnit.SECONDS,
                    new SynchronousQueue());
            threadPoolUtils.setThreadPoolExecutor(threadPoolExecutor);
        }
        threadPoolUtils.getThreadPoolInfo();
    }
}
