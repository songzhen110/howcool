package com.songzhen.howcool.event;


import org.springframework.context.ApplicationEvent;

public class RegUserEvent extends ApplicationEvent {

    private String uId;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public RegUserEvent(Object source, String uId) {
        super(source);
        this.uId = uId;
    }

    public String getUid() {
        return uId;
    }
}
