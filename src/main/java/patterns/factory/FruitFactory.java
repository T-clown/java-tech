package patterns.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public interface FruitFactory {

    Fruit create(FruitType name);

    static FruitFactory factory(Consumer<Builder> consumer) {
        Map<FruitType, Supplier<Fruit>> map = new HashMap<>();
        consumer.accept(map::put);
        return name -> map.get(name).get();
    }

}
