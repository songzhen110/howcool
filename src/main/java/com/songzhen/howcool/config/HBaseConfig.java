package com.songzhen.howcool.config;

import com.songzhen.howcool.service.HBaseService;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * HBase相关配置
 *
 * @author zifangsky
 * @version 1.0.0
 * @since 2018/7/12
 */
//@Configuration
public class HBaseConfig {
    @Value("${hbase.nodes}")
    private String nodes;

    @Value("${hbase.client.keyvalue.maxsize}")
    private String maxsize;

    @Bean
    public HBaseService getHBaseService() {
        org.apache.hadoop.conf.Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", nodes);
        conf.set("hbase.client.keyvalue.maxsize", maxsize);
        return new HBaseService(conf);
    }
}