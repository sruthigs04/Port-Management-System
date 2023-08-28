package port;

abstract class fuel {
	abstract void checkFuel();
	abstract double fuelUsed();
	abstract double fuelCalc(int x1, int y1, int x2, int y2, double fuelPerKm);
}

public class Ship extends fuel {
	

	int shipID ;
	int Id=0;
	private int hvMaxCount=25;
	private int lqMaxCount=20;
	private int rfMaxCount=25;
	private int bsMaxCount=30;
	int hvCount=0;
	int lqCount=0;
	int rfCount=0;
	int bsCount=0;
	int shipMaxWeight;
	double availableFuel=10; 
	Container cont[] = new Container[100];
	double shipFuelBase=0;
	double shipFuelBs=3;
	double shipFuelHv=5;
	double shipFuelRf=4;
	double shipFuelLq=2;	

	int currContainerCount=0;
	String currentLocation;
	String source;	
	String destination;
	
	final String portNames[] = {"Vizag","Chennai","Nagappattinam","Tuticorn","Kochi","Mangalore","Panaji","Mumbai"}; 
	int portCoordinates [][] = { {80,70},{40,40}, {20,30},{10,20},{20,-30},{40,-30},{60,-50},{90,-40}};
	int shipCount=0;
	int shipsIndex=-1;	
	
	Ship (int source, int destination){
		this.source= (portNames[source-1]);
		this.destination = portNames[destination-1];
		this.shipID = (Id++)+1;
		this.shipFuelBase=50.00;
	}
	
	public int getHvMaxCount() {
		return hvMaxCount;
	}


	public int getLqMaxCount() {
		return lqMaxCount;
	}

	public int getRfMaxCount() {
		return rfMaxCount;
	}

	public int getBsMaxCount() {
		return bsMaxCount;
	}
	
	void loadShip(Port sPort, Port dPort, Ship s1) {
		s1.currentLocation=s1.source;
		int counter=0;
		int d=0;
		if (sPort.contIndex>=0)
		{
			for(int i=0;i<sPort.contIndex;i++)
			{
				if (sPort.container[i].getContainerType()=="hv" && s1.hvCount<s1.getHvMaxCount() && (s1.source==sPort.container[i].getSource())) 
				{
					s1.hvCount+=1;
					s1.cont[i] = sPort.container[i];
					sPort.setPortStorageHeavy(sPort.getPortStorageHeavy()-1);
					counter+=1;
	
				}
				if (sPort.container[i].getContainerType()=="bs" && s1.bsCount<s1.getBsMaxCount() && (s1.source==sPort.container[i].getSource()))
				{
					s1.bsCount+=1;
					s1.cont[i] = sPort.container[i];
					sPort.setPortStorageBasic(sPort.getPortStorageBasic()-1);
					counter+=1;
				}
				if (sPort.container[i].getContainerType()=="lq" && s1.lqCount<s1.getLqMaxCount() && (s1.source==sPort.container[i].getSource()))
				{
					s1.lqCount+=1;
					s1.cont[i] = sPort.container[i];
					sPort.setPortStorageLiquid(sPort.getPortStorageLiquid()-1);
					counter+=1;
				}
				if (sPort.container[i].getContainerType()=="rf" && s1.rfCount<s1.getRfMaxCount() && (s1.source==sPort.container[i].getSource()))
				{
					s1.rfCount+=1;
					s1.cont[i] = sPort.container[i];
					sPort.setPortStorageRefrigerated(sPort.getPortStorageRefrigerated()-1);
					counter+=1;
				}				
			}
			sPort.contIndex-=counter;
			s1.currContainerCount=counter;
		}
	}	
	
	
	void unloadShip(Port dPort, Port sPort, Ship s1)
	{
		int d=0;
		s1.currentLocation=s1.destination;
		if (s1.currContainerCount>=0)
		{
			for(int i=0;i<s1.currContainerCount;i++)
			{
				if (s1.cont[i].getContainerType()=="hv" && s1.hvCount<s1.getHvMaxCount() && s1.cont[i].getDestination()==dPort.getPortName() ) 
				{
					s1.hvCount-=1;
					dPort.setPortStorageHeavy(dPort.getPortStorageHeavy()+1);
					d+=1;	
				}
				if (s1.cont[i].getContainerType()=="bs" && s1.bsCount<s1.getBsMaxCount() && s1.cont[i].getDestination()==dPort.getPortName() )
				{
					s1.bsCount-=1;
					dPort.setPortStorageBasic(dPort.getPortStorageBasic()+1);
					d+=1;
				}
				if (s1.cont[i].getContainerType()=="lq" && s1.lqCount<s1.getLqMaxCount() && s1.cont[i].getDestination()==dPort.getPortName())
				{
					s1.lqCount-=1;
					dPort.setPortStorageLiquid(dPort.getPortStorageLiquid()+1);
					d+=1;
				}
				if (s1.cont[i].getContainerType()=="rf" && s1.rfCount<s1.getRfMaxCount() && s1.cont[i].getDestination()==dPort.getPortName() )
				{
					s1.rfCount-=1;
					dPort.setPortStorageRefrigerated(dPort.getPortStorageRefrigerated()+1);
					d+=1;
				}				
			}
			s1.currContainerCount=0;
			dPort.contIndex+=d;
			s1.availableFuel-= (s1.availableFuel+s1.fuelUsed());
			if (s1.availableFuel<0)
				s1.availableFuel=0;
		}	
	}
	
	
	
	void checkFuel() {
		double fuel=fuelUsed();
		System.out.println("Available fuel = "+this.availableFuel);
		System.out.println("Fuel needed = "+fuel);
		if (this.availableFuel<fuel)
		{
		System.out.printf("Please refuel the ship with minimum of "+(fuel-this.availableFuel)+" liters");
		this.availableFuel+=(fuel-this.availableFuel);
		}
		else if(this.availableFuel>fuel)
		System.out.println("Sufficient fuel.");
		 
	}
	
	double fuelUsed() {
		double fuel=this.shipFuelBase; 
		fuel+= this.hvCount*this.shipFuelHv;
		fuel+= this.bsCount*this.shipFuelBs;
		fuel+= this.lqCount*this.shipFuelLq;
		fuel+= this.rfCount*this.shipFuelRf;
		return fuel;
	}
	
	void setSail(int destination) {
		this.checkFuel();
		if (this.availableFuel>=this.fuelUsed() && this.destination==portNames[destination-1])
			System.out.println("Sufficient Fuel. Containers have been shipped from "+this.source+" to "+this.destination);
	}
	
	double fuelCalc(int x1, int y1, int x2, int y2, double fuelPerKm) 
	{
		double distance = Math.sqrt((Math.pow(x2,2) - (Math.pow(x1, 2))) + (Math.pow(y2,2) - (Math.pow(y1, 2))));
		double fuel = distance * fuelPerKm;		
		return fuel;	 
	}
	
}	
	

	
	

