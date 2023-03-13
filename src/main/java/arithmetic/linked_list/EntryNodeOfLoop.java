package arithmetic.linked_list;

/**
 * 给一个长度为n链表，若其中包含环，请找出该链表的环的入口结点，否则，返回null。
 */
public class EntryNodeOfLoop {
    public static Node entryNodeOfLoop(Node pHead) {
        if (pHead == null) {
            return null;
        }
        // 定义快慢指针
        Node slow = pHead;
        Node fast = pHead;
        while (fast != null && fast.next != null) {
            // 快指针是满指针的两倍速度
            fast = fast.next.next;
            slow = slow.next;
            // 记录快慢指针第一次相遇的结点
            if (slow == fast) {
                break;
            }
        }
        // 若是快指针指向null，则不存在环
        if (fast == null || fast.next == null) {
            return null;
        }
        // 重新指向链表头部
        fast = pHead;
        // 与第一次相遇的结点相同速度出发，相遇结点为入口结点
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}
