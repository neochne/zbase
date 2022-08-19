package com.zbase.util;

import com.zbase.consumer.Consumer2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class CollectionUtils {

    private CollectionUtils() {
    }

    public static <K, V> List<K> mapKey2List(Map<K, V> map) {
        return new ArrayList<>(map.keySet());
    }

    public static <K, V> List<V> mapValue2List(Map<K, V> map) {
        return new ArrayList<>(map.values());
    }

    public static <K,V> void iterateMap(Map<K,V> map, Consumer2<K,V> consumer) {
        for (Map.Entry<K, V> entry : map.entrySet() ) {
            consumer.accept(entry.getKey(),entry.getValue());
        }
    }

}
