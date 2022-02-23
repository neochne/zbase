package com.zbase.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class AsyncUtils {

    private AsyncUtils() { }

    private static ExecutorService sSingleExecutorService;

    public static ExecutorService getSingleExecutorService() {
        if (sSingleExecutorService == null) {
            sSingleExecutorService = Executors.newSingleThreadExecutor();
        }
        return sSingleExecutorService;
    }

}
