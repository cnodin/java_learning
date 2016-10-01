package org.pollux.nettythedefinitiveguid.ch02_nio_starting.bogus_aio;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by pollux on 16/8/28.
 */
public class TimeServerHandlerExecutePool {

  private ExecutorService executor;

  public TimeServerHandlerExecutePool(int maxPoolSize, int queueSize){
    executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), maxPoolSize, 120L,
            TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(queueSize));
  }

  public void execute(Runnable task){
    executor.execute(task);
  }

}
