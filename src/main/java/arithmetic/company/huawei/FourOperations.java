package arithmetic.company.huawei;

import java.util.Scanner;
import java.util.Stack;

/**
 * 四则运算
 */
public class FourOperations {

    public static void main(String[] args) {
        //fourOperations();
        String a = "3+2*{1+2*[-4/(8-6)+7]}";
        a = a.replace("[", "(");
        a = a.replace("{", "(");
        a = a.replace("]", ")");
        a = a.replace("}", ")");
    }

    private static void fourOperations() {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int n = s.length();
        int num1 = 0;
        int o1 = 1;
        int num2 = 1;
        int o2 = 1;
        Stack<Integer> stk = new Stack<>();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                //遇到数字则定义num2
                int cur = 0;
                while (i < n && Character.isDigit(s.charAt(i))) {
                    cur = cur * 10 + (s.charAt(i) - '0');
                    i++;
                }
                i--;
                num2 = o2 == 1 ? num2 * cur : num2 / cur;
            } else if (c == '*' || c == '/') {
                //遇到×÷定义o2
                o2 = c == '*' ? 1 : -1;
            } else if (c == '(' || c == '{' || c == '[') {
                //遇到括号则保存当前结果，并初始化
                stk.push(num1);
                stk.push(o1);
                stk.push(num2);
                stk.push(o2);

                num1 = 0;
                o1 = 1;
                num2 = 1;
                o2 = 1;
            } else if (c == '+' || c == '-') {
                //遇到加减，说明可以开始计算，计算num1并对定义其他几个变量
                if (c == '-' && (i == 0 || s.charAt(i - 1) == '(' || s.charAt(i - 1) == '[' || s.charAt(i - 1) == '{')) {
                    o1 = -1;
                    continue;
                }
                num1 = num1 + o1 * num2;
                o1 = (c == '+' ? 1 : -1);
                num2 = 1;
                o2 = 1;
            } else {
                //遇到右括号，则出栈，并计算num2
                int cur = num1 + o1 * num2;
                o2 = stk.pop();
                num2 = stk.pop();
                o1 = stk.pop();
                num1 = stk.pop();
                num2 = o2 == 1 ? num2 * cur : num2 / cur;
            }
        }
        System.out.println(num1 + o1 * num2);
    }


}
