package com.songzhen.howcool.task;

import java.util.concurrent.Callable;

public class DemoTaskThread implements Callable {

    private double latitude;
    private double longitude;

    public DemoTaskThread(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public Object call() throws Exception {
        return latitude+longitude;
    }
}
