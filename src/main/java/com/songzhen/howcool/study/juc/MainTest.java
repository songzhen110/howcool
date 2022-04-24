package com.songzhen.howcool.study.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * https://www.cnblogs.com/thisiswhy/p/13948055.html
 */
public class MainTest {
    //是否可以提交
    public static volatile boolean IS_OK = true;

    public static void main(String[] args) {
        //子线程等待主线程通知
        CountDownLatch mainMonitor = new CountDownLatch(1);
        int threadCount = 5;
        CountDownLatch childMonitor = new CountDownLatch(threadCount);
        //子线程运行结果
        List<Boolean> childResponse = new ArrayList<Boolean>();
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < threadCount; i++) {
            // int finalI = i;
            executor.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "：开始执行");
                    // if (finalI == 4) {
                    // throw new Exception("出现异常");
                    // }
                    TimeUnit.MILLISECONDS.sleep(1000);
                    childResponse.add(Boolean.TRUE);
                    childMonitor.countDown();
                    System.out.println(Thread.currentThread().getName() + "：准备就绪,等待其他线程结果,判断是否事务提交");
                    mainMonitor.await();
                    if (IS_OK) {
                        System.out.println(Thread.currentThread().getName() + "：事务提交");
                    } else {
                        System.out.println(Thread.currentThread().getName() + "：事务回滚");
                    }
                } catch (Exception e) {
                    childResponse.add(Boolean.FALSE);
                    childMonitor.countDown();
                    System.out.println(Thread.currentThread().getName() + "：出现异常,开始事务回滚");
                }
            });
        }
        //主线程等待所有子线程执行response
        try {
            childMonitor.await();
            TimeUnit.SECONDS.sleep(10);
            for (Boolean resp : childResponse) {
                if (!resp) {
                    //如果有一个子线程执行失败了，则改变mainResult，让所有子线程回滚
                    System.out.println(Thread.currentThread().getName()+":有线程执行失败，标志位设置为false");
                    IS_OK = false;
                    break;
                }
            }
            //主线程获取结果成功，让子线程开始根据主线程的结果执行（提交或回滚）
            mainMonitor.countDown();
            //为了让主线程阻塞，让子线程执行。
            Thread.currentThread().join();
            executor.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}