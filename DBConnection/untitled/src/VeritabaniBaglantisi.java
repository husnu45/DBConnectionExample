import java.sql.*;

public class VeritabaniBaglantisi {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/veritabani_adi";
    static final String USER = "kullanici";
    static final String PASS = "sifre";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName(JDBC_DRIVER);

            System.out.println("Veritabanına bağlanılıyor...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Veritabanına bağlantı başarılı!");

            System.out.println("Employees tablosundan veri çekiliyor...");
            stmt = conn.createStatement();
            String sql = "SELECT id, name, position, salary FROM employees";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String position = rs.getString("position");
                int salary = rs.getInt("salary");

                System.out.println("ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Position: " + position);
                System.out.println("Salary: " + salary);
                System.out.println();
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Veritabanı bağlantısı kapatıldı.");
    }
}
