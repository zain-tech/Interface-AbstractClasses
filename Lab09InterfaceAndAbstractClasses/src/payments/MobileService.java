package payments;

import java.util.ArrayList;

import accounts.ConnectionAccount;

public abstract class MobileService {
	
	ConnectionAccount account;
    double balance;
    double dataAvailable;
	double discount;
	
	double bill;
	public MobileService(ConnectionAccount account,
            double balance,
            double dataAvailable)
            {
		this.account=account;
		this.balance=balance;
		this.dataAvailable=dataAvailable;
		bill=0;
            }
	public MobileService(ConnectionAccount account2, double dataAvailable2) {
		// TODO Auto-generated constructor stub
		this.account=account2;
		this.dataAvailable=dataAvailable2;
		bill=0;
	}
	public abstract boolean canMakeCall();
	public abstract boolean makeCall​(Call call);
	public boolean useData​(double dataUsed)
	{
		if(account.getConnectionType().contains("post"))
		{	dataAvailable=dataUsed/1000;
		return true;
		}
		else
		{
			if(dataAvailable>dataUsed)
			{
				dataAvailable-=(dataUsed/1000);
				return true;
			}
			return false;
		}
		
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
        sb.append("Account : ").append(this.account).append("\n");
        sb.append("Balance : ").append(this.balance).append("\n");
        sb.append("Data Available : ").append(this.dataAvailable).append("\n");
        return sb.toString();
		
	}
	
	public ConnectionAccount getAccount()
	{
		return account;
	}
	public double getBalance()
	{
		return balance;
	}
	public double getDataAvailable()
	{
		return this.dataAvailable;
	}
	public abstract double calcBill();
	
}
