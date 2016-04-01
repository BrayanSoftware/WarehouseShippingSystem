package shipping.royalmail;

import java.math.BigDecimal;
import java.math.RoundingMode;

import shipping.CostRule;


/**
 * The Class RM0_HalfRule.
 * The rule that calculates the cost of weight up to 0.5Kg for Royal Mail
 */
class RM0_HalfRule extends CostRule{

	/**
	 * Instantiates a new r m0_ half rule.
	 * Calls the super class constructor with weight lower limit, weight upper limit and ship time in days. 
	 */
	public RM0_HalfRule() {
		super(0,0.5,2);		
	}

	/* 
	 * @see shipping.CostRule#caculateCost(double). This is the rule specific implementation of that method.
	 */
	@Override
	protected BigDecimal caculateCost(double weight) {
		return new BigDecimal(2.50).setScale(2, RoundingMode.CEILING);
	}
}

