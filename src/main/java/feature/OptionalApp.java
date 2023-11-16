package feature;

import java.util.Arrays;
import java.util.Optional;

public class OptionalApp {

    public static void main(String[] args) {
        Optional<Integer> any = Arrays.asList(1, 2, 3).stream().filter(x -> x > 3).findAny();
        any.orElseThrow(() -> new IllegalStateException());
        Integer integer = any.get();
    }
}
