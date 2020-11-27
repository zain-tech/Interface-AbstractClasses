package payments;

import rates.Tarrif;

public class Call {

	String phoneNumber;
    String startTime;
    String endTime;
    Tarrif callType;
	
	public Call(String phoneNumber,
            String startTime,
            String endTime,
            Tarrif callType)
            {
		this.phoneNumber=phoneNumber;
		this.startTime=startTime;
		this.endTime=endTime;
		this.callType=callType;
            }
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(phoneNumber).append("		").append(getCallType()).append("		").append(startTime).append("			").append(endTime);
		return sb.toString();
	}
	public String getCallType()
	{
		switch(callType)
		{
		case INTERNATIONAL:
			return "INTERNATIONAL";
		case LOCAL:
			return "LOCAL";
		case ROAMING:
			return "ROAMING";
		default:
			break;
		
		}
		return "Error";
		
	}
	
}
