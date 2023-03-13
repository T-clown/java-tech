package arithmetic.linked_list;

/**
 * 判断给定的链表中是否有环。如果有环则返回true，否则返回false。
 */
public class NodeHasCycle {

    public static void main(String[] args) {
        Node node = new Node(2, new Node(0, new Node(-4, new Node(2, null))));
        System.out.println(hasCycle(node));
    }

    private static boolean hasCycle(Node head) {
        Node step1Node = head;
        Node step2Node = head;
        while (step1Node != null) {
            if (step2Node == null) {
                return false;
            }
            Node step2NodeNext = step2Node.next;
            if (step2NodeNext == null) {
                return false;
            } else {
                step2Node = step2NodeNext.next;
            }
            step1Node = step1Node.next;
            if (step1Node == step2Node) {
                return true;
            }
        }
        return false;
    }
}
