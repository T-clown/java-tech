
package patterns.state;



/**
 * Angry state.
 */
public class AngryState implements State {

  @Override
  public void doAction(Context context) {
    System.out.println("Player is in Angry state");
    context.setState(this);
  }

  @Override
  public String toString(){
    return "Angry State";
  }

}
