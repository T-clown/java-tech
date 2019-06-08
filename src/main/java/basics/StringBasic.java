package basics;

import lombok.ToString;

public class StringBasic {
    public static void main(String[] args) {
        //calculate();
        //str();
        //strConcat();
        demo();
    }

    private static void calculate(){
        /**
         * n<<m  n乘以2的m次方
         * n>>m  n除以2的m次方(有符号右移)
         * n>>>m n除以2的m次方(无符号右移)
         */
        System.out.println(2<<3);//2*8
        System.out.println(8>>2);//8/4
    }
    /**
     直接使用双引号声明出来的 String 对象会直接存储在常量池中。
     如果不是用双引号声明的 String 对象，可以使用 String 提供的 intern 方String.intern() 是一个 Native 方法，
     它的作用是：如果运行时常量池中已经包含一个等于此 String 对象内容的字符串，则返回常量池中该字符串的引用；
     如果没有，则在常量池中创建与此 String 内容相同的字符串，并返回常量池中创建的字符串的引用。
     */
    private static void str(){
        String s1 = new String("a");
        String s2 = s1.intern();
        String s3 = "a";
        System.out.println(s1 == s2);//false，因为一个是堆内存中的String对象一个是常量池中的String对象，
        System.out.println(s3 == s2);//true，因为两个都是常量池中的String对象
    }

    private  static void strConcat(){
        String str1 = "str";
        String str2 = "ing";

        String str3 = "str" + "ing";//常量池中的对象
        String str4 = str1 + str2; //在堆上创建的新的对象
        String str5 = "string";//常量池中的对象
        System.out.println(str3 == str4);//false
        System.out.println(str3 == str5);//true
        System.out.println(str4 == str5);//false
    }

    private  static void demo(){
        String s1 = new String("abc");
        String s2 = "abc";
        String s3=s1.intern();
        System.out.println(s1==s3);
        System.out.println(s2==s3);

    }
}