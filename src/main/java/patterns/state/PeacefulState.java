
package patterns.state;


/**
 * Peaceful state.
 */
public class PeacefulState implements State {

  @Override
  public void doAction(Context context) {
    System.out.println("Player is in Peaceful state");
    context.setState(this);
  }

  @Override
  public String toString(){
    return "Peaceful State";
  }

}
