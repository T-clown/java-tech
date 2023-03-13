package arithmetic.linked_list;

/**
 * https://www.nowcoder.com/practice/b58434e200a648c589ca2063f1faf58c?tpId=295&tqId=654&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3FfromPut%3Dpc_kol_wsx
 * 将一个节点数为 size 链表 m 位置到 n 位置之间的区间反转
 */
public class ReverseBetweenNode {
    public static void main(String[] args) {
        Node node = reverseBetween1(new Node(1, new Node(2, null)), 2, 2);
        while (node != null) {
            System.out.println(node.getValue());
            node = node.getNext();
        }
    }

    public static Node reverseBetween1(Node head, int m, int n) {
        //设置虚拟头节点
        Node dummyNode = new Node(-1);
        dummyNode.next = head;
        //找出反转的前一个节点
        Node pre = dummyNode;
        for (int i = 0; i < m - 1; i++) {
            pre = pre.next;
        }
        //反转后的第一个节点
        Node cur = pre.next;
        Node curNext;
        for (int i = 0; i < n - m; i++) {
            //当前节点的下一个节点，即要反转到第一个位置的节点
            curNext = cur.next;
            //这个时候就把curNext节点摘出来了
            cur.next = curNext.next;
            //pre.next为前一次反转后的第一个节点，挂到curNext后面，变成第二个，以此类推
            curNext.next = pre.next;
            //curNext变成反转后的第一个节点，并挂给pre
            pre.next = curNext;
        }
        return dummyNode.next;
    }


    private static Node reverseBetween2(Node head, int m, int n) {
        Node dummy = new Node(-1, head);

        Node startNode = null;
        Node endNode = null;

        Node reverseStartNode = null;
        Node reverseEndNode = null;

        Node pre = null;
        Node curNode = dummy;
        int pointer = 0;
        while (curNode != null) {
            if (pointer == (m - 1)) {
                //反转链表的前一个节点
                startNode = curNode;
            }
            if (pointer == (n + 1)) {
                //反转链表的后一个节点
                endNode = curNode;
            }
            if (m <= pointer && pointer <= n) {
                if (m == pointer) {
                    //反转链表的最后一个节点
                    reverseEndNode = curNode;
                }
                if (n == pointer) {
                    //反转链表的第一个节点
                    reverseStartNode = curNode;
                }
                Node curNext = curNode.getNext();
                curNode.setNext(pre);
                pre = curNode;
                curNode = curNext;
            } else {
                curNode = curNode.getNext();
            }
            pointer++;
        }
        startNode.setNext(reverseStartNode);
        reverseEndNode.setNext(endNode);
        return dummy.getNext();
    }
}
