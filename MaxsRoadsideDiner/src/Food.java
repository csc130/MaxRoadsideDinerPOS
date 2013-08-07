

public class Food {
	
	public static enum Size { NONE, SMALL, MEDIUM, LARGE };
	public static enum Category { NONE, APPETIZER, ENTREE, DESSERT, BEVERAGE };
	public static enum MeatType { NONE, VEGETARIAN, PORK, CHICKEN, BEEF };

	private double cost = 0;
	private int itemNumber = numberOfItems + 1;
	private String name = "<<No Name Set>>";
	private Category itemCategory = Category.NONE;
	private String itemDescription = "<<No Description Set>>";
	private MeatType meatType = MeatType.NONE;
	private Size size = Size.NONE;
	
	private static int numberOfItems = 0;
	private static int numberSold = 0;
	
	public Food() {
		numberOfItems++;
	}
	
	/**
	 * @param itemNumber
	 * @param cost
	 * @param itemCategory
	 * @param itemDescription
	 * @param meatType
	 * @param size
	
	 */
	public Food(int itemNumber, String name, double cost, Category itemCategory,
			String itemDescription, MeatType meatType, Size size) {
		
		this.cost = cost;
		this.name = name;
		this.itemNumber = itemNumber;
		this.itemCategory = itemCategory;
		this.itemDescription = itemDescription;
		this.meatType = meatType;
		this.size = size;
		
	}
	
	public void sell() {
		numberSold += 1;
	}
	
	public int getNumberSold() {
		return numberSold;
	}
	
	/**
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}
	/**
	 * @param cost the cost to set
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}
	/**
	 * @return the itemNumber
	 */
	public int getItemNumber() {
		return itemNumber;
	}
	/**
	 * @param itemNumber the itemNumber to set
	 */
	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}
	/**
	 * @return the itemCategory
	 */
	public Category getItemCategory() {
		return itemCategory;
	}
	/**
	 * @param itemCategory the itemCategory to set
	 */
	public void setItemCategory(Category itemCategory) {
		this.itemCategory = itemCategory;
	}
	/**
	 * @return the itemDescription
	 */
	public String getItemDescription() {
		return itemDescription;
	}
	/**
	 * @param itemDescription the itemDescription to set
	 */
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	/**
	 * @return the meatType
	 */
	public MeatType getMeatType() {
		return meatType;
	}
	/**
	 * @param meatType the meatType to set
	 */
	public void setMeatType(MeatType meatType) {
		this.meatType = meatType;
	}
	/**
	 * @return the size
	 */
	public Size getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(Size size) {
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
