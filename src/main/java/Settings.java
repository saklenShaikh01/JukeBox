import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Set;

public class Settings {
    Scanner s=new Scanner(System.in);
    void updatePassword(Connection con, String username, String password)
    {
        try {
            String sql = "update userdetail set password=? where username=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(2,username);
            statement.setString(1,password);
            statement.executeUpdate();
            System.out.println("Password Updated Successfully");
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    void feedback(Connection con,int userid)
    {
        System.out.println("Enter your reason to delete account...");
        String feedback=" ";
        feedback+=s.nextLine();
        try {
            String sql = "insert into feedback (feedback,userid) values(?,?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1,feedback);
            statement.setInt(2,userid);
            statement.executeUpdate();
            System.out.println("Feedback Added Successfully...");
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

}
