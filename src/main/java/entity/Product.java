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