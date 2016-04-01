package shipping;

import java.util.ArrayList;
import java.util.UUID;
import java.math.BigDecimal;
import retailer.Package;


/**
 * The Interface ShippingService.
 */
public interface ShippingService{
	
	/**
	 * Gets the service name.
	 *
	 * @return the service name
	 */
	public abstract String getServiceName();
	
	
	/**
	 * Gets the shipping cost for the weight
	 *
	 * @param weight the weight
	 * @return the shipping cost
	 */
	public abstract BigDecimal getShippingCost(double weight);
	
	/**
	 * Gets the shipping time in days.
	 *
	 * @param weight the weight
	 * @return the shipping time in days
	 */
	public abstract int getShippingTimeInDays(double weight);
	
	/**
	 * Handle packages dispatched by the retailer.
	 *
	 * @param retailerId the retailer id
	 * @param pack the package
	 * @param returnAddress the return address of the retailer.
	 * @return the string specifying the success. 
	 */
	public abstract String handleDispatchedPackages(UUID retailerId,Package pack, String returnAddress);
	
	
	/**
	 * Gets the handled packages.
	 *
	 * @param retailerId the retailer id
	 * @return an array list of the handled packages
	 */
	public abstract ArrayList<Package> getHandledPackages(UUID retailerId); 
}

