import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Artist extends User {
    private List<Song> songs;             // list of the songs
    private List<String> albums;          // list of the albums
    private List<String> popularSongs;    // list of the favorite songs
    private List<String> updates; // update list
    private List<RegularUser> followers;
    private boolean isApproved;
    private List<Integer> ratings;
    private Map<String, List<Integer>> songRatings = new HashMap<>();

    public Artist(String username, String email, String password, String fullName, int age) {
        super(username, email, password, fullName, age);
        this.songs = new ArrayList<>();
        this.albums = new ArrayList<>();
        this.popularSongs = new ArrayList<>();
        this.updates = new ArrayList<>();  // start to saving updates
        this.followers = new ArrayList<>();
        this.isApproved = false;
        this.ratings = new ArrayList<>();
    }

    @Override
    public void login() {
        System.out.println(getUsername() + " logged in as Artist.");
    }

    @Override
    public void logout() {
        System.out.println(getUsername() + " logged out.");
    }

    // rating songs
    public void addRating(String songName,int rating) {
        if (rating >= 1 && rating <= 5) {
            songRatings.computeIfAbsent(songName, k -> new ArrayList<>()).add(rating);
            System.out.println("Rating of " + rating + " added for song: " + songName);
        } else {
            System.out.println("Invalid rating! Please provide a rating between 1 and 5.");
        }
    }

    // delete the distinction
    public void removeRating(int rating) {
        if (ratings.contains(rating)) {
            ratings.remove(Integer.valueOf(rating));  // حذف امتیاز از لیست
            System.out.println("Rating of " + rating + " removed.");
        } else {
            System.out.println("Rating not found!");
        }
    }

    public void viewRatingsForSong(String songName) {
        if (songRatings.containsKey(songName)) {
            System.out.println("Ratings for " + songName + ":");
            for (int rating : songRatings.get(songName)) {
                System.out.println(rating);
            }
        } else {
            System.out.println("No ratings available for this song.");
        }
    }

    //method for receive approve or reject artist
    public void setApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }

    public boolean isApproved() {
        return isApproved;
    }

    // edit the lyric of songs
    public void editSongLyrics(String songName, String newLyrics) {
        for (Song song : songs) {
            if (song.getSongName().equals(songName)) {
                song.setLyrics(newLyrics);  // edit the lyric
                updates.add("Song lyrics updated for: " + songName);  // اضافه کردن به‌روزرسانی
                System.out.println(getUsername() + " edited the lyrics of the song: " + songName);
                return;
            }
        }
        System.out.println("Song not found!");
    }

    //method for receive updates
    public List<String> getUpdates() {
        return updates;
    }

    // approve or reject user's suggested edits
    public void approveLyricEdit(String songName, boolean approve) {
        if (approve) {
            System.out.println(getUsername() + " approved the lyric edit for the song: " + songName);
        } else {
            System.out.println(getUsername() + " rejected the lyric edit for the song: " + songName);
        }
    }

    // adding a new song to the platform
    public void addNewSong(String songName, String lyrics, String album, String genre, String releaseDate, String date, int trackOrder) {
        Song newSong = new Song(songName, album, this, lyrics, genre, releaseDate, trackOrder);
        songs.add(newSong);
        updates.add("New song added: " + songName);  //adding updates
        System.out.println(getUsername() + " added a new song: " + songName);
    }

    // create the new album
    public void createNewAlbum(String albumName) {
        albums.add(albumName);
        updates.add("New album created: " + albumName);  //adding updates
        System.out.println(getUsername() + " created a new album: " + albumName);
    }

    // adding a song to the album
    public void addSongToAlbum(String songName, String albumName) {
        boolean albumExists = albums.contains(albumName);
        boolean songExists = false;

        for (Song song : songs) {
            if (song.getSongName().equals(songName)) {
                songExists = true;
                break;
            }
        }

        if (albumExists && songExists) {
            System.out.println(getUsername() + " added the song: " + songName + " to the album: " + albumName);
        } else if (!albumExists) {
            System.out.println("Album " + albumName + " does not exist.");
        } else {
            System.out.println("Song " + songName + " does not exist.");
        }
    }

    //method for send updates to followers
    public void sendUpdatesToFollowers() {
        if (followers.isEmpty()) {
            System.out.println("No followers to send updates.");
            return;
        }

        // send updates to each follower
        for (RegularUser follower : followers) {

            System.out.println("Sending update to " + follower.getUsername() + ":");
            System.out.println("Hey " + follower.getFullName() + ", " + getUsername() + " has posted new updates!");
            //adding more details
            System.out.println("New song added: " + songs.getLast().getSongName());
            System.out.println("Check out the artist's profile for more info.");
            System.out.println("-----------------------------");
        }
    }

    // method for follow artist by regular users
    public void addFollower(RegularUser user) {
        if (!followers.contains(user)) {
            followers.add(user);
            System.out.println(user.getUsername() + " is now following " + getUsername());
        } else {
            System.out.println(user.getUsername() + " is already following " + getUsername());
        }
    }

    // artists can only be followed and cannot follow users
    public void followArtist() {
        System.out.println(getUsername() + " can only be followed but cannot follow other users.");
    }

    // display artist profile information
    public void displayProfile() {
        System.out.println("Profile of " + getUsername() + ":");
        System.out.println("Full Name: " + getFullName());
        System.out.println("Age: " + getAge());
        System.out.println("Songs: " + songs);
        System.out.println("Albums: " + albums);
        System.out.println("Popular Songs: " + popularSongs);
    }

    // helper methods for songs and alums management
    public List<Song> getSongs() {
        return songs;
    }

    public List<String> getAlbums() {
        return albums;
    }

    public List<String> getPopularSongs() {
        return popularSongs;
    }
}
