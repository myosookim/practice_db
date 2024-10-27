import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class DBTest2 {
    public static void main(String[] args) throws SQLException, IOException {
        Scanner scanner = new Scanner(System.in);
        Connection conn = null;

        String dbacct, passwrd, dbname, ssn, lname;
        Double salary;

        System.out.println("Enter database account: ");
        dbacct = scanner.nextLine();
        System.out.println("Enter password: ");
        passwrd = scanner.nextLine();
        System.out.println("Enter database name: ");
        dbname = scanner.nextLine();

        String url = "jdbc:mysql://localhost:3306/"+dbname+"?serverTimezone=UTC";
        conn = DriverManager.getConnection(url, dbacct, passwrd);

        String stmt1 = "SELECT Lname, Salary FROM EMPLOYEE WHERE Ssn=?";
        PreparedStatement p = conn.prepareStatement(stmt1);

        System.out.println("Enter a Social Security Number(Ssn): ");
        ssn = scanner.nextLine();

        p.clearParameters();
        p.setString(1, ssn);
        ResultSet r = p.executeQuery();

        while (r.next()){
            lname = r.getString(1);
            salary = r.getDouble(2);
            System.out.println(lname + " " + salary);
        }

        try {
            if(conn != null){
                conn.close();
            }
        }catch (SQLException e){

        }
    }
}
