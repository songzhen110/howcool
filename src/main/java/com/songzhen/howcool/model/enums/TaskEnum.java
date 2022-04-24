package com.songzhen.howcool.model.enums;

import java.util.HashMap;
import java.util.Map;

public enum TaskEnum {

    A("a",ScrollTypeEnum.PRE.getType()),
    B("b",ScrollTypeEnum.COMMON.getType())
    ;

    /**
     * 逻辑模型
     */
    private String logicTable;

    /**
     * 滚动类型
     *
     */
    private Integer scrollType;


    private static Map<String, TaskEnum> taskEnumMap = new HashMap<>();

    static {
        for (TaskEnum rightTypeEnum : TaskEnum.values()) {
            taskEnumMap.put(rightTypeEnum.getLogicTable(), rightTypeEnum);
        }
    }

    TaskEnum(String logicTable,Integer scrollType) {
        this.logicTable = logicTable;
        this.scrollType=scrollType;
    }

    public void setLogicTable(String logicTable) {
        this.logicTable = logicTable;
    }

    public String getLogicTable() {
        return logicTable;
    }

   public Map<String, TaskEnum> getTaskEnumMap(){
        return taskEnumMap;
   }

    public Integer getScrollType() {
        return scrollType;
    }
}
