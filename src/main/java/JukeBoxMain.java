import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class JukeBoxMain
{
    public static void main(String[] args) throws SongPathNotFoundException{
        String path;
        String username;
        int ch;
        MakeConnection makeConnection=new MakeConnection();
        Connection con= makeConnection.createConnection();
        UserDetails userDetails=new UserDetails();
        AudioConnection audioConnection=new AudioConnection();
        SearchSongs searchSongs=new SearchSongs();
        Settings settings=new Settings();
        PlayList playList=new PlayList();
        Scanner s=new Scanner(System.in);
        System.out.println("******************** Juke Box ********************");
        System.out.println("For existing user Enter :\n1. Log In...\n---------------\nFor new user Enter :\n2. Sign In...\n---------------\nFor guest user Enter :\n3. Guest User...");
        int select=s.nextInt();
        switch (select) {
            case 1: {
                System.out.println("********** Juke Box Login Page **********");
                System.out.println("Please Enter Your Username...");
                username=s.next();
                System.out.println("Please Enter Your Password...");
                String password=s.next();
                username=userDetails.getloginDetails(con,username,password);
                System.out.println("***Welcome "+username+" to the Juke Box***");
                System.out.println("-------------------------------------");
                do {
                    System.out.println("********** Juke Box Home Page **********");
                    System.out.println("***Please select...***");
                    System.out.println("""
                            1. Show All Songs
                            2. Search by Singer
                            3. Search by Song Name
                            4. Search by Genere
                            5. Create Playlist
                            6. Add Songs to playlist
                            7. Play Songs from playlist
                            8.Settings
                            9. Exit""");
                    int choice = s.nextInt();
                    if (choice==1){
                        audioConnection.showallTable(con);
                        System.out.println("Select...\n1. Play Song...\n2. Skip...");
                        int opt=s.nextInt();
                        if (opt==1) {
                            path = audioConnection.filePath(con);
                            SimpleAudioPlayer.music(path);
                        }
                    }
                    else if(choice==2) {
                        audioConnection.showallTable(con);
                        List<Songs> songs=searchSongs.getList(con);
                        searchSongs.songsBySinger(songs);
                        path = audioConnection.filePath(con);
                        SimpleAudioPlayer.music(path);
                    }
                    else if(choice==3) {
                        audioConnection.showallTable(con);
                        List<Songs>songs=searchSongs.getList(con);
                        searchSongs.songsBySongName(songs);
                        path = audioConnection.filePath(con);
                        SimpleAudioPlayer.music(path);
                    }
                    else if(choice==4) {
                        audioConnection.showallTable(con);
                        List<Songs>songs=searchSongs.getList(con);
                        searchSongs.songsByGenere(songs);
                        path = audioConnection.filePath(con);
                        SimpleAudioPlayer.music(path);
                    }
                    else if(choice==5)
                    {
                        int userid= playList.getUserid(username,con);
                        playList.getPlaylistName(userid,con);
                        System.out.println("Please Enter name for playlist...");
                        String playlistName=s.next();
                        playList.createPlaylist(userid,con,playlistName);
                    } else if (choice==6) {
                        int i;
                        int userid=playList.getUserid(username,con);

                        do {
                            System.out.println("***Please Enter...***\n1. Add Songs\n2. Delete Playlist");
                            int sel = s.nextInt();
                            switch (sel) {
                                case 1: {
                                    System.out.println("**********************************************************");
                                    System.out.println("Existing Playlist : ");
                                    playList.getPlaylistName(userid,con);
                                    int playlistid = playList.playlistSongs(con);
                                    playList.addSongs(playlistid, con);
                                    break;
                                }
                                case 2: {
                                    System.out.println("**********************************************************");
                                    System.out.println("Existing Playlist : ");
                                    playList.getPlaylistName(userid,con);
                                    System.out.println("Playlist id from above to delete playlist...");
                                    int playlist = s.nextInt();
                                    playList.deleteplaylist(con, playlist);
                                    break;
                                }
                            }
                            System.out.println("Select...\n1. Continue\n2. Return to selectionn ");
                            i=s.nextInt();
                        }while (i==1);

                    } else if (choice==7)
                    {
                        int userid=playList.getUserid(username,con);
                        System.out.println("List of Playlist : ");
                        playList.getPlaylistName(userid,con);
                        playList.playlistSongs(con);
                        path = audioConnection.filePath(con);
                        SimpleAudioPlayer.music(path);
                    } else if (choice==8) {
                        System.out.println("********** Juke Box Setting Page **********");
                        System.out.println("***Please Enter...***\n1. Change Password\n2. Give feedback of application");
                        int get=s.nextInt();
                        switch (get)
                        {
                            case 1 :
                            {
                                System.out.println("Enter new password...");
                                String pass=s.next();
                                userDetails.Password_Validation(pass);
                                settings.updatePassword(con,username,pass);
                                break;
                            }
                            case 2:
                            {
                                int userid= playList.getUserid(username,con);
                                settings.feedback(con,userid);
                                break;
                            }
                        }
                    } else if (choice==9)
                    {
                        System.out.println("You are exited...");
                        System.out.println("***** Thank You *****");
                        System.exit(3);
                    } else
                    {
                        System.out.println("Wrong selection please make proper selection...");
                    }
                    System.out.println("***Please Enter...***\n1. Home Page\n2. Exit");
                    ch=s.nextInt();
                    }while (ch==1);
                if (ch==2) {
                    System.out.println("You are exited...");
                    System.out.println("***** Thank You *****");
                    System.exit(1);
                }
                break;
                }
            case 2: {
                System.out.println("********** Juke Box Sign in Page **********");
                System.out.println("Please Enter Your Username...");
                username=s.next();
                System.out.println("Please Enter Your Password...\n(***Password must be of 8 digit in which it must include Letters, Special character & Numbers...***)");
                String password=s.next();
                userDetails.Password_Validation(password);
                System.out.println("Please Enter Your Mail-Id...");
                String email=s.next();
                System.out.println("Please Enter Your Phone No...");
                long phoneNo=s.nextLong();
                userDetails.getsigninDetail(con,username,password,email,phoneNo);
                break;
            }
            default:{
                System.out.println("Sorry Wrong selection either select 1 or 2...");
                break;
            }
        }
        }
    }