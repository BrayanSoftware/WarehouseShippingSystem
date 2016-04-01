package retailer;

import java.util.ArrayList;
import java.util.Scanner;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * The Class Package.
 */
public class Package{
	
	/** The length cm. */
	private int lengthCm;
	
	/** The width cm. */
	private int widthCm;
	
	/** The height cm. */
	private int heightCm;
	
	/** The item weight. */
	private double itemWeight;
	
	/** The packaging option. */
	private PackagingOption packOption;
	
	/** The total weight. */
	private double totalWeight;
	
	/** The to address. */
	private String toAddress;
	
	/** The shipping cost. */
	private BigDecimal shippingCost;
	
	/** The id. */
	private int id;
	
	/** The id generator. */
	private static int idGenerator=1;
	
	/** Whether this is dispatched or not. */
	private boolean isDispatched;
	
	/**
	 * Instantiates a new package.
	 *
	 * @param lengthCm the length cm
	 * @param widthCm the width cm
	 * @param heightCm the height cm
	 * @param itemWeight the item weight
	 * @param toAddress the to address
	 */
	public Package(int lengthCm,int widthCm, int heightCm, double itemWeight, String toAddress){
		this.lengthCm=lengthCm;
		this.widthCm=widthCm;
		this.heightCm=heightCm;
		
		this.itemWeight=itemWeight;
		this.packOption=selectBestPackagingOption();
		this.totalWeight=this.itemWeight+this.packOption.getWeight(); 
		
		this.toAddress=toAddress;
		shippingCost=null;
		
		this.id=idGenerator;
		idGenerator++;
		
		setDispatched(false);
		
	}
	
	/**
	 * Checks if is dispatched.
	 *
	 * @return true, if is dispatched
	 */
	public boolean isDispatched() {
		return isDispatched;
	}

	/**
	 * Sets the dispatched.
	 *
	 * @param isDispatched the new dispatched
	 */
	public void setDispatched(boolean isDispatched) {
		this.isDispatched = isDispatched;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getID(){
		return id;
	}
	
	/**
	 * Gets the shipping cost.
	 *
	 * @return the shipping cost
	 */
	public BigDecimal getShippingCost() {
		return shippingCost;
	}

	/**
	 * Sets the shipping cost.
	 *
	 * @param shippingCost the new shipping cost
	 */
	public void setShippingCost(BigDecimal shippingCost) {
		this.shippingCost = shippingCost;
	}

	/**
	 * Gets the to address.
	 *
	 * @return the to address
	 */
	public String getToAddress() {
		return toAddress;
	}
	
	/**
	 * Sets the to address.
	 *
	 * @param toAddress the new to address
	 */
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	
	/**
	 * Sets the item weight. This will also set the total
	 * weight accordingly.
	 *
	 * @param itemWeight the new item weight
	 */
	public void setItemWeight(double itemWeight) {
		DecimalFormat df = new DecimalFormat("#.##");
		this.itemWeight=Double.valueOf(df.format(itemWeight).toString());
		this.totalWeight=Double.valueOf(df.format(this.itemWeight+this.packOption.getWeight()).toString());
		
	}
	
	/**
	 * Gets the item weight.
	 *
	 * @return the item weight
	 */
	public double getItemWeight() {
		return this.itemWeight;
	}
	
	/**
	 * Gets the packaging option.
	 *
	 * @return the packaging option
	 */
	public PackagingOption getPackagingOption(){
		return this.packOption;
	}
	
	/**
	 * Select packaging options.
	 *
	 * @return an array list of suitable packaging options.
	 */
	private ArrayList<PackagingOption> selectPackagingOptions(){
		ArrayList<PackagingOption> suitablePackagingOptions=new ArrayList<PackagingOption>();
		
		for (PackagingOption option: PackagingOption.values()){
			if(lengthCm<=option.getMaxLengthCm() && widthCm<=option.getMaxWidthCm() && heightCm<=option.getMaxHeightCm()){
				suitablePackagingOptions.add(option);
			}
		}
		return suitablePackagingOptions; 
	}
	
	/**
	 * Select best packaging option.
	 *
	 * @return the best packaging option
	 */
	private PackagingOption selectBestPackagingOption(){
		ArrayList<PackagingOption> suitablePackagingOptions=selectPackagingOptions();
		PackagingOption bestOption=suitablePackagingOptions.get(0);
		int leastVolume = bestOption.getMaxLengthCm()*bestOption.getMaxWidthCm()*bestOption.getMaxHeightCm();
		for (PackagingOption option: suitablePackagingOptions){
			int tempValue=option.getMaxLengthCm()*option.getMaxWidthCm()*option.getMaxHeightCm();
			if(tempValue<leastVolume){
				leastVolume=tempValue;
				bestOption=option;
			}
		}
		return bestOption;
	}
	
	/**
	 * Sets the packaging option.
	 */
	public void setPackagingOption(){ 
		ArrayList<PackagingOption> suitablePackagingOptions=selectPackagingOptions();
		for(PackagingOption option: suitablePackagingOptions){
			System.out.println(suitablePackagingOptions.indexOf(option)+" "+option.name());
		}
		System.out.print("\nPlease enter the number of preferred packaging option. > ");
		
		Scanner sc=null;
		try{
			sc = new Scanner(System.in);
			String option = sc.nextLine();
			while(true){
				if(option.isEmpty() || !option.matches("[0-9]+")  || Integer.parseInt(option)<0 ||
						Integer.parseInt(option)>suitablePackagingOptions.size()-1){
					System.out.print("Please enter a valid number > ");
					option=sc.nextLine();
				}
				else{
					this.packOption=suitablePackagingOptions.get(Integer.parseInt(option));
					this.totalWeight=this.itemWeight+this.packOption.getWeight();
					break; 
				}
			}	
		}
		catch(Exception e){
			if(sc!=null){
				sc.close();
			}
			System.out.println("Please try again.");
		}
		finally{
			sc.close();
		}
	}
	
	/**
	 * Sets the packaging option. Changes the total weight accordingly.
	 *
	 * @param packOption the new packaging option
	 */
	public void setPackagingOption(PackagingOption packOption){ 
		this.packOption=packOption;
		this.totalWeight=this.itemWeight+this.packOption.getWeight();
	}
	
	/**
	 * Gets the total weight.
	 *
	 * @return the total weight
	 */
	public double getTotalWeight(){
		return this.totalWeight;
	}
	
	/**
	 * Gets the length cm.
	 *
	 * @return the length cm
	 */
	public int getLengthCm() {
		return lengthCm;
	}

	/**
	 * Sets the length cm.
	 *
	 * @param lengthCm the new length cm
	 */
	public void setLengthCm(int lengthCm) {
		this.lengthCm = lengthCm;
		setPackagingOption(selectBestPackagingOption());
	}

	/**
	 * Gets the width cm.
	 *
	 * @return the width cm
	 */
	public int getWidthCm() {
		return widthCm;
	}

	/**
	 * Sets the width cm.
	 *
	 * @param widthCm the new width cm
	 */
	public void setWidthCm(int widthCm) {
		this.widthCm = widthCm;
		setPackagingOption(selectBestPackagingOption());
	}

	/**
	 * Gets the height cm.
	 *
	 * @return the height cm
	 */
	public int getHeightCm() {
		return heightCm;
	}

	/**
	 * Sets the height cm.
	 *
	 * @param heightCm the new height cm
	 */
	public void setHeightCm(int heightCm) {
		this.heightCm = heightCm;
		setPackagingOption(selectBestPackagingOption());
	}
	
} 

