package arithmetic.company.huawei;

import java.util.*;

/**
 * 坐标移动
 * 开发一个坐标计算工具， A表示向左移动，D表示向右移动，W表示向上移动，S表示向下移动。从（0,0）点开始移动，从输入字符串里面读取一些坐标，并将最终输入结果输出到输出文件里面。
 * <p>
 * 输入：
 * <p>
 * 合法坐标为A(或者D或者W或者S) + 数字（两位以内）
 * <p>
 * 坐标之间以;分隔。
 * <p>
 * 非法坐标点需要进行丢弃。如AA10;  A1A;  $%$;  YAD; 等。
 */
public class CoordinateShift {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String inLine = in.nextLine();
        String[] split = inLine.split(";");

        int[] a = {0, 0};

        for (int i = 0; i < split.length; i++) {
            if (!isValid(split[i])) {
                continue;
            }
            shift(a, split[i]);
        }
        System.out.println("(" + a[0] + "," + a[1] + ")");
    }

    private static void shift(int[] arr, String line) {
        String head = String.valueOf(line.charAt(0));
        int i = Integer.parseInt(line.substring(1, line.length()));
        if ("A".equals(head)) {
            arr[0] -= i;
            return;
        }
        if ("D".equals(head)) {
            arr[0] += i;
            return;
        }
        if ("W".equals(head)) {
            arr[1] += i;
            return;
        }
        if ("S".equals(head)) {
            arr[1] -= i;
        }
    }

    private static Set<String> heads = Set.of("A", "D", "W", "S");

    private static boolean isValid(String line) {
        if (line == null || line.length() == 0) {
            return false;
        }
        String head = String.valueOf(line.charAt(0));
        if (!heads.contains(head)) {
            return false;
        }
        String number = line.substring(1, line.length());
        try {
            //判断是否为有效数字
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {

        }
        return false;
    }
}
