package com.songzhen.howcool.service;

import com.songzhen.howcool.model.enums.TaskEnum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lucas
 * @date 2021/9/9 17:06
 */
public interface TableStrategy {
    /**
     * 扫描表策略
     */
    Map<Integer, TableStrategy> map = new HashMap<>();

    /**
     * 根据不同的滚表策略创建任务
     * @return
     */
    List<String> createTask(TaskEnum taskEnum, Long jobRecordId);

    void init();
}
