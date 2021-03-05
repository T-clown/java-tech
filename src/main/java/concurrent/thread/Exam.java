package concurrent.thread;

public class Exam {
    public static void main(String[] args) {
        breakFun();
        System.out.println();
        continueFun();
        System.out.println();
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
        retry:
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                count++;
                if (count == 3) {
                    //跳出所有循环
                    //break retry;
                    ////跳出当前循环(跳到外部循环)
                    continue retry;
                }
                System.out.print(count + " ");
            }
        }
    }
}
