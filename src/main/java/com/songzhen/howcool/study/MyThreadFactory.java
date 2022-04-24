package com.songzhen.howcool.study;

import java.util.concurrent.ThreadFactory;

public class MyThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {

        return new Thread("aa");
    }
}
