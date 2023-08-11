package arithmetic.linked_list;

/**
 * 给定一个节点数为n的无序单链表，对其按升序排序。
 */
public class SortNode {
    public static void main(String[] args) {
        Node node1 = new Node(9, new Node(8, new Node(7, new Node(6, new Node(5, new Node(0, null))))));
        Node node2 = new Node(1, new Node(3, new Node(2, new Node(4, new Node(5, new Node(0, null))))));
        Node node = sortInList(node2);
        while (node != null) {
            System.out.println(node.getValue());
            node = node.getNext();
        }
    }

    //合并两段有序链表
    private static Node merge(Node pHead1, Node pHead2) {
        //一个已经为空了，直接返回另一个
        if (pHead1 == null) {
            return pHead2;
        }
        if (pHead2 == null) {
            return pHead1;
        }
        //加一个表头
        Node head = new Node(0);
        Node cur = head;
        //两个链表都要不为空
        while (pHead1 != null && pHead2 != null) {
            //取较小值的节点
            if (pHead1.value <= pHead2.value) {
                cur.next = pHead1;
                //只移动取值的指针
                pHead1 = pHead1.next;
            } else {
                cur.next = pHead2;
                //只移动取值的指针
                pHead2 = pHead2.next;
            }
            //指针后移
            cur = cur.next;
        }
        //哪个链表还有剩，直接连在后面
        if (pHead1 != null) {
            cur.next = pHead1;
        } else {
            cur.next = pHead2;
        }
        //返回值去掉表头
        return head.next;
    }

    /**
     * 归并排序，分治思想
     *
     * @param head
     * @return
     */
    public static Node sortInList(Node head) {
        //链表为空或者只有一个元素，直接就是有序的
        if (head == null || head.next == null) {
            return head;
        }
        Node left = head;
        Node mid = head.next;
        Node right = head.next.next;
        //右边的指针到达末尾时，中间的指针指向该段链表的中间
        while (right != null && right.next != null) {
            left = left.next;
            mid = mid.next;
            right = right.next.next;
        }
        //左边指针指向左段的左右一个节点，从这里断开
        left.next = null;
        //分成两段排序，合并排好序的两段
        return merge(sortInList(head), sortInList(mid));
    }

}
