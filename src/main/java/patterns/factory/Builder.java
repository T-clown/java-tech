package patterns.factory;

import java.util.function.Supplier;

public interface Builder {
    void add(FruitType type, Supplier<Fruit> supplier);
}
