package retailer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import shipping.ShippingService;

/**
 * The Class Retailer.
 */
abstract class Retailer{
	
	/** The retailer id. */
	protected UUID retailerId;
	
	/** The shipping services used by retailer. */
	protected ArrayList<ShippingService> services;
	
	/** The return address. */
	protected String returnAddress;
	
	/** A Hash Map, Key: Shipping Service , Value: An array list of packages dispatched and to be dispatched via that 
	 *  service. 
	 *  */
	protected HashMap <ShippingService,ArrayList<Package>> fromServiceToPackages;
	
	/**
	 * Gets the retailer id.
	 *
	 * @return the retailer id
	 */
	public UUID getRetailerId() {
		return retailerId;	
	}
	
	/**
	 * Gets the shipping services using.
	 *
	 * @return the services
	 */
	public ArrayList<ShippingService> getServices() {
		return services;
	}

	/**
	 * Sets the services.
	 *
	 * @param services the new services
	 */
	public void setServices(ArrayList<ShippingService> services) {
		this.services = services;
	}
	
	/**
	 * Gets the return address.
	 *
	 * @return the return address
	 */
	public String getReturnAddress() {
		return returnAddress;
	}

	/**
	 * Sets the return address.
	 *
	 * @param returnAddress the new return address
	 */
	public void setReturnAddress(String returnAddress) {
		this.returnAddress = returnAddress;
	}
	
	/**
	 * Save the packages to the hash map according to appropriate shipping service.
	 *
	 * @param service the service
	 * @param pack the pack
	 */
	public void pushPackages(ShippingService service, Package pack){
		if(!fromServiceToPackages.containsKey(service)){
			ArrayList<Package> temp=new ArrayList<Package>();
			temp.add(pack);
			fromServiceToPackages.put(service,temp);
		}
		else{
			fromServiceToPackages.get(service).add(pack);
		}
	}
	
	/**
	 * Prints the details of packages to be dispatched. 
	 */
	public void printPackages(){
		System.out.println("List of parcels to be dispatched");
		for(ShippingService service: services){
			if(fromServiceToPackages.containsKey(service)){
				ArrayList<Package> temp=fromServiceToPackages.get(service);
				
				for(Package pack:temp){
					if(!pack.isDispatched()){
						System.out.println(service.getServiceName());
						System.out.format("%1s%25s%25s", "Package Id","Delivery Address","Postage Cost £\n");
						break;
					}
				}
				
				for(Package pack:temp){
					if(!pack.isDispatched()){
						System.out.format("%1s%40s%15s",pack.getID(),pack.getToAddress(),pack.getShippingCost());
						
					}
				}
				System.out.println();
			}
			
		}
	}
	
	/**
	 * Gets the shipping cost.
	 *
	 * @param service the service
	 * @param pack the pack
	 * @return the shipping cost
	 */
	public BigDecimal getShippingCost(ShippingService service, Package pack){
		return service.getShippingCost(pack.getTotalWeight());
	}
	
	/**
	 * Gets the shipping time in days.
	 *
	 * @param service the service
	 * @param pack the pack
	 * @return the shipping time in days
	 */
	public int getShippingTimeInDays(ShippingService service, Package pack) {
		return service.getShippingTimeInDays(pack.getTotalWeight());
	}
	
	/**
	 * Prepare to dispatch: setting the prices and adding the package to Hash Map. 
	 *
	 * @param service the service
	 * @param pack the pack
	 */
	public abstract void prepareToDispatch(ShippingService service, Package pack);
	
	/**
	 * Dispatch. Mark the package is dispatched and take actions. 
	 *
	 * @param service the service
	 * @param pack the pack
	 * @return the string
	 */
	public abstract String dispatch(ShippingService service, Package pack);
	
	
	/**
	 * Select shipping service.
	 *
	 * @param pack the pack
	 * @return the shipping service
	 */
	public abstract ShippingService selectShippingService(Package pack);


	/**
	 * Gets the quickest service.
	 *
	 * @param pack the package
	 * @return the quickest shipping services in an Array List
	 */
	public ArrayList<ShippingService> getQuickestService(Package pack){ 
			ShippingService quickestServ=null;
			ArrayList<ShippingService> quickServices=new ArrayList<ShippingService>();
			
			for(ShippingService s:getServices()){
				if(quickestServ==null){
					quickestServ=s;
				}
				else if(getShippingTimeInDays(s,pack)==getShippingTimeInDays(quickestServ,pack)){
					quickServices.add(s);
				}
				else if(getShippingTimeInDays(s,pack)<getShippingTimeInDays(quickestServ,pack)){
					quickestServ=s;
					quickServices.clear();
				}
			}
			quickServices.add(quickestServ);
			return quickServices;
	}
	
	
	/**
	 * Gets the lowest cost services.
	 *
	 * @param pack the pack
	 * @return the lowest cost shipping services in an array list.
	 */
	public ArrayList<ShippingService> getLowestCostServices(Package pack){
		ShippingService lowestServ=null;
		ArrayList<ShippingService> lowServices=new ArrayList<ShippingService>();
		
		for(ShippingService s:services){
			if(lowestServ==null){
				lowestServ=s;
			}
			else if(getShippingCost(s,pack).compareTo(getShippingCost(lowestServ,pack))==0){
				lowServices.add(s);
			}
			else if(getShippingCost(s,pack).compareTo(getShippingCost(lowestServ,pack))==-1){
				lowestServ=s;
				lowServices.clear();
			}
		}
		lowServices.add(lowestServ);
		return lowServices;
	}
}
