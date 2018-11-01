package com.lihy.view.sharding.config;

import com.google.common.collect.Range;
import io.shardingsphere.api.algorithm.sharding.RangeShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.RangeShardingAlgorithm;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author lihy
 * @date 2018/10/25
 */
public class TableRangeShardingAlgorithm implements RangeShardingAlgorithm<Long> {
    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Long> rangeShardingValue) {
        int size = collection.size();
        Collection<String> stringCollection = new ArrayList<>();
        Range valueRange = rangeShardingValue.getValueRange();
        for (Long i = (Long) valueRange.lowerEndpoint(); i <= (Long) valueRange.upperEndpoint(); i++) {
            for (String each : collection) {
                if (each.endsWith(i % size + "")) {
                    stringCollection.add(each);
                }
            }
        }
        return stringCollection;
    }
}
