package patterns.builder;

import com.alibaba.fastjson.JSON;

public class App {
    public static void main(String[] args) {
        Phone phone = Phone.builder().id(1).brand("Apple").build();

        System.out.println(JSON.toJSONString(phone));
    }
}