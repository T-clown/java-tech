package arithmetic.linked_list;

import lombok.Data;

@Data
public class Node {
    public int value;

    public Node pre;

    public Node next;

    Node(int value) {
        this.value = value;
        next = null;
    }

    public Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }

    public Node(int value, Node pre, Node next) {
        this.value = value;
        this.pre = pre;
        this.next = next;
    }

    public static Node create() {
        Node head = new Node(1);
        Node next = head;
        for (int i = 2; i < 10; i++) {
            next.setNext(new Node(i));
            next = next.getNext();
        }
        return head;
    }

    public static Node reverse(Node head) {
        Node pre = null;
        Node curNode = head;
        while (curNode != null) {
            Node nextNode = curNode.next;
            curNode.next = pre;
            pre = curNode;
            curNode = nextNode;
        }
        return pre;
    }
}
