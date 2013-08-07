import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MSAccessDatabase
{
	static Connection conn = null;
	public static ArrayList<String> categorySelectionList;
//	private static ArrayList<String> categorySelectionList;
	
	public static Connection getConnection()
	{
		try{
 			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver"); //find the ODBC-JDBC driver

		} catch(ClassNotFoundException e) {
			System.out.println(e);
		}
			
		try{

			conn = DriverManager.getConnection("jdbc:odbc:MaxsRoadsideDiner","","");

		}catch(SQLException se){
			System.out.println(se);
		}
		System.out.println("Connection Successful");
		
		return conn;
	}
	
	public static boolean authenticateUser(String inputUserID, String inputPassword) throws SQLException, ClassNotFoundException 		
	{
		boolean userFound = false;
		
		Connection conn = getConnection();

		System.out.println(inputUserID);
		System.out.println(inputPassword);
	
		try{

			String selectSQL = "SELECT * FROM Employee WHERE userID = '" + inputUserID + "' AND Password = '" + inputPassword + "'";

			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(selectSQL);
			
			if (rset.next())
			{
				userFound = true;
			}
		
		stmt.close();
	} catch(SQLException se) {
		System.out.println(se);
	}
	
	return userFound;
	}
	
	public static void createOrderTable() 		
	{
		Connection conn = getConnection();

		try{
			   Statement stmt = conn.createStatement();
			   String table = 
			   "CREATE TABLE CustomerOrder(ReceiptNumber integer, QuantitySold integer, ItemDescription varchar(25), Price currency)";
			   stmt.executeUpdate(table);
			   System.out.println("Table creation process successfully!");
			   stmt.close();
			   }
			   catch(SQLException s){
			   System.out.println("Table all ready exists!");
			   }
	}
	
	public static boolean getItem(String itemOrdered)
	{
		boolean itemFound = false;
		Connection conn = getConnection();

		System.out.println(itemOrdered);
	
		try{

			String selectSQL = "SELECT * FROM Inventory WHERE itemDescription = '" + itemOrdered + "'";

			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(selectSQL);
			
			if (rset.next())
			{
				ItemInventory.setItemNumber(rset.getInt(1));
				ItemInventory.setItemCategory(rset.getString(2));
				ItemInventory.setName(rset.getString(3));
				ItemInventory.setItemDescription(rset.getString(4));
				ItemInventory.setSize(rset.getString(5));
				ItemInventory.setPrice(rset.getDouble(6));

				itemFound = true;
			}
			
			stmt.close();
		} catch(SQLException se) {
			System.out.println(se);
		}
		
		return itemFound;
	}
/*	
	public static void updateOrder(int ReceiptNumber, String itemDescription)
	{
		Connection conn = getConnection();

		String SQL_UPDATE = "UPDATE CustomerOrder SET QuantitySold = ?, Price = ? WHERE ReceiptNumber = ?, itemDescription = ?"; 
	
		try{

			PreparedStatement stmt = conn.prepareStatement(SQL_UPDATE);
			
			if(stmt != null)
			{
				
				Double Price = getQtySold() * ItemInventory.getPrice();
				
				stmt.setInt(1, Account.getQtySold());
				stmt.setDouble(2, Price);
				
			
    
				stmt.executeUpdate();
	        
	        System.out.println("Checking:  " + Account.getCheckingAcctBalance());
	        System.out.println("Savings:  " + Account.getSavingsAcctBalance());
	        System.out.println("Account:  " + Account.getAcctNumber());
	        
			stmt.close();
		} catch(SQLException se) {
			System.out.println(se);
		}
		
		
	}*/

	public static ArrayList<String> getCategorySelections() {
		
		Connection conn = getConnection();
		
		String selectSQL = "SELECT DISTINCT Category FROM Inventory";
		
		ArrayList<String> categorySelectionList = new ArrayList<String>(); 
		
		try{

			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(selectSQL);
			
			
			while (rset.next()) { 
				String category = rset.getString(1);
			    categorySelectionList.add(category); 
			}    
			
			System.out.println("Count = " + categorySelectionList.size());
			System.out.println(categorySelectionList);
			
			stmt.close();
		} catch(SQLException se) {
			System.out.println(se);
		}
		
		
		return categorySelectionList;

	}
	
	public static ArrayList<String> getItemSelections(String itemCategory) {
		
		Connection conn = getConnection();
		
		System.out.println(itemCategory);
		
		String selectSQL = "SELECT itemDescription FROM Inventory WHERE Category = '" + itemCategory + "'";
		
		ArrayList<String> itemSelectionList = new ArrayList<String>(); 
		
		try{

			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(selectSQL);
			
			
			while (rset.next()) { 
				String item = rset.getString(1);
			    itemSelectionList.add(item); 
			}    
			
			System.out.println("Count = " + itemSelectionList.size());
			System.out.println(itemSelectionList);
			
			stmt.close();
		} catch(SQLException se) {
			System.out.println(se);
		}
		
		
		return itemSelectionList;

	}
	
	public static void addEmployee()
	{
		Connection conn = getConnection();

		String SQL_INSERT = "INSERT INTO EMPLOYEE" 
		    + " VALUES(?, ?)"; 

		try{

			PreparedStatement stmt = conn.prepareStatement(SQL_INSERT);
			
	        stmt.setString(1, Employee.getEmployeeUserID()); 
	        stmt.setString(2, Employee.getEmployeePassword()); 
	        
	        stmt.executeUpdate();
	        
			stmt.close();
		} catch(SQLException se) {
			System.out.println(se);
		}
		
		
	}
}