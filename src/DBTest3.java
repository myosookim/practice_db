import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DBTest3 {
    public static void main(String[] args) throws SQLException, IOException {
        Scanner scanner = new Scanner(System.in);
        Connection conn = null;

        String dbacct, passwrd, dbname, dname, dnumber, mgr_ssn, mgr_sdate;

        System.out.println("Enter database account: ");
        dbacct = scanner.nextLine();
        System.out.println("Enter password: ");
        passwrd = scanner.nextLine();
        System.out.println("Enter database name: ");
        dbname = scanner.nextLine();

        String url = "jdbc:mysql://localhost:3306/"+dbname+"?serverTimezone=UTC";
        conn = DriverManager.getConnection(url, dbacct, passwrd);

        String stmt1 = "INSERT INTO DEPARTMENT VALUES (?,?,?,?)";
        PreparedStatement p = conn.prepareStatement(stmt1);

        System.out.println("Enter department account: ");
        dname = scanner.nextLine();
        System.out.println("Enter department number: ");
        dnumber = scanner.nextLine();
        System.out.println("Enter manager's ssn: ");
        mgr_ssn = scanner.nextLine();
        System.out.println("Enter manager's start date: ");
        mgr_sdate = scanner.nextLine();

        p.clearParameters();
        p.setString(1, dname);
        p.setString(2, dnumber);
        p.setString(3, mgr_ssn);
        p.setString(4, mgr_sdate);

        // 값을 저장할 필요가 없으므로 바로 update
        p.executeUpdate();

        try {
            if(conn != null){
                conn.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
