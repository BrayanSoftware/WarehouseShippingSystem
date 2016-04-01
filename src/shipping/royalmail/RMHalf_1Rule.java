package shipping.royalmail;

import java.math.BigDecimal;
import java.math.RoundingMode;
import shipping.CostRule;


/**
 * The Class RMHalf_1Rule.
 * The rule that calculates the cost of weight between 0.501 and 1Kg for Royal Mail
 */
class RMHalf_1Rule extends CostRule{

	/**
	 * Instantiates a new RM half_1 rule.
	 * Calls the super class constructor with weight lower limit, weight upper limit and ship time in days. 
	 */
	public RMHalf_1Rule() {
		super(0.501,1,2);		
	}

	/* 
	 * @see shipping.CostRule#caculateCost(double). This is the rule specific implementation of that method.
	 */
	@Override
	protected BigDecimal caculateCost(double weight) {
		return new BigDecimal(5.00).setScale(2, RoundingMode.CEILING);
	}
	
}