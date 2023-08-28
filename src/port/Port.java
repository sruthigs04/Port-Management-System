package port;

public class Port   {

	private String portName;
	Container container[] = new Container[500];
	int contIndex = 0;	
	
	static final String contTypes[] = {"rf","lq","bs","hv"}; 
	final String portNames[] = {"Vizag","Chennai","Nagappattinam","Tuticorn","Kochi","Mangalore","Panaji","Mumbai"}; 

	int portMaxStorageHeavy = 150;
	int portMaxStorageBasic = 150;
	int portMaxStorageLiquid = 100;	
	int portMaxStorageRefrigerated = 100;   
	private int portStorageHeavy = 0;
	private int portStorageBasic = 0;
	private int portStorageLiquid = 0;	
	private int portStorageRefrigerated = 0; 
	boolean isCont=false;

	
	Port(String name) {
		this.portName = name;
	}
	

	public String getPortName() {
		return portName;
	}

	public void setPortName(String portName) {
		this.portName = portName;
	}
	
	public int getPortStorageHeavy() {
		return portStorageHeavy;
	}


	public void setPortStorageHeavy(int portStorageHeavy) {
		this.portStorageHeavy = portStorageHeavy;
	}


	public int getPortStorageBasic() {
		return portStorageBasic;
	}


	public void setPortStorageBasic(int portMStorageBasic) {
		this.portStorageBasic = portMStorageBasic;
	}


	public int getPortStorageLiquid() {
		return portStorageLiquid;
	}


	public void setPortStorageLiquid(int portStorageLiquid) {
		this.portStorageLiquid = portStorageLiquid;
	}


	public int getPortStorageRefrigerated() {
		return portStorageRefrigerated;
	}


	public void setPortStorageRefrigerated(int portStorageRefrigerated) {
		this.portStorageRefrigerated = portStorageRefrigerated;
	}

	
	void addContainer(int n, int source, int destination, String type, Port port)
	{
		for (int i=0;i<n;i++)	
		{
		Container c = new Container(type,portNames[source-1], portNames[destination-1]);
		int index = port.contIndex;
		port.container[index]=c; 
		if (type=="hv")
			port.setPortStorageHeavy(port.getPortStorageHeavy()+1);
		if (type=="bs")
			port.setPortStorageBasic(port.getPortStorageBasic()+1); 
		if (type=="rf")
			port.setPortStorageRefrigerated(port.getPortStorageRefrigerated()+1); 
		if (type=="lq")
			port.setPortStorageLiquid(port.getPortStorageLiquid()+1); 
		port.contIndex+=1;
		port.isCont=true;
		}
	}
	

	
	
	}
	


		

 	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	



	
	
	
	
	
	
	
	
	

