package arithmetic.alibaba;

import java.util.Stack;

import lombok.Data;

/**
 * 如何实现一个高效的单向链表逆序输出
 */
public class ReverseLinkedList {

    public static void main(String[] args) {
        Node head = create();
        //solution1(head);
        //solution2(head);

        deleteNode(head, 2);
        System.out.println();
        while (head != null) {
            System.out.println(head.data);
            head = head.next;
        }
    }

    /**
     * 给定一个链表，删除链表的倒数第N个节点，并且返回链表的头结点
     * 双指针
     *
     * @param head
     * @param n
     */
    public static Node deleteNode(Node head, int n) {
        Node dummy = new Node(0);
        dummy.next = head;
        Node first = dummy;
        Node second = dummy;
        // Move first to the end, maintaining the gap
        while (first != null) {
            first = first.next;
            if (n < 1 && first != null) {
                //让first指针先遍历n个节点，second指针才开始走，当first为空，second即为倒数第n个节点
                second = second.next;
            }
            --n;
        }
        second.next = second.next.next;
        return dummy.next;
    }

    /**
     * 反转链表
     */
    private static void solution1(Node head) {
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

        while (pre != null) {
            System.out.println(pre.getData());
            pre = pre.getNext();
        }

    }

    /**
     * 使用栈
     */
    private static void solution2(Node head) {
        Stack<Node> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.getNext();
        }
        while (!stack.empty()) {
            System.out.println(stack.pop().data);
        }
    }

    private static Node create() {
        Node head = new Node(0);
        Node next = head, node = head;

        for (int i = 1; i < 10; i++) {
            next.setNext(new Node(i));
            next = next.getNext();
        }

        while (node != null) {
            System.out.println(node.data);
            node = node.getNext();
        }
        return head;
    }

    @Data
    static class Node {
        public int data;

        public Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }
}
