package deepcopy;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Cloneable, Serializable {
    private String name;
    private Address address;

    /**
     * 需要注意的是，super.clone()其实是浅拷贝，所以在重写User类的clone()方法时，
     * address对象需要调用address.clone()重新赋值
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    public User clone() throws CloneNotSupportedException {
        User user = (User) super.clone();
        user.setAddress(this.address.clone());
        return user;
    }
}
