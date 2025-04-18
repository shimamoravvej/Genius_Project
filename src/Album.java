import java.util.ArrayList;
import java.util.List;

public class Album extends Content {
    private String albumTitle;
    private List<Content> songs;
    private Artist artist;
    private String releaseDate;
    private List<Artist> collaboratingArtists;
    private int trackOrder;

    public Album(String albumTitle, Artist artist, String releaseDate, String date) {
        super(albumTitle, null, artist, null, null, releaseDate);
        this.albumTitle = albumTitle;
        this.artist = artist;
        this.releaseDate = releaseDate;
        this.songs = new ArrayList<>();
        this.collaboratingArtists = new ArrayList<>();
    }

    @Override
    public int getTrackOrder() {
        return songs.size();
    }

    //add the CollaboratingArtist to album
    public void addCollaboratingArtist(Artist collaboratingArtist) {
        if (!collaboratingArtists.contains(collaboratingArtist)) {
            collaboratingArtists.add(collaboratingArtist);
            System.out.println(collaboratingArtist.getUsername() + " added as a collaborating artist for the album.");
        } else {
            System.out.println(collaboratingArtist.getUsername() + " is already a collaborator.");
        }
    }

    //display the CollaboratingArtist to album
    public void displayCollaboratingArtists() {
        System.out.println("Collaborating Artists for " + getTitle() + ":");
        for (Artist artist : collaboratingArtists) {
            System.out.println(artist.getUsername());
        }
    }

    // add song to album
    public void addSongToAlbum(Content song, int position) {
        if (!songs.contains(song)) {
            if (position >= 0 && position <= songs.size()) {
                songs.add(position, song);
                System.out.println("Song " + song.getTitle() + " added to album " + albumTitle + " at position " + position);
            } else {
                songs.add(song);  // اگر موقعیت نامعتبر باشد، آهنگ در انتهای لیست اضافه می‌شود
                System.out.println("Song " + song.getTitle() + " added to album " + albumTitle + " at the end.");
            }
        } else {
            System.out.println("Song " + song.getTitle() + " is already part of the album.");
        }
    }

    // display the album information
    public void displayAlbumInfo() {
        System.out.println("Album Title: " + albumTitle);
        System.out.println("Artist: " + artist.getUsername());
        System.out.println("Release Date: " + releaseDate);
        System.out.println("Songs in Album:");
        for (int i = 0; i < songs.size(); i++) {
            System.out.println((i + 1) + ". " + songs.get(i).getTitle());
        }
    }

    //display the song information and manner of them in album
    public void displaySongOrder() {
        System.out.println("Song Order in Album " + albumTitle + ":");
        for (int i = 0; i < songs.size(); i++) {
            System.out.println((i + 1) + ". " + songs.get(i).getTitle());
        }
    }

    // helper methods
    public String getAlbumTitle() {
        return albumTitle;
    }

    public List<Content> getSongs() {
        return songs;
    }

    public Artist getArtist() {
        return artist;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}
