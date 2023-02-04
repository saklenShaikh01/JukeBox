import java.sql.*;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDetails {

    Scanner s=new Scanner(System.in);

    public String getloginDetails(Connection con,String username,String password)
    {
        int flag=0;
        try {
            Settings settings=new Settings();
            UserDetails userDetails=new UserDetails();
            String sql = "select*from userdetail where username=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1,username);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString(2).equals(password)) {
                    flag=1;
                }else {
                    System.out.println("Incorrect password update your password...");
                    System.out.println("Enter new password...");
                    String pass=s.next();
                    userDetails.Password_Validation(pass);
                    settings.updatePassword(con,username,pass);
                    System.out.println("Login again...");
                    System.exit(0);
                }

            }
            if (flag==0)
            {
                System.out.println("Incorrect username please try again...");
                System.exit(1);
            }
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return username;
    }

    public String getsigninDetail(Connection con,String username,String password,String email,long phoneNo)
    {
        try {
            int userid=0;
            String sql = "insert into userdetail values(?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1,username);
            statement.setString(2,password);
            statement.setString(3,email);
            statement.setLong(4,phoneNo);
            statement.setInt(5,userid);
            statement.executeUpdate();
            System.out.println("***Congratulations "+username+" account successfully created...***\n***Please login again for activating account...***");
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return username;
    }

    public boolean Password_Validation(String password)
    {

        if(password.length()>=8)
        {
Pattern p = Pattern.compile("[^A-za-z0-9]");
     Matcher m = p.matcher(password);

     boolean b = m.find();
            if (b) {
                System.out.println("***Password strength is strong***");
            }
            else {
                System.out.println("Sorry...\nThere is no special char...\n***Password strength is weak***\n***Password must be of 8 digit in which it must include Letters, Special character & Numbers...***");
            System.exit(6);
            }
        }
        else {
            System.out.println("Sorry...\n***Password strength is weak***\n***Password must be of 8 digit in which it must include Letters, Special character & Numbers...*** ");
            System.exit(5);
            return false;
        }
        return true;
    }


    }

