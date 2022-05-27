package com.zbase.util;

import java.util.List;
import java.util.Map;

public final class CollectionUtils {

    private CollectionUtils() {
    }

    public static <K, V> void mapKey2List(Map<K, V> map, List<K> keyList) {
        keyList.addAll(map.keySet());
    }

    public static <K, V> void mapValue2List(Map<K, V> map, List<V> valueList) {
        valueList.addAll(map.values());
    }

    public static <K, V> void mapKeyValue2List(Map<K, V> map, List<K> keyList,List<V> valueList) {
        for (Map.Entry<K, V> entry : map.entrySet() ) {
            keyList.add(entry.getKey());
            valueList.add(entry.getValue());
        }
    }

}
