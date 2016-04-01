package reporting;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;
import shipping.ShippingService;
import retailer.Package;


/**
 * The Class ShippingUsageReportingSystem.
 */
public class ShippingUsageReportingSystem extends ReportingSystem{
	
	/**
	 * Instantiates a new shipping usage reporting system.
	 *
	 * @param services the services
	 */
	public ShippingUsageReportingSystem(ArrayList<ShippingService> services){
		this.services=services;
	}
	
	/* 
	 * @see reporting.ReportingSystem#getReport(java.util.UUID)
	 * Creates a report about the usage of different shipping companies, used by the retailer with appropriate ID. 
	 */
	@Override
	public void getReport(UUID retailerId) {
		System.out.println("Usage of shipping Companies Report for the retailer ID: "+retailerId);
		
		for(ShippingService service :services){
			System.out.println(service.getServiceName());
			System.out.format("%1s%25s%16s", "Number of Parcels Sent","Total Weight Kg","Total Cost £\n");

			double tempTotalWeight=0;
			BigDecimal tmpTotalCost = new BigDecimal(0);
			
			for (Package p: service.getHandledPackages(retailerId)){
				tempTotalWeight+=p.getTotalWeight();
				tmpTotalCost=tmpTotalCost.add(p.getShippingCost());
			}
			
			System.out.format("%1s%40s%15s",service.getHandledPackages(retailerId).size(),tempTotalWeight,tmpTotalCost);
			System.out.println();
			System.out.println("--------------------------------------------------------------------------------------");
		}
		
	}
	
}
