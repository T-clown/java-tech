package patterns.strategy.strategy;

import java.io.Serializable;

/**
 * 原文连接：https://www.51cto.com/article/643998.html
 *
 * @param <T>
 */
public interface LoginHandler<T extends Serializable> {

    /**
     * 获取登录类型
     *
     * @return
     */
    LoginType getLoginType();

    /**
     * 登录
     *
     * @param request
     * @return
     */
    LoginResponse<String, T> handleLogin(LoginRequest request);
}
