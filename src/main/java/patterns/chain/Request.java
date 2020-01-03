

package patterns.chain;

import java.util.Objects;

/**
 * Request.
 */
public class Request {


  private final RequestType requestType;


  private final String requestDesc;


  private boolean handled;


  public Request(final RequestType requestType, final String requestDesc) {
    this.requestType = Objects.requireNonNull(requestType);
    this.requestDesc = Objects.requireNonNull(requestDesc);
  }


  public String getRequestDesc() {
    return requestDesc;
  }


  public RequestType getRequestType() {
    return requestType;
  }

  public void markHandled() {
    this.handled = true;
  }

  public boolean isHandled() {
    return this.handled;
  }

  @Override
  public String toString() {
    return getRequestDesc();
  }

}
