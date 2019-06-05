package basics;

import java.util.Arrays;
import java.util.BitSet;

public class BitSetDemo {
    /**
     * 求一个字符串包含的char
     *
     */
    public static void containChars(String str) {
        BitSet used = new BitSet();
        for (int i = 0; i < str.length(); i++)
            used.set(str.charAt(i)); // set bit for char

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int size = used.size();
        System.out.println(size);
        for (int i = 0; i < size; i++) {
            if (used.get(i)) {
                sb.append((char) i);
            }
        }
        sb.append("]");
        System.out.println(sb.toString());
    }

    /**
     * 求素数 有无限个。一个大于1的自然数，如果除了1和它本身外，不能被其他自然数整除(除0以外）的数称之为素数(质数） 否则称为合数
     */
    public static void computePrime() {
        BitSet sieve = new BitSet(1024);
        int size = sieve.size();
        for (int i = 2; i < size; i++)
            sieve.set(i);
        int finalBit = (int) Math.sqrt(sieve.size());

        for (int i = 2; i < finalBit; i++)
            if (sieve.get(i))
                for (int j = 2 * i; j < size; j += i)
                    sieve.clear(j);

        int counter = 0;
        for (int i = 1; i < size; i++) {
            if (sieve.get(i)) {
                System.out.printf("%5d", i);
                if (++counter % 15 == 0)
                    System.out.println();
            }
        }
        System.out.println();
    }

    /**
     * 进行数字排序
     */
    public static void sortArray() {
        int[] array = new int[] { 423, 700, 9999, 2323, 356, 6400, 1,2,3,2,2,2,2 };
        BitSet bitSet = new BitSet(2 << 13);
        // 虽然可以自动扩容，但尽量在构造时指定估算大小,默认为64
        System.out.println("BitSet size: " + bitSet.size());

        for (int i = 0; i < array.length; i++) {
            bitSet.set(array[i]);
        }
        //剔除重复数字后的元素个数
        int bitLen=bitSet.cardinality();

        //进行排序，即把bit为true的元素复制到另一个数组
        int[] orderedArray = new int[bitLen];
        int k = 0;
        for (int i = bitSet.nextSetBit(0); i >= 0; i = bitSet.nextSetBit(i + 1)) {
            orderedArray[k++] = i;
        }

        System.out.println("After ordering: ");
        for (int i = 0; i < bitLen; i++) {
            System.out.print(orderedArray[i] + "\t");
        }

        System.out.println("iterate over the true bits in a BitSet");
        //或直接迭代BitSet中bit为true的元素iterate over the true bits in a BitSet
        for (int i = bitSet.nextSetBit(0); i >= 0; i = bitSet.nextSetBit(i + 1)) {
            System.out.print(i+"\t");
        }
        System.out.println("---------------------------");
    }

    /**
     * 将BitSet对象转化为ByteArray
     * @param bitSet
     * @return
     */
    public static byte[] bitSet2ByteArray(BitSet bitSet) {
        byte[] bytes = new byte[bitSet.size() / 8];
        for (int i = 0; i < bitSet.size(); i++) {
            int index = i / 8;
            int offset = 7 - i % 8;
            bytes[index] |= (bitSet.get(i) ? 1 : 0) << offset;
        }
        return bytes;
    }

    /**
     * 将ByteArray对象转化为BitSet
     * @param bytes
     * @return
     */
    public static BitSet byteArray2BitSet(byte[] bytes) {
        BitSet bitSet = new BitSet(bytes.length * 8);
        int index = 0;
        for (int i = 0; i < bytes.length; i++) {
            for (int j = 7; j >= 0; j--) {
                bitSet.set(index++, (bytes[i] & (1 << j)) >> j == 1);
            }
        }
        return bitSet;
    }

    /**
     * 简单使用示例
     */
    public static void simpleExample() {
        String names[] = { "Java", "Source", "and", "Support" };
        BitSet bits = new BitSet();
        for (int i = 0, n = names.length; i < n; i++) {
            if ((names[i].length() % 2) == 0) {
                bits.set(i);
            }
        }

        System.out.println(bits);
        System.out.println("Size : " + bits.size());
        System.out.println("Length: " + bits.length());
        for (int i = 0, n = names.length; i < n; i++) {
            if (!bits.get(i)) {
                System.out.println(names[i] + " is odd");
            }
        }
        BitSet bites = new BitSet();
        bites.set(0);
        bites.set(1);
        bites.set(2);
        bites.set(3);
        bites.andNot(bits);
        System.out.println(bites);
    }

    public static void main(String args[]) {
        //BitSet使用示例
        BitSetDemo.containChars("How do you do? 你好呀");
        BitSetDemo.computePrime();
        BitSetDemo.sortArray();
        BitSetDemo.simpleExample();


        //BitSet与Byte数组互转示例
        BitSet bitSet = new BitSet();
        bitSet.set(3, true);
        bitSet.set(98, true);
        System.out.println(bitSet.size()+","+bitSet.cardinality());
        //将BitSet对象转成byte数组
        byte[] bytes = BitSetDemo.bitSet2ByteArray(bitSet);
        System.out.println(Arrays.toString(bytes));

        //在将byte数组转回来
        bitSet = BitSetDemo.byteArray2BitSet(bytes);
        System.out.println(bitSet.size()+","+bitSet.cardinality());
        System.out.println(bitSet.get(3));
        System.out.println(bitSet.get(98));
        for (int i = bitSet.nextSetBit(0); i >= 0; i = bitSet.nextSetBit(i + 1)) {
            System.out.print(i+"\t");
        }
    }
}

aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa

package basics;

import lombok.ToString;

public class JavaBasic {
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
aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa

package basics;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import entity.Product;
import org.apache.commons.lang.StringUtils;

/**
 * 使用场景
 * 在搭建框架的时候，有时候不知道需要什么类，什么方法，这个类有哪些属性。比如查询数据库之后的数据，反射成对象。
 */
public class ReflectDemo {
    public static void main(String[] args) throws Exception {
      constructor();
      method();
      field();
      progress();
    }
    /**
     * 获取Class对象三种方式
     */
    private static void clazz() throws Exception {
        Class<String> a = String.class;
        Class b = "abc".getClass();
        Class c = Class.forName("java.lang.String");
    }

    /**
     * Constructor
     * @throws Exception
     */
    private static void constructor() throws Exception {
        //获取字节码
        Class<Product> p = Product.class;
        //获取所有的类的构造器
        Constructor[] acs = p.getConstructors();
        //获取参数是StringBuffer的构造器
        Constructor<Product> ac = p.getConstructor(String.class);
        // 利用构造器，创建对象
        Product a = ac.newInstance("aa");

        //获取私有构造器
        Constructor<Product> con = p.getDeclaredConstructor();
        //取消访问权限检查
        con.setAccessible(true);
        //执行构造器
        con.newInstance();

    }

    /**
     *Method
     * @throws Exception
     */
    private static void method() throws Exception {
        //获取字节码
        Class<Product> clazz = Product.class;
        //获取secretFunc方法
        Method method = clazz.getDeclaredMethod("reflect", String.class, String.class);
        //实例一个demo02
        Product p = new Product();
        //去掉权限验证（暴力反射）
        method.setAccessible(true);
        //执行p的reflect方法,并获得返回值
        String result = (String) method.invoke(p,"a","b");
        //输出结果
        System.out.println(result);
        /**
         * 有些方法只知道名字，不知道参数，可以用getMethods()先取出所有方法
         * 再用，getParameters()来获得参数类型
         */
        Method[]  methods=clazz.getMethods();
        int count=method.getParameterCount();
        Class[] classes=method.getParameterTypes();
        Parameter[]  parameters=method.getParameters();
    }

    /**
     *Field
     * @throws Exception
     */
    private static void field() throws Exception {
        //获取字节码
        Class<Product> clazz = Product.class;
        //获取name成员变量
        Field f = clazz.getDeclaredField("name");
        //实例一个demo
        Product p = new Product("苹果");
        //去掉权限验证（暴力反射）
        f.setAccessible(true);
        //获取这个属性的值
        System.out.println(f.get(p));
        //修改demo02的XXX属性
        if(f.getType()==String.class){
            f.set(p,"香蕉");
        }
        System.out.println(f.get(p));
    }

    /**
     * 简化读取配置
     */
    private static String getProfileData() {
        return "entity.Product";
    }

    /**
     * 简化数据库查询（用JSON代替了，JDBC其实也可以）
     */
    private static String getDataFromDatabase() {
        return JSON.toJSONString(new Product(1,"Apple",16,5399));
    }


    private static void progress() throws Exception {
        //读取配置得到对象配置
        Class clazz = Class.forName(getProfileData());
        //实例化对象
        Object needObj = clazz.newInstance();
        //读取数据库，获得数据
        JSONObject datas = JSON.parseObject(getDataFromDatabase());
        //获取参数名称集合
        Set<String> keys = datas.keySet();
        for (String key : keys) {
            //调用set方法
            Method md = clazz.getMethod("set" + StringUtils.capitalize(key), String.class);
            md.invoke(needObj, datas.get(key));
        }
        // 这就得到了需要的对象
        System.out.println(needObj);
    }
}
aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa

package basics;

public class TryCatchFinally {
    public static void main(String[] args) {
        System.out.println(test09());
    }

    //try
    public static final String test01() {
        String t = "";
        try {
            t = "try";
            return t;
        } catch (Exception e) {
            t = "catch";
            return t;
        } finally {
            t = "finally";
        }
    }

    //finally
    public static final String test02() {
        String t = "";

        try {
            t = "try";
            return t;
        } catch (Exception e) {
            // result = "catch";
            t = "catch";
            return t;
        } finally {
            t = "finally";
            return t;
        }
    }

    //catch
    public static final String test03() {
        String t = "";
        try {
            t = "try";
            Integer.parseInt(null);
            return t;
        } catch (Exception e) {
            t = "catch";
            return t;
        } finally {
            t = "finally";
            // System.out.println(t);
            // return t;
        }
    }

    //finally
    public static final String test04() {
        String t = "";
        try {
            t = "try";
            Integer.parseInt(null);
            return t;
        } catch (Exception e) {
            t = "catch";
            return t;
        } finally {
            t = "finally";
            return t;
        }
    }

    //NumberFormatException
    public static final String test05() {
        String t = "";

        try {
            t = "try";
            Integer.parseInt(null);
            return t;
        } catch (Exception e) {
            t = "catch";
            Integer.parseInt(null);
            return t;
        } finally {
            t = "finally";
            //return t;
        }
    }

    //finally
    public static final String test06() {
        String t = "";

        try {
            t = "try";
            Integer.parseInt(null);
            return t;
        } catch (Exception e) {
            t = "catch";
            Integer.parseInt(null);
            return t;
        } finally {
            t = "finally";
            return t;
        }
    }

    //NumberFormatException
    public static final String test07() {
        String t = "";

        try {
            t = "try";
            Integer.parseInt(null);
            return t;
        } catch (NullPointerException e) {
            t = "catch";
            return t;
        } finally {
            t = "finally";
        }
    }

    //finally
    public static final String test08() {
        String t = "";

        try {
            t = "try";
            Integer.parseInt(null);
            return t;
        } catch (NullPointerException e) {
            t = "catch";
            return t;
        } finally {
            t = "finally";
            return t;
        }
    }

    //NullPointerException
    public static final String test09() {
        String t = "";

        try {
            t = "try";
            return t;
        } catch (Exception e) {
            t = "catch";
            return t;
        } finally {
            t = "finally";
            String.valueOf(null);
            return t;
        }
    }

}
