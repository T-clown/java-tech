package patterns.factory;

import java.util.function.Consumer;

import patterns.entity.Apple;
import patterns.entity.Banana;
import patterns.entity.Orange;
import patterns.entity.Watermelon;

public class FactoryTest {
    public static void main(String[] args) {
        Consumer<Builder> consumer = builder -> {
            builder.put(FruitType.APPLE, Apple::new);
            builder.put(FruitType.BANANA, Banana::new);
            builder.put(FruitType.ORANGE, Orange::new);
            builder.put(FruitType.WATERMELON, Watermelon::new);
        };
        FruitFactory factory=FruitFactory.factory(consumer);
        Fruit fruit = factory.create(FruitType.APPLE);
        System.out.println(fruit.toString());
    }
}
