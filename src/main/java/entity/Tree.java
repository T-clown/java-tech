package entity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
@AllArgsConstructor
@Data
public class Tree {
    private int id;
    private String name;

    private List<Tree> trees;

    public static void main(String[] args) {
        Tree tree = new Tree(0, "0",null);
        Tree tree1 = new Tree(1, "1",null);
        Tree tree2= new Tree(2, "2",null);
        tree.setTrees(Arrays.asList(tree1,tree2));

        Tree tree3 = new Tree(3, "3",null);

        Tree tree4 = new Tree(4, "4",null);
        tree1.setTrees(Arrays.asList(tree3));
        tree2.setTrees(Arrays.asList(tree4));

        List<Integer> result=new ArrayList<>();
        List<Integer> list = list(tree, result);
        System.out.println(list.size());
    }

    private static List<Integer> list(Tree tree, List<Integer> list) {
        if (tree == null) {
            return list;
        }
        list.add(tree.getId());

        if (CollectionUtils.isEmpty(tree.trees)) {
            return list;
        }
        tree.getTrees().forEach(x -> list(x, list));
        return list;
    }
}
