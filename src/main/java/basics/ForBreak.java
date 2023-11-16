package basics;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class ForBreak implements Serializable {

    public static void main(String[] args) {
//        new HashMap();
//        new ConcurrentHashMap<>();
        breakFun();
//        System.out.println();
//        continueFun();
//        System.out.println();
        retryFun();

    }

    private static void breakFun() {
        int count = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                count++;
                if (count == 3) {
                    //跳出当前循环(跳到外部循环)
                    break;
                }
                System.out.print(count + " ");
            }
        }
    }

    private static void continueFun() {
        int count = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                count++;
                if (count == 3) {
                    //跳过当前步骤
                    continue;
                }
                System.out.print(count + " ");
            }
        }
    }

    private static void retryFun() {
        int count = 0;
        a:
        for (int i = 1; i <= 2; i++) {
            for (int j = 1; j <= 5; j++) {
                count++;
                if (count == 3) {
                    //跳出所有循环
                    break a;
                    //跳出当前循环(跳到外部循环)
                    //continue retry;
                }
                System.out.print(count + " ");
            }
        }
        System.out.println("结束");

    }
}
