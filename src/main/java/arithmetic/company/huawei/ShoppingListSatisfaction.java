package arithmetic.company.huawei;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * https://www.nowcoder.com/practice/f9c6f980eeec43ef85be20755ddbeaf4?tpId=37&tqId=21239&rp=1&ru=/exam/oj/ta&qru=/exam/oj/ta&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37&difficulty=undefined&judgeStatus=undefined&tags=&title=
 */
public class ShoppingListSatisfaction {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String[] arr = s.split(" ");
        int amount = Integer.parseInt(arr[0]);
        int num = Integer.parseInt(arr[1]);

        List<Item> products = new ArrayList<Item>();

        for (int i = 0; i < num; i++) {
            String a = scanner.nextLine();
            String[] array = a.split(" ");
            products.add(new Item(i + 1, Integer.parseInt(array[0]), Integer.parseInt(array[1]), Integer.parseInt(array[2])));
        }
        Map<Integer, List<Item>> parentIdChildMap = products.stream().collect(Collectors.groupingBy(Item::getParentId));
        //价格和满意度倒序
        List<Item> parentProducts = products.stream().filter(x -> x.getParentId() == 0).sorted(Comparator.comparing(Item::getPrice).reversed().thenComparing(Item::getSatisfaction).reversed()).collect(Collectors.toList());


    }

    static class Item {
        private int id;
        private int price;
        private int satisfaction;
        private int parentId;

        public Item(int id, int price, int satisfaction, int parentId) {
            this.id = id;
            this.price = price;
            this.satisfaction = satisfaction;
            this.parentId = parentId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getSatisfaction() {
            return satisfaction;
        }

        public void setSatisfaction(int satisfaction) {
            this.satisfaction = satisfaction;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }
    }
}
