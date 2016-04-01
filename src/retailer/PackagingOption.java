package retailer;


// TODO: Auto-generated Javadoc
/**
 * The Enum PackagingOption.
 */
enum PackagingOption{
	
	/** The envelope. */ ENVELOPE(0.150,30,22,7), 
	/** The small box.*/ SMALL_BOX(0.5,30,25,25), 
	/** The big box. */  BIG_BOX(1.5,60,30,30);
	
	/** The weight. */
	private double weight;
	
	/** The maxlength cm. */
	private int maxlengthCm;
	
	/** The maxwidth cm. */
	private int maxwidthCm;
	
	/** The maxheight cm. */
	private int maxheightCm;
	
	/**
	 * Instantiates a new packaging option.
	 *
	 * @param weight the weight
	 * @param maxlengthCm the maxlength cm
	 * @param maxwidthCm the maxwidth cm
	 * @param maxheightCm the maxheight cm
	 */
	private PackagingOption(double weight, int maxlengthCm, int maxwidthCm, int maxheightCm){
		this.weight = weight;
		this.maxlengthCm = maxlengthCm;
		this.maxwidthCm = maxwidthCm;
		this.maxheightCm = maxheightCm;
	
	}
	
	/**
	 * Gets the weight.
	 *
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * Gets the max length cm.
	 *
	 * @return the max length cm
	 */
	public int getMaxLengthCm() {
		return maxlengthCm;
	}

	/**
	 * Gets the max width cm.
	 *
	 * @return the max width cm
	 */
	public int getMaxWidthCm() {
		return maxwidthCm;
	}
	
	/**
	 * Gets the max height cm.
	 *
	 * @return the max height cm
	 */
	public int getMaxHeightCm() {
		return maxheightCm;
	}
}
