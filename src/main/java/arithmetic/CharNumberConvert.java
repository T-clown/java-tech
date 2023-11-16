package arithmetic;

public class CharNumberConvert {
    public static void main(String[] args) {

    }

    /**
     * 字符和数字互相转换
     */
    private static void test() {
        System.out.println((int) 'a');
        System.out.println((char) 97);
        System.out.println((char) 0);
        System.out.println((int) 'z');
        System.out.println((int) 'A');
        System.out.println((int) 'Z');
        System.out.println((int) '0');
        System.out.println((int) '9');

        //大写转小写
        System.out.println(Character.toLowerCase('A'));
        //大写转小写
        System.out.println((char) ('A' - 'A' + 'a'));
        //小写转大写
        System.out.println(Character.toUpperCase('a'));
        //小写转大写
        System.out.println((char) ('a' + 'A' - 'a'));
        System.out.println((char) ('B' - 'b') + 1);
        System.out.println('B' - 'b');
        System.out.println('a' - 'A');
        System.out.println((char) ('0' + 1));
    }
}
