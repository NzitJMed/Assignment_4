package question_3;

import java.sql.*;
import java.util.Scanner;
import com.mysql.cj.jdbc.Driver;


class Question__3
{


	// We are performing CRUD operation using preparedStatement
	
	//1.Field Declarations
	
	String url="jdbc:mysql://localhost:3306/filadb";
	String user="root";
	String password="Fouani__2021";
	Scanner input= new Scanner(System.in);
	
	String stdname,stdpwd,stdemail;
	int stdage;
	
	
	Connection connection=null;
	PreparedStatement pstmt=null;
	ResultSet resultSet=null;
	Statement statement= null;
	
	
	public void insert()throws SQLException
	{
		//  1. Load and connection of the Driver¨
		
		Driver driver= new Driver();
		DriverManager.registerDriver(driver);
		
	    //  2. Prompt the use to insert: student name, student age, student password,student email.
		System.out.println("Enter the new student name: ");
		stdname=input.next();
		System.out.println("Enter the age of the new student: ");
		stdage=input.nextInt();
		System.out.println("Enter your password: ");
		stdpwd=input.next();
		System.out.println("Enter the student email: ");
		stdemail=input.next();
		String insertsqlQuery="insert into studentdb(stdname,stdage,stdpwd,stdemail) values(?,?,?,?)";
		
		try {
			connection=DriverManager.getConnection(url,user, password);
			if(connection!=null)
				
				{
				  System.out.println("The connection established..");
				  pstmt=connection.prepareStatement(insertsqlQuery);
				  if(pstmt!=null) {
					  pstmt.setString(1,stdname);
					  pstmt.setInt(2,stdage);
					  pstmt.setString(3,stdpwd);
					  pstmt.setString(4,stdemail);
					  int nrOfRows=pstmt.executeUpdate();
					  if(nrOfRows>0){
						  System.out.println("A  new user was insert successfully !");
						  System.out.println("Thus , the number of rows affected is: " +nrOfRows);
					  }
							
						}
						
					}
					
				} catch (SQLException ex) {
					ex.printStackTrace();
				}catch(Exception b) {
					b.printStackTrace();
					
				}finally {
					if(connection!=null) {
						connection.close();
					}if(pstmt!=null) {
						pstmt.close();
					}if(input!=null) {
						input.close();
					}
		}

}

	
	public void select() throws SQLException
	{
	//  1. load and register the Driver.				
				
		Driver driver= new Driver();
		DriverManager.registerDriver(driver);
		String selectQuery="select * from studentdb";
		int count=0;
				
		try {
			   connection=DriverManager.getConnection(url,user,password);
				if(connection !=null){
				System.out.println("Connection is established.");
				statement=connection.createStatement();
				resultSet=statement.executeQuery(selectQuery);
				if(resultSet!=null){
					while(resultSet.next()){
						 stdname=resultSet.getString(2);
						stdage=resultSet.getInt(3);
						stdpwd=resultSet.getString("stdpwd");
					    stdemail=resultSet.getString("stdemail");
						String output="User #%d:%s - %s - %s - %s";
						System.out.println(String.format(output,++count,stdname,stdage,stdpwd,stdemail));
					}
		        }
              }
					
					
			} catch (SQLException e) {
					e.printStackTrace();
			}catch (Exception s) {
					s.printStackTrace();
			}finally {
				
				if(connection!=null)
					{
						connection.close();
					}if(statement!=null)
					  {
						statement.close();
						}if(resultSet!=null)
						{
						resultSet.close();
						}
					
			}
				        

			
		
	}
	
	public void update() throws SQLException {	
		
		Driver driver= new Driver();
		DriverManager.registerDriver(driver);
		
		
		// Prompt the user to update some values.
		System.out.println("Enter your new password please: ");
		stdpwd=input.next();
		System.out.println("Enter your new student name please: ");
		stdname=input.next();
		System.out.println("Enter your the new student email please: ");
		stdemail=input.next();		
		System.out.println("Where the student age  is: ");
		stdage=input.nextInt();
		
		String updatQuery="update studentdb set stdpwd=?,stdname=?,stdemail=? where stdage=?";
		try {
			connection=DriverManager.getConnection(url,user,password);
			if(connection!=null) {
				System.out.println("Connection successfully established.");
				pstmt=connection.prepareStatement(updatQuery);
				if(pstmt!=null) {
					pstmt.setString(1, stdpwd);
					pstmt.setString(2, stdname);
					pstmt.setString(3, stdemail);
					pstmt.setInt(4, stdage);
					int rowsUpdate=pstmt.executeUpdate();
					if(rowsUpdate>0) {
						
						System.out.println("An existing user was updated successfully !");
					}
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(connection!=null) {
				connection.close();
			}if(pstmt!=null) {
				pstmt.close();
			}if (input!=null) {
				input.close();
			}
		}

	
		
	}
	
	
	public void delete() throws SQLException{		
		
		Driver driver= new Driver();
		DriverManager.registerDriver(driver);
		System.out.println("Delete a row where student name is:  ");
		stdname=input.next();
		// Prompt the user to enter the row to delete
		System.out.println("Enter the student name of the row that want to be deleted: ");
		String stdname=input.next();
		
		
		String updatQuery="delete from studentdb where stdname=?";
		try {
			connection=DriverManager.getConnection(url,user,password);
			if(connection!=null) {
				System.out.println("Connection successfully established.");
				pstmt=connection.prepareStatement(updatQuery);
				if(pstmt!=null) {
					pstmt.setString(1, stdname);
					
					int rowsDeleted=pstmt.executeUpdate();
					if(rowsDeleted>0) {
						
						System.out.println("An existing user was updated successfully !");
					}
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(connection!=null) {
				connection.close();
			}if(pstmt!=null) {
				pstmt.close();
			}if (input!=null) {
				input.close();
			}
		}
		
	}
		
	}
	
	


public class Question3 {

	public static void main(String[] args)throws SQLException {
Scanner input=new Scanner(System.in);
		
		Question__3 obj= new Question__3(); // We create a object, by calling the class
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
			obj.insert();			
		}else if(number==2) {
			obj.select();
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
