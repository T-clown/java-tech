

package patterns.state;

import lombok.Data;

/**
 * Context has internal state that defines its behavior.
 */
@Data
public class Context {

  private State state;

  public Context() {
    state = new PeacefulState();
  }

  @Override
  public String toString() {
    return "The context";
  }

}
