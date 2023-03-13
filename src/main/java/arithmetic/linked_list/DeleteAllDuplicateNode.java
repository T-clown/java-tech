package arithmetic.linked_list;

/**
 * 删除给出链表中的重复元素（链表中元素从小到大有序），使链表中的所有元素都只出现一次
 */
public class DeleteAllDuplicateNode {

    public static void main(String[] args) {
        Node head = new Node(2, new Node(3, new Node(3, new Node(9, new Node(9, null)))));
        Node node = deleteDuplicates(head);
        while (node != null) {
            System.out.println(node.getValue());
            node = node.getNext();
        }
    }

    private static Node deleteDuplicates(Node head) {
        Node dummy = new Node(-1, head);
        Node lastNode = dummy;
        Node curNode = dummy;
        boolean curNodeIsDup = false;
        while (true) {
            Node nextNode = curNode.next;
            if (nextNode == null) {
                if (curNodeIsDup) {
                    lastNode.next = null;
                }
                return dummy.next;
            }
            if (curNode.value == nextNode.value) {
                curNode.next = nextNode.next;
                curNodeIsDup = true;
            } else {
                if (curNodeIsDup) {
                    lastNode.next = nextNode;
                    curNodeIsDup = false;
                } else {
                    lastNode = curNode;
                }
                curNode = nextNode;
            }
        }
    }
}
