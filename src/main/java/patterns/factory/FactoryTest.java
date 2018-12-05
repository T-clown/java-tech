package patterns.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.google.common.collect.Maps;
import patterns.entity.Apple;
import patterns.entity.Banana;
import patterns.entity.Orange;
import patterns.entity.Watermelon;

public class FactoryTest {
    public static void main(String[] args) {
        Consumer<Builder> consumer = builder -> {
            builder.add(FruitType.APPLE, Apple::new);
            builder.add(FruitType.BANANA, Banana::new);
            builder.add(FruitType.ORANGE, Orange::new);
            builder.add(FruitType.WATERMELON, Watermelon::new);
        };
        FruitFactory factory=FruitFactory.factory(consumer);
        Fruit fruit = factory.create(FruitType.APPLE);
        System.out.println(fruit.toString());
    }
}
