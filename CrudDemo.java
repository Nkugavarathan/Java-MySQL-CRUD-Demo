import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.Scanner;

public class CrudDemo {
    public static void main(String[] args) throws SQLException {
        String url="jdbc:mysql://localhost:3306/crud";
        String userName="root";
        String pas="";

        Connection con=DriverManager.getConnection(url,userName,pas);
        Statement st=con.createStatement();
        ResultSet rs;
        PreparedStatement pst;

        int id,age,choice;
        String name,city;
        String query;

        Scanner scan=new Scanner(System.in);

        while(true){
            System.out.println("Welcome to Java CRUD Operation Demo");
            System.out.println("1. Insert");
            System.out.println("2. Update");
            System.out.println("3. Delete");
            System.out.println("4. Select");
            System.out.println("5. Search by ID ");
            System.out.println("6. Extit");
            System.out.print("Enter a choice : ");
            choice=scan.nextInt();
            System.out.println("-----------------------------");

            switch (choice){
                case 1:
                    System.out.println("1. Insert New Data ");
                    System.out.print("Enter Name : ");
                    name = scan.next();
                    System.out.print("Enter Age : ");
                    age = scan.nextInt();
                    System.out.print("Enter City : ");
                    city = scan.next();

                    query = "insert into User (Name,Age,City) values(?,?,?)";
                    pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                    pst.setString(1, name);
                    pst.setInt(2, age);
                    pst.setString(3, city);
                    pst.executeUpdate();

                    // Retrieve generated ID
                    rs = pst.getGeneratedKeys();
                    if (rs.next()) {
                        int generatedId = rs.getInt(1); // Get the generated ID
                        System.out.println("Data Successfully Inserted with ID: " + generatedId);
                    } else {
                        System.out.println("Insertion successful, but couldn't retrieve ID.");
                    }

                    break;

                case 2:
                    System.out.println("2. Update a data");
                    System.out.print("Enter ID : ");
                    id=scan.nextInt();
                    System.out.print("Enter Name : ");
                    name=scan.next();
                    System.out.print("Enter Age : ");
                    age= scan.nextInt();
                    System.out.print("Enter City : ");
                    city=scan.next();

                     query="update User set Name=?,Age=?,City=? where ID=?";
                     pst=con.prepareStatement(query);

                     pst.setString(1,name);
                     pst.setInt(2,age);
                     pst.setString(3,city);
                     pst.setInt(4,id);
                     pst.executeUpdate();
                    System.out.println("Data Update Success");
                     break;
                case 3:
                    System.out.println("3. Delete a data");
                    System.out.print("Enter ID : ");
                    id=scan.nextInt();

                    query="delete from User where id=?";
                    pst=con.prepareStatement(query);
                    pst.setInt(1,id);
                    pst.executeUpdate();
                    System.out.println("Data Deleted Successfully..");
                    break;
                case 4:
                    System.out.println("4. Print all records");
                    query="select * from User";
                    st= con.createStatement();
                    rs=st.executeQuery(query);
                    while (rs.next()){
                        System.out.println("ID is : "+rs.getInt("ID"));
                        System.out.println("Name is : "+rs.getString("Name"));
                        System.out.println("Age is : "+rs.getInt("Age"));
                        System.out.println("City is : "+rs.getString("City"));
                        System.out.println("*******************");
                    }

                    break;

                case 5:
                    System.out.print("Enter ID : ");
                    id=scan.nextInt();

                    query="select Name,Age,City from User where id=?";
                    pst=con.prepareStatement(query);
                    pst.setInt(1,id);
                    rs=pst.executeQuery();
                    if (rs.next()) {

                        System.out.println("--User Found--");
                        System.out.println("ID: " + id);
                        System.out.println("Name: " + rs.getString("Name"));
                        System.out.println("Age: " + rs.getInt("Age"));
                        System.out.println("City: " + rs.getString("City"));

                    } else {

                        System.out.println("No user found with ID: " + id);
                    }
                    break;
                case 6:
                    System.out.println("Exit and Thank you ");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Selection");
                    break;
            }
            System.out.println("-----------------------------");
        }
    }
}


