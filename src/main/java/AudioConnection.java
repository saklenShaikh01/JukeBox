import java.sql.*;
import java.util.Scanner;

public class AudioConnection
{
    String path;
    Scanner s=new Scanner(System.in);

    String showallTable(Connection con)
    {
        try {

            String sql = "select * from songsdetails";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("**********************************************************");
            System.out.println("SongId         Song name             Singer              gnere");
            while (resultSet.next()) {
                System.out.println("-----------------------------------------------------------");
                System.out.format("%-5s      %-20s %-20s %-10s %n",resultSet.getString(1) ,resultSet.getString(2),resultSet.getString(3)
                        ,resultSet.getString(4));
            }
            System.out.println("**********************************************************");
        }
        catch (SQLException e)
        {
            System.out.println("table not showing");
        }
        return path;
    }
    String filePath(Connection con) throws SongPathNotFoundException
    {
        int flag=0;
        try {
            System.out.println("Select song id from above to play the song...");
            int choice = s.nextInt();
            String s = "select * from songsdetails where songid=?";
            PreparedStatement statement1 = con.prepareStatement(s);
            statement1.setInt(1, choice);
            ResultSet r = statement1.executeQuery();
            while (r.next()) {
                path = r.getString(5);
                flag=1;
            }
            if (flag==0)
            {
                throw new SongPathNotFoundException("Song Not Found...");
            }
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }catch (SongPathNotFoundException s)
        {
            System.out.println(s.getMessage());
        }
        return path;
    }
}