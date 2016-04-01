package shipping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import retailer.Package;


/**
 * The Class abstractShippingService.
 */
public abstract class abstractShippingService implements ShippingService{
	
	/** The service name. */
	private String serviceName;
	
	/** The rules. */
	private ArrayList<CostRule> rules;
	
	/** The handled packages. */
	private HashMap<UUID,ArrayList<Package>> handledPackages=new HashMap<UUID,ArrayList<Package>>();
	
	/**
	 * Sets the service name.
	 *
	 * @param serviceName the new service name
	 */
	protected void setServiceName(String serviceName){
		this.serviceName=serviceName;
	}
	
	/* 
	 * @see shipping.ShippingService#getServiceName()
	 */
	@Override
	public String getServiceName(){
		return serviceName;
	}
	
	/**
	 * Sets the cost rules.
	 *
	 * @param rules the new cost rules in an ArrayList
	 */
	protected void setCostRules(ArrayList<CostRule> rules){
		this.rules=rules;
	}
	
	/**
	 * Gets the cost rules.
	 *
	 * @return the cost rules
	 */
	protected ArrayList<CostRule> getCostRules(){
		return rules;
	}
	
	/**
	 * Select cost rule that is applicable for a given weight.
	 *
	 * @param rules an array list of cost rules.
	 * @param weight the weight
	 * @return the satisfying cost rule
	 */
	protected CostRule selectCostRule (ArrayList<CostRule> rules,double weight){
		CostRule correctRule=null;
		weight=CostRule.formatWeight(weight);
		
		for(CostRule rule:rules){
			if( weight>=rule.getWeightLowerLimitKg() & weight<=rule.getWeightUpperLimitKg() ){
				correctRule=rule;
			}
		}
		return correctRule;
	}
	
	/* 
	 * @see shipping.ShippingService#getShippingCost(double)
	 */
	@Override
	public BigDecimal getShippingCost(double weight) {
		CostRule rule=selectCostRule(getCostRules(),weight);
		return rule.caculateCost(weight);
	}
	
	/* 
	 * @see shipping.ShippingService#getShippingTimeInDays(double)
	 */
	@Override
	public int getShippingTimeInDays(double weight) {
		CostRule rule=selectCostRule(getCostRules(),weight);
		return rule.getShipTimeInDays();
	}
	
	
	/* 
	 * @see shipping.ShippingService#handleDispatchedPackages(java.util.UUID, retailer.Package, java.lang.String)
	 */
	@Override
	public String handleDispatchedPackages(UUID retailerId,Package pack, String returnAddress){
		if(!handledPackages.containsKey(retailerId)){
			ArrayList<Package> temp=new ArrayList<Package>();
			temp.add(pack);
			handledPackages.put(retailerId,temp);
		}
		else{
			handledPackages.get(retailerId).add(pack);
		}
		String rtnString="Your parcel total weight is: "+ pack.getTotalWeight()+"Kg\n"
				+"It will be collected by "+getServiceName()+" today and delivered to "+pack.getToAddress()+".\n"
				+"You will be charged £ "+pack.getShippingCost();
		return rtnString;
	}
	
	/* 
	 * @see shipping.ShippingService#getHandledPackages(java.util.UUID)
	 */
	@Override
	public ArrayList<Package> getHandledPackages(UUID retailerId){
		if(handledPackages.containsKey(retailerId)){
			return handledPackages.get(retailerId); 
		}
		else{
			return new ArrayList<Package>();
		}
	}
	
}
