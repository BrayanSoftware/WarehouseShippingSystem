package shipping.dhl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import shipping.CostRule;


/**
 * The Class DHL0_MaxRule.
 * The rule that calculates the cost of any weight for DHL
 */
class DHL0_MaxRule extends CostRule{

	/**
	 * Instantiates a new DH l0_ max rule.
	 * Calls the super class constructor with weight lower limit, weight upper limit and ship time in days
	 */
	public DHL0_MaxRule() {
		super(0, Double.MAX_VALUE,1);
	}

	/* 
	 * @see shipping.CostRule#caculateCost(double). This is the rule specific implementation of that method.
	 */
	@Override
	protected BigDecimal caculateCost(double weight) {
		return new BigDecimal(10.00+10.00*formatWeight(weight)).setScale(2, RoundingMode.CEILING);
	}
}
