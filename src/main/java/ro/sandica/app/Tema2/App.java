package ro.sandica.app.Tema2;

import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
/**
 * Hello world!
 *
 */
public class App 
{
	@SuppressWarnings("restriction")
    public static void main( String[] args ) throws Exception
    {
    	
		HttpServer server = HttpServer.create(new InetSocketAddress(8001), 0);
		server.createContext("/", new Register());
        server.createContext("/login", new Login());
        server.createContext("/home", new Home());
        server.createContext("/services", new Services());
        server.createContext("/products", new Products());
        server.createContext("/about", new AboutUs());
        server.createContext("/contact", new Contact());
        server.createContext("/prices", new Prices());
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println( "The server has started on port 8001..." );
    }
}
