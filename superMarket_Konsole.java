import java.io.* ;
import java.util.*;

public class superMarket_Konsole {
	
	public static void main(String args[])
		throws IOException
		{
			String st;
			int currentCustomer_ID = 1;
			ArrayList customerList = new ArrayList();
			ArrayList prizeListTenK = new ArrayList();
			ArrayList prizeListTopTen = new ArrayList();
		//	String passwd = null;

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("\n***********  WELCOME TO SUPER MARKET KONSOLE *********");
			System.out.println();
						
			FileReader fr = new FileReader("CustomerData.txt");
			BufferedReader bf = new BufferedReader(fr);
			String s;
			while((s=bf.readLine())!=null) {
				
				String delims = "*";
    			StringTokenizer scanner;   
    			 
    			scanner = new StringTokenizer(s, delims, false);
    			ArrayList al = new ArrayList();
    			
   				while (scanner.hasMoreTokens()) {
      				 String aWord = scanner.nextToken();
       				 
       				 al.add(aWord);
        		  }	
        		
        		Object arr[] = al.toArray();
        		Customer cust = new Customer((String)arr[0], (String)arr[1], (String)arr[2], Integer.parseInt((String)arr[3]));
        		if (((String)arr[5]).equals("T"))
        			cust.isTop10 = true;
        		else
        			cust.isTop10 = false;
        	
        		if(((String)arr[6]).equals("T"))
        			cust.isGreater_T_10K = true;
        		else
        			cust.isGreater_T_10K = false;	
        		
        		cust.Add_Purchase(Float.parseFloat((String)arr[4]));
        		customerList.add(cust);
        		currentCustomer_ID++;
        			
			}
			fr.close();
						
			while(true){
			System.out.println(" ");
			System.out.println(" ");
			System.out.println("********** PLEASE ENTER YOUR OPTION *************");
		    System.out.println("* To Register to the Super Market -- 1          *");
	        System.out.println("* TO make a Purchase              -- 2          *");
			System.out.println("* To Generate prize winners' list -- 3          *");
		    System.out.println("* To View total sales position    -- 4          *");
			System.out.println("* To Exit the system              -- 5          *");
			System.out.println("*************************************************");
			System.out.println("");

			st = br.readLine();
			int choice = Integer.parseInt( st );
			switch( choice ){
			
			case 1:{
			
			   String address;	
			   try {	
		       System.out.print("Welcome ");
			   Thread.sleep(500);
			   System.out.print("to "); 
			   Thread.sleep(500); 
			   System.out.print("the "); 
			   Thread.sleep(500);
			   System.out.print("Super Market "); 
			   Thread.sleep(500); 
			   System.out.println("Registration");
			   System.out.println(" ");
			   Thread.sleep(700);		
				} catch (InterruptedException e) { System.out.println("Main Thread Interrupt"); }

			   System.out.println("Please Enter your complete address (in one line):");	
			   address = br.readLine();
			   			   	
  			   System.out.println("Please Enter your telephone number:");
		         String tel_no;
			   
			   tel_no = br.readLine();			
			  
               System.out.println("Please Enter your Driving License number:");
		       String dl_no;
			   
			   dl_no = br.readLine();		 	
			   
			   Customer smc = new Customer(address, tel_no, dl_no, currentCustomer_ID);				
			  
			   customerList.add(smc);
			  
			   System.out.println();
			   System.out.println("***** Please note that your customer number is :"+ currentCustomer_ID + "  *****");
			   System.out.println();
			   System.out.println();
			   currentCustomer_ID++;
			   }
			   break;

			case 2:{
			
			   int customer_ID;	
			   String CN;
			   float purchase;
			   Customer cust, chk;
			   System.out.println("Please enter your Customer Number ");
			   CN = br.readLine();
			   try {
				customer_ID = Integer.parseInt(CN);
				if(customer_ID >= currentCustomer_ID)
				{
					System.out.println("*****  This customer number does not exist. Please try again  *****");
					continue;
				}
				System.out.println(customer_ID);
				} catch(NumberFormatException fe) {
					 System.out.println ("Invalid format of Customer Number !!!!");
					 customer_ID = 0;
					 continue;
				}				   
			    System.out.println("Please enter the purchase amount ");
  			    CN = br.readLine();
			   try {
				purchase = Float.parseFloat(CN);
				System.out.println(purchase);
				} catch(NumberFormatException fe) { 
				System.out.println ("Invalid format of Customer Number");
				purchase = 0; }
			    cust = ((Customer)customerList.get(customer_ID - 1));		   			   
			   
			    cust.Add_Purchase(purchase);	
			    System.out.println("Customer Purchase Successfully updated ");
			   
			   customerList.set(customer_ID - 1, cust);
			   chk = ((Customer)customerList.get(customer_ID - 1));		
			   System.out.print("Total purchase made by the customer is ");
			   System.out.println(chk.Total_Purchase());
			   }
			   break;	

			case 3:  {
				
				System.out.println("Enter the Manager code  ( password ) :");
			    String 	passwd = br.readLine();
			    if(passwd.equals( "pass" )){
				   	WinList lg = new WinList();
				   int sizeOfList = 0;
				   lg.findTopTen(customerList);
				   lg.findTenKWins(customerList);
			   	}
			   	else
			   	   System.out.println("You have entered wrong password");
			   }
			   break;
			
			case 4:{
				System.out.println("Enter the Manager code  ( password ) :");
			    String 	passwd = br.readLine();
			    if(passwd.equals( "04CS3019" )){
			    	System.out.println("*****   Total Sales   ******");
					float totSales = 0;
					Object ar[] = customerList.toArray();
					for(int i = 0; i<customerList.size(); i++)
				    	{
							totSales = totSales + ((Customer)ar[i]).Total_Purchase();
				    	}
					System.out.println(totSales);
				}
			    else
			   	   System.out.println("You have entered wrong password");
			}
			break;
			
			 case 5: {
						
				String buff, tOrF1, tOrF2;
				Customer supCust;
				Object ar[] = customerList.toArray();
				String source1 = "";
				char buff1[] = new char[source1.length()];
				source1.getChars(0, source1.length(), buff1, 0);
				FileWriter fw1 = new FileWriter("CustomerData.txt");
				for(int i1=0; i1<buff1.length; i1+=1)	
					{
						fw1.write(buff1[i1]);
					}
				fw1.close();
				FileWriter fw = new FileWriter("CustomerData.txt", true);
				for(int i = 0; i<customerList.size(); i++)	
					{
						supCust = (Customer)ar[i];
						tOrF1 = "F";
						if(supCust.isGreater_T_10K)
							tOrF2 = "T";
						else
							tOrF2 = "F";
						buff = supCust.Address() + "*" +supCust.Telephone() + "*"+ supCust.DL_no()+"*"+ Integer.toString(supCust.Customer_ID())+"*" + Float.toString(supCust.Total_Purchase())+"*"+tOrF1+"*"+tOrF2+"\n";
						char source[] = new char[buff.length()];
						buff.getChars(0, buff.length(), source, 0);
						for(int j=0; j<source.length; j+=1)	
						{
							fw.write(source[j]);
						}
				        
				     }
				 fw.close();	
				 //break; 
				
				 
				}
				default:
				break;
			 
				}
				if(choice == 5){
				
				 System.out.println("\n********* SHUTTING DOWN THE KONSOLE");
				 for( int i = 0; i < 10; i++){
				 	try{
				 		Thread.sleep(200);
				 		System.out.print("*");
				 		}catch( InterruptedException e){};
				 	
				 }
				 System.exit(0);
			 }
				
			}
		}
			
}	


class WinList {

	public int sizeOfList;
	public int sizeOf10KList;
	public int Top_10;
	public float Total_sales_lastYear;
	public float Total_sales;
	
	public WinList()
	    {
	               sizeOfList = 0;
	               sizeOf10KList = 0;
	               Top_10 = 0;
	   }

	public void findTopTen(ArrayList ar)
	   {	
       	float currMax;
       	Customer currCust;
		float toCompare = 0;
		int currCustMax = 0;
		
		Object cst[] = ar.toArray();	
		System.out.println("*****  Customers who made the Highest total purchase   *****");
		
		while((Top_10 < 10)&&(Top_10 < ar.size()))
		{	
			currMax = 0;
			for(int  i=0; i< cst.length; i++)
			{
				currCust = (Customer) cst[i];
		    	toCompare = currCust.Total_Purchase();
		    	if((currMax <= toCompare)&&(currCust.isTop10 == false))
		    	{
		        	currCustMax = i;
		        	currMax = toCompare;
		        }
			}
		
	            		
		System.out.print("Customer Number -> "+ ((Customer) cst[currCustMax]).Customer_ID());
		System.out.println(" , Purchase Made ->" +  ((Customer) cst[currCustMax]).Total_Purchase());
		((Customer) cst[currCustMax]).isTop10 = true;
		currCustMax = 0;
		Top_10++;
		
		}
	 }
	 
	 
	 public void findTenKWins(ArrayList ar)
	 {
	 	Customer supCust;
	 	Object cst[] = ar.toArray();
	 	System.out.println();
	 	System.out.println();
	 	System.out.println("*****   Customers who made purchases of more than 10K   *****");
	 	for(int i = 0; i<cst.length; i++)
	 	{
	 		supCust = (Customer) cst[i];
	 		if(supCust.Greater_T_10K())
	 		{
	 			System.out.print("Customer Number -> "+ supCust.Customer_ID());
	 			System.out.println(" , Purchase Made -> "+supCust.Total_Purchase());
	 		}
	 		
	 	
	 	}
	 }
}

class Customer {

	private String Address;
	private String Telephone;
	private String DL_no;
	private int       Customer_ID;
	private float    Total_Purchase;
	public  boolean  isTop10;
	public  boolean  isGreater_T_10K;
	
	public Customer(String Address_in, String Telephone_in, String DL_no_in, int Customer_ID_in)
	{
	 	Address     = Address_in;
		Telephone   = Telephone_in;
		DL_no       = DL_no_in;			
		Customer_ID = Customer_ID_in;
		Total_Purchase = 0;
		isTop10        = false;
		isGreater_T_10K= false;
	}

	public String Address ()        { return Address;   }
	public String Telephone ()      { return Telephone ;}
	public String DL_no ()          { return DL_no;     }
	public int    Customer_ID ()    { return Customer_ID;}
	public float  Total_Purchase () { return Total_Purchase; }
	
	public void Add_Purchase ( float addPurchase ) {  Total_Purchase = Total_Purchase + addPurchase ; }
	
	public boolean Greater_T_10K() { if (Total_Purchase > 10000) return true; 
				     else return false; }
				     
	public void reset() { Total_Purchase = 0;  isTop10 = false; isGreater_T_10K = false;
}
	

	
	}
	
