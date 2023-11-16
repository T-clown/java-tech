package patterns.proxy.jdk.proxy;

import java.util.AbstractMap;
import java.util.Map;
import java.util.function.Function;

public class MapUtil {
    public static <K, V> V computeIfAbsent(Map<K, V> map, K key, Function<K, V> mappingFunction) {
        V value = map.get(key);
        if (value != null) {
            return value;
        } else {
            mappingFunction.getClass();
            return map.computeIfAbsent(key, mappingFunction::apply);
        }
    }

    public static <K, V> Map.Entry<K, V> entry(K key, V value) {
        return new AbstractMap.SimpleImmutableEntry(key, value);
    }

    private MapUtil() {
    }
}
