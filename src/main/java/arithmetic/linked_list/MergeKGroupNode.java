package arithmetic.linked_list;

import java.util.Arrays;
import java.util.List;

/**
 * 合并 k 个升序的链表并将结果作为一个升序的链表返回其头节点。
 */
public class MergeKGroupNode {
    public static void main(String[] args) {
        Node node1 = new Node(1, new Node(3, new Node(5, null)));
        Node node2 = new Node(2, new Node(4, new Node(6, null)));
        Node node3 = new Node(0, new Node(8, new Node(9, null)));
        List<Node> nodes = Arrays.asList(node1, node2, node3);
        Node node = merge(nodes);
        while (node != null) {
            System.out.println(node.getValue());
            node = node.getNext();
        }
    }


    private static Node merge(List<Node> lists) {
        if (lists == null || lists.size() == 0) {
            return null;
        }
        Node head = null;
        for (Node node : lists) {
            head = merge(head, node);
        }
        return head;
    }

    private static Node merge(Node head1, Node head2) {
        return MergeNode.merge(head1, head2);
    }


}
