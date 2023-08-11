package arithmetic.linked_list;

/**
 * https://www.nowcoder.com/practice/b49c3dc907814e9bbfa8437c251b028e?tpId=295&tqId=722&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3FfromPut%3Dpc_kol_wsx
 * 将给出的链表中的节点每 k 个一组翻转，返回翻转后的链表
 * 如果链表中的节点数不是 k 的倍数，将最后剩下的节点保持原样
 * 你不能更改节点中的值，只能更改节点本身。
 */
public class ReverseKNode {
    public static void main(String[] args) {
        Node node = reverseKGroup3(Node.create(14), 3);
        while (node != null) {
            System.out.println(node.getValue());
            node = node.getNext();
        }
    }

    /**
     *
     * @param head
     * @param k
     * @return
     */
    public static Node reverseKGroup3(Node head, int k) {
        Node dummy = new Node(0);
        Node res = dummy;
        Node curNode = head;
        while (curNode != null) {
            Node node = curNode;
            for (int i = 1; i < k; i++) {
                node = node.next;
                if (node == null) {
                    //不足k个不用反转，直接返回
                    res.next = curNode;
                    return dummy.next;
                }
            }
            Node next = node.next;
            res.next = reverse(curNode, k);
            res = curNode;
            curNode = next;
        }
        return dummy.next;
    }

    public static Node reverse(Node head, int k) {
        Node pre = null;
        while (k > 0) {
            Node next = head.next;
            head.next = pre;
            pre = head;
            head = next;
            k--;
        }
        return pre;
    }


    public Node reverseKGroup2(Node head, int k) {
        //先创建一个哑节点
        Node dummy = new Node(0);
        //让哑节点的指针指向链表的头
        dummy.next = head;
        //开始反转的前一个节点，比如反转的节点范围是[link1，link2],
        //那么pre就是link1的前一个节点
        Node pre = dummy;
        Node end = dummy;
        while (end.next != null) {
            //每k个反转，end是每k个链表的最后一个
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            //如果end是空，说明不够k个，就不需要反转了，直接退出循环。
            if (end == null) {
                break;
            }
            //反转开始的节点
            Node start = pre.next;
            //next是下一次反转的头结点，先把他记录下来
            Node next = end.next;
            //因为end是这k个链表的最后一个结点，把它和原来链表断开，
            //这k个节点我们可以把他们看做一个小的链表，然后反转这个
            //小链表
            end.next = null;
            //因为pre是反转链表的前一个节点，我们把小链表[start,end]
            //反转之后，让pre的指针指向这个反转的小链表
            pre.next = reverse(start);
            //注意经过上一步反转之后，start反转到链表的尾部了，就是已经
            //反转之后的尾结点了，让他之前下一次反转的头结点即可（上面分析
            //过，next就是下一次反转的头结点）
            start.next = next;
            //前面反转完了，要进入下一波了，pre和end都有重新赋值
            pre = start;
            end = start;
        }
        return dummy.next;
    }

    //链表的反转
    private Node reverse(Node head) {
        Node pre = null;
        Node curr = head;
        while (curr != null) {
            Node next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

    public static Node reverseKGroup(Node head, int k) {
        //找到每次翻转的尾部
        Node tail = head;
        //遍历k次到尾部
        for (int i = 0; i < k; i++) {
            //如果不足k到了链表尾，直接返回，不翻转
            if (tail == null) {
                return head;
            }
            tail = tail.next;
        }
        //翻转时需要的前序和当前节点
        Node pre = null;
        Node cur = head;
        //在到达当前段尾节点前
        while (cur != tail) {
            //翻转
            Node temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        //当前尾指向下一段要翻转的链表
        head.next = reverseKGroup(tail, k);
        return pre;
    }
}
