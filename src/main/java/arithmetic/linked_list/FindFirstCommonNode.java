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
     *
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


    /**
     * 计算链表长度的函数
     *
     * @param pHead
     * @return
     */
    public static int listLenth(Node pHead) {
        Node p = pHead;
        int n = 0;
        while (p != null) {
            n++;
            p = p.next;
        }
        return n;
    }

    /**
     * 方法三
     *
     * @param pHead1
     * @param pHead2
     * @return
     */
    public static Node findFirstCommonNode3(Node pHead1, Node pHead2) {
        int p1 = listLenth(pHead1);
        int p2 = listLenth(pHead2);
        //当链表1更长时，链表1指针先走p1-p2步
        if (p1 >= p2) {
            int n = p1 - p2;
            for (int i = 0; i < n; i++) {
                pHead1 = pHead1.next;
            }
            //两个链表同时移动，直到有公共节点时停下
            while ((pHead1 != null) && (pHead2 != null) && (pHead1 != pHead2)) {
                pHead1 = pHead1.next;
                pHead2 = pHead2.next;
            }
        }
        //反之，则链表2先行p2-p1步
        else {
            int n = p2 - p1;
            for (int i = 0; i < n; i++) {
                pHead2 = pHead2.next;
            }
            //两个链表同时移动，直到有公共节点时停下
            while ((pHead1 != null) && (pHead2 != null) && (pHead1 != pHead2)) {
                pHead1 = pHead1.next;
                pHead2 = pHead2.next;
            }
        }
        return pHead1;
    }
}
