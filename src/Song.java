import java.util.List;

// Song class inheriting from Content
public class Song extends Content {
    private int trackOrder;  // manner of the songs  in album
    private String songName; // song name
    private String lyrics;

    // creator
    public Song(String title, String album, Artist artist, String lyrics, String genre, String releaseDate, int trackOrder) {
        super(title, album, artist, lyrics, genre, releaseDate);
        this.trackOrder = trackOrder;
        this.songName = title;  // take the song name
        this.lyrics = lyrics;
    }

    // receive the manner of the songs in album
    @Override
    public int getTrackOrder() {
        return trackOrder;
    }

    // change the manner of the songs
    public void setTrackOrder(int trackOrder) {
        this.trackOrder = trackOrder;
    }

    // change the lyrics
    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    @Override
    public void displayContentInfo() {
        super.displayContentInfo();
        System.out.println("Song Name: " + songName);
        System.out.println("Track Order: " + trackOrder);
        System.out.println("Lyrics: " + lyrics);
    }

    @Override
    public void viewContent() {
        super.viewContent();
        System.out.println("Viewing song: " + songName);
    }

    //access to song lyric
    public String getLyrics() {
        return lyrics;
    }

    //access to song name
    public String getSongName() {
        return songName;
    }

    // edite to song lyric
    public void editLyrics(String newLyrics) {
        this.lyrics = newLyrics;
        System.out.println("Lyrics of song \"" + songName + "\" updated successfully.");
    }

    // add the collaboratingArtist
    public void addCollaboratingArtist(Artist collaboratingArtist) {
        super.addCollaboratingArtist(collaboratingArtist); // استفاده از متد والد
    }

    // display the CollaboratingArtist
    public void displayCollaboratingArtists() {
        super.displayCollaboratingArtists(); // استفاده از متد والد
    }
}
