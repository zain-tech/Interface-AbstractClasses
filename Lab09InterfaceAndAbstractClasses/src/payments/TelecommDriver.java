/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payments;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import rates.Tarrif;
import accounts.ConnectionAccount;


/**
 * This class is driver class for handling all customers call data and generate
 * bills
 *
 * @author Instructor
 */
public class TelecommDriver {

    /**
     * This method is the main method that executes when project is executed.
     * @param args the command line arguments
     * @throws FileNotFoundException if source is not found
     */
    public static void main(String[] args) throws FileNotFoundException {
//      1. Create an arraylist of MobileService and name it as connections to store the list of connections
    	ArrayList<MobileService> connections=new ArrayList<MobileService>();
    	//      2. Create a scanner object and name it as sc to read the input from keyboard
    	Scanner sc = new Scanner(System.in);
    	//      while (iterate) {  
//          3. Read the values from the file and create an appropriate object for each record.
//          4. i.e For example: 
//          4. i.e For example: 
//          5. Kristen Johnson
//             +911752496875
//             postpaid
//             02/29/2016
//             10234.3
//             Create an object of PostpaidService and name it account 
    	File file = new File("E:\\Users\\Zain\\workspace\\Lab09InterfaceAndAbstractClasses\\src\\usersCallLog.txt"); 
    	Scanner sfc = new Scanner(file); 
    	String date="";
    	String prevline=null;
    	double data = 0;
    	String name;
    	double bal = 0;
    	PostpaidService n = null;
    	PrepaidService r = null;
    	ConnectionAccount c;
    	while (sfc.hasNextLine()) {
    		if(prevline==null)
            name = sfc.nextLine();
    		else
    		name = prevline;
            String type = sfc.nextLine();
            String number = sfc.nextLine();
            
           if(type.charAt(1)== 'o')
            {
        	   date = sfc.nextLine();
        	   bal = Double.parseDouble(sfc.nextLine());
            }
            else
            {  bal = Double.parseDouble(sfc.nextLine());
            	data = Double.parseDouble(sfc.nextLine());	
            }
            if(type.charAt(1)=='o')
            {
            	c = new ConnectionAccount(name,number,type,date);
            	n = new PostpaidService(c,bal,0);
            	String call=null;
                
                do{	
                call =sfc.nextLine();
                Call log;
                if(call.charAt(0)=='+')
                {
                	String[] arr = call.split("  ", 4);
                	log = new Call(arr[0],arr[1],arr[2],ValueOF(arr[3]));
                	n.makeCall​(log);
                }
                else 
                	break;
                }while(sfc.hasNextLine());
                prevline=call;
                connections.add(n);
            }
            else
            {
            	c = new ConnectionAccount(name,number,type,date);
            	r = new PrepaidService(c,bal,data);
            	String call=null;
                
                do{	
                	if(sfc.hasNextLine())
                call =sfc.nextLine();
                	else
                		break;
                Call log;
                if(call.charAt(0)=='+')
                {
                	String[] arr = call.split("  ", 4);
                	log = new Call(arr[0],arr[1],arr[2],ValueOF(arr[3]));
                	r.makeCall​(log);
                }
                else 
                	break;
                }while(true);
                prevline=call;
                connections.add(r);
            }
           
            }
//          do{
//              6. makeCall for each record of call data given in input file.
//             } while(user have call record)
//          7. Add above created object to variable connections.
//		8. Print "************************Postpaid customers invoice***************************"
//      9. Create enhanced for loop and print all the postpaid customers from connections arraylist
    	
    	System.out.println("************************Postpaid customers invoice***************************");
    	System.out.println("-----------------------------------------------------------------------------");
    	
    	for(int i=0;i<connections.size();i++)
    	{
    		if(connections.get(i).getAccount().getConnectionType().charAt(1)=='o')
    			System.out.println(connections.get(i).toString());
    	}
    	System.out.println("************************Prepaid customers invoice***************************");
    	System.out.println("-----------------------------------------------------------------------------");
    	for(int i=0;i<connections.size();i++)
    	{
    		if(connections.get(i).getAccount().getConnectionType().charAt(1)=='r')
    			System.out.println(connections.get(i).toString());
    	}
    	//	   	10. Print "************************Prepaid customers invoice***************************"
//      11. Create for loop and print all the prepaid customers from connections arraylist
//      12. Print "**********************Invoking useData() of customer*************************"
//      13. Invoke useData() method and print MobileService's object in connections arraylist for customers using below details for using data (in kB).
//          a. Customer Name: "Aegon Targaryen", Data Used: 156774.40 kB
//          b. Customer Name: "Tyrion Lannister", Data Used: 13516.80 kB
//          c. Customer Name: "Eddard Stark", Data Used: 104427.52 kB 
    	System.out.println("**********************Invoking useData() of customer*************************");
    	for(int i=0;i<connections.size()-1;i++)
    	{
    		
    			System.out.println("Add Data Used for " + connections.get(i).getAccount().getCustomerName());
    			connections.get(i).useData​(sc.nextDouble());
    			System.out.println(connections.get(i).toString());
    	}
//      14. Print "************************Best postpaid customer******************************"
//		15. Iterate through the connections arraylist and print the best postpaid customer who has highest bill amount.
    	double high = 0;
    	int x = 0;
    	for(int i=0;i<connections.size()-1;i++)
    	{
    		if(connections.get(i).getAccount().getConnectionType().charAt(1)=='o')
    		if(high<connections.get(i).calcBill())
    			{
    			high=connections.get(i).calcBill();
    			x=i;
    			}
    	}
    	System.out.println("************************Best postpaid customer******************************");
    	System.out.println("Name : " + connections.get(x).getAccount().getCustomerName());
		System.out.println(connections.get(x).toString());
    }

	private static Tarrif ValueOF(String string) {
		Tarrif t = null;
		switch(string)
		{
		case "L":
			t=Tarrif.LOCAL;
			return t;
		case "R":
			t=Tarrif.ROAMING;
			return t;
		case "I":
			t=Tarrif.INTERNATIONAL;
			return t;
		}
		return t;
	}

}
