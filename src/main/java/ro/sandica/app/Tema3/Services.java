package ro.sandica.app.Tema3;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

@SuppressWarnings("restriction")
public class Services implements HttpHandler{
	Home home = new Home();
	
	
	
public void handle(HttpExchange exchange) throws IOException {
		
		
		String response = "<html><head><title>Services</title>"
				+ "<style>"
				+ "body {margin: 0 auto;}"
				+ "ul li {display:inline;list-type:none;padding:3px 10px;}"
				+ "h1 {text-align:center}"
				+ "</style>"
				+ "</head>"
				+ "<ul>"
				+ "<li><a href=\"http://localhost:8001/login\">Log out</a></li>"
				+ "<li><a href=\"http://localhost:8001/home\\\">Home</a></li>"
				+ "<li><a href=\"http://localhost:8001/products\">Products</a></li>"
				+ "<li><a href=\"http://localhost:8001/prices\">Prices</a></li>"
				+ "<li><a href=\"http://localhost:8001/about\">About</a></li>"
				+ "<li><a href=\"http://localhost:8001/contact\">Contact</a></li>"
				+ "</ul>"
				+ "<h1>Welcome to Services page, "
				+ " "+ home.fName 
				+ "</h1>"
				+ "<body></html>";
        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
        
    }
}
