package arithmetic.company.huawei;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 给定坐标轴上的一组线段，线段的起点和终点均为整数并且长度不小于1，请你从中找到最少数量的线段，这些线段可以覆盖住所有线段。
 * 问题：线段的起点和终点能否重复？
 */
public class SegmentCovering {



    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int count = Integer.parseInt(in.nextLine());

        // 所有的线段
        Map<Integer, Integer> segments = new HashMap<>();
        for (int i = 0; i < count; i++) {
            String[] split = in.nextLine().split(",");
            //线段起点重复的，取最长的一条
            Integer orDefault = segments.getOrDefault(Integer.parseInt(split[0]), -1);
            segments.put(Integer.parseInt(split[0]), Math.max(Integer.parseInt(split[1]), orDefault));
        }

        // 按线段起点排序
        List<Integer> heads = segments.keySet().stream().sorted(Comparator.comparing(x -> x)).collect(Collectors.toList());


        // 选择的线段
        LinkedList<Integer> selectedHeads = new LinkedList<>();
        // 遍历线段
        Integer head1 = heads.get(0);
        Integer tail1 = segments.get(head1);

        for (int i = 1; i < heads.size(); i++) {
            Integer head = heads.get(i);
            Integer tail = segments.get(head);

            if (tail <= tail1) {
                //前面线段包含了当前线段：跳过当前线段
                continue;
            }
            if (head > tail1) {
                //当前线段和前面线段无交集
                selectedHeads.add(head1);
                head1 = head;
                tail1 = tail;
                continue;
            }
            //两个线段有交集
            if (selectedHeads.isEmpty()) {
                selectedHeads.add(head1);
                head1 = head;
                tail1 = tail;
            } else {
                //上上条线段
                Integer last = selectedHeads.getLast();
                Integer lastTail = segments.get(last);
                if (head <= lastTail) {
                    //上上条线段和当前线段合起来覆盖了上一条线段：放弃上一条线段
                    selectedHeads.remove(head1);
                    head1 = head;
                    tail1 = tail;
                } else {
                    //三条线段都有自己的部分，则把上一条加入集合，继续
                    selectedHeads.add(head1);
                    head1 = head;
                    tail1 = tail;
                }
            }
        }
        selectedHeads.add(head1);
        System.out.println(selectedHeads.size());

    }
}



