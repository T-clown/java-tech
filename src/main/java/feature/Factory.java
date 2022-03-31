package feature;
@FunctionalInterface
public interface Factory<T> {
    T product();
}
