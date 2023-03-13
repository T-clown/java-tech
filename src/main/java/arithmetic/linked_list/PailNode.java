package arithmetic.linked_list;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个链表，请判断该链表是否为回文结构。
 * 回文是指该字符串正序逆序完全一致。
 */
public class PailNode {
    public static void main(String[] args) {
        //{-401261,-449050,-456674,-456674,-449050,-401261}
        Node node = new Node(-401261, new Node(-449050, new Node(-456674, new Node(-456674, new Node(-449050, new Node(-401261, null))))));
        System.out.println(isPail(node));
    }


    public static boolean isPail(Node head) {
        if (head == null) {
            return false;
        }
        Node curNode = head;
        List<Integer> list = new ArrayList<>();
        while (curNode != null) {
            list.add(curNode.value);
            curNode = curNode.next;
        }
        return isPlalindrome(list);
    }

    private static boolean isPlalindrome(List<Integer> list) {
        int len = list.size();
        for (int i = 0; i < len / 2; i++) {
            if (list.get(i).intValue() != (list.get(len - 1 - i))) {
                return false;
            }
        }
        return true;
    }


}
