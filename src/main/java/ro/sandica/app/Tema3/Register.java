package ro.sandica.app.Tema3;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


@SuppressWarnings("restriction")
public class Register implements HttpHandler{
	 public void handle(HttpExchange t) throws IOException {
	     
         String response = "<html><title>Register</title><body><h1>Register</h1>"
         		+ "<br><br>"
         		+ "<form action=\"http://localhost:8001/login\">"
         		+ "UserName: <input type=\"text\" name=\"uname\"><br><br>"
         		+ "Password: <input type=\"text\" name=\"pword\"><br>"
         		+ "<input type=\"submit\" value=\"Submit\">"
         		+ "</form>"
         		+ "<br><br>"      		
         		+ "</body></html>";
         
         t.sendResponseHeaders(200, response.length());
         OutputStream os = t.getResponseBody();
         os.write(response.getBytes());
         os.close();    
         
         
     }
}
