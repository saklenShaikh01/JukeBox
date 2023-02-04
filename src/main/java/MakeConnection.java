import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MakeConnection {
    Connection createConnection()
    {
        Connection con=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  // load the driver
            con = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/Juke",
                            "root", "Bismillah@123");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return con;
    }
}