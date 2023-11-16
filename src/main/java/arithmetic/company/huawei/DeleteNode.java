package arithmetic.company.huawei;

import java.util.Objects;
import java.util.Scanner;

/**
 * 输入一个单向链表和一个节点的值，从单向链表中删除等于该值的节点，删除后如果链表中无节点则返回空指针。
 * <p>
 * 链表的值不能重复。
 * <p>
 * 构造过程，例如输入一行数据为:
 * 6 2 1 2 3 2 5 1 4 5 7 2 2
 * 则第一个参数6表示输入总共6个节点，第二个参数2表示头节点值为2，剩下的2个一组表示第2个节点值后面插入第1个节点值，为以下表示:
 * 1 2 表示为
 * 2->1
 * 链表为2->1
 * <p>
 * 3 2表示为
 * 2->3
 * 链表为2->3->1
 * <p>
 * 5 1表示为
 * 1->5
 * 链表为2->3->1->5
 * <p>
 * 4 5表示为
 * 5->4
 * 链表为2->3->1->5->4
 * <p>
 * 7 2表示为
 * 2->7
 * 链表为2->7->3->1->5->4
 * <p>
 * 最后的链表的顺序为 2 7 3 1 5 4
 * <p>
 * 最后一个参数为2，表示要删掉节点为2的值
 * 删除 结点 2
 * <p>
 * 则结果为 7 3 1 5 4
 */
public class DeleteNode {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        String[] split = s.split(" ");
        int nodeNum = Integer.parseInt(split[0]);
        Node dummy = new Node(-1, null);
        dummy.next = new Node(Integer.parseInt(split[1]), null);
        int idx = 2;
        for (int i = 1; i < nodeNum; i++) {
            appendNode(dummy, Integer.parseInt(split[idx + 1]), Integer.parseInt(split[idx]));
            idx += 2;
        }
        int i = Integer.parseInt(split[split.length - 1]);
        Node curNode = dummy;
        Node preNode = dummy;
        while (curNode != null) {
            if (curNode.value == i) {
                //删除当前节点
                preNode.next = curNode.next;
                break;
            }
            preNode = curNode;
            curNode = curNode.next;
        }
        Node head = dummy.next;
        Objects.requireNonNull(head);
        String r = "";
        while (head != null) {
            r += (head.value + " ");
            head = head.next;
        }
        System.out.println(r);

    }


    private static void appendNode(Node head, int node, int value) {
        while (head != null) {
            if (head.value == node) {
                Node next = head.next;
                head.next = new Node(value, next);
                break;
            }
            head = head.next;
        }
    }

    static class Node {
        private int value;

        private Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
