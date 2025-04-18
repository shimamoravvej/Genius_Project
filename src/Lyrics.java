public class Lyrics extends Content {
    private String lyrics;

    public Lyrics(String title, String album, Artist artist, String lyrics, String genre, String releaseDate) {
        super(title, album, artist, lyrics, genre, releaseDate);
        this.lyrics = lyrics; //assigning song lyrics
    }

    @Override
    public int getTrackOrder() {
        // this method can be used to determine the track number in an album according to its display order
        return 0;
    }

    // display lyrics
    public void displayLyrics() {
        System.out.println("Lyrics for " + getTitle() + ": " + lyrics);
    }

    // edite lyrics
    public void editLyrics(String newLyrics) {
        this.lyrics = newLyrics;
        System.out.println("Lyrics for " + getTitle() + " updated.");
    }
}
