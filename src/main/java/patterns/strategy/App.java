package patterns.strategy;

public class App {
    public static void main(String[] args) {
        //默认
        String promotionKey="DEFAULT";
        Promotion promotion=PromotionStrategyFactory.getPromotion(promotionKey);
        promotion.execute();
        //买一送一
        String promotionKey2="BUY_ONE_GET_TWO";
        Promotion promotion2=PromotionStrategyFactory.getPromotion(promotionKey2);
        promotion2.execute();
        //返现
        String promotionKey3="RETURN_CASH";
        Promotion promotion3=PromotionStrategyFactory.getPromotion(promotionKey3);
        promotion3.execute();

        //NO
        String promotionKey4="NO";
        Promotion promotion4=PromotionStrategyFactory.getPromotion(promotionKey4);
        promotion4.execute();
    }
}
