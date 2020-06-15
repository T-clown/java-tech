package concurrent;

import com.alibaba.fastjson.JSON;

public class JucApp {
    public static void main(String[] args) throws InterruptedException {
        String[] split = "aaa".split(",");
        System.out.println(JSON.toJSONString(split));
    }
}
