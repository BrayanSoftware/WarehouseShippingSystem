package retailer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.UUID;
import shipping.ShippingService;


/**
 * The Class OOPRetailer.
 */
class OOPRetailer extends Retailer{
	
	/**
	 * Instantiates a new OOP retailer.
	 *
	 * @param services the services Array List
	 * @param returnAddress the return address
	 */
	public OOPRetailer(ArrayList<ShippingService> services, String returnAddress){
		this.retailerId=UUID.randomUUID();
		this.services=services;
		this.returnAddress=returnAddress;
		this.fromServiceToPackages=new HashMap<ShippingService,ArrayList<Package>>();
	}
	
	/* 
	 * @see retailer.Retailer#prepareToDispatch(shipping.ShippingService, retailer.Package)
	 */
	@Override
	public void prepareToDispatch(ShippingService service, Package pack) {
		
		pack.setShippingCost(service.getShippingCost(pack.getTotalWeight()));
		super.pushPackages(service, pack);
	}
	
	/* 
	 * @see retailer.Retailer#dispatch(shipping.ShippingService, retailer.Package)
	 */
	@Override
	public String dispatch(ShippingService service, Package pack){
		pack.setDispatched(true);
		return "An update from "+service.getServiceName()+":::\n"+
				service.handleDispatchedPackages(this.getRetailerId(),pack, returnAddress);
	}
	
	/* 
	 * @see retailer.Retailer#selectShippingService(retailer.Package)
	 */
	@Override
	public ShippingService selectShippingService(Package pack){ 
		
		HashMap<Integer,ShippingService> tempMap=new HashMap<Integer,ShippingService>();
		int tempCounter=1;
		System.out.println("Select a Shipping Service for package: "+pack.getID()+" Total Weight: "+pack.getTotalWeight());
		
		System.out.println("These are the available shipping options for this package.");
		System.out.format("%1s%26s%16s%20s", "No:","Service Name", "Cost","Shipping Time\n");
		
		for(ShippingService s:getServices()){
			tempMap.put(tempCounter, s);
			System.out.format("%1d%26s%18s%12d", tempCounter,s.getServiceName(),
			getShippingCost(s,pack),getShippingTimeInDays(s,pack));
			System.out.println();
			tempCounter++;
		}
		
		String strLowCost="The lowest cost services for shipping (is/are)";
		for(ShippingService s: getLowestCostServices(pack)){
			strLowCost+=","+s.getServiceName();
		}
		System.out.println(strLowCost);
		
		String strQuickCost="The speedest services for shipping (is/are)";
		for(ShippingService s: getQuickestService(pack)){
			strQuickCost+=","+s.getServiceName();
		}
		System.out.println(strQuickCost);
		
		System.out.print("\nPlease enter the number of preferred service > ");
		
		ShippingService selectedService=null;
		Scanner sc=null;
		try{
			sc = new Scanner(System.in);
			String option = sc.nextLine();
			while(true){
				if(option.isEmpty() || !option.matches("[0-9]+")  || !tempMap.containsKey(Integer.parseInt(option))){
					System.out.print("Please enter a valid number > ");
					option=sc.nextLine();
				}
				else{
					selectedService=tempMap.get(Integer.parseInt(option));
					break; 
				}
			}	
		}
		catch(Exception e){
			if(sc!=null){
				sc.close();
			}
			System.out.println("Please try again.");
		}
		finally{
			sc=null;
		}
		return selectedService; 
	}

	
}
