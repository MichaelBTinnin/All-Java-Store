
/*****************************************************************
 * This class creates a bike and sets as well as returns the various attributes of a bike
 * 
 * @author michael
 *
 */
public class Bike
{
	// data members
	private String type;// bike type
	private double price;// bike price
	private String color;// bike color
	private int gears;// number of gears
	private int quantity;// number in stock
	private int itemNum;//item and its location in array

	

	// default constructor
	public Bike()
	{
		type = "null";
		price = -1;
		color = "null";
		gears = -1;
		quantity = -1;
	}

	// overloaded auto generated constructor
	public Bike(String type, double price, String color, int gears, int quantity)
	{
		super ( );
		this.type = type;
		this.price = price;
		this.color = color;
		this.gears = gears;
		this.quantity = quantity;
	}
	//here are the getters and setters
	public String getType( )
	{
		return type;
	}

	public void setType( String type )
	{
		this.type = type;
	}

	public double getPrice( )
	{
		return price;
	}

	public void setPrice( double price )
	{
		this.price = price;
	}

	public String getColor( )
	{
		return color;
	}

	public void setColor( String color )
	{
		this.color = color;
	}

	public int getGears( )
	{
		return gears;
	}

	public void setGears( int gears )
	{
		this.gears = gears;
	}

	public int getQuantity( )
	{
		return quantity;
	}

	/**
	 * @param quantity
	 */
	public void setQuantity( int quantity )
	{
		this.quantity = quantity;
	}
	public int getItemNum( )
	{
		return itemNum;
	}

	public void setItemNum( int itemNum )
	{
		this.itemNum = itemNum;
	}
	//to String 
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString( )
	{
		return "                |" + type + ":|\n *price " + price + " - color " + color + " - gears " + gears
				+ " - quantity " + quantity + " *";
	}

}
