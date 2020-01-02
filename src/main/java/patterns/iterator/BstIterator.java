
package patterns.iterator;

import java.util.ArrayDeque;
import java.util.NoSuchElementException;


public class BstIterator<T extends Comparable<T>> implements Iterator<TreeNode<T>> {

  private ArrayDeque<TreeNode<T>> pathStack;

  public BstIterator(TreeNode<T> root) {
    pathStack = new ArrayDeque<>();
    pushPathToNextSmallest(root);
  }

  /**
   * This BstIterator manages to use O(h) extra space, where h is the height of the tree It achieves
   * this by maintaining a stack of the nodes to handle (pushing all left nodes first), before
   * handling self or right node.
   *
   * @param node TreeNode that acts as root of the subtree we're interested in.
   */
  private void pushPathToNextSmallest(TreeNode<T> node) {
    while (node != null) {
      pathStack.push(node);
      node = node.getLeft();
    }
  }


  @Override
  public boolean hasNext() {
    return !pathStack.isEmpty();
  }


  @Override
  public TreeNode<T> next() throws NoSuchElementException {
    if (pathStack.isEmpty()) {
      throw new NoSuchElementException();
    }
    var next = pathStack.pop();
    pushPathToNextSmallest(next.getRight());
    return next;
  }

}
