package ro.sandica.app.Tema2;

import DB.DBConnection;

public class TestConnection {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		DBConnection con = new DBConnection();
		System.out.println(con);
		
	
		try {
			System.out.println(con.getPasswordByUsername("Catalin", "users"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
