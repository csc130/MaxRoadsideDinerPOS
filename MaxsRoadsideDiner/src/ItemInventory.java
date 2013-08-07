import java.text.DecimalFormat;


public class ItemInventory 
{
	static int itemNumber;
	static String category;
	static String name;
	static String itemDescription;
	static String size;
	static double price;
	static int quantity;
	
	
	public ItemInventory()
	{
		
		itemNumber = 0;
		category = "";
		name = "";
		itemDescription = "";
		size = "";
		price = 0.00;
		quantity = 0;
		
	}
		
		public ItemInventory(int itemNumber, String name, double price, String category,
				String itemDescription, String size) {
			
			itemNumber = 0;
			category = "";
			name = "";
			itemDescription = "";
			size = "";
			price = 0.00;
			
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
		public static void setItemNumber(int itemNum) {
			itemNumber = itemNum;
		}
		/**
		 * @return the cost
		 */
		public static double getPrice() {
			return price;
		}
		/**
		 * @param cost the cost to set
		 */
		public static void setPrice(double cost) {
			price = cost;
		}

		/**
		 * @return the itemCategory
		 */
		public static String getItemCategory() {
			return category;
		}
		/**
		 * @param itemCategory the itemCategory to set
		 */
		public static void setItemCategory(String itemCategory) {
			category = itemCategory;
		}
		/**
		 * @return the itemDescription
		 */
		public static String getItemDescription() {
			return itemDescription;
		}
		/**
		 * @param itemDescription the itemDescription to set
		 */
		public static void setItemDescription(String description) {
			itemDescription = description;
		}

		/**
		 * @return the size
		 */
		public String getSize() {
			return size;
		}
		/**
		 * @param size the size to set
		 */
		public static void setSize(String itemSize) {
			size = itemSize;
		}

		public static String getName() {
			return name;
		}

		public static void setName(String itemName) {
			name = itemName;
		}

		
	}


