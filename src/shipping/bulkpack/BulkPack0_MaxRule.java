package shipping.bulkpack;

import shipping.CostRule;

import java.math.BigDecimal;
import java.math.RoundingMode;


/**
 * The Class BulkPack0_MaxRule.
 * The rule that calculates the cost of any weight for Bulk Pack
 */
class BulkPack0_MaxRule extends CostRule{

	/**
	 * Instantiates a new bulk pack0_ max rule.
	 * Calls the super class constructor with weight lower limit, weight upper limit and ship time in days
	 */
	public BulkPack0_MaxRule() {
		super(0, Double.MAX_VALUE,10);
	}

	/* 
	 * @see shipping.CostRule#caculateCost(double). This is the rule specific implementation of that method.
	 */
	@Override
	protected BigDecimal caculateCost(double weight) {
		return new BigDecimal(25.00).setScale(2, RoundingMode.CEILING);
	}
}