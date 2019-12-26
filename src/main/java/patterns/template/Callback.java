package patterns.template;
public interface Callback<V,T> {
    V doSomething(T t);
}
