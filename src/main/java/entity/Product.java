package entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
public class Product {
    public int id;
    public String name;
    public int stock;
    public int price;

    //静态代码块
    static {
        System.out.println("执行了静态代码块");
    }
    //静态变量
    private static String staticFiled = staticMethod();

    //赋值静态变量的静态方法
    public static String staticMethod(){
        System.out.println("执行了静态方法");
        return "给静态字段赋值了";
    }

    private  String  reflect(String a,String b){
        return a+b;
    }

    public Product() {
    }
    public Product( String name) {
        this.name = name;
    }

    public Product(int id, String name, int stock, int price) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.price = price;
    }


}