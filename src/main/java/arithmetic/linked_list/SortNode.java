package arithmetic.linked_list;

/**
 * 给定一个节点数为n的无序单链表，对其按升序排序。
 */
public class SortNode {
    public static void main(String[] args) {
        Node node1 = new Node(9, new Node(8, new Node(7, new Node(6,new Node(5,new Node(0,null))))));
        Node node = sortNode(node1);
        while (node != null) {
            System.out.println(node.getValue());
            node = node.getNext();
        }
    }


    public static Node sortNode(Node head) {
        Node curNode = head;
        Node sortedHead = null;
        while (curNode != null) {
            Node curNext = curNode.next;
            curNode.next = null;
            sortedHead = sort(sortedHead, curNode);
            curNode = curNext;
        }
        return sortedHead;
    }

    public static Node sort(Node sortedNode, Node node) {
        if (node == null) {
            return sortedNode;
        }
        if (sortedNode == null) {
            return node;
        }
        Node dummy = new Node(-1, sortedNode);
        Node curNode = dummy;
        while (true) {
            Node curNext = curNode.next;
            if (curNext == null) {
                curNode.next = node;
                return dummy.next;
            }
            if (curNext.value <= node.value) {
                curNode = curNext;
            } else {
                curNode.next = node;
                node.next = curNext;
                return dummy.next;
            }
        }
    }
}
