package patterns.strategy;

/**
 * 原创链接：https://mp.weixin.qq.com/s/N0j-xM6WxhTcdJAmulSOaA
 */
public interface Promotion<T,R> {
    R execute(T t);
}
