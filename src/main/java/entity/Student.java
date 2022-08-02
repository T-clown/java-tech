package entity;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = -4392658638228508589L;

    private String name;
    private Integer age;
    private Integer score;
    //private Integer score2;
    private transient String password;
    private static Integer sex = 1;

    /**
     * 反序列化检测函数
     * @param objectInputStream
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        // 调用默认的反序列化函数
        objectInputStream.defaultReadObject();
        // 手工检查反序列化后学生成绩的有效性，若发现有问题，即终止操作！
        if (0 > score || 100 < score) {
            throw new IllegalArgumentException("学生分数只能在0到100之间！");
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static Integer getSex() {
        return sex;
    }

    public static void setSex(Integer sex) {
        Student.sex = sex;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                ", password='" + password + '\'' +
                '}';
    }
}
