
/*
 * Name: MichaelTinnin
 * Assignment: Store 1-7
 * Due: Pt5 4-22-29, Pt6 4-23-19, Pt7 4-24-19
 * Input output files: store_items.txt
 * Description: 
 * Pt1-3 Program has several bikes with 5 data fields, 
 * also bikes and their data can be entered in, 
 * outputting all bikes in inventory
 * Pt4 Outputs the bikes from inventory on 
 *     a menu of all items in stock
 * Pt5-6 A customer or group order from the menu is now possible.
 * Pt7 Program now has a password that will release a manager's
 * report showing inventory, total sales for the day, and missed
 * sales due to item out of stock. Manager can "buy' and enter new
 * bikes into the text file of inventory.
 * Pt8 user can verify order before it is placed
 */
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class Store_Driver8d_MT
{
	public static Scanner input = new Scanner ( System.in );
	public static java.io.File inFile;
	public static Scanner inputFile;

	public static void main( String[ ] args ) throws FileNotFoundException
	{
		System.out.println ( "Must have manager's access to open the store." );
		int password = 12345;// managers password
		System.out.println ( "Enter your manager's password" );
		int passwordAttempt = input.nextInt ( );// password entry to verify
		while ( passwordAttempt != password )
		{
			System.out.println ( "Invalid attempt, enter your manager's password." );
			passwordAttempt = input.nextInt ( );
		}

		Bike bikesArray[] = new Bike[50];// bikes in inventory
		readIn ( bikesArray );

		// create bikes for part 1-2 of assignment
		/*
		 * Bike OverloadedBike = new Bike ( "road", 500.00, "blue", 21, 15 ); Bike DefaultBike = new Bike ( );
		 * System.out.println ( OverloadedBike.getType ( ) ); OverloadedBike.setType ( "mountain" ); System.out.println (
		 * OverloadedBike.getType ( ) ); System.out.println ( OverloadedBike.toString ( ) ); attributes ( DefaultBike );
		 * System.out.println ( DefaultBike.toString ( ) ); attributes ( OverloadedBike ); System.out.println (
		 * OverloadedBike.toString ( ) );
		 */

		inputFile.close ( );
	}// end of main

	/******************************************************************************
	 * determines number of bikes in array
	 * 
	 * @param bikesArray
	 *           bikes in inventory
	 * @return array location to add new type of bike to inventory
	 */
	public static int getCount( Bike[ ] bikesArray )
	{
		int count = 0;// count for filled locations of bikes
		for ( int i = 0; i < 50; i++ )
		{
			if ( bikesArray[i] != null )
			{
				count++;
			}
		}
		return count;
	}

	/*********************************************************************
	 * reads in text file of bike inventory and allows to add more bikes
	 * 
	 * @param inputFile
	 *           text file of bikes already in inventory
	 */
	private static void readIn( Bike[ ] bikesArray )
	{
		inFile = new java.io.File ( "store_items.txt" );
		try
		{
			inputFile = new Scanner ( inFile );

		} catch ( FileNotFoundException e )
		{
			// Use a RuntimeException to avoid messy programming elsewhere.
			throw new RuntimeException ( "file not found" + e );
		}

		String[ ] bikeAttributesArray = new String[6];// reads a line of fields in bikeAttributesArray

		int i = 0;// location of a bike in the bikesArray
		while ( inputFile.hasNextLine ( ) )
		{
			Bike nextBike = new Bike ( );// next bike to be entered into inventory

			String oneBike = inputFile.nextLine ( );// temporary location of the bike being read in
			bikeAttributesArray = oneBike.split ( "," );
			nextBike.setItemNum ( Integer.parseInt ( bikeAttributesArray[0] ) );// storing attributes of each bike
			nextBike.setType ( bikeAttributesArray[1] );// storing attributes of each bike
			nextBike.setPrice ( Double.parseDouble ( bikeAttributesArray[2] ) );// storing attributes of each bike
			nextBike.setColor ( bikeAttributesArray[3] );// storing attributes of each bike
			nextBike.setGears ( Integer.parseInt ( bikeAttributesArray[4] ) );// storing attributes of each bike
			nextBike.setQuantity ( Integer.parseInt ( bikeAttributesArray[5] ) );// storing attributes of each bike
			bikesArray[i] = nextBike;
			// System.out.println ( nextBike ); //useful for testing
			i++;
		}
		printMenu ( bikesArray );
		System.out.println ( "Do you need to input more bikes we already carry into inventory? Enter y or n." );
		char anotherBike = input.next ( ).toLowerCase ( ).charAt ( 0 );// LCV

		while ( ! ( anotherBike == ( 'y' ) ) && ( ! ( anotherBike == ( 'n' ) ) ) )
		{
			System.out.println ( "Invalid entry, do you need to input more bikes to inventory? Enter y or n." );
			anotherBike = input.next ( ).toLowerCase ( ).charAt ( 0 );// LCV

		}

		while ( anotherBike == ( 'y' ) )
		{

			changeQuantity ( bikesArray );

			System.out.println ( "Do you have another bike we already carry to enter? Enter y or n." );
			anotherBike = input.next ( ).toLowerCase ( ).charAt ( 0 );// LCV
			while ( ! ( anotherBike == ( 'y' ) ) && ( ! ( anotherBike == ( 'n' ) ) ) )
			{
				System.out.println ( "Invalid entry, do you need to input more bikes to inventory? Enter y or n." );
				anotherBike = input.next ( ).toLowerCase ( ).charAt ( 0 );// LCV

			}
		}
		/*
		 * used to print bikesArray in part 3 for ( int i = 0; i < count; i++ ) {
		 * 
		 * System.out.println ( bikesArray[i] ); }
		 */

		printMenu ( bikesArray );
		double daysTotal = 0;// total sales for the day
		int missedSales = 0;// customer order of out of stock item
		enterPurchases ( bikesArray, missedSales, daysTotal );

	}// end of readIn

	/********************************************************************************
	 * manager enters password to see inventory, total sales for the day, and missed sales. also replenishes inventory
	 * 
	 * @param bikesArray
	 *           bikes in stock and the info on each
	 * @param count
	 *           location in bikesArray
	 * @param daysTotal
	 *           total sales for the day
	 * @param missedSales
	 *           sales missed due to product out of stock
	 * @param b
	 *           new bike going into inventory
	 * 
	 */
	private static void managersReport( Bike[ ] bikesArray, double daysTotal, int missedSales )
	{
		char isMgr = 'n';// identity of user
		while ( isMgr == 'n' )
		{
			System.out.println ( "Are you the manager? Enter y or n." );
			isMgr = input.next ( ).toLowerCase ( ).charAt ( 0 );
		}

		int password = 12345;// managers password
		System.out.println ( "Enter your manager's password" );
		int passwordAttempt = input.nextInt ( );// password entry to verify
		while ( passwordAttempt != password )
		{
			System.out.println ( "Invalid attempt, enter your manager's password." );
			passwordAttempt = input.nextInt ( );
		}
		System.out.println ( "CURRENT INVENTORY" );
		System.out.printf ( "%10s%15s%15s%15s%15s%15s\n", "Item #", "Type", "Color", "Gears", "Quantity", "Price" );
		int count = getCount ( bikesArray );// number of bikes in array
		for ( int i = 0; i < count; i++ )
		{
			System.out.printf ( "%5d%20s%15s%15d%15d%15.2f", bikesArray[i].getItemNum ( ), bikesArray[i].getType ( ),
					bikesArray[i].getColor ( ), bikesArray[i].getGears ( ), bikesArray[i].getQuantity ( ),
					bikesArray[i].getPrice ( ) );
			System.out.println (
					"   \n-------------------------------------------------------------------------------------------" );

		}
		System.out.println ( "" );
		System.out.println ( "Total sales for today were " + daysTotal );
		System.out.println ( "Number of sales missed due to a 0 quantity in stock is " + missedSales );
		System.out.println ( "Do you want to increase the quantity of bikes we already carry? enter y or n" );
		char quantityInventory = input.next ( ).toLowerCase ( ).charAt ( 0 );// add quantity to bikes or not

		PrintWriter writeFile = null;

		try
		{
			writeFile = new PrintWriter ( "store_items.txt" );
			if ( quantityInventory == 'y' )
			{
				while ( quantityInventory == 'y' && count < bikesArray.length )
				{
					changeQuantity ( bikesArray );

					System.out.println ( "Do you need to enter more bike(s) we already carry into inventory,enter y or n." );
					quantityInventory = input.next ( ).toLowerCase ( ).charAt ( 0 );

				}
			}
			System.out.println ( "Are there any new types of bikes you need to enter? enter y or n" );
			char typeInventory = input.next ( ).toLowerCase ( ).charAt ( 0 );// new type of bike to add to inventory
			while ( typeInventory == ( 'y' ) )
			{
				Bike b = new Bike ( );// new type of bike going into inventory
				b = attributesNewType ( b );
				bikesArray[count] = b;
				count++;
				System.out.println ( "Are there any new types of bikes you need to enter?" );
				typeInventory = input.next ( ).toLowerCase ( ).charAt ( 0 );
			}
			for ( int i = 0; i < count; i++ )
			{
				writeFile.print ( bikesArray[i].getItemNum ( ) + "," );
				writeFile.print ( bikesArray[i].getType ( ) + "," );
				writeFile.print ( bikesArray[i].getPrice ( ) + "," );
				writeFile.print ( bikesArray[i].getColor ( ) + ',' );
				writeFile.print ( bikesArray[i].getGears ( ) + "," );
				writeFile.print ( bikesArray[i].getQuantity ( ) + "\n" );
			}
			writeFile.close ( );
		} catch ( FileNotFoundException e )
		{
			throw new RuntimeException ( "file not found" + e );

		}

	}// end of managersReport

	/*************************************************************************************************
	 * adds new type of bike to inventory
	 * 
	 * @param bikesArray
	 *           inventory location
	 * @return new bike type to add to BikesArray/inventory
	 */
	private static Bike attributesNewType( Bike b )
	{
		System.out.println ( "Enter new item's item number." );
		b.setItemNum ( input.nextInt ( ) );
		System.out.println ( "Enter type: " );
		b.setType ( input.nextLine ( ) );
		b.setType ( input.nextLine ( ) );
		System.out.println ( "Enter price: " );
		b.setPrice ( input.nextDouble ( ) );
		System.out.println ( "Enter number of gears:" );
		b.setGears ( input.nextInt ( ) );
		System.out.println ( "Enter color:" );
		b.setColor ( input.next ( ) );
		System.out.println ( "Enter quantity:" );
		b.setQuantity ( input.nextInt ( ) );
		return b;

	}// end of attributesNewType

	/********************************************************************************
	 * increases quantity of bikes already listed in inventory
	 * 
	 * @param bikesArray
	 *           attributes of bikes carried at the store
	 */

	public static void changeQuantity( Bike[ ] bikesArray )
	{
		System.out.println ( "Enter item number to change quantity. " );
		int cngQty = input.nextInt ( );// type of bike to be added to inventory
		System.out.println ( "Enter amount to add to quantity: " );
		int qtyToAdd = input.nextInt ( );// quantity of the bike being added
		bikesArray[cngQty].setQuantity ( bikesArray[cngQty].getQuantity ( ) + qtyToAdd );

	}// end of change quantity

	/****************************************************************************
	 * prints a menu of bikes in inventory with their attributes
	 * 
	 * @param bikesArray
	 *           attributes of bikes in inventory
	 * @param count
	 *           bikeArray location being printed
	 */
	public static void printMenu( Bike[ ] bikesArray )
	{
		int count = getCount ( bikesArray );// number of bikes in array
		System.out.println ( "                           __________________________" );
		System.out.println ( "                              |TREK OF FT COLLINS|\n" );

		System.out.printf ( "%10s%15s%15s%15s%15s%15s\n", "Item #", "Type", "Color", "Gears", "Quantity", "Price" );

		for ( int i = 0; i < count; i++ )
		{
			System.out.printf ( "%5d%20s%15s%15d%15d%15.2f", bikesArray[i].getItemNum ( ), bikesArray[i].getType ( ),
					bikesArray[i].getColor ( ), bikesArray[i].getGears ( ), bikesArray[i].getQuantity ( ),
					bikesArray[i].getPrice ( ) );
			System.out.println (
					"   \n-------------------------------------------------------------------------------------------" );

		}
		System.out.println ( "                          _________________________________" );
		System.out.println ( "                       ***OR CUSTOM ORDER WITH YOUR CHOICE***" );
		System.out.println ( "                                  **OF OPTIONS**\n" );
	}// end of printMenu

	/********************************************************************************
	 * 
	 * @param bikesArray
	 *           bikes on menu
	 * @param missedSales
	 *           sales missed because an item was out of stock today
	 * @param daysTotal
	 *           Total sales for the day
	 */
	public static void enterPurchases( Bike[ ] bikesArray, int missedSales, double daysTotal )
	{
		LinkedList<Bike> list = new LinkedList<Bike> ( );
		// list.add(bikesArray[0]); if wanted to add bikesArray to linked List
		double total = 0;// final bill

		double customerTotal = 0;// amount of bill from each customer in a group
		double price = 0;// amount of each bike going on bill

		char anotherGroup;// LCV
		do
		{
			char anotherCustomer = 'y';// LCV

			while ( anotherCustomer == ( 'y' ) )
			{
				printMenu ( bikesArray );
				int type;// type of bike being purchased

				char chooseBike = 'y';// LCV
				while ( chooseBike == ( 'y' ) )
				{
					System.out.println ( "Please enter the item number of the" );
					System.out.println ( "type of bike you would like to purchase," );
					type = input.nextInt ( );
					int qty = bikesArray[type].getQuantity ( );// quantity in stock of bike being purchased
					if ( qty > 0 )
					{
						qty--;
						bikesArray[type].setQuantity(qty);
						list.add ( bikesArray[type] );
						/*
						 * Pt7 qty--; bikesArray[type].setQuantity ( qty ); price = bikesArray[type].getPrice ( ); total +=
						 * price; customerTotal += price; daysTotal += total;
						 */

					}
					else
					{
						System.out.println ( "We don't have that bike in stock." );
						missedSales++;
					}

					System.out.println ( "Do you want to add another bike before the next customer "
							+ "in your group chooses? enter y or n " );
					chooseBike = input.next ( ).toLowerCase ( ).charAt ( 0 );

					while ( ! ( chooseBike == ( 'y' ) ) && ( ! ( chooseBike == ( 'n' ) ) ) )
					{
						System.out.println (
								"Invalid answer, Do you want to add another bike to your purchase before the next person chooses, enter y or n ? " );
						chooseBike = input.next ( ).toLowerCase ( ).charAt ( 0 );
					}

				} // end of while(chooseBike.equals("yes")
				for ( int i = 0; i < list.size ( ); i++ )
				{
					System.out.println ( list.get ( i ) + " " );
				}
				System.out.println ( "Is the list of items correct?" );
				char verify = input.next ( ).toLowerCase ( ).charAt ( 0 );// verifies order is correct
				if ( verify == 'y' )
				{
					;
				}
				else
				{
					char removeItm = 'y';// if an item is to be removed from the bill
					while ( removeItm == 'y' )
					{
						System.out.println (
								"Enter the item number you would like to remove. The first item on your order is item 0." );
						int listLoc = input.nextInt ( );// item to remove from bill
						int itemNum = list.get(listLoc).getItemNum();//item number of bike going back into inventory
						int quantity = bikesArray[itemNum].getQuantity();//quantity being adjusted due to invalid order
						quantity++;
						bikesArray[itemNum].setQuantity(quantity);
						list.remove ( listLoc );
						System.out.println ( "Are there more items to remove? enter y or n" );
						removeItm = input.next ( ).toLowerCase ( ).charAt ( 0 );
					}

				}
				for ( int i = 0; i < list.size ( ); i++ )
				{
					int itemNum = list.get ( i ).getItemNum ( );// location in bikesArray of bike on bill
					price = bikesArray[itemNum].getPrice ( );
					total += price;
					customerTotal += price;
					daysTotal += total;
				}
				System.out.println ( "Your individual cost is " + customerTotal );
				customerTotal = 0;
				for ( int i = list.size ( ) - 1; list.size ( ) - 1 >= 0; i-- )
				{
					list.remove ( list.get ( i ) );
				}
				System.out.println ( "Is there another customer on this bill? enter y or n" );
				anotherCustomer = input.next ( ).toLowerCase ( ).charAt ( 0 );
				while ( ! ( anotherCustomer == ( 'y' ) ) && ( ! ( anotherCustomer == ( 'n' ) ) ) )
				{
					System.out.println ( "Invalid answer, is there another customer on this bill, enter y or n ? " );
					anotherCustomer = input.next ( ).toLowerCase ( ).charAt ( 0 );
				}

			} // end of while(anotherCustomer == ('y'))
			System.out.println ( "Please pay " + total );
			total = 0;
			customerTotal = 0;

			System.out.println ( "Is there another group of customer(s)? enter y or n" );
			anotherGroup = input.next ( ).toLowerCase ( ).charAt ( 0 );
		} while ( anotherGroup == ( 'y' ) );
		managersReport ( bikesArray, daysTotal, missedSales );
	}// end of enterPurchases

}
/********************************************************************
 * Pt1-2 Went slow learning about creating objects and constructors Pt3 initialized variables in Bike class causing
 * trouble when auto generator was used for overloaded bike. Trial and error reading in the attributes line. When
 * reading in bikes entered by the user I initially created one bike above the loop, causing the bikes entered to be
 * overwritten with each entry. Solution, create a new bike in the loop Pt4 experimented with formatting for easy to
 * read layout of menu took trial and error PT5-6 had to experiment with setting quantity in a specific array. Pt7
 * needing to return two variables from make purchases to be used in managersReport, having trouble.
 */
