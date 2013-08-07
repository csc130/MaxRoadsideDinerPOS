
public class Employee 
{
	private static String employeeUserID;
	private static String employeePassword;

	public Employee()
	{
		employeeUserID = "";
		employeePassword = "";
	}
	
	public void setEmployeeUserID(String empUserID)
	{
		employeeUserID = empUserID;
	}
	
	public static String getEmployeeUserID()
	{
		return employeeUserID;
	}
	
	public void setEmployeePassword(String empPassword)
	{
		employeePassword = empPassword;
	}
	
	public static String getEmployeePassword()
	{
		return employeePassword;
	}
}
