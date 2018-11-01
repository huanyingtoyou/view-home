package com.lihy.view.sharding.config;

import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

import java.util.Collection;

/**
 * 分库算法
 * @author lihy
 * @date 2018/10/25
 */
public class DatabaseShardingAlgorithm implements PreciseShardingAlgorithm<Integer> {
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Integer> preciseShardingValue) {
        int size = collection.size();
        for (String each : collection) {
            if (each.endsWith(preciseShardingValue.getValue() % size + "")) {
                return each;
            }
        }
        throw new UnsupportedOperationException();
    }
}
