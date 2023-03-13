package arithmetic.heap_stack_queue;

import java.util.Stack;

/**
 * 给出一个仅包含字符'(',')','{','}','['和']',的字符串，判断给出的字符串是否是合法的括号序列
 * 括号必须以正确的顺序关闭，"()"和"()[]{}"都是合法的括号序列，但"(]"和"([)]"不合法。
 */
public class ValidBracket {
    public static void main(String[] args) {
        String a = "()[]{}";
        System.out.println(isValid(a));
    }


    public static boolean isValid(String s) {
        //辅助栈
        Stack<Character> st = new Stack<>();
        //遍历字符串
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                //遇到左小括号
                //期待遇到右小括号
                st.push(')');
            } else if (s.charAt(i) == '[') {
                //遇到左中括号
                //期待遇到右中括号
                st.push(']');
            } else if (s.charAt(i) == '{') {
                //遇到左打括号
                //期待遇到右打括号
                st.push('}');
            }
            //必须有左括号的情况下才能遇到右括号
            else if (st.isEmpty() || st.pop() != s.charAt(i)) {
                return false;
            }
        }
        //栈中是否还有元素
        return st.isEmpty();
    }
}
