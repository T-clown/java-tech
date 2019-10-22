package patterns.strategy;

import java.util.HashMap;
import java.util.Map;

public class PromotionStrategyFactory {
    private static Map<String, Promotion> PROMOTION_STRATEGY_MAP = new HashMap<>();

    /**
     * 初始化类，存放具体的活动实例
     */
    static {
        PROMOTION_STRATEGY_MAP.put(PromotionEnum.DEFAULT.getCode(), new DefaultActivity());
        PROMOTION_STRATEGY_MAP.put(PromotionEnum.BUY_ONE_GET_TWO.getCode(), new ByOneGetTwo());
        PROMOTION_STRATEGY_MAP.put(PromotionEnum.RETURN_CASH.getCode(), new ReturnCash());
    }

    /**
     * 单例模式，私有构造方法
     */
    private PromotionStrategyFactory() {
    }

    public static Promotion getPromotion(String promotionKey) {
        // Promotion promotion = PROMOTION_STRATEGY_MAP.get(promotionKey);
        // return promotion == null ? PROMOTION_STRATEGY_MAP.get(PromotionEnum.DEFAULT.getCode()) : promotion;
        return PROMOTION_STRATEGY_MAP.getOrDefault(promotionKey, PROMOTION_STRATEGY_MAP.get(PromotionEnum.DEFAULT.getCode()));
    }

}
