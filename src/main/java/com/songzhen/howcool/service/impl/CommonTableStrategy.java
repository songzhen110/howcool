package com.songzhen.howcool.service.impl;

import com.google.common.collect.Lists;
import com.songzhen.howcool.model.enums.ScrollTypeEnum;
import com.songzhen.howcool.model.enums.TaskEnum;
import com.songzhen.howcool.service.TableStrategy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author lucas
 * @date 2021/9/9 17:07
 */
@Service
public class CommonTableStrategy implements TableStrategy {

    @Override
    public List<String> createTask(TaskEnum taskEnum, Long jobRecordId) {
        return Lists.newArrayList();
    }

    @Override
    @PostConstruct
    public void init() {
        map.put(ScrollTypeEnum.COMMON.getType(), this);
    }
}
