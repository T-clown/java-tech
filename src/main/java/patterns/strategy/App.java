package patterns.strategy;

import java.math.BigDecimal;

public class App {
    public static void main(String[] args) {
        //默认
        String promotionKey="DEFAULT";
        Promotion<String, Boolean>  promotion=PromotionStrategyFactory.getPromotion(promotionKey);
        Boolean execute = promotion.execute(promotionKey);
        //买一送一
        String promotionKey2="BUY_ONE_GET_TWO";
        Promotion<String, String> promotion2=PromotionStrategyFactory.getPromotion(promotionKey2);
        String execute1 = promotion2.execute(promotionKey2);
        //返现
        String promotionKey3="RETURN_CASH";
        Promotion<String, BigDecimal> promotion3=PromotionStrategyFactory.getPromotion(promotionKey3);
        BigDecimal execute2 = promotion3.execute(promotionKey3);

        System.out.println();

        //NO
//        String promotionKey4="NO";
//        Promotion<String, Boolean> promotion4=PromotionStrategyFactory.getPromotion(promotionKey4);
//        promotion4.execute(promotionKey4);
    }
}
