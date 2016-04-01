package shipping.dhl;

import shipping.abstractShippingService;
import shipping.CostRule;
import java.util.ArrayList;


/**
 * The Class DHL.
 */
public class DHL extends abstractShippingService{
	
	
	/**
	 * Instantiates a new DHL.
	 * Sets up the name, Create an array list of Cost rules, set the cost rules for DHL.   
	 */
	public DHL(){
		super.setServiceName("DHL");
		ArrayList<CostRule> DHLRules=new ArrayList<CostRule>();
		DHLRules.add(new DHL0_MaxRule());
		super.setCostRules(DHLRules);
	}
	
	
	
} 
