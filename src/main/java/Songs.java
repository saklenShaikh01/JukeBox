public class Songs implements Comparable<Songs>{
private int id;
private String songName;
private String singer;
private String genere;

    public Songs(int id, String songName, String singer, String genere) {
        this.id = id;
        this.songName = songName;
        this.singer = singer;
        this.genere = genere;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSinger() {
        return singer;
    }

    public void setAuthor(String singer) {
        this.singer = singer;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }


    @Override
    public String toString() {
        return id + "\t\t " + songName + "\t    "  + singer + "\t\t  " + genere;
    }
    @Override
    public int compareTo(Songs s1)
    {
        return songName.compareToIgnoreCase(s1.songName);
    }
}
