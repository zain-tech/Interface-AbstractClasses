package accounts;

public class ConnectionAccount {
	String customerName;
    String phoneNumber;
    String connectionType;
    String joiningDate;
    int numberOfYears;
    
    public ConnectionAccount(String customerName,
    String phoneNumber,
    String connectionType,
    String joiningDate)
    {
    	this.customerName=customerName;
    	this.phoneNumber=phoneNumber;
    	this.connectionType=connectionType;
    	this.joiningDate=joiningDate;
    }
    public String getCustomerName()
    {
    	return customerName;
    }
    public String getPhoneNumber()
    {
    	return phoneNumber;
    }
    public String getConnectionType()
    {
    	return connectionType;
    }
    public String getJoiningDate()
    {
    	return joiningDate;
    }
    public int numberOfYears()
    {
    	return numberOfYears;
    }
    
}
