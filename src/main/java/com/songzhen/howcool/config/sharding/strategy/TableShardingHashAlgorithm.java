package com.songzhen.howcool.config.sharding.strategy;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * 数据表分片算法
 *
 * @author lucas
 */
@Component
public class TableShardingHashAlgorithm implements PreciseShardingAlgorithm<Long> {

    private static int DATABASE_COUNT;

    private static int TABLE_COUNT;

    @Value("${datasource.names.count}")
    public void setDatabaseCount(int databaseCount) {
        TableShardingHashAlgorithm.DATABASE_COUNT = databaseCount;
    }

    @Value("${datasource.tables.count}")
    public void setTableCount(int tableCount) {
        TableShardingHashAlgorithm.TABLE_COUNT = tableCount;
    }

    public TableShardingHashAlgorithm() {
        //DATABASE_COUNT = Objects.isNull(DATABASE_COUNT)?1:DATABASE_COUNT;
        //TABLE_COUNT = Objects.isNull(TABLE_COUNT)?1:TABLE_COUNT;
    }

    public static int getIndex(long value) {
        return DefaultShardingHash.shardingTBValue(value,DATABASE_COUNT,TABLE_COUNT);
    }

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        List<String> tables = new ArrayList<>(collection);

        return tables.get(getIndex(preciseShardingValue.getValue()));
    }

}
