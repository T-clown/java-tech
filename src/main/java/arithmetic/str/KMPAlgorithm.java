package arithmetic.str;

public class KMPAlgorithm {

    public static int[] generateNext(String pattern) {
        int m = pattern.length();
        int[] next = new int[m];
        int j = 0;
        for (int i = 1; i < m; i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = next[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    public static int kmpSearch(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        int[] next = generateNext(pattern);
        int j = 0;
        for (int i = 0; i < n; i++) {
            while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
                j = next[j - 1];
            }
            if (text.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            if (j == m) {
                return i - m + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String text = "ABABABCABABABCABABABC";
        String pattern = "ABABABC";
        int result = kmpSearch(text, pattern);
        if (result != -1) {
            System.out.println("匹配成功，第一次出现的位置是: " + result);
        } else {
            System.out.println("未找到匹配的位置");
        }
    }
}
