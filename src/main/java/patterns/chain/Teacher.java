

package patterns.chain;

/**
 * Teacher.
 */
public class Teacher extends RequestHandler {

  public Teacher(RequestHandler handler) {
    super(handler);
  }

  @Override
  public void handleRequest(Request req) {
    if (RequestType.TEACH == req.getRequestType()) {
      printHandling(req);
      req.markHandled();
    } else {
      super.handleRequest(req);
    }
  }

  @Override
  public String toString() {
    return "老师";
  }

}
