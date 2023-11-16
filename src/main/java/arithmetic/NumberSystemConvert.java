package arithmetic;

public class NumberSystemConvert {

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(0));
        ten2Binary();
        binary2Ten();
        System.out.println(Integer.parseInt("00001010000000000000001111000001",2));
        ten2Sixteen();
    }

    /**
     * 十进制转十六进制
     */
    private static void ten2Sixteen() {
        int decimalNumber = 28759;
        String hexString = Integer.toHexString(decimalNumber);
        // 输出：A
        System.out.println(hexString);
    }

    /**
     * 十六进制转十进制
     */
    private static void sixteen2Ten() {
        String hexString = "A";
        int decimalNumber = Integer.parseInt(hexString, 16);
        // 输出：10
        System.out.println(decimalNumber);

        int decimalNumber2 = Integer.valueOf(hexString, 16);
        // 输出：10
        System.out.println(decimalNumber2);

    }

    /**
     * 二进制转十进制
     */
    private static void binary2Ten() {
        String binaryString = "0001010";
        int decimalNumber = Integer.parseInt(binaryString, 2);
        // 输出：10
        System.out.println(decimalNumber);

        int decimalNumber2 = Integer.valueOf(binaryString, 2);
        // 输出：10
        System.out.println(decimalNumber2);
    }

    /**
     * 十进制转二进制
     */
    private static void ten2Binary() {
        int decimalNumber = 10;
        String binaryString = Integer.toBinaryString(decimalNumber);
        // 输出：1010
        System.out.println(binaryString);

        String binaryString2 = Integer.toString(decimalNumber, 2);
        // 输出：1010
        System.out.println(binaryString2);
    }
}
