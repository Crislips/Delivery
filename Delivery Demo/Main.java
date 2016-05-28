import java.util.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;





public class Main {
	
	
	public static int id = 0;
	public static String company = "";
	public static int temp = 0;
	public static int weight = 0;
	public static int cost = 5;
	public static String fragile = "";
	public static String hazard = "";
	public static String date;
	
	
	
	public static String tableformat = "%-5d%-14s%-6d%-7d%-7d%-8s%-7s%-13s%n";
	public static String titleformat = "%-5s%-14s%-6s%-7s%-7s%-8s%-7s%-13s%n";

	
	//System.out.printf(titleformat, "ID", "COMPANY", "TEMP CELSIUS", "WEIGHT KG", "COST", "FRAGILE?", "HAZARDOUS?", "DELIVERY DATE");			/////Column title print before I made array.
	public static String[] columnTitles = new String[8];
	public void populateArray(){
	columnTitles[0] = "ID";
	columnTitles[1] = "COMPANY";
	columnTitles[2] = "TEMP";
	columnTitles[3] = "WEIGHT";
	columnTitles[4] = "COST";
	columnTitles[5] = "FRAGILE";
	columnTitles[6] = "HAZARD";
	columnTitles[7] = "DELIV DATE";
	}
	
	
	public static JFrame gui = new JFrame();
	public static int addme;
	public static int sum = 0;
	public static int mean = 0;
	public static int meanincrement;
	
	public static void main(String[] args) throws Exception {
		/*gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setSize(200, 200);
		gui.setVisible(true);
		gui.setTitle("First Gui");
		*/
		
		//Accessing driver from JAR file
		Class.forName("com.mysql.jdbc.Driver");
		
		
//CLICK TO LOGIN
		
		
		//Creating a variable for the connection called "con"
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/deliveries","root","root");
		//jdbc:mysql://localhost:3306/deliveries ----> This is the database name
		//root is the database user
		//root is the password
		
		//Populates Array of Column Titles
				Main arraymaker = new Main();
				arraymaker.populateArray();
		
	//Possible functions from SQL server and accompanying queries
		PreparedStatement statement = con.prepareStatement("select * from delivery");									//Default table
		ResultSet result = statement.executeQuery();
		
		//Company Functions
		PreparedStatement ASCcompany = con.prepareStatement("SELECT * FROM delivery ORDER BY company ASC");					//Sort ASC by company
		ResultSet companyASC = ASCcompany.executeQuery();
		PreparedStatement DESCcompany = con.prepareStatement("SELECT * FROM delivery ORDER BY company DESC");					//Sort DESC by company
		ResultSet companyDESC = DESCcompany.executeQuery();
		
		//Temp Functions
		PreparedStatement ASCtemp = con.prepareStatement("SELECT * FROM delivery ORDER BY tempC ASC");					//Sort ASC by temp
		ResultSet tempASC = ASCtemp.executeQuery();
		PreparedStatement DESCtemp = con.prepareStatement("SELECT * FROM delivery ORDER BY tempC DESC");					//Sort DESC by temp
		ResultSet tempDESC = DESCtemp.executeQuery();
		
		//Weight Functions
		PreparedStatement ASCweight = con.prepareStatement("SELECT * FROM delivery ORDER BY weightKG ASC");					//Sort ASC by weight
		ResultSet weightASC = ASCweight.executeQuery();
		PreparedStatement DESCweight = con.prepareStatement("SELECT * FROM delivery ORDER BY weightKG DESC");					//Sort DESC by weight
		ResultSet weightDESC = DESCweight.executeQuery();
		
		//Cost Functions
		PreparedStatement ASCcost = con.prepareStatement("SELECT * FROM delivery ORDER BY shipment_cost ASC");					//Sort ASC by cost
		ResultSet costASC = ASCcost.executeQuery();
		PreparedStatement DESCcost = con.prepareStatement("SELECT * FROM delivery ORDER BY shipment_cost DESC");					//Sort DESC by cost
		ResultSet costDESC = DESCcost.executeQuery();
		
		//Fragile Functions
		PreparedStatement ASCfragile = con.prepareStatement("SELECT * FROM delivery ORDER BY fragile ASC");					//Sort ASC by fragile
		ResultSet fragileASC = ASCfragile.executeQuery();
		PreparedStatement DESCfragile = con.prepareStatement("SELECT * FROM delivery ORDER BY fragile DESC");					//Sort DESC by fragile
		ResultSet fragileDESC = DESCfragile.executeQuery();
		
		//Hazardous functions
		PreparedStatement ASChazardous = con.prepareStatement("SELECT * FROM delivery ORDER BY hazardous ASC");					//Sort ASC by hazardous
		ResultSet hazardousASC = ASChazardous.executeQuery();
		PreparedStatement DESChazardous = con.prepareStatement("SELECT * FROM delivery ORDER BY hazardous DESC");					//Sort DESC by hazardous
		ResultSet hazardousDESC = DESChazardous.executeQuery();
		
		//Date functions
		PreparedStatement ASCdate = con.prepareStatement("SELECT * FROM delivery ORDER BY delivery_date ASC");					//Sort ASC by date
		ResultSet dateASC = ASCdate.executeQuery();
		PreparedStatement DESCdate = con.prepareStatement("SELECT * FROM delivery ORDER BY delivery_date DESC");					//Sort DESC by date
		ResultSet dateDESC = DESCdate.executeQuery();
		
		
		
		
		Scanner reader = new Scanner(System.in); // Reading from System.in
		while (true) {
			System.out.println("Welcome to the United States Cold Storage delivery management system. Would you like to continue to your orders");
			System.out.println("Type 'Yes' or 'No.' ");
			String User = reader.nextLine();
			
			if (User.equals("Yes") || User.equals("yes") || User.equals("Yes.") || User.equals("yes.")) {
				break;
			} else if (User.equals("No") || User.equals("no") || User.equals("No.") || User.equals("no.")) {
				System.out.println("Logged out. Exiting program. See ya later!");
				System.exit(1);
				break;
			} else {
				System.out.println("That is not 'Yes' or 'No,' please try again!");
				continue;
			}
		}
		
		
		//Prints out the whole table with column titles.
		System.out.printf(titleformat, columnTitles[0], columnTitles[1], columnTitles[2], columnTitles[3], columnTitles[4], columnTitles[5], columnTitles[6], columnTitles[7]);
		while(result.next()) {
			id = result.getInt(1);
			company = result.getString(2);
			temp = result.getInt(3);
			weight = result.getInt(4);
			cost = result.getInt(5);
			fragile = result.getString(6);
			hazard = result.getString(7);
			date = result.getString(8);
			System.out.printf(tableformat, id, company, temp, weight, cost, fragile, hazard, date);
		}
		
		
		boolean on = true;
		while (on) {
			System.out.println('\n' + "From here, you may manipulate certain data to view the table differently. What would you like to do?");
			System.out.println("The functions you may perform are 'sort,' 'sum,' 'average,' and 'exit.'");
			String tableAction = reader.nextLine();
			
						//Enters a switch based off of functions to perform
			if (tableAction.equals("sort")){
				
				while (true){
					System.out.println("Which column would you like to sort by? Type 'company,' 'weight,' 'cost,' or 'date.' Type 'back' to go back to the previous selection.");
					String category = reader.nextLine();
					switch (category) {				//Enters a switch based off of what category you want to perform the function on
					case "company":
						while(true){
							System.out.println("Type: 'ascending' or 'descending.'");
							String direction = reader.nextLine();
							if (direction.equals("ascending")){
								//Prints out whole table sorted by company name in ascending order
								System.out.printf(titleformat, columnTitles[0], columnTitles[1], columnTitles[2], columnTitles[3], columnTitles[4], columnTitles[5], columnTitles[6], columnTitles[7]);
								while(companyASC.next()){
									id = companyASC.getInt(1);
									company = companyASC.getString(2);
									temp = companyASC.getInt(3);
									weight = companyASC.getInt(4);
									cost = companyASC.getInt(5);
									fragile = companyASC.getString(6);
									hazard = companyASC.getString(7);
									date = companyASC.getString(8);
									System.out.printf(tableformat, id, company, temp, weight, cost, fragile, hazard, date); }
								System.out.println('\n' + "Press enter to continue.");
								reader.nextLine();
								break;
							} else if (direction.equals("descending")){
								//Prints out whole table sorted by company name in descending order
								System.out.printf(titleformat, columnTitles[0], columnTitles[1], columnTitles[2], columnTitles[3], columnTitles[4], columnTitles[5], columnTitles[6], columnTitles[7]);
								while(companyDESC.next()){
									id = companyDESC.getInt(1);
									company = companyDESC.getString(2);
									temp = companyDESC.getInt(3);
									weight = companyDESC.getInt(4);
									cost = companyDESC.getInt(5);
									fragile = companyDESC.getString(6);
									hazard = companyDESC.getString(7);
									date = companyDESC.getString(8);	
									System.out.printf(tableformat, id, company, temp, weight, cost, fragile, hazard, date); }
								System.out.println('\n' + "Press enter to continue.");
								reader.nextLine();
								break;
							} else {
								System.out.println("Error! Incorrect command.");
								continue;
							}
						}
						break;

					case "weight":
						while(true){
							System.out.println("Type: 'ascending' or 'descending.'");
							String direction = reader.nextLine();
							if (direction.equals("ascending")){
								//Prints out whole table sorted by company name in ascending order
								System.out.printf(titleformat, columnTitles[0], columnTitles[1], columnTitles[2], columnTitles[3], columnTitles[4], columnTitles[5], columnTitles[6], columnTitles[7]);
								while(weightASC.next()){
									id = weightASC.getInt(1);
									company = weightASC.getString(2);
									temp = weightASC.getInt(3);
									weight = weightASC.getInt(4);
									cost = weightASC.getInt(5);
									fragile = weightASC.getString(6);
									hazard = weightASC.getString(7);
									date = weightASC.getString(8);
									System.out.printf(tableformat, id, company, temp, weight, cost, fragile, hazard, date); }
								System.out.println('\n' + "Press enter to continue.");
								reader.nextLine();
								break;
							} else if (direction.equals("descending")){
								//Prints out whole table sorted by company name in descending order
								System.out.printf(titleformat, columnTitles[0], columnTitles[1], columnTitles[2], columnTitles[3], columnTitles[4], columnTitles[5], columnTitles[6], columnTitles[7]);
								while(weightDESC.next()){
									id = weightDESC.getInt(1);
									company = weightDESC.getString(2);
									temp = weightDESC.getInt(3);
									weight = weightDESC.getInt(4);
									cost = weightDESC.getInt(5);
									fragile = weightDESC.getString(6);
									hazard = weightDESC.getString(7);
									date = weightDESC.getString(8);	
									System.out.printf(tableformat, id, company, temp, weight, cost, fragile, hazard, date); }
								System.out.println('\n' + "Press enter to continue.");
								reader.nextLine();
								break;
							} else {
								System.out.println("Error! Incorrect command.");
								continue;
							}
						}
						break;

					case "cost":
						while(true){
							System.out.println("Type: 'ascending' or 'descending.'");
							String direction = reader.nextLine();
							if (direction.equals("ascending")){
								//Prints out whole table sorted by company name in ascending order
								System.out.printf(titleformat, columnTitles[0], columnTitles[1], columnTitles[2], columnTitles[3], columnTitles[4], columnTitles[5], columnTitles[6], columnTitles[7]);
								while(costASC.next()){
									id = costASC.getInt(1);
									company = costASC.getString(2);
									temp = costASC.getInt(3);
									weight = costASC.getInt(4);
									cost = costASC.getInt(5);
									fragile = costASC.getString(6);
									hazard = costASC.getString(7);
									date = costASC.getString(8);
									System.out.printf(tableformat, id, company, temp, weight, cost, fragile, hazard, date); }
								System.out.println('\n' + "Press enter to continue.");
								reader.nextLine();
								break;
							} else if (direction.equals("descending")){
								//Prints out whole table sorted by company name in descending order
								System.out.printf(titleformat, columnTitles[0], columnTitles[1], columnTitles[2], columnTitles[3], columnTitles[4], columnTitles[5], columnTitles[6], columnTitles[7]);
								while(costDESC.next()){
									id = costDESC.getInt(1);
									company = costDESC.getString(2);
									temp = costDESC.getInt(3);
									weight = costDESC.getInt(4);
									cost = costDESC.getInt(5);
									fragile = costDESC.getString(6);
									hazard = costDESC.getString(7);
									date = costDESC.getString(8);	
									System.out.printf(tableformat, id, company, temp, weight, cost, fragile, hazard, date); }
								System.out.println('\n' + "Press enter to continue.");
								reader.nextLine();
								break;
							} else {
								System.out.println("Error! Incorrect command.");
								continue;
							}
						}
						break;

					case "date":
						while(true){
							System.out.println("Type: 'ascending' or 'descending.'");
							String direction = reader.nextLine();
							if (direction.equals("ascending")){
								//Prints out whole table sorted by company name in ascending order
								System.out.printf(titleformat, columnTitles[0], columnTitles[1], columnTitles[2], columnTitles[3], columnTitles[4], columnTitles[5], columnTitles[6], columnTitles[7]);
								while(dateASC.next()){
									id = dateASC.getInt(1);
									company = dateASC.getString(2);
									temp = dateASC.getInt(3);
									weight = dateASC.getInt(4);
									cost = dateASC.getInt(5);
									fragile = dateASC.getString(6);
									hazard = dateASC.getString(7);
									date = dateASC.getString(8);
									System.out.printf(tableformat, id, company, temp, weight, cost, fragile, hazard, date); }
								System.out.println('\n' + "Press enter to continue.");
								reader.nextLine();
								break;
							} else if (direction.equals("descending")){
								//Prints out whole table sorted by company name in descending order
								System.out.printf(titleformat, columnTitles[0], columnTitles[1], columnTitles[2], columnTitles[3], columnTitles[4], columnTitles[5], columnTitles[6], columnTitles[7]);
								while(dateDESC.next()){
									id = dateDESC.getInt(1);
									company = dateDESC.getString(2);
									temp = dateDESC.getInt(3);
									weight = dateDESC.getInt(4);
									cost = dateDESC.getInt(5);
									fragile = dateDESC.getString(6);
									hazard = dateDESC.getString(7);
									date = dateDESC.getString(8);	
									System.out.printf(tableformat, id, company, temp, weight, cost, fragile, hazard, date); }
								System.out.println('\n' + "Press enter to continue.");
								reader.nextLine();
								break;
							} else {
								System.out.println("Error! Invalid command.");
								continue;
							}
						}
						break;

					case "back":
						break;

					default:
						System.out.println("Error! Invalid command.");
						continue;
					}
					break;
				}
			continue;
			} else if (tableAction.equals("sum")){
				System.out.println("Which column would you like to add up? Type 'weight' or 'cost.' Type 'back' to go back to the previous selection.");
				String category2 = reader.nextLine();
				sum = 0;
				if (category2.equals("weight")){	
					//Block for summing weight
					result = statement.executeQuery();
					while(result.next()) {
						int addme = result.getInt(4);
						sum = sum + addme;
					}
					System.out.println("The sum weight of all the shipments is " + sum + "kg.");
					continue;
				} else if (category2.equals("cost")){
					//Block for summing cost
					result = statement.executeQuery();
					while(result.next()) {
						int addme = result.getInt(5);
						sum = sum + addme;
					}
					System.out.println("The sum of the cost of all the shipments is $" + sum + ".");
					continue;
				} else {
				System.out.println("Error! Invalid command.");
			}
				
			continue;	
			} else if (tableAction.equals("average")){
				System.out.println("Which column would you like to add up? Type 'weight' or 'cost.' Type 'back' to go back to the previous selection.");
				String category3 = reader.nextLine();
				sum = 0;
				meanincrement = 1;
				if (category3.equals("weight")){	
					//Block for averaging weight
					result = statement.executeQuery();
					while(result.next()) {
						int addme = result.getInt(4);
						sum = sum + addme;
						meanincrement ++;
					}
					mean = sum / meanincrement;
					System.out.println("The average weight of all the shipments is " + mean + "kg.");
					continue;
				} else if (category3.equals("cost")){
					//Block for averaging cost
					result = statement.executeQuery();
					while(result.next()) {
						int addme = result.getInt(5);
						sum = sum + addme;
						meanincrement ++;
					}
					mean = sum / meanincrement;
					System.out.println("The average cost of all the shipments is $" + mean + ".");
					continue;
				} else {
					System.out.println("Error! Invalid command.");
				}
				
				
				
				
				
				
				
				
				
				
				
			
			continue;	
			}else if (tableAction.equals("exit")){
				System.out.println("Logged out. Exiting program. See ya later!");
				on = false;
				break;
				
			}else {
				System.out.println("Error! Invalid command.");
				continue;
			}
			
		}





		reader.close();
	}



}
