package arithmetic.linked_list;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点并返回链表的头指针
 */
public class RemoveNthFromEnd {
    public static void main(String[] args) {
        Node head = new Node(1, new Node(2, new Node(3, new Node(4, new Node(5, null)))));
        Node node = removeNthFromEnd(head, 2);
        while (node != null) {
            System.out.println(node.getValue());
            node = node.getNext();
        }
    }

    /**
     * 双指针
     *
     * @param head
     * @param n
     */
    private static Node deleteNode(Node head, int n) {
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

    private static Node findNode(Node head, int n) {
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
        return n >= 0 ? null : second.next;
    }

    public static Node removeNthFromEnd(Node head, int n) {
        Node dumpy = new Node(-1, head);
        Node first = dumpy;
        Node second = dumpy;

        while (first != null) {
            first = first.next;
            if (n < 0) {
                second = second.next;
            }
            n--;
        }
        second.next = second.next.next;
        return dumpy.next;
    }
}
