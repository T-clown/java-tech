package entity;

import lombok.Data;

@Data
public class Product {
    public int id;
    public String name;
    public int stock;
    public int price;

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


aaaaaaaaaaaaaaaaaaaaaa

package entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WriteModel extends BaseRowModel {
    @ExcelProperty(value = "姓名",index = 0)
    private String name;
    @ExcelProperty(value = "密码",index = 1)
    private String password;
    @ExcelProperty(value = "年龄",index = 2)
    private Integer age;
}
