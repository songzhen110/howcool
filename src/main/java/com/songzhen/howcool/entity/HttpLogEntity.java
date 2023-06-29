package com.songzhen.howcool.entity;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Map;

@Data
@RequestScope
@Component
public class HttpLogEntity {

    private String path;

    private Map<String,String[]> params;

    private Object req;

    private Object res;
}
