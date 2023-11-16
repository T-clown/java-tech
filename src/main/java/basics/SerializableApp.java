package basics;

import entity.Product;
import entity.Student;
import lombok.SneakyThrows;
import patterns.singleton.StaticInnerSingleton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializableApp {

    @SneakyThrows
    public static void main(String[] args) {
        System.out.println();
        StaticInnerSingleton instance = StaticInnerSingleton.getInstance();
        System.out.println();
//        Product.staticMethod(2);
//        serialize();
//        deserialize();
       // singleton();
    }

    public static void serialize() throws IOException {
        Student student = new Student();
        student.setName("serialize");
        student.setAge(18);
        student.setScore(100);
        student.setPassword("123");
        student.setProduct(new Product(1,"aa",2,3));

        ObjectOutputStream objectOutputStream =
                new ObjectOutputStream(new FileOutputStream(new File("student.txt")));
        objectOutputStream.writeObject(student);
        objectOutputStream.close();

        System.out.println("序列化成功！已经生成student.txt文件");
        System.out.println("==============================================");
    }

    public static void singleton() throws IOException, ClassNotFoundException {

        ObjectOutputStream objectOutputStream =
                new ObjectOutputStream(
                        new FileOutputStream(new File("singleton.txt"))
                );
        // 将单例对象先序列化到文本文件singleton.txt中
        objectOutputStream.writeObject(StaticInnerSingleton.getInstance());
        objectOutputStream.close();
        ObjectInputStream objectInputStream =
                new ObjectInputStream(
                        new FileInputStream(new File("singleton.txt"))
                );
        // 将文本文件singleton.txt中的对象反序列化为singleton1
        StaticInnerSingleton singleton1 = (StaticInnerSingleton) objectInputStream.readObject();
        objectInputStream.close();

        StaticInnerSingleton singleton2 = StaticInnerSingleton.getInstance();

        // 运行结果竟打印 false ！
        System.out.println(singleton1 == singleton2);
    }

    public static void deserialize() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream =
                new ObjectInputStream(new FileInputStream(new File("student.txt")));
        Student student = (Student) objectInputStream.readObject();
        objectInputStream.close();

        System.out.println("反序列化结果为：");
        System.out.println(student);
    }
}
