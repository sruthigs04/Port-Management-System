package port;

import java.util.*;

public class Main {	
	static final String portNames[] = {"Vizag","Chennai","Nagappattinam","Tuticorn","Kochi","Mangalore","Panaji","Mumbai"}; 

	static void displayPorts()
	{
		int count=1;
		for (String i : portNames )
		{
			System.out.println(count +". " +i);
			count++;
		}	
	}	
	
	public static void main(String[] args) {
		
		final String contTypes[] = {"rf","lq","bs","hv"}; 

		
		Ship ships[] = new Ship[10];			
		Port ports[] = new Port[8]; 
		int shipsIndex=-1;	
		int shipId[] = new int[10]; 
		int shipIdCounter = 0;
		boolean sail=false;

		for(int i=0;i<8;i++) {
			ports[i] = new Port(portNames[i]);
		} 
		
		for(int i=0;i<10;i++) {
			ships[i] = new Ship(1,1);
		}
		
			
		int source = 0;
		int destination =0;
		
		int productType;
		int n;
		String type;		
		
		Scanner sc = new Scanner(System.in);
		int input =0;
		System.out.println("\n------ Port and Shipping Management ------\n");

		
		while(input!=8) 
		{
			
		System.out.println("\n	    ------Select-------");
		System.out.println("1. View Port Details");
		System.out.println("2. View Ship Details");
		System.out.println("3. Place Order ");
//		System.out.println("         ------ Administrator ------");
		System.out.println("4. Load Containers to the Ship");
		System.out.println("5. Check Fuel");
		System.out.println("6. Sail"); 
		System.out.println("7. Unload Container to the Port");
		System.out.println("8. Exit");
		
		input = sc.nextInt();
				
		switch (input) 
		{		
		case 1:
			System.out.println("	------ Port Details ------");
			System.out.println("Enter port location (Enter values from 1-8) ");
			displayPorts();
			source = sc.nextInt();
			if (source>0 && source<9) {
			System.out.println("UPDATE -");
			System.out.println("Port Name - "+portNames[source-1]);
			System.out.println("Total Number of Containers - "+ports[source-1].contIndex);
			System.out.println("Total Number of Heavy Containers - "+ports[source-1].getPortStorageHeavy());
			System.out.println("Total Number of Basic Containers - "+ports[source-1].getPortStorageBasic());
			System.out.println("Total Number of Liquid Containers - "+ports[source-1].getPortStorageLiquid());
			System.out.println("Total Number of Refrigerated Containers - "+ports[source-1].getPortStorageRefrigerated());
			}
			else 
				System.out.println("ERROR - Invalid Port Number");
			break;
			
		case 2:
			System.out.println("	------ Ship Details ------");
			if (shipIdCounter<=0) {
				System.out.println("UPDATE - No ships have been added");
				break;
			}
			else {
			System.out.println("Enter Ship ID from the given IDs -");
			for(int i=0;i<shipId.length;i++)
			{
				if(shipId[i]!=0) {
				System.out.println(shipId[i]);
				}
			}
			int id=sc.nextInt();
			for (int i=0;i<shipId.length;i++)
			{
				if (ships[i].shipID==1 && ships[i].currentLocation!=null) {
					System.out.println("UPDATE -");
					System.out.println("Current Port Location - "+ships[i].currentLocation);
					System.out.println("Available Fuel - "+ships[i].availableFuel);
					System.out.println("Total Number of Containers - "+ships[i].currContainerCount);
					System.out.println("Total Number of Heavy Containers - "+ships[i].hvCount);
					System.out.println("Total Number of Basic Containers - "+ships[i].bsCount);
					System.out.println("Total Number of Liquid Containers - "+ships[i].lqCount);
					System.out.println("Total Number of Refrigerated Containers - "+ships[i].rfCount);
				}
			}
			}
			break;
		
		
		case 3:
			System.out.println(" ------ Place order ------ ");
			System.out.println("Enter the type of product to be shipped : ");
			System.out.println("1. Refrigerated\n2. Liquid\n3. Heavy\n4. Basic (Enter values from 1-4)");
			productType = sc.nextInt();
			type = contTypes[productType-1];
			System.out.println("Enter number of containers ");
			n = sc.nextInt();
			System.out.println("Enter source port location. (Enter values from 1-8) ");
			displayPorts();
			source = sc.nextInt();
			System.out.println("Enter destination port location. (Enter values from 1-8) ");
			destination = sc.nextInt();
			Port p1=new Port("temp");
			if (source>0 && source<9 && destination>0 && destination<9) {
			p1.addContainer(n, source, destination, type, ports[source-1]);
			System.out.println(" UPDATE - The containers have been loaded to Port "+portNames[source-1]);
			}
			else
				System.out.println("ERROR - Invalid Port Number");

			break;
		
		case 4:
			System.out.println("--- Load Ship ---");
			System.out.println("Enter Source Port. (Enter values from 1-8) ");
			displayPorts();
			source = sc.nextInt();
			if (ports[source-1].isCont==true) {
			System.out.println("Enter Destination Port. (Enter values from 1-8) ");
			destination = sc.nextInt();
			if (source>0 && source<9 && destination>0 && destination<9  ) {
			Ship s1 = new Ship(source,destination);
			s1.loadShip(ports[source-1], ports[destination-1], s1);
			ships[++shipsIndex] = new Ship(source,destination);			
			ships[shipsIndex] = s1;
			shipId[shipIdCounter++]+=1;		
			System.out.println("UPDATE - The ship with Ship ID="+ships[shipsIndex].Id+" has been loaded with containers.");
			}
			else 
				System.out.println("ERROR - Invalid Port Number");
			break;
			}
			else {
				System.out.println("ERROR - No containers to load");
				break;	
				}
						
		case  5:
			if (shipIdCounter<=0) {
				System.out.println("ERROR - No ships have been scheduled");
				break;
			}
			for (int i=0;i<shipId.length;i++)
			{
				if (ships[i].shipID==shipId[i]) 
					ships[i].checkFuel();				
			}	
	
			break;
			
		case 6:
			if (shipIdCounter<=0) {
				System.out.println("ERROR - No ships have been scheduled to sail");
				break;
			}
			else {
			for (int i=0;i<shipId.length;i++)
			{
				if (ships[i].shipID==shipId[i]) 
					ships[i].setSail(destination);
					sail=true;
			}	
			}
			break;
			
			
		case 7:
			System.out.println("--- Unload Ship ---");
			System.out.println("Enter source port location");
			source = sc.nextInt();
			System.out.println("Enter unload port location");
			destination = sc.nextInt();
//			if (sail==false) {
//				System.out.println("No containers to unload");
//				break;
//			}
			for (int i=0;i<shipId.length;i++)
			{
				if (ships[i].shipID==shipId[i] ) 
				{
					ships[i].unloadShip(ports[destination-1], ports[source-1], ships[i]);
				}					
			}	
			System.out.println("UPDATE - The containers have been unloaded to Port "+portNames[destination-1]);
			
			break;
		
		case 8:
			System.out.println("End of program");
			break;
			
			
		}// end of switch	
			

		
		}// end of while		
			
	}		
		
		
}




