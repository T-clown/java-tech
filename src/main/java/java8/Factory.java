package java8;
@FunctionalInterface
public interface Factory<T> {
    T product();
}
