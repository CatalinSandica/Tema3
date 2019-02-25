package ro.sandica.app.Tema3;

import com.sun.net.httpserver.*;

import DB.DBConnection;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

@SuppressWarnings("restriction")
public class Home implements HttpHandler{
	  
    private static final String F_NAME = "fname";
    private static final String L_NAME = "lname";
    
    public static String fName;
    public static String lName;
    
    DBConnection con = new DBConnection();
    
	public void handle(HttpExchange exchange) throws IOException {
		
		URI uri = exchange.getRequestURI();
		String response = createResponseFromQueryParams(uri);
        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
        System.out.println(fName + " " +lName);
        
    }
	
	private String createResponseFromQueryParams(URI uri) {
        
        //Get the request query
        String query = uri.getQuery();
        if (query != null) {
            System.out.println("Query: " + query);
            String[] queryParams = query.split("&");
            if (queryParams.length > 0) {
                for (String qParam : queryParams) {
                    String[] param = qParam.split("=");
                    if (param.length > 0) {
                        for (int i = 0; i < param.length; i++) {
                            if (F_NAME.equalsIgnoreCase(param[0])) {
                                fName = param[1];
                            }
                            if (L_NAME.equalsIgnoreCase(param[0])) {
                                lName = param[1];
                            }
                        }
                    }
                }
            }
        }
        try {
			System.out.println(con.getPasswordByUsername(fName, "users"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
         try {
			if(con.getPasswordByUsername(fName, "users").equals(lName)) {
				
				return "<html><head><title>Home</title>"
						+ "<style>"
						+ "body {margin: 0 auto;}"
						+ "ul li {display:inline;list-type:none;padding:3px 10px;}"
						+ "h1 {text-align:center}"
						+ "</style>"
						+ "</head>"
						+ "<ul>"
						+ "<li><a href=\"http://localhost:8001/login\">Log out</a></li>"
						+ "<li><a href=\"http://localhost:8001/services\\\">Services</a></li>"
						+ "<li><a href=\"http://localhost:8001/products\">Products</a></li>"
						+ "<li><a href=\"http://localhost:8001/prices\">Prices</a></li>"
						+ "<li><a href=\"http://localhost:8001/about\">About</a></li>"
						+ "<li><a href=\"http://localhost:8001/contact\">Contact</a></li>"
						+ "</ul>"
						+ "<h1>Welcome,"
						+ " "+ fName
						+ "</h1>"
						+ "<body></html>" ;
			 }
			else {
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "<html><title>Login</title><body><h1>Login</h1>"
		  		+ "<br><br>"
		  		+ "<form action=\"http://localhost:8001/home\">"
		  		+ "UserName: <input type=\"text\" name=\"fname\"><br><br>"
		  		+ "Password: <input type=\"text\" name=\"lname\"><br>"
		  		+ "<input type=\"submit\" value=\"Submit\">"
		  		+ "</form>"
		  		+ "<br><br>"
		  		+ "<a href=\"http://localhost:8001\">Go to register</a>"
		  		+ "</body></html>";
    }

   
}
