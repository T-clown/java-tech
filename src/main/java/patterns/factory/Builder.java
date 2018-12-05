package patterns.factory;

import java.util.function.Supplier;

public interface Builder {
    void put(FruitType type, Supplier<Fruit> supplier);
}
