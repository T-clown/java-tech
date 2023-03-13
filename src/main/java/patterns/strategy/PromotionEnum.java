package patterns.strategy;

public enum PromotionEnum implements IEum {
    /**
     * 默认
     */
    DEFAULT("DEFAULT", "默认没有活动"),
    /**
     * 买一送一
     */
    BUY_ONE_GET_TWO("BUY_ONE_GET_TWO", "买一送一活动"),
    /**
     * 返现
     */
    RETURN_CASH("RETURN_CASH", "返现活动");

    private final String code;

    private final String desc;

    PromotionEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
