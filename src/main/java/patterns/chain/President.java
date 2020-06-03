
package patterns.chain;

/**
 * President.
 */
public class President extends RequestHandler {

  public President(RequestHandler handler) {
    super(handler);
  }

  @Override
  public void handleRequest(Request req) {
    if (RequestType.SPEECH == req.getRequestType()) {
      printHandling(req);
      req.markHandled();
    } else {
      super.handleRequest(req);
    }
  }

  @Override
  public String toString() {
    return "校长";
  }
}
