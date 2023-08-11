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
public class Reflect {
    public static void main(String[] args) throws Exception {
        ClassLoader.getSystemClassLoader().loadClass("entity.Product");
        System.out.println("-----------------------------------------");
        Class.forName("entity.Product");
        //clazz();
        //constructor();
        method();
        //field();
        //progress();

    }


    /**
     * 获取Class对象三种方式
     */
    private static void clazz() throws Exception {
        Class<Product> a = Product.class;
        Class b = new Product().getClass();
        Class c = Class.forName("entity.Product");
        System.out.println(a+"\t"+b+"\t"+c);
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
        //Product p = new Product();
        Product p = clazz.newInstance();
        //去掉权限验证（暴力反射）
        method.setAccessible(true);
        //执行p的reflect方法,并获得返回值
        String result = (String) method.invoke(null,"a","b");
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