package concurrent.thread;

public class Exam {
    public static void main(String[] args) {

        //brea();
        //System.out.println();
        //con();
        //System.out.println();
        //retry();
    }

    private static void brea() {
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

    private static void con() {
        int count = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                count++;
                if (count == 3) {
                    continue;
                }
                System.out.print(count + " ");
            }
        }
    }

    private static void retry() {
        int count = 0;
        retry:
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                count++;
                if (count == 3) {
                    //跳出所有循环
                    break retry;
                }
                System.out.print(count + " ");
            }
        }
    }
}
