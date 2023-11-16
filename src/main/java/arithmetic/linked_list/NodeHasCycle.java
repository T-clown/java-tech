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


    public boolean hasCycle2(Node head) {
        //先判断链表为空的情况
        if (head == null) {
            return false;
        }
        //快慢双指针
        Node fast = head;
        Node slow = head;
        //如果没环快指针会先到链表尾
        while (fast != null && fast.next != null) {
            //快指针移动两步
            fast = fast.next.next;
            //慢指针移动一步
            slow = slow.next;
            //相遇则有环
            if (fast == slow) {
                return true;
            }
        }
        //到末尾则没有环
        return false;
    }
}
