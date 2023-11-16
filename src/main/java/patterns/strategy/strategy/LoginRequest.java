package patterns.strategy.strategy;

import lombok.Data;

@Data
public class LoginRequest {

    private LoginType loginType;

    private Long userId;
}
