import java.util.*;
import java.io.*;
import java.sql.*;

class LibraryManagement{
    Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
    PreparedStatement ps = null;
	//Adding Books
    void add_books(){
        try{
           con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","shoaib123");
           BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
           do{
                String i = "Insert into keeping_book_record values(?,?,?,?)";
                ps = con.prepareStatement(i);

				System.out.println("\n___________Adding New Book__________\n");
                //taking input
                System.out.print("Enter Acc_no.:");
                int Acc_no = Integer.parseInt(br.readLine());

                System.out.print("Enter Book Title:");
                String title = br.readLine();

                System.out.print("Enter Author name:");
                String author = br.readLine();

                System.out.print("Enter Book Category:");
                String category = br.readLine();
                
                //Inserting the records into the table
                ps.setInt(1, Acc_no);
                ps.setString(2, title);
                ps.setString(3, author);
                ps.setString(4, category);
				
				int flag = ps.executeUpdate();
				if(flag>0){
					System.out.println("Book Added Successfully.");
				}
				else{
					System.out.println("Failed to add.");
				}

                System.out.println("\n Press y to continue\nPress n to exit");
				System.out.print("Do you want to continue:");
                String ch = br.readLine();
                if (ch.startsWith("y")) {
                    continue;
                }
				else{
					break;
				}
           }while(true);
        }
        catch(Exception e){  
            System.out.println(e);
        }
    }

	//showing Books List
	void book_list(){
		try{
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","shoaib123");
			
			String l = "select*from keeping_book_record";
			stmt = con.createStatement();
			rs = stmt.executeQuery(l);
			System.out.println("\n________List Of BOOKS________\n");
			System.out.println("Acc_no.\tTitle\t\t\t\tAuthor\t\t\t\t\t\t\tCategory");
			while(rs.next()){
				System.out.println(rs.getInt(1) + "\t" + rs.getString(2)+ "\t\t\t\t" + rs.getString(3) + "\t\t\t\t" + rs.getString(4));
			}
			System.out.println("\n");
			con.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}

	//showing Members list
	void members_list(){
		try{
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","shoaib123");
			
			String l = "select*from members_detail";
			stmt = con.createStatement();
			rs = stmt.executeQuery(l);
			System.out.println("\n---------MEMBERS LIST---------\n");
			System.out.println("Member Id\tName\tContact Number\tDepartment");
			while(rs.next()){
				System.out.println(rs.getString(1) + "\t" + rs.getString(2)+ "\t" + rs.getString(3) + "\t" + rs.getString(4));
			}
			System.out.println("\n");
			con.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}

	void find_member(){
		try{
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","shoaib123");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			do{
				String q ="select * from members_detail where member_id = ?";
                ps = con.prepareStatement(q);
                System.out.print("Enter member_id:");
                String id = br.readLine();

                ps.setString(1,id);
				rs = ps.executeQuery();
                if(rs.next()){
					System.out.println("Member Id\tName\tContact Number\tDepartment");
                    System.out.println(rs.getString(1) + "\t" + rs.getString(2)+ "\t" + rs.getString(3) + "\t" + rs.getString(4));
				}
                else{
                    System.out.println("Member not found.");
                }
				System.out.println("\nPress y to continue\nPress n to exit");
				System.out.print("Do you want to continue: ");
                String ch = br.readLine();
                if (ch.startsWith("y")) {
                    continue;
                }
				else{
					break;
				}
			}
			while(true);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}

	//Adding Members
	void add_member(){
		try{
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","shoaib123");
           BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		   do{
				String i = "insert into members_detail values(?,?,?,?)";
				ps = con.prepareStatement(i);

				//Taking input
				System.out.print("Enter Member Id:");
				String id = br.readLine();

				System.out.print("Enter Member Name:");
				String name = br.readLine();

				System.out.print("Enter Contact Number:");
				double cn = Double.parseDouble(br.readLine());

				System.out.print("Enter Department Name:");
				String dept = br.readLine();

				//Inserting data into the table
				ps.setString(1, id);
				ps.setString(2, name);
				ps.setDouble(3, cn);
				ps.setString(4, dept);

				int flag = ps.executeUpdate();
				if(flag>0){
					System.out.println("\nMember Added Successfully.");
				}
				else{
					System.out.println("Failed to add the member.");
				}
				
                System.out.println("\nPress y to continue\nPress n to exit");
				System.out.print("Do you want to continue: ");
                String ch = br.readLine();
                if (ch.startsWith("y")) {
                    continue;
                }
				else{
					break;
				}
		   }
		   while(true);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}

	//Upadte Members detail
	void update_details(){
		try{
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","shoaib123");
           BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		   do{
				System.out.println("______________Update Members Detail_____________");
				String i = "update members_detail set member_name =?,phone_n = ?,department = ? where member_id = ?";
				ps = con.prepareStatement(i);

				//Taking input
				System.out.print("Enter Member Name:");
				String name = br.readLine();

				System.out.print("Enter Contact Number:");
				double cn = Double.parseDouble(br.readLine());

				System.out.print("Enter Department:");
				String dept = br.readLine();

				System.out.print("Enter Member Id:");
				String id = br.readLine();


				//Inserting data into the table
				ps.setString(1, name);
				ps.setDouble(2, cn);
				ps.setString(3, dept);
				ps.setString(4, id);

				int  flag = ps.executeUpdate();
				if(flag > 0){
					System.out.println("\nOne record updated.");
				}
				else{
					System.out.println("Failed to update.");
				}
                System.out.println("\nPress y to continue\nPress n to exit");

				System.out.print("Do you want to continue: ");
                String ch = br.readLine();
                if (ch.startsWith("y")) {
                    continue;
                }
				else{
					break;
				}
		   }
		   while(true);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}

	//Getting Detail of Issued Books
	void issue_book_list(){
		try{
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","shoaib123");
			
			System.out.println("\n____________ISSUED BOOKS__________");
			String is = "select k.ac_no,k.title,i.member_id,i.issue_date,i.status from keeping_book_record k inner join issue_books i on k.ac_no = i.ac_no where i.status = 'issued' order by member_id";
			stmt = con.createStatement();
			rs = stmt.executeQuery(is);

			System.out.println("Acc No.\t\t\t Title \t\t\t Member ID. \t\t\t Issue Date \t\t Status");
			while(rs.next()){
				System.out.println(rs.getInt(1) + "\t\t\t" + rs.getString(2)+ "\t\t" + rs.getString(3) + "\t\t\t\t" + rs.getDate(4) + "\t\t" + rs.getString(5));
			}
			con.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}

	//Book Issue
	void issue_books(){
		try{
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","shoaib123");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		   do{
				
				String i = "insert into issue_books(member_id,ac_no) values(?,?)";
				ps = con.prepareStatement(i);

				System.out.print("Enter member id:");
				String id  = br.readLine();

				System.out.print("Enter Book Accession Number:");
				int Ac_no = Integer.parseInt(br.readLine());

				//Inserting data into the table
				ps.setString(1, id);
				ps.setInt(2, Ac_no);

				int flag = ps.executeUpdate();
				if(flag > 0){
					System.out.println("\nBook Issued Successfully.");
				}
				else{
					System.out.println("Failed to issue book.");
				}
				
                System.out.println("\nPress y to continue\nPress n to exit");
                System.out.print("Do you want to continue: ");
				String ch = br.readLine();
                if (ch.startsWith("y")) {
                    continue;
                }
				else{
					break;
				}
		   }
		   while(true);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}

	// book return
	void return_book(){
		try{
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","shoaib123");
           BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		   do{
				System.out.println("\n_________Return Book________\n");
				String i = "insert into return_book(member_id,ac_no,issue_date) values(?,?,?)";
				ps = con.prepareStatement(i);
				//Taking input
				System.out.print("Enter Member Id:");
				String id = br.readLine();

				System.out.print("Enter Book Ac_No:");
				int ac = Integer.parseInt(br.readLine());

				System.out.print("Enter issue date:");
				String date = br.readLine();

				//Inserting data into the table
				ps.setString(1, id);
				ps.setInt(2, ac);
				ps.setString(3, date);

				int flag = ps.executeUpdate();
				if(flag > 0){
					
					String d = "delete from issue_books where member_id = ? and ac_no = ?";
					ps=con.prepareStatement(d);
					ps.setString(1, id);
					ps.setInt(2,ac);
					rs = ps.executeQuery();
					if(rs.next()){
						System.out.println("\nBook Returned Successfully.");
					}
					break;
				}
				else{
					System.out.println("\nEnter correct details.");
				}
                System.out.println("\nPress y to continue\nPress n to exit");

                System.out.print("Do you want to continue: ");
				String ch = br.readLine();
                if (ch.startsWith("y")) {
                    continue;
                }
				else{
					break;
				}
		   }
		   while(true);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	//Fine details
	void fine_detail(){
		try{
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "shoaib123");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			do{
				String f = "select*from return_book where member_id = ?";
				ps = con.prepareStatement(f);
                System.out.print("Enter member_id:");
                String id = br.readLine();
				ps.setString(1, id);

				rs = ps.executeQuery();
				if(rs.next()){
					System.out.println("Member Id\tAc_no\tDays\tFine");
                    System.out.println(rs.getString("member_id") + "\t\t" + rs.getInt("Ac_no")+ "\t" + rs.getInt("Days") +"\t"+rs.getInt("Fine"));
				}
				else{
					System.out.println("\nRecord not found!! try again\n");
					fine_detail();
				}
				System.out.println("\nPress y to continue, n to exit");
				System.out.print("Do you want to continue: ");
                String ch = br.readLine();
                if (ch.startsWith("y")) {
                    continue;
                }
				else{
					break;
				}
			}while(true);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}

	void exit() throws InterruptedException{
        System.out.print("\t\t\tExiting System");
        int i = 5;
        while(i!=0){
            System.out.print(".");
            Thread.sleep(350);
            i--;
        }    
    }

}
//--------------------------------Main class-------------------------

public class library_Main{
	public static void main(String args[]) throws InterruptedException{
		System.out.println("\n>>>>>>>>>>>>>>>>----LIBRARY MANAGEMENT-----<<<<<<<<<<<<<<<<");
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","shoaib123");	
		}
		catch(Exception e){
			System.out.println(e);
		}
		int choice;
		do{
			LibraryManagement lb = new LibraryManagement();
			Scanner sc = new Scanner(System.in);
			System.out.println("\n_____________MAIN SECTION____________");
			System.out.println("1.Books\n2.Member Details\n3.Issue Book\n4.Return Book\n5.Exit");
			System.out.print("Enter your choice : ");
			choice = sc.nextInt();
			switch(choice){
			case 1:
				int choi;
				do{
					System.out.println("\n_____________Book1 SECTION____________");
					Scanner in = new Scanner(System.in);
					System.out.println("1.Books List.\n2.Add Books.\n3.Back");
					System.out.print("Enter your choice:");
					choi = in.nextInt();
					switch(choi){
						case 1:
							lb.book_list();
							break;
						case 2:
							lb.add_books();
							break;
						default:
							break;

					}
				}
				while(choi!=3);
				break;
			case 2:
				int ch;
				do{
					Scanner in = new Scanner(System.in);
					System.out.println("\n_________Member Section________");
					System.out.println("\n1.Members List\n2.Find Member\n3.Add New Member\n4.Update Details\n5.Back");
					System.out.print("Enter your choice : ");
					ch = in.nextInt();
					switch (ch) {
						case 1:
							lb.members_list();
							break;
						case 2:
							lb.find_member();
						    break;
						case 3:
							lb.add_member();
							break;
					    case 4:
							lb.update_details();
							break;
						case 5:
							System.out.println("..................Thank you..................");
							break;
						default:
							break;
					}
				}while(ch!=5);
				break;
			case 3:
				int cho;
				do{
					Scanner in = new Scanner(System.in);
					System.out.println("\n__________BOOK ISSUE SECTION_________");
					System.out.println("\n1.Issued Books.\n2.Issue New Book.\n3.Back");
					System.out.print("\nEnter your Choice: ");
					cho = in.nextInt();
					switch (cho){
						case 1:
							lb.issue_book_list();
							break;
						case 2:
							lb.issue_books();
							break;
						case 3:
							System.out.println("..........Exited..........");
							break;
						default:
							break;
					}
				}while(cho!=3);
				break;
			case 4:
				int op;
				do{
					Scanner in = new Scanner(System.in);
					System.out.println("\n__________BOOK RETURN SECTION_________");
					System.out.println("\n1.Return Book.\n2.Fine Detail.\n3.Back");
					System.out.print("Enter your Choice: ");
					op = in.nextInt();
					switch(op){
						case 1:
							lb.return_book();
							break;
						case 2:
							lb.fine_detail();
							// System.out.println("----In Progress------");
							break;
						case 3:
							break;
						default:
							break;
					}
				}while(op!=3);
				break;
			case 5:
				lb.exit();
				System.out.println("\n_____________Thank You For Using Library Management System____________\n");
			deafult:
				break;
			}
		}
		while(choice!=5);
	}
}


