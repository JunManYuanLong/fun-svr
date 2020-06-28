package com.okay.family.common;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class OkayThreadPool {

    private static ThreadPoolExecutor executor = createPool();

    public static void addSyncWork(Runnable runnable) {
        executor.execute(runnable);
    }

    private static ThreadPoolExecutor createPool() {
        return new ThreadPoolExecutor(5, 50, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(1000));

    }

}
