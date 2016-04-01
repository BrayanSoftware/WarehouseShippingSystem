package shipping;

import java.math.BigDecimal;
import java.text.DecimalFormat;


/**
 * The Class CostRule.
 */
public abstract class CostRule{
	
	/** The weight lower limit kg. */
	private double weightLowerLimitKg;
	
	/** The weight upper limit kg. */
	private double weightUpperLimitKg;
	
	/** The ship time in days. */
	private int shipTimeInDays;
	
	
	/**
	 * Instantiates a new cost rule.
	 *
	 * @param weightLowerLimitKg the weight lower limit kg
	 * @param weightUpperLimitKg the weight upper limit kg
	 * @param shipTimeInDays the ship time in days
	 */
	public CostRule(double weightLowerLimitKg,double weightUpperLimitKg,int shipTimeInDays){
		this.setWeightLowerLimitKg(weightLowerLimitKg);
		this.setWeightUpperLimitKg(weightUpperLimitKg);
		this.setShipTimeInDays(shipTimeInDays);
	}
	
	/**
	 * Format weight.
	 *
	 * @param weight the weight
	 * @return formatted weight in the form of a double
	 */
	static protected Double formatWeight(double weight){
		DecimalFormat weightFormat = new DecimalFormat("#.###");
		String strFormattedWeight=weightFormat.format(weight).toString();
		Double dblFormattedWeight=Double.valueOf(strFormattedWeight);
		return dblFormattedWeight;
	}
	
	/**
	 * Calculate cost.
	 *
	 * @param weight the weight
	 * @return cost in the form of big decimal
	 */
	protected abstract BigDecimal caculateCost(double weight);
	
	/**
	 * Sets the weight lower limit kg.
	 *
	 * @param weightLowerLimitKg the new weight lower limit kg
	 */
	public void setWeightLowerLimitKg(double weightLowerLimitKg) {
		this.weightLowerLimitKg = formatWeight(weightLowerLimitKg);
		
	}
	
	/**
	 * Sets the weight upper limit kg.
	 *
	 * @param weightUpperLimitKg the new weight upper limit kg
	 */
	public void setWeightUpperLimitKg(double weightUpperLimitKg) {
		this.weightUpperLimitKg = formatWeight(weightUpperLimitKg);
		
	}
	
	/**
	 * Sets the ship time in days.
	 *
	 * @param shipTimeInDays the new ship time in days
	 */
	public void setShipTimeInDays(int shipTimeInDays){
		this.shipTimeInDays=shipTimeInDays;
	}
	
	/**
	 * Gets the weight lower limit kg.
	 *
	 * @return the weight lower limit kg
	 */
	public double getWeightLowerLimitKg() {
		return weightLowerLimitKg;
	}
	
	/**
	 * Gets the weight upper limit kg.
	 *
	 * @return the weight upper limit kg
	 */
	public double getWeightUpperLimitKg() {
		return weightUpperLimitKg;
	}
	
	/**
	 * Gets the ship time in days.
	 *
	 * @return the ship time in days
	 */
	public int getShipTimeInDays(){
		return shipTimeInDays;
	}
	
}

