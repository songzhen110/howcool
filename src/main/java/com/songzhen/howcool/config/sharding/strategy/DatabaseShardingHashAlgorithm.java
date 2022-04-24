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
 * 数据库分片算法
 *
 * @author lucas
 */
@Component
public class DatabaseShardingHashAlgorithm implements PreciseShardingAlgorithm<Long> {

    @Value("${datasource.names.count}")
    public void setDatabaseCount(int databaseCount) {
        DatabaseShardingHashAlgorithm.DATABASE_COUNT = databaseCount;
    }


    private static int DATABASE_COUNT;

    public DatabaseShardingHashAlgorithm() {
        //DATABASE_COUNT = Objects.isNull(DATABASE_COUNT)?1:DATABASE_COUNT;
    }



    public static int getIndex(long value) {
        return DefaultShardingHash.shardingDBValue(value,DATABASE_COUNT);
    }

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        List<String> names = new ArrayList<>(collection);

        return names.get(getIndex(preciseShardingValue.getValue()));
    }

}
