package patterns.factory;

import java.util.function.Supplier;

/**
 * 无泛型的BiConsumer
 */
public interface Builder {
    void put(FruitType type, Supplier<Fruit> supplier);
}
