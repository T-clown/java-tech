package arithmetic.top100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * https://mp.weixin.qq.com/s/pFJKlF2J7icWuO6ITvsSQw
 */
public class GroupAnagrams {
    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(JSON.toJSONString(groupAnagrams(strs)));

    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) { return new ArrayList<>(); }
        Map<String, List<String>> map = new HashMap<>();
        //处理技巧，同一个类别中string的char数组排序后是一样的，以这个作为map的key
        for (String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String keyStr = String.valueOf(ca);

            if (!map.containsKey(keyStr)) { map.put(keyStr, new ArrayList<>()); }

            map.get(keyStr).add(s);
        }
        return new ArrayList<>(map.values());
    }

}
