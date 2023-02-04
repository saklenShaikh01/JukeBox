import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class PlayListTest {
PlayList playList;
    String username;
    String playlistName;
    int userid;
    MakeConnection makeConnection;
    Connection con;
    @Before
    public void setUp() throws Exception {
        playList=new PlayList();
        makeConnection=new MakeConnection();
        con= makeConnection.createConnection();
    }

    @After
    public void tearDown() throws Exception {
        username=null;
        playlistName=null;
        userid=0;
    }

    @Test
    public void getUserid() {
        username="SakShaikh";
        playlistName="Naat";
        userid= playList.getUserid(username,con);
        assertEquals(1,userid);
    }
    @Test
    public void getUseridFail() {
        username="SakShaikh";
        playlistName="Naat";
        userid= playList.getUserid(username,con);
        assertNotEquals(2,userid);
    }
    @Test
    public void createPlaylist() {
        username="SakShaikh";
        playlistName="Naat";
        userid= playList.getUserid(username,con);
        playlistName=playList.createPlaylist(userid,con,playlistName);
        assertEquals("playlist created",playlistName,playlistName);
    }
    @Test
    public void createPlaylistFail() {
        username="SakShaikh";
        playlistName="Naat";
        userid= playList.getUserid(username,con);
        playlistName=playList.createPlaylist(userid,con,playlistName);
        assertNotEquals("Null",playlistName,username);
    }
}