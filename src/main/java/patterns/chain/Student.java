

package patterns.chain;

/**
 * Student.
 */
public class Student extends RequestHandler {

  public Student(RequestHandler handler) {
    super(handler);
  }

  @Override
  public void handleRequest(Request req) {
    if (RequestType.STUDY == req.getRequestType()) {
      printHandling(req);
      req.markHandled();
    } else {
      super.handleRequest(req);
    }
  }

  @Override
  public String toString() {
    return "学生";
  }
}
