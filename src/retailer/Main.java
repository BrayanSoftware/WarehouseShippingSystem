package retailer;

import java.util.ArrayList;
import shipping.ShippingService;
import reporting.ReportingSystem;
import reporting.ShippingUsageReportingSystem;
import shipping.dhl.DHL;
import shipping.bulkpack.BulkPack;
import shipping.royalmail.RoyalMail;


/**
 * The Class Main.
 */
class Main{
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String args[]){
		
		//Creating an array list of ShippingService to be used by the OOP Retailer.
		ArrayList<ShippingService> ServicesToOOP=new ArrayList<ShippingService>();
		
		ShippingService RM=new RoyalMail();
		ShippingService DHL=new DHL();
		ShippingService BulkPack=new BulkPack();
		
		ServicesToOOP.add(RM);
		ServicesToOOP.add(DHL);
		ServicesToOOP.add(BulkPack);
		
		//Creating a retailer object
		Retailer OOP=new OOPRetailer(ServicesToOOP,"Wolfson Building, Parks Road, Oxford, OX1 3QD");
		
		
		//Q1,Q2: Demonstration of calculating delivery costs. 
		ArrayList<ShippingService> currentServices=OOP.getServices();
		Package testPackage=new Package(30,25,25,0.0,"102-104 St Aldates, OX1 1ZZ");
		
		for(ShippingService s:currentServices){
			System.out.println(s.getServiceName());
			System.out.format("%1s%25s%16s%32s", "Item Weight Kg","Total Weight Kg","Cost £","Shipping Time in days\n");	
			for(double weight=0.1 ;weight<3.1;weight=weight+0.1){
				testPackage.setItemWeight(weight);
				System.out.format("%1s%25s%26s%26s",testPackage.getItemWeight(),testPackage.getTotalWeight(),
				s.getShippingCost(testPackage.getTotalWeight()),
				s.getShippingTimeInDays(testPackage.getTotalWeight()));
				System.out.println("");
			}
			System.out.println("--------------------------------------------------------------------------------------");
		}
		
		
		//Creating three Package objects for demonstration.
		
		Package dummyOne=new Package(30,25,25,0.45,"102-104 St Aldates, OX1 1ZZ");
		Package dummyTwo=new Package(30,25,25,0.45,"63 Military Road, CT1 1LU");
		Package dummyThree=new Package(30,25,25,0.45,"705 Abito, M50 3BS");
		
		Package arr[]={dummyOne,dummyTwo};
		for (Package p: arr){
			//Selecting the Shipping Service.
			ShippingService service=OOP.selectShippingService(p);
			//Prepare the package to dispatch
			OOP.prepareToDispatch(service, p);
			//Dispatch the package
			System.out.println(OOP.dispatch(service,p));
			System.out.println("--------------------------------------------------------------------------------------");
		}
		
	
		//Q3 Creating a reporting system object
		ReportingSystem rs=new ShippingUsageReportingSystem(OOP.getServices());
		//Generate the report about the usage of shipping companies for the Management of retail company.
		rs.getReport(OOP.getRetailerId());
	
		// Creating a Package, prepare for dispatch,but not dispatched and this package is to be dispatched. This is for demonstration of 4. 
		ShippingService dummyThreeService=OOP.selectShippingService(dummyThree);
		OOP.prepareToDispatch(dummyThreeService, dummyThree);
		System.out.println("--------------------------------------------------------------------------------------");
	
		//Q4.Create a List of packages to be dispatched with shipping companies. 
		OOP.printPackages();
		
	}
}






