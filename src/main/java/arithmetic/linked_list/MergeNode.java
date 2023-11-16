package arithmetic.linked_list;

/**
 * 输入两个递增的链表，单个链表的长度为n，合并这两个链表并使新链表中的节点仍然是递增排序的。
 */
public class MergeNode {
    public static void main(String[] args) {
        Node node1 = new Node(1, new Node(3, new Node(5, null)));
        Node node2 = new Node(2, new Node(3, new Node(6, null)));
        Node node = merge(node1, node2);
        while (node != null) {
            System.out.println(node.getValue());
            node = node.getNext();
        }
    }


    public static Node merge(Node head1, Node head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }
        Node headNode = head1.value <= head2.value ? head1 : head2;
        Node secondNode = head1.value <= head2.value ? head2 : head1;
        Node firstNode = headNode;
        while (true) {
            Node next = firstNode.next;
            if (next == null) {
                firstNode.next = secondNode;
                break;
            }
            if (next.value <= secondNode.value) {
                firstNode = next;
            } else {
                firstNode.next = secondNode;
                secondNode = next;
            }
        }
        return headNode;
    }
}
