package arithmetic.linked_list;

import java.util.Stack;

import lombok.Data;

/**
 * 反转单向链表
 */
public class ReverseNode {

    public static void main(String[] args) {
        Node pre = solution1(Node.create());
        while (pre != null) {
            System.out.println(pre.getValue());
            pre = pre.getNext();
        }

        System.out.println();

        Node pre2 = solution2(Node.create());
        while (pre2 != null) {
            System.out.println(pre2.getValue());
            pre2 = pre2.getNext();
        }
    }


    /**
     * 反转链表
     */
    private static Node solution1(Node head) {
        Node pre = null;
        Node next;
        while (head != null) {
            //存下引用
            next = head;
            //因为要操作next，所以提前改变head的引用
            head = head.getNext();
            //设置node的后驱节点
            next.setNext(pre);
            //将此node赋值给前驱节点
            pre = next;
        }
        return pre;
    }

    private static Node reverse(Node head) {
        Node pre = null;
        Node cur = head;
        while (cur != null) {
            Node curNext = cur.next;
            cur.next = pre;
            pre = cur;
            cur = curNext;
        }
        return pre;
    }

    /**
     * 使用栈
     */
    private static Node solution2(Node head) {
        Stack<Node> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.getNext();
        }
        Node last = null;
        Node node = null;
        while (!stack.empty()) {
            Node pop = stack.pop();
            if (last == null) {
                last = pop;
            } else {
                node.setNext(pop);
            }
            node = pop;
            if (stack.empty()) {
                node.setNext(null);
            }
        }
        return last;
    }

}
