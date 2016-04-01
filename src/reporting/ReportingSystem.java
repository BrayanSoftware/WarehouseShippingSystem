package reporting;

import java.util.UUID;
import java.util.ArrayList;
import shipping.ShippingService;

/**
 * The Class ReportingSystem. class used to create different reports based on data stored at shipping Service side 
 */
public abstract class ReportingSystem {
	
	
	/** The shipping services used. */
	ArrayList<ShippingService> services;
	
	/**
	 * Gets the services.
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
	 * Gets the report appropriate for the retailer with ID retailer ID.
	 *
	 * @param retailerId the retailer id
	 * the report will be displayed to the user. 
	 * 
	 */
	public abstract void getReport(UUID retailerId);
	
}



