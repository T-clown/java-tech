package arithmetic.linked_list;

/**
 * 输入两个无环的单向链表，找出它们的第一个公共结点，如果没有公共节点则返回空。
 */
public class FindFirstCommonNode {

    public static void main(String[] args) {
        Node commonNode = new Node(6, new Node(7, null));
        Node node1 = new Node(1, new Node(2, new Node(3, commonNode)));
        Node node2 = new Node(4, new Node(5, commonNode));
        Node firstCommonNode = findFirstCommonNode(node1, node2);
        while (firstCommonNode != null) {
            System.out.println(firstCommonNode.getValue());
            firstCommonNode = firstCommonNode.getNext();
        }
    }

    /**
     * 解法一：两个指针都走一遍head1和head2，则一定会在第一个公共节点相遇
     * 解法二：利用Set
     * @param head1
     * @param head2
     * @return
     */
    private static Node findFirstCommonNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node start1 = head1;
        Node start2 = head2;
        while (true) {
            if (start1 == start2) {
                return start1;
            }
            start1 = (start1 == null) ? head2 : start1.next;
            start2 = (start2 == null) ? head1 : start2.next;
        }
    }
}
