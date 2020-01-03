

package patterns.chain;


public class App {


  public static void main(String[] args) {

    RequestHandler chain=buildChain();
    chain.handleRequest(new Request(RequestType.SPEECH, "演讲"));
    chain.handleRequest(new Request(RequestType.TEACH, "教书"));
    chain.handleRequest(new Request(RequestType.STUDY, "学习"));

  }


  private static RequestHandler buildChain() {
    return new President(new Teacher(new Student(null)));
  }

}
