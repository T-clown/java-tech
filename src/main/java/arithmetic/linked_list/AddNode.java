package arithmetic.linked_list;

/**
 * 假设链表中每一个节点的值都在 0 - 9 之间，那么链表整体就可以代表一个整数。
 * 给定两个这种链表，请生成代表两个整数相加值的结果链表。
 */
public class AddNode {
    public static void main(String[] args) {
        Node node1 = new Node(9, new Node(3, new Node(7, null)));
        Node node3 = new Node(6, new Node(3, null));
        Node node2 = new Node(2, new Node(3, new Node(6, new Node(9, null))));
        Node node = addNode(node1, node3);
        while (node != null) {
            System.out.println(node.getValue());
            node = node.getNext();
        }
    }


    public static Node addNode(Node head1, Node head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }
        Node start1 = reverse(head1);
        Node start2 = reverse(head2);
        int addVal = 0;
        Node head = null;
        while (start1 != null) {
            if (start2 != null) {
                addVal += start2.value;
            }
            int sum = start1.value + addVal;
            start1.value = sum % 10;
            addVal = sum / 10;

            if (head == null) {
                //保留头节点
                head = start1;
            }
            Node next1 = start1.next;
            Node next2 = start2 == null ? null : start2.next;
            if (next1 == null) {
                //同时为null
                if (next2 == null) {
                    if (addVal > 0) {
                        start1.next = new Node(addVal, null);
                    }
                    return reverse(head);
                } else {
                    start1.next = next2;
                    start1 = next2;
                    start2 = null;
                }
            } else {
                start1 = next1;
                start2 = next2;
            }
        }
        return reverse(head);
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
