package deepcopy;

import java.io.IOException;

import com.alibaba.fastjson.JSON;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.SerializationUtils;

/**
 * 浅拷贝只是拷贝了源对象的地址，所以源对象的值发生变化时，拷贝对象的值也会发生变化。
 * 而深拷贝则是拷贝了源对象的所有值，所以即使源对象的值发生变化时，拷贝对象的值也不会改变
 */
public class DeepCopy {

    public static void main(String[] args) throws CloneNotSupportedException, IOException {
        cloneCopy();
        serializableCopy();
        jsonCopy();
        jacksonCopy();
    }


    public static void cloneCopy() throws CloneNotSupportedException {
        Address address = new Address("杭州", "中国");
        User user = new User("大山", address);

        // 调用clone()方法进行深拷贝
        User copyUser = user.clone();

        // 修改源对象的值
        user.getAddress().setCity("深圳");

        // 检查两个对象的值不同
        System.out.println(user.getAddress().getCity().equals(copyUser.getAddress().getCity()));
    }

    /**
     * 实现Serializable接口，使其支持序列化
     */
    public static void serializableCopy() {

        Address address = new Address("杭州", "中国");
        User user = new User("大山", address);

        // 使用Apache Commons Lang序列化进行深拷贝
        User copyUser = SerializationUtils.clone(user);

        // 修改源对象的值
        user.getAddress().setCity("深圳");

        // 检查两个对象的值不同
        System.out.println(user.getAddress().getCity().equals(copyUser.getAddress().getCity()));

    }

    /**
     * 要有无参构造
     */
    public static void jsonCopy() {

        Address address = new Address("杭州", "中国");
        User user = new User("大山", address);

        // 使用Gson序列化进行深拷贝
        User copyUser = JSON.parseObject(JSON.toJSONString(user),User.class);

        // 修改源对象的值
        user.getAddress().setCity("深圳");

        // 检查两个对象的值不同
        System.out.println(user.getAddress().getCity().equals(copyUser.getAddress().getCity()));

    }

    public static void jacksonCopy() throws IOException {

        Address address = new Address("杭州", "中国");
        User user = new User("大山", address);

        // 使用Jackson序列化进行深拷贝
        ObjectMapper objectMapper = new ObjectMapper();
        User copyUser = objectMapper.readValue(objectMapper.writeValueAsString(user), User.class);

        // 修改源对象的值
        user.getAddress().setCity("深圳");

        // 检查两个对象的值不同
        System.out.println(user.getAddress().getCity().equals(copyUser.getAddress().getCity()));

    }
}
