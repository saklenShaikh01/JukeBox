import java.sql.*;
import java.util.*;

public class SearchSongs {
    Scanner s=new Scanner(System.in);

List<Songs> getList(Connection con)
{
    List<Songs> songs=new ArrayList<>();
    try {
        String sql="select songid,songName,singer,genere from songsdetails";
        PreparedStatement statement= con.prepareStatement(sql);
        ResultSet resultSet= statement.executeQuery();
        while (resultSet.next())
        {
            int songid = resultSet.getInt(1);
            String songName=resultSet.getString(2);
            String singer=resultSet.getString(3);
            String genere=resultSet.getString(4);
            Songs songs1=new Songs(songid,songName,singer,genere);
            songs.add(songs1);
        }
        Collections.sort(songs);
    }catch (SQLException e)
    {
        System.out.println(e.getMessage());
    }catch (NumberFormatException ex)
    {
        System.out.println(ex.getMessage());
    }
    return songs;
}
public List<Songs> songsBySinger(List <Songs> songs)
{
    int flag=0;
    List<Songs> list=new ArrayList<>();
    System.out.println("Please enter singer name here...");
    String singer=s.next();
    for (Songs songs1:songs)
    {
        if (songs1.getSinger().equalsIgnoreCase(singer))
        {
            list.add(songs1);
            flag=1;
        }
    }
    if (flag==0)
    {
        System.out.println("Song Not Found...");
        System.exit(0);
    }
    Comparator <Songs> comparator = (o1,o2)-> {
        return (o1.getSinger().compareToIgnoreCase(o2.getSinger()));
    };
    Collections.sort(list,comparator);
    System.out.println("SongId \t SongName \t    Singer \t     Genere");
    Songs[] list1=list.toArray(new Songs[0]);
    for (int i=0;i<list1.length;i++)
    {
        System.out.println(list1[i]);
    }
    return list;
}
    public List<Songs> songsByGenere(List<Songs> songs)
    {
        int flag=0;
        List<Songs> list=new ArrayList<>();
        System.out.println("Please enter genere here...");
        String genere=s.next();
        for (Songs songs1:songs)
        {
            if (songs1.getGenere().equalsIgnoreCase(genere))
            {
                list.add(songs1);
                flag=1;
            }
        }
        if (flag==0)
        {
            System.out.println("Song Not Found...");
            System.exit(0);
        }
        Comparator <Songs> comparator = (o1,o2)-> {
            return (o1.getGenere().compareToIgnoreCase(o2.getGenere()));
        };
        Collections.sort(list,comparator);
        System.out.println("SongId \t SongName \t    Singer \t     Genere");
        Songs[] list1=list.toArray(new Songs[0]);
        for (int i=0;i<list1.length;i++)
        {
            System.out.println(list1[i]);
        }

        return list;
    }
    public List<Songs> songsBySongName(List<Songs>songs)
    {
        int flag=0;
        List<Songs> list=new ArrayList<>();
        System.out.println("Please enter song name here...");
        String songName=s.next();
        for (Songs songs1:songs)
        {
            if (songs1.getSongName().equalsIgnoreCase(songName))
            {
                list.add(songs1);
                flag=1;
            }

        }
        if (flag==0)
        {
            System.out.println("Song Not Found...");
            System.exit(0);
        }
        Comparator <Songs> comparator = (o1,o2)-> {
            return (o1.getSongName().compareToIgnoreCase(o2.getSongName()));
        };
        Collections.sort(list,comparator);
        Songs[] list1=list.toArray(new Songs[0]);
        System.out.println("SongId \t SongName \t    Singer \t     Genere");
        for (int i=0;i<list1.length;i++)
        {
            System.out.println(list1[i]);
        }
        return songs;
     }
}
