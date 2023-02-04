import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SearchSongsTest {
SearchSongs searchSongs;
Songs songs;
List <Songs> song=new ArrayList<>();
String filePath="E:/NIIT/Capstone_Project/oh-merciful.wav";
    MakeConnection makeConnection=new MakeConnection();
    Connection con= makeConnection.createConnection();

    @org.junit.Before
    public void setUp() throws Exception {
        searchSongs=new SearchSongs();
        songs=new Songs(1,"Oh-Merciful","JunedJamshed","Hamd");
    }

    @org.junit.After
    public void tearDown() throws Exception {
        searchSongs=null; songs=null;
    }

    @org.junit.Test
    public void getList() {
        List<Songs> output = searchSongs.getList(con);
        assertEquals("File not found...",4,output.size());
    }

}