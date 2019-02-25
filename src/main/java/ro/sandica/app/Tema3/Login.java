package ro.sandica.app.Tema3;


import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import DB.DBConnection;


@SuppressWarnings("restriction")
public class Login implements HttpHandler{
	
	private static final String U_NAME = "uname";
    private static final String P_WORD = "pword";
    
    public static String uName;
    public static String pWord;
    
    DBConnection con = new DBConnection();
	
     public void handle(HttpExchange t) throws IOException {

    	 URI uri = t.getRequestURI();
    	 String response = "<html><title>Login</title><body><h1>Login</h1>"
    		  		+ "<br><br>"
    		  		+ "<form action=\"http://localhost:8001/home\">"
    		  		+ "UserName: <input type=\"text\" name=\"fname\"><br><br>"
    		  		+ "Password: <input type=\"text\" name=\"lname\"><br>"
    		  		+ "<input type=\"submit\" value=\"Submit\">"
    		  		+ "</form>"
    		  		+ "<br><br>"
    		  		+ "<a href=\"http://localhost:8001\">Go to register</a>"
    		  		+ "</body></html>";
    	 
         t.sendResponseHeaders(200, response.getBytes().length);
         OutputStream os = t.getResponseBody();
         os.write(response.getBytes());
         os.close();
         splitQueryFromURI(uri);
         System.out.println("Username = "+uName + ", Password = " +pWord);
         
         try {
			con.insertValuesInDB(uName, pWord, "users");
			System.out.println("The values were inserted successfully...");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

     }
     
     private void splitQueryFromURI(URI uri) { 
         
         String query = uri.getQuery();
         if (query != null) {
             System.out.println("Query: " + query);
             String[] queryParams = query.split("&");
             if (queryParams.length > 0) {
                 for (String qParam : queryParams) {
                     String[] param = qParam.split("=");
                     if (param.length > 0) {
                         for (int i = 0; i < param.length; i++) {
                             if (U_NAME.equalsIgnoreCase(param[0])) {
                                 uName = param[1];
                             }
                             if (P_WORD.equalsIgnoreCase(param[0])) {
                                 pWord = param[1];
                             }
                         }
                     }
                 }
             }
         }
          
     }
     
     
	

}
