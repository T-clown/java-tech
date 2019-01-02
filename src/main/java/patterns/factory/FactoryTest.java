package patterns.factory;

import java.util.Date;
import java.util.function.Consumer;

import patterns.entity.Apple;
import patterns.entity.Banana;
import patterns.entity.Orange;
import patterns.entity.Watermelon;

public class FactoryTest {
    public static void main(String[] args) {
        //factoryTest();
        Date start=new Date();
        start.setDate(250);
        Date end=new Date();
        end.setDate(30);
        System.out.println(FruitType.BANANA.getDesc((short)1,start,end));
    }

    private static void factoryTest() {
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
