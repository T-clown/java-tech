package deepcopy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User2 implements Cloneable, Serializable {
    private String name;
    private Address address;
    private String aBbb;
    private String aaBbbb;

    /**
     * 需要注意的是，super.clone()其实是浅拷贝，所以在重写User类的clone()方法时，
     * address对象需要调用address.clone()重新赋值
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    public User2 clone() throws CloneNotSupportedException {
        User2 user = (User2) super.clone();
        user.setAddress(this.address.clone());
        return user;
    }

}
