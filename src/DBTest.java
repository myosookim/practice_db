import java.sql.*;

public class DBTest {

    public static final String url = "jdbc:mysql://localhost:3306/mydb?serverTimeZone=UTC";
    public static final String user = "root";
    public static final String password = "12345555";

    public static void main(String[] args){
        try(Connection conn = getConnection()) {
            System.out.println("정상적으로 연결되었습니다");
            runSQL(conn);
        } catch (SQLException e){
            System.err.println("연결할 수 없습니다.");
            e.printStackTrace();
        }
    }

    private static void runSQL(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()){
            getResult(stmt);
        }
    }

    private static void getResult(Statement stmt) throws SQLException{
        String sql = "SELECT Fname, Salary From EMPLOYEE WHERE sex = 'M'";
        String fname;
        double salary;
        try (ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()){
                fname = rs.getString(1);
                salary = rs.getDouble("Salary");
                System.out.printf("Fname : %s Salary : %f\n", fname, salary);
            }
        }
    }


    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }
}
