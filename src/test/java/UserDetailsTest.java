import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class UserDetailsTest {
    UserDetails userDetails;
    String password=null;
    String email=null;
    long phoneNo;
    MakeConnection makeConnection=new MakeConnection();
    Connection con= makeConnection.createConnection();
String username=null;
    @Before
    public void setUp() throws Exception {
        userDetails=new UserDetails();
    }

    @After
    public void tearDown() throws Exception {
        username=null;
        password=null;
        email=null;
        phoneNo=0;
    }
    @Test
    public void getloginDetails() {
        username="SakShaikh";
        password="Sak@123";
        String user=userDetails.getloginDetails(con,username,password);
        assertEquals("Welcome",user,"SakShaikh");
    }

    @Test
    public void getWrongusernamelogin() {
        username="sakshaikh";
        password="Sak@123";
        String user=userDetails.getloginDetails(con,username,password);
        assertNotEquals("Incorrect Username",user,"SakShaikh");
    }

    @Test
    public void getsigninDetail() {
        username="SamKhan";
        password="Sam@123";
        email="Sam@gmail.com";
        phoneNo=787592143;
        String usersignin=userDetails.getsigninDetail(con,username,password,email,phoneNo);
        assertEquals("Congratulations",usersignin,"SamKhan");
    }
    @Test
    public void getsigninDetailFail() {
        username="SamKhan";
        password="Sam@123";
        email="Sam@gmail.com";
        phoneNo=787592143;
        String usersignin=userDetails.getsigninDetail(con,username,password,email,phoneNo);
        assertNotEquals("Congratulations",usersignin,"samkhan");
    }
}