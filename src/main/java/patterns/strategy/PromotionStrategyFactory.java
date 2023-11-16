package patterns.strategy;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.Function;
import org.apache.poi.ss.formula.functions.T;

import java.util.HashMap;
import java.util.Map;
@Slf4j
public class PromotionStrategyFactory {
    private final static Map<String, Promotion> PROMOTION_STRATEGY_MAP = new HashMap<>();

    /**
     * 初始化类，存放具体的活动实例
     */
    static {
        log.info("执行静态代码块");
        PROMOTION_STRATEGY_MAP.put(PromotionEnum.DEFAULT.getCode(), new DefaultActivity());
        PROMOTION_STRATEGY_MAP.put(PromotionEnum.BUY_ONE_GET_TWO.getCode(), new ByOneGetTwo());
        PROMOTION_STRATEGY_MAP.put(PromotionEnum.RETURN_CASH.getCode(), new ReturnCash());
    }

    /**
     * 单例模式，私有构造方法
     */
    private PromotionStrategyFactory() {
        log.info("执行构造方法");
    }

    public static <T,R> Promotion<T,R> getPromotion(String promotionKey) {
        log.info("获取策略类");
        // Promotion promotion = PROMOTION_STRATEGY_MAP.get(promotionKey);
        // return promotion == null ? PROMOTION_STRATEGY_MAP.get(PromotionEnum.DEFAULT.getCode()) : promotion;
        return PROMOTION_STRATEGY_MAP.get(promotionKey);
    }

}
