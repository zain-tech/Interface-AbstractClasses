package payments;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import rates.PostpaidDiscounts;
import accounts.ConnectionAccount;

public class PostpaidService extends MobileService implements Billing {

	ArrayList<Call> log;
	PostpaidDiscounts d;
	public PostpaidService(ConnectionAccount account, double balance,
			double dataAvailable) {
		super(account, balance, dataAvailable);
		log= new ArrayList<Call>();
		
		// TODO Auto-generated constructor stub
	}
	public PostpaidService(ConnectionAccount account,
            double dataAvailable) {
		super(account, dataAvailable);
        log= new ArrayList<Call>();
    		
	
	}
	@Override
	public double calcBill() {
		double calbill=0;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			for(int i=0;i<log.size();i++)
			{
			java.util.Date start =  format.parse(log.get(i).startTime);
			java.util.Date end =  format.parse(log.get(i).endTime);
			double difference = end.getTime() - start.getTime();
			if(difference>0)
			switch(log.get(i).getCallType())
			{
			case "LOCAL":
				calbill+= (double)Math.round(((difference/1000)*0.0038235)*100)/100;
				break;
			case "INTERNATIONAL":
				calbill+= (double)Math.round(((difference/1000)*0.003823*2)*100)/100;
				break;
			case "ROAMING":
				calbill+= (double)Math.round(((difference/1000)*0.003823*2*1.1)*100)/100;
				break;
			default:
				break;
				
			}
			else
				throw new ArithmeticException("Date and Time Incorrect!!!");
			}
		}
		 catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return calbill;
	}

	@Override
	public double discountForReturningCustomer() {
		// TODO Auto-generated method stub
		return calcBill()*0.2;
	}
	public boolean canMakeCall()
	{
		
			return true;	
	}
	public boolean makeCall​(Call call)
	{
		log.add(call);
		return true;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Customer Name : ").append(account.getCustomerName()).append("\n");
		sb.append("Phone Number : ").append(account.getPhoneNumber()).append("\n");
		sb.append("Connection Type : ").append(account.getConnectionType()).append("		Connection Date : ").append(account.getJoiningDate()).append("\n");
		sb.append("-----------------------------------------------------------------------------").append("\n");
		sb.append("Last Call Details : ").append("\n");
		sb.append("Phone Number		Call Type		From				To").append("\n");
		for(int i=0;i<log.size();i++)
		sb.append(log.get(i).toString()).append("\n");
		sb.append("-----------------------------------------------------------------------------").append("\n");
		sb.append("Additional mobile data used: ").append(this.dataAvailable).append("MB\n");
		sb.append("Bill Amount : ").append(String.format("%.2f$",calcBill())).append("\n");
		sb.append("Returning Customer Discount(20.0%) : ").append(String.format("%.2f$",discountForReturningCustomer())).append("\n");
		sb.append("Final Bill Amount : ").append(String.format("%.2f$",calcBill()-discountForReturningCustomer())).append("\n");
		sb.append("-----------------------------------------------------------------------------").append("\n");
		return sb.toString();
	}
	
	public double finalBillAfterDiscount()
	{
		
		return balance;
		
	}

}
