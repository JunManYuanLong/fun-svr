package com.okay.family.common.threadpool;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义线程池,用例批量运行用例,非并发测试线程池
 */
public class OkayThreadPool {

    private static ThreadPoolExecutor executor = createPool();

    public static void addSyncWork(Runnable runnable) {
        executor.execute(runnable);
    }

    private static ThreadPoolExecutor createPool() {
        return new ThreadPoolExecutor(16, 100, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(1000));
    }

}
