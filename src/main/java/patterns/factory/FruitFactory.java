package patterns.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public interface FruitFactory {
    /**
     * 无泛型版本的Function
     * @param type
     * @return
     */
    Fruit create(FruitType type);

    static FruitFactory factory(Consumer<Builder> consumer) {
        Map<FruitType, Supplier<Fruit>> map = new HashMap<>();
        consumer.accept(map::put);
        return name -> map.get(name).get();
    }

}
