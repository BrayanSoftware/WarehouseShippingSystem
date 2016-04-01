package shipping.royalmail;

import shipping.abstractShippingService;
import shipping.CostRule;

import java.util.ArrayList;


/**
 * The Class RoyalMail.
 */
public class RoyalMail extends abstractShippingService{
	
	/**
	 * Instantiates a new royal mail.
	 * Sets up the name, Create an array list of Cost rules, set the cost rules for 
	 * Royal Mail. 
	 */
	public RoyalMail(){
		
		super.setServiceName("Royal Mail");
		ArrayList<CostRule> RMRules=new ArrayList<CostRule>();
		RMRules.add(new RM0_HalfRule());
		RMRules.add(new RMHalf_1Rule());
		RMRules.add(new RM1_MaxRule());
		super.setCostRules(RMRules);
		
	}
}
