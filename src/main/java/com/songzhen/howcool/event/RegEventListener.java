package com.songzhen.howcool.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class RegEventListener implements ApplicationListener<RegUserEvent> {

    @Override
    public void onApplicationEvent(RegUserEvent event) {
        System.out.println("接收到 \t"+event.getUid() + "\t 注册成功事件");
    }
}
