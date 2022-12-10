package Assigment_JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Scanner;

import com.mysql.cj.ServerPreparedQueryTestcaseGenerator;
import com.mysql.cj.jdbc.Driver;



class General
{
	//.1 Declaration of our fields data that will be used in the project
	String url="jdbc:mysql://localhost:3306/filadb";
	String user="root";
	String password="Fouani__2021";
	Connection connection=null;
	Statement statement=null;
	ResultSet resultSet=null;
	Scanner input= new Scanner(System.in);
	// Students variables
	int sid;
	int sage;
	String sname;
	String saddr;
	//Used for update and delete methods
	String newSname="";
	int newSage=26;
	String userString;
	int userInterger;
	String []choiceString= {"sname","saddr"};
	int []choiceInt= {sid,sage};
	int userChoice;
	String updatesqlQuery;
	
	
	
	/**----------------------------------------------This is the part of our insert method.--------------------------------------------------------*/
	public void create() throws SQLException
	{
		// Prompt the user to create insert data on the database
		System.out.println("You are going to create\n1:Student id. \n2:Student name.\n3:Student age\n4:Student address");
		System.out.println("Enter the student id please: ");
		sid=input.nextInt();
		System.out.println("Enter the student name please: ");
		sname=input.next();
		System.out.println("Enter the student age please: ");
	    sage=input.nextInt();
		System.out.println("Enter your Address please: ");
		saddr= input.next();
		try {
			//.2 Loading and register the Driver
			Driver driver= new Driver();
			DriverManager.registerDriver(driver);
			//.3 Establish connection
			connection=DriverManager.getConnection(url,user,password);
			if(connection!=null)
			{
				//. Create a statement object to send SQL query to the database
				statement=connection.createStatement();
				// Using the statement to make a query.
				String insertsqlQuery=String.format("insert into Students(sid,sname,sage,saddr) values('%d','%s','%d','%s')",sid,sname,sage,saddr);
				int nrOfRows=statement.executeUpdate(insertsqlQuery);
				System.out.println("Nr. of rows affected is: " + nrOfRows);
				
				
			}
			
		} catch (SQLException ex) {
			System.out.println("Something went wrong, check your SQL synthax....");
			ex.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(statement!=null)
			{
				statement.close();
			}
			if(connection!=null)
			{
				connection.close();
			}
			if(input!=null)
			{
				input.close();
			}
			
		}
		
	}
	
	/**----------------------------------------------------This is part of our read Method-----------------------------------------------------*/
	
	public void read() throws SQLException{
		// Prompt the user to read  data  the database
		
				System.out.println("You have to choose one alternativ to read the the content of the Student table");
				String choice="";
				System.out.println("Type \n1: For Student id\n2: For Student name\n3: For Student age\n4: For student address\n5: For entire table");
				System.out.println("Enter your choice please: ");
				choice=input.next();
				String []values= {"sid","sname","sage","saddr"};
				for(int i=0; i<values.length;i++) {
					if(choice.equals(values)) {
						
					}
					
				}
				
				
				int sid=input.nextInt();
				System.out.println("Enter the student name please: ");
				String sname=input.next();
				System.out.println("Enter the student age please: ");
				int sage=input.nextInt();
				System.out.println("Enter your Address please: ");
				String saddr= input.next();
				try {
					//.2 Loading and register the Driver
					Driver driver= new Driver();
					DriverManager.registerDriver(driver);
					//.3 Establish connection
					connection=DriverManager.getConnection(url,user,password);
					if(connection!=null)
					{
						//. Create a statement object to send SQL query to the database
						statement=connection.createStatement();
						// Using the statement to make a query.
						String selectsqlQuery=String.format("select * from Student(sid,sname,sage,saddr", sid,sname,sage,saddr);
						resultSet=statement.executeQuery(selectsqlQuery);
						if(statement!=null) {
							//. Process the resulSet
							System.out.println("sid\tsname\tsage\tsaddr");
							System.out.println("=======================================================");
							while(resultSet!=null) {
								sid=resultSet.getInt("sid");
								sname=resultSet.getString("sname");
								sage=resultSet.getInt("sage");
								saddr=resultSet.getString("saddr");
								System.out.println(sid +"\t" +sname +"\t"+ sage+"\t"+ saddr);
								
							}
						}
					}
					
					
				} catch (SQLException ep) {
					System.out.println("Something went wrong check your SQL syntax please.....");
					ep.printStackTrace();
				}catch (Exception e) {
					e.printStackTrace();
				}finally {
					if(statement!=null)
					{
						statement.close();
					}
					if(connection!=null)
					{
						connection.close();
					}
					if(input!=null)
					{
						input.close();
					}
					
					
				
		
	}
	
}
	/**----------------------------------------------------Update Method from question 1--------------------------------------------------------*/
	public void update() throws SQLException
	{
		// Data fields 
		
		
		
		
		try {
			// 2.Establish connection 
			connection=DriverManager.getConnection(url,user,password);
			if(connection!=null)
			{
				//.3 create Statement object  to run
				statement=connection.createStatement();
				if(statement!=null) {
					//Create statement object  to send SQL query to the database
					System.out.println("Make your choice to update data on the table.");
					System.out.println("sid\nsname\nsage\nsaddr");
					//Prompt the user to make a choice 
					System.out.println("Enter your choose:");
					userString=input.next();     // We get the input of the user.
					System.out.println( userString+ " might to be update in function either sid(Student Identity ) or sage(Student Age)");
					System.out.println("Enter your alternativ  either 'sid ' or 'sage");
					//---------------------------------------
					for(int i=0;i<choiceInt.length;i++) {
						int user=input.nextInt();
						if(user==choiceInt[i]) {
							System.out.println( choiceInt[i] +" is your choice");
							System.out.println("Enter your" + choiceInt[i]);
							userChoice=input.nextInt();
							choiceInt[i]=userChoice;
							System.out.println(userChoice);
						}
						
					}
					//----------------------------------------------------------
					
					for (int i=0; i<choiceString.length;i++) {
						if(userString.equalsIgnoreCase(choiceString[i])) {   // We compare from our array to check if the input matched with these elts into the table
							
							System.out.println("Your update is: " + choiceString[i]);
							System.out.println("---------------------------------------------------------------------");
							System.out.println("Enter a new new name please: ");
							choiceString[i]=input.next();
							System.out.println(choiceString[i]);
							newSname=choiceString[i];							
		
						}else {
							System.out.println("Your input doesn't match to the elements into the table.");							
							
						}
						
						
						
						
					}
					// Using this statement to make a query
					newSname="'" +newSname+"'";						
					updatesqlQuery=("update Student set sname="+newSname +" where sage= "+newSage+".");						
					int nrOfRows=statement.executeUpdate(updatesqlQuery);
					System.out.println("nr. of rows affected is: " + nrOfRows);
					
					
					
				
				}
			}			
			
		} catch (SQLException et) {
			System.out.println("Something wrong, check your SQL synthax...");
			et.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** -----------------------------------------Delete method from question 1-------------------------------------------------------*/
	@SuppressWarnings("unused")
	public void delete()throws SQLException {
		
		int[]StudentID= {101,102,103,104,105,106,107,108,109,110,111,112,113,114};
		try {
			// 2. Establish the connection
			connection = DriverManager.getConnection(url,user, password);
			if(connection!=null)
			{
				// 3. Create statement object
				statement=connection.createStatement();
				if (statement!=null) {
					/**The query below will delete two rows into our table: That's why we used "OR"*/
					//Prompt the user to enter values that will be deleted
					System.out.println("Make your choice to delete  data on the table\nBased on sid.");
					System.out.println("Chose from table sid: "+Arrays.toString(StudentID));
					//Prompt the user to make a choice 
					System.out.println("Enter your sid please:");
					userInterger=input.nextInt();     // We get the input of the user.
					
					for(int j=0; j<StudentID.length;j++) {
						
						if(userInterger==StudentID[j]) {
							userInterger=StudentID[j];
														
							break;
						}else{
							System.out.println("Sorry,id doesn't match to the student id into the table.");
							break;
							
						}
					}
					String deleteSqlQuery="delete from Student where sid ="+userInterger+".";
					
					// 4. Using the statement object to execute the query
					int nrOfRows=statement.executeUpdate(deleteSqlQuery);
					System.out.println("Nr. of rows deleted is: " +nrOfRows);
					
					
							
										
				}
				
			}
			
		} catch (SQLException ex) {
			System.out.println("Something went wrong.....");
			ex.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(resultSet!=null) {
				resultSet.close();
			}
			if(statement!=null)
			{
				statement.close();
			}
			if(connection!=null)
			{
				connection.close();
			}
		}
	}
}


public class Question_1 {

	public static void main(String[] args) throws SQLException{
		Scanner input=new Scanner(System.in);
		
		General obj= new General(); // We create a object, by calling the class
		System.out.println(" ------------------------------------------");
		System.out.println("|                Menue                     |");
		System.out.println("|    Choose by typing the number below     |");
		System.out.println("|                                          |");
		System.out.println("| 1-Create                       2-Read    |");
		System.out.println("|                                          |");
		System.out.println("| 3-Update                       4-Delete  |");
		System.out.println(" ------------------------------------------");
		System.out.println("Enter your choice please: ");
		int number=input.nextInt();
		if(number==1) {
			obj.create();			
		}else if(number==2) {
			obj.read();
		}else if(number==3) {
			obj.update();
		}else if(number==4) {
			obj.delete();
		}else {
			System.out.println(number +" doesn't figure in the menue.");
			System.exit(0);
		}
		input.close();
   }
}

