package arithmetic.exercise;

import java.util.Stack;

/**
 * https://mp.weixin.qq.com/s/CPIDKHsg3ROT10rVFDyDJQ
 * 给定一个只包含 '('和')' 的字符串，找出最长的包含有效括号的子串的长度
 */
public class Demo {
    public static void main(String[] args) {
        String str = "()((())";
        System.out.println(solution(str));
        System.out.println(solution2("()((())"));
        System.out.println(solution3("()((())"));
    }

    /**
     * 空复杂度都是 O(n)
     *
     * @param s
     * @return
     */
    public static int solution(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        char[] sArr = s.toCharArray();
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        // -1 入栈用于处理边界条件
        stack.push(-1);
        for (int i = 0; i < n; ++i) {
            // stack.size() > 1 表示栈不为空，而且我们必须保证栈顶元素是 '('
            if (sArr[i] == ')' && stack.size() > 1 && sArr[stack.peek()] == '(') {
                // 配对的 '(' 出栈
                stack.pop();
                // 记录长度
                result = Math.max(result, i - stack.peek());
            } else { // 其他情况，直接将当前位置入栈
                stack.push(i);
            }
        }

        return result;
    }

    /**
     * 空复杂度都是 O(n)
     *
     * @param s
     * @return
     */
    public static int solution2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        char[] sArr = s.toCharArray();
        int[] dp = new int[n];
        int result = 0;
        for (int i = 1; i < n; ++i) {
            if (sArr[i] == ')') {
                // 如果前一个位置是 '('，直接配对
                if (sArr[i - 1] == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                }
                // 前一个位置是 ')'
                // 我们从当前位置往左看，如果第一个没有被匹配的位置是 '('
                // 表明当前位置是可以被匹配的
                else if (i - dp[i - 1] - 1 >= 0 && sArr[i - dp[i - 1] - 1] == '(') {
                    // 这里其实是 dp[i] = i - (i - dp[i - 1] - 1) + 1 = dp[i - 1] + 2
                    // 但是我们还需要考虑之前的答案，也就是 dp[i - dp[i - 1] - 2]
                    // 首先判断 i - dp[i - 1] - 2 是否越界
                    // 如果没有越界就将其加上
                    dp[i] = dp[i - 1] + 2;

                    if (i - dp[i - 1] >= 2) {
                        dp[i] += dp[i - dp[i - 1] - 2];
                    }
                }
                result = Math.max(result, dp[i]);
            }
        }
        return result;
    }

    public static int solution3(String s) {
            int left = 0, right = 0, maxlength = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    left++;
                } else {
                    right++;
                }
                if (left == right) {
                    maxlength = Math.max(maxlength, 2 * right);
                } else if (right >= left) {
                    left = right = 0;
                }
            }
            left = right = 0;
            for (int i = s.length() - 1; i >= 0; i--) {
                if (s.charAt(i) == '(') {
                    left++;
                } else {
                    right++;
                }
                if (left == right) {
                    maxlength = Math.max(maxlength, 2 * left);
                } else if (left >= right) {
                    left = right = 0;
                }
            }
            return maxlength;
    }
}
