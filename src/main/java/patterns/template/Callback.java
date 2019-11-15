package patterns.template;

public interface Callback<V,T> {
    public V doSomething(T t);
}
