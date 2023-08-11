package network.tomcat.servlet;


import network.tomcat.http.Request;
import network.tomcat.http.Response;
import network.tomcat.http.AbstractServlet;

public class FirstServlet extends AbstractServlet {

	@Override
	public void doGet(Request request, Response response) throws Exception {
		this.doPost(request, response);
	}

	@Override
	public void doPost(Request request, Response response) throws Exception {
		response.write("This is First Serlvet");
	}

}
