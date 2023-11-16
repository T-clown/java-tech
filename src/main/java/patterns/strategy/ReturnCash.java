package patterns.strategy;

import java.math.BigDecimal;

public class ReturnCash implements Promotion<String, BigDecimal> {
    @Override
    public BigDecimal execute(String param) {
        System.out.println("返现5元");
        return new BigDecimal("5");
    }
}
