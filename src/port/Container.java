package port; 

public class Container implements BasicContainer, HeavyContainer, LiquidContainer, RefrigeratedContainer  { 
	
	private String containerType;
	private String source;	
	private String destination;
	int contIndex; 
	
	
	Container (String productType, String source, String destination) {
		this.source = source;
		this.destination = destination;
		this.containerType = productType;
		contIndex+=1;		
	}	
	
	public String getSource() {
		return source;
	}

	public String getDestination() {
		return destination;
	}
	
	public String getContainerType() {
		return containerType;
	}

	@Override
	public void setType4(String productType) {
		// TODO Auto-generated method stub
		this.containerType="rf";	
		
	}

	@Override
	public void setType3(String productType) {
		// TODO Auto-generated method stub
		this.containerType="lq";
	
	}

	@Override
	public void setType2(String productType) {
		// TODO Auto-generated method stub
		this.containerType="hv";		
	}

	@Override
	public void setType1(String productType) {
		// TODO Auto-generated method stub
		this.containerType="bs";		
	}


	
}
