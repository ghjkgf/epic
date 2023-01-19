package com.example.asynch;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SyncExample {
    public static void doSomethingA() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("--- doSomethingA---");
    }

    public static void doSomethingB() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("--- doSomethingB---");
    }

    public static void main(String[] args) throws InterruptedException {
        syncDo();
        System.out.println("================================");
        asyncDo();
        asyncPool();
    }
    public static void syncDo(){
        long start = System.currentTimeMillis();
        // 1.执行任务A
        doSomethingA();
        // 2.执行任务B
        doSomethingB();
        System.out.println(System.currentTimeMillis() - start);
    }
    public static void asyncDo() throws InterruptedException {
        long start = System.currentTimeMillis();
        // 1.开启异步单元执行任务A
        Thread threadA = new Thread(() -> {
            try {
                doSomethingA();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "threadA");
        threadA.start();
        // 2.执行任务B
        doSomethingB();
        // 3.同步等待线程A运行结束
        // join方法的主要作用就是同步，它可以使得线程之间的并行执行变为串行执行。
        // 在A线程中调用了B线程的join()方法时，表示只有当B线程执行完毕时，A线程才能继续执行.
        threadA.join();
        System.out.println(System.currentTimeMillis() - start);
    }

    public static void asyncDo2() throws InterruptedException {
        long start = System.currentTimeMillis();
        // 1.开启异步单元执行任务A
        Thread thread = new Thread("threadA") {
            @Override
            public void run() {
                try {
                    doSomethingA();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        // 2.执行任务B
        doSomethingB();
        // 3.同步等待线程A运行结束
        thread.join();
        System.out.println(System.currentTimeMillis() - start);
    }

    // 0自定义线程池
    private final static int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
    private final static ThreadPoolExecutor POOL_EXECUTOR
            = new ThreadPoolExecutor(AVAILABLE_PROCESSORS,
            AVAILABLE_PROCESSORS * 2,
            1,
            TimeUnit.MINUTES,
            new LinkedBlockingQueue<>(5),
            new ThreadPoolExecutor.CallerRunsPolicy());
    public static void asyncPool() throws InterruptedException {
        long start = System.currentTimeMillis();
        // 1.开启异步单元执行任务A
        POOL_EXECUTOR.execute(() -> {
            try {
                doSomethingA();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        // 2.执行任务B
        doSomethingB();
        // 3.同步等待线程A运行结束
        System.out.println(System.currentTimeMillis() - start);
        // 4.挂起当前线程
        System.out.println(Thread.currentThread().getName());
        Thread.currentThread().join();
    }

    public static void asyncPool2() throws InterruptedException{
        long start = System.currentTimeMillis();
        // 1.开启异步单元执行任务A
        POOL_EXECUTOR.execute(() -> {
            try {
                doSomethingA();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        // 2.执行任务B
        POOL_EXECUTOR.execute(() -> {
            try {
                doSomethingB();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        // 3.同步等待线程A运行结束
        System.out.println(System.currentTimeMillis() - start);
        // 4.挂起当前线程
        Thread.currentThread().join();
    }
}