package shipping.royalmail;

import java.math.BigDecimal;
import java.math.RoundingMode;
import shipping.CostRule;


/**
 * The Class RM1_MaxRule.
 * The rule that calculates the cost of weight higher than 1Kg for Royal Mail
 */
class RM1_MaxRule extends CostRule{

	/**
	 * Instantiates a new rm1_ max rule.
	 * Calls the super class constructor with weight lower limit, weight upper limit and ship time in days.
	 */
	public RM1_MaxRule() {
		super(1.001,Double.MAX_VALUE,4);
	}

	/* 
	 * @see shipping.CostRule#caculateCost(double).  This is the rule specific implementation of that method.
	 */
	@Override
	protected BigDecimal caculateCost(double weight) {
		return new BigDecimal(Math.ceil(formatWeight(weight))*5.00).setScale(2, RoundingMode.CEILING);
	}
}