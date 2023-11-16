package arithmetic.double_pointer;

import com.alibaba.fastjson2.JSON;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 给出一组区间，请合并所有重叠的区间。
 * 请保证合并后的区间按区间起点升序排列。
 */
public class MergeInterval {

    public static void main(String[] args) {
        ArrayList<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(10, 30));
        intervals.add(new Interval(20, 60));
        intervals.add(new Interval(80, 100));
        intervals.add(new Interval(150, 180));
        System.out.println(JSON.toJSONString(merge(intervals)));
    }

    public static ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1) {
            return intervals;
        }
        //先排序，根据start从小到大排序
        List<Interval> sortedIntervals = intervals.stream().sorted(Comparator.comparing(x -> x.start)).collect(Collectors.toList());
        ArrayList<Interval> res = new ArrayList<>();
        Interval pre = sortedIntervals.get(0);
        for (int i = 1, size = sortedIntervals.size(); i < size; i++) {
            Interval cur = sortedIntervals.get(i);
            if (cur.start <= pre.end) {
                int start = Math.min(pre.start, cur.start);
                int end = Math.max(pre.end, cur.end);
                pre.start = start;
                pre.end = end;
            } else {
                res.add(pre);
                pre = cur;
            }
            if (i == (size - 1)) {
                res.add(pre);
            }
        }
        return res;
    }

    static class Interval {
        int start;
        int end;

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }
}
