package payments;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import accounts.ConnectionAccount;


public class PrepaidService extends MobileService implements Billing {

	Call log;
	public PrepaidService(ConnectionAccount account, double balance,
			double dataAvailable) {
		super(account, balance, dataAvailable);
		// TODO Auto-generated constructor stub
		discount=10;
	}

	@Override
	public double calcBill() {
		double calbill=0;
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		try {
			String[] arr = log.startTime.split(" ", 2);
			String[] arr1 = log.endTime.split(" ", 2);
        	
			java.util.Date start =  format.parse(arr[1]);
			java.util.Date end =  format.parse(arr1[1]);
			double difference = end.getTime() - start.getTime();
			switch(log.getCallType())
			{
			case "LOCAL":
				calbill+= (double)Math.round(((difference/1000)*0.00338235)*100)/100;
				break;
			case "INTERNATIONAL":
				calbill+= (double)Math.round(((difference/1000)*0.0033823*3.445)*100)/100;
				break;
			case "ROAMING":
				calbill+= (double)Math.round(((difference/1000)*0.0033823*3.445*0.4306)*100)/100;
				break;
			default:
				break;
				
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return calbill;
	}

	public boolean makeCall​(Call call)
	{
			log=call;
			balance=balance-calcBill();
			if(balance>0)
			return true;
			else
			return false;
	}
	
	@Override
	public double discountForReturningCustomer() {
		// TODO Auto-generated method stub
		return discount;
	}
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Customer Name : ").append(account.getCustomerName()).append("\n");
		sb.append("Phone Number : ").append(account.getPhoneNumber()).append("\n");
		sb.append("Connection Type : ").append(account.getConnectionType()).append("\n");
		sb.append("-----------------------------------------------------------------------------").append("\n");
		sb.append("Last Call Details : ").append("\n");
		sb.append("Phone Number		Call Type		From				To").append("\n");
		sb.append(log.toString()).append("\n");
		sb.append("-----------------------------------------------------------------------------").append("\n");
		sb.append("Available mobile data : ").append(this.dataAvailable).append("MB\n");
		sb.append("Balance : ").append(String.format("%.2f$",this.balance)).append("\n");
		sb.append("Last Call Amount : ").append(String.format("%.2f$",calcBill())).append("\n");
		sb.append("-----------------------------------------------------------------------------").append("\n");
		return sb.toString();
	}
	
	public boolean canMakeCall()
	{
		if(balance>0.5)
			return true;
		return false;
	}
	
}
