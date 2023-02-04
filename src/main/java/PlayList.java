import java.sql.*;
import java.util.Scanner;
public class PlayList {

    Scanner s=new Scanner(System.in);
    String playlistName;
    int userid;
    public int getUserid(String username,Connection con)
    {
        try {
            String s = "select * from userdetail where username=?";
            PreparedStatement statement1 = con.prepareStatement(s);
            statement1.setString(1, username);
            ResultSet r = statement1.executeQuery();
            while (r.next()) {
                userid = r.getInt(5);
            }
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return userid;
    }

    String createPlaylist(int userid,Connection con,String playlistName)
    {
        int playlistid=0;
        try{
            String sql="insert into playlist(playlistid,playListName,userid)values(?,?,?)";
            PreparedStatement statement=con.prepareStatement(sql);
            statement.setInt(1,playlistid);
            statement.setString(2,playlistName);
            statement.setInt(3,userid);
            statement.executeUpdate();
            System.out.println("***Playlist Created...***\nName of playlist : "+playlistName);
        }catch (SQLException e)
        {
            System.out.println("table not showing");
        }
        return playlistName;
    }
    String playlistName1;
    String getPlaylistName(int userid,Connection con)
    {
        try {
            int flag=0;
            String sq = "select * from playlist where userid=?";
            PreparedStatement statement1 = con.prepareStatement(sq);
            statement1.setInt(1, userid);
            ResultSet r = statement1.executeQuery();
            System.out.println("Playlist Id  Playlist Name");
            while (r.next()) {
                playlistid = r.getInt(1);
                playlistName=r.getString(2);
                System.out.println(playlistid+"             "+playlistName);
                flag=1;
            }
if (flag==0)
{
    System.out.println("***You don't have any playlist...***");
}
        }catch (SQLException e)
        {
            System.out.println("table not showing");
        }
        return playlistName1;
    }

    void deleteplaylist(Connection con,int playlistid)
    {
        try {
            String sql = "delete from playlistdetails where playlistid=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, playlistid);
            statement.executeUpdate();
            String s = "delete from playlist where playlistid=?";
            PreparedStatement statement1 = con.prepareStatement(s);
            statement1.setInt(1, playlistid);
            statement1.executeUpdate();
            System.out.println("Playlist deleted...");
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    int playlistid;
int playlistSongs(Connection con)
{
    try {
        int flag=0;
        System.out.println("Select playlist id from above to view & add songs...");
        playlistid=s.nextInt();
        System.out.println("---------------------------------------------------------");
        String sql = "select*from songsdetails where songid in (select songid from playlistdetails where playlistid=?)";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, playlistid);
        ResultSet rs = statement.executeQuery();
        System.out.format("%-5s      %-20s %-20s %-10s %n", "SongId", "SongName", "Singer", "Genere");
        System.out.println("---------------------------------------------------------");
        while (rs.next()) {
            System.out.format("%-5s      %-20s %-20s %-10s %n", rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
        flag=1;
        }
        if (flag==0)
        {
            System.out.println("***Playlist is empty...***");
        }
    }catch (SQLException e)
    {
        System.out.println(e.getMessage());
    }
    return playlistid;
}
    void addSongs(int playlistid,Connection con)
    {
        Scanner sc=new Scanner(System.in);
        try {
            AudioConnection audioConnection=new AudioConnection();
            audioConnection.showallTable(con);
            System.out.println("Enter song id to add song to playlist : ");
            int songid=sc.nextInt();
            String sql="insert into playlistdetails(playlistid,songid)values(?,?)";
            PreparedStatement statement=con.prepareStatement(sql);
            statement.setInt(1,playlistid);
            statement.setInt(2,songid);
            statement.executeUpdate();
            System.out.println("***Song Added to Playlist...***");
        }catch (SQLException e)
        {
            System.out.println("table not showing");
        }
    }

}
