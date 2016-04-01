package shipping.bulkpack;

import shipping.abstractShippingService;
import shipping.CostRule;

import java.util.ArrayList;


/**
 * The Class BulkPack.
 */
public class BulkPack extends abstractShippingService{
	
	/**
	 * Instantiates a new bulk pack.
	 * Sets up the name, Create an array list of Cost rules, set the cost rules for Bulk Pack. 
	 */
	public BulkPack(){
		super.setServiceName("BulkPack");
		ArrayList<CostRule> BulkPackRules=new ArrayList<CostRule>();
		BulkPackRules.add(new BulkPack0_MaxRule());
		super.setCostRules(BulkPackRules);
	}
	
} 
