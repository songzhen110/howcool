package com.songzhen.howcool.study;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FiveThread {

    static class MyThread extends Thread {
        @Override
        public void run() {

        }
    }

    static class MyRunnable implements Runnable{

        @Override
        public void run() {

        }
    }

    static class MyCallable implements Callable<String>{

        @Override
        public String call() throws Exception {
            return null;
        }
    }

    public static void main(String[] args) {
        //继承Thread类
        new MyThread().start();
        //实现Runnable接口
        new Thread(new MyRunnable()).start();

        //lamda
        new Thread(()->{ System.out.println("2121");});
        //
        new Thread(new FutureTask<String>(new MyCallable())).start();

        ExecutorService executorService = Executors.newFixedThreadPool(1,new MyThreadFactory());
        executorService.submit(new MyRunnable());


    }
}
