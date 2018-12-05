package patterns.factory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public enum FruitType {
    APPLE(1, "苹果"),
    ORANGE(2, "橘子"),
    BANANA(3, "香蕉"),
    WATERMELON(4, "西瓜");

    private short value;
    private String desc;

    FruitType(int value, String desc) {
        this.value = (short)value;
        this.desc = desc;
    }

    public short getValue() {
        return value;
    }

    public void setValue(short value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static List<FruitType> getAllTypes() {
        // return Stream.of(FruitType.values()).collect(Collectors.toList());
        return Arrays.asList(FruitType.values());
    }

    public static FruitType getTypeByDesc(String desc) {
        return Stream.of(FruitType.values())
            .filter(x -> x.getDesc().equals(desc)).findFirst()
            .orElse(null);
    }

    public static FruitType getTypeByValue(short value) {
        return Stream.of(FruitType.values())
            .filter(x -> x.getValue() == value).findFirst()
            .orElse(null);
    }

    public boolean isEqual(short type) {
        return this.value == type;
    }
    public boolean isEqual(int type) {
        return this.value == type;
    }
    public boolean isEqual(Short type) {
        return type != null && this.value == type.intValue();
    }

}
