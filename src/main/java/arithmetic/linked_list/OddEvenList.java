package arithmetic.linked_list;

/**
 * 给定一个单链表，请设定一个函数，将链表的奇数位节点和偶数位节点分别放在一起，重排后输出。
 * 注意是节点的编号而非节点的数值。
 */
public class OddEvenList {
    public static void main(String[] args) {
        //Node node = oddEvenList(Node.create(1, 6));
        //Node node = oddEvenList2(Node.create(1, 6));
        Node node = oddEvenList2(new Node(41, new Node(67)));
        while (node != null) {
            System.out.println(node.getValue());
            node = node.getNext();
        }
    }

    /**
     * 答案
     *
     * @param head
     * @return
     */
    public Node oddEvenList3(Node head) {
        //如果链表为空，不用重排
        if (head == null || head.next == null) {
            return head;
        }
        //even开头指向第二个节点，可能为空
        Node even = head.next;
        //odd开头指向第一个节点
        Node odd = head;
        //指向even开头
        Node evenHead = even;
        while (even != null && even.next != null) {
            //odd连接even的后一个，即奇数位
            odd.next = even.next;
            //odd进入后一个奇数位
            odd = odd.next;
            //even连接后一个奇数的后一位，即偶数位
            even.next = odd.next;
            //even进入后一个偶数位
            even = even.next;
        }
        //even整体接在odd后面
        odd.next = evenHead;
        return head;
    }

    /**
     * 非递归
     *
     * @param head
     * @return
     */
    public static Node oddEvenList2(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        //奇数节点
        Node odd = new Node(-1);
        //偶数节点
        Node even = new Node(-1);

        int depth = 1;
        Node nextEven = even;
        Node curNode = head;
        while (curNode != null) {
            Node next = curNode.next;
            curNode.next = null;
            if (depth % 2 == 1) {
                //奇数节点
                odd.next = curNode;
                odd = curNode;
            } else {
                //偶数节点
                nextEven.next = curNode;
                nextEven = curNode;
            }
            depth++;
            curNode = next;
        }

        curNode = head;
        while (true) {
            if (curNode.next == null) {
                curNode.next = even.next;
                break;
            }
            curNode = curNode.next;
        }
        return head;
    }

    //奇数节点
    static Node odd = new Node(-1);
    //偶数节点
    static Node even = new Node(-1);

    /**
     * 递归
     *
     * @param head
     * @return
     */
    public static Node oddEvenList(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        //奇数节点
        Node odd1 = odd;
        //偶数节点
        Node even1 = even;

        recursion(head, 1);

        Node curNode = odd1.next;
        while (curNode != null) {
            if (curNode.next == null) {
                curNode.next = even1.next;
                break;
            }
            curNode = curNode.next;
        }
        return odd1.next;
    }

    public static void recursion(Node head, int depth) {
        if (head == null) {
            return;
        }
        Node next = head.next;
        head.next = null;
        if (depth % 2 == 1) {
            //奇数节点
            odd.next = head;
            odd = head;
        } else {
            //偶数节点
            even.next = head;
            even = head;
        }
        recursion(next, depth + 1);
    }


}
