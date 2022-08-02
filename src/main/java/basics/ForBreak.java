package basics;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class ForBreak implements Serializable {

    public static void main(String[] args) {
//        new HashMap();
//        new ConcurrentHashMap<>();
//        breakFun();
//        System.out.println();
//        continueFun();
//        System.out.println();
//        retryFun();
        Arrays.asList(1,2,3,4,5).forEach(x-> {
            if(x==4){
                return;
            }
            System.out.println(x);;
        });
        System.out.println(11111111);
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
        retry:
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                count++;
                if (count == 3) {
                    //跳出所有循环
                    //break retry;
                    //跳出当前循环(跳到外部循环)
                    continue retry;
                }
                System.out.print(count + " ");
            }
        }
    }
}
