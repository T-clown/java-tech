package patterns.strategy.strategy;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class LoginResponse<V, T extends Serializable> {
    private T data;

    private V message;

    private String code;

    public LoginResponse(V message, T data, String code) {
        this.message = message;
        this.data = data;
        this.code = code;
    }

    public static <T extends Serializable> LoginResponse<String, T> success(String message, T data) {
        return new LoginResponse<>(message, data, null);
    }
}
