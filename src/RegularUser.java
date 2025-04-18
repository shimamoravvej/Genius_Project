import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegularUser extends User {
    private List<Artist> followedArtists = new ArrayList<>();
    // list of followed artist
    private List<String> songComments;// list of song's comments
    private List<Integer> ratings;
    private Map<String, List<Integer>> songRatings = new HashMap<>();


    public RegularUser(String username, String email, String password, String fullName, int age) {
        super(username, email, password, fullName, age);
        followedArtists = new ArrayList<Artist>();
        songComments = new ArrayList<>();
        this.ratings = new ArrayList<>();
    }

    @Override
    public void login() {
        System.out.println(getUsername() + " logged in as RegularUser.");
    }

    @Override
    public void logout() {
        System.out.println(getUsername() + " logged out.");
    }

    //adding rate to song
    public void addRating(String songName,int rating) {
        // review that the rate is between 1 - 5
        if (rating >= 1 && rating <= 5) {
            songRatings.computeIfAbsent(songName, k -> new ArrayList<>()).add(rating);
            System.out.println("Rating of " + rating + " added for song: " + songName);
        } else {
            System.out.println("Invalid rating! Please provide a rating between 1 and 5.");
        }
    }

    // delete the rate from the song
    public void removeRating(int rating) {
        if (ratings.contains(rating)) {
            ratings.remove(Integer.valueOf(rating));  // حذف امتیاز از لیست
            System.out.println("Rating of " + rating + " removed.");
        } else {
            System.out.println("Rating not found!");
        }
    }

    public void receiveUpdate(String updateMessage) {
        System.out.println("🔔 " + getUsername() + " received update: " + updateMessage);
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


    // method for receive update
    public void getArtistUpdates(String artistName) {
        boolean found = false;

        for (Artist artist : followedArtists) {
            if (artist.getFullName().equalsIgnoreCase(artistName)) {
                found = true;
                System.out.println("📢 Updates from " + artist.getFullName() + ":");

                List<String> updates = artist.getUpdates(); // فرض بر اینکه Artist کلاس getUpdates داره
                if (updates.isEmpty()) {
                    System.out.println("No updates yet.");
                } else {
                    for (String update : updates) {
                        System.out.println("- " + update);
                    }
                }
                break;
            }
        }

        if (!found) {
            System.out.println(getUsername() + " is not following artist: " + artistName);
        }
    }

    // view sons lyrics
    public void viewSongLyrics(String songName, Artist artist) {
        System.out.println(getUsername() + " is viewing lyrics for the song: " + songName);
    }

    // suggested edits to song lyrics by approval artist
    public void suggestLyricEdit(String songName, String suggestedEdit) {
        System.out.println(getUsername() + " suggested an edit for the song: " + songName);
        System.out.println("Suggested edit: " + suggestedEdit);
    }

    // following the artist
    public void followArtist(Artist artist) {
        if (!followedArtists.contains(artist)) {
            followedArtists.add(artist);
            artist.addFollower(this);  //adding the regular user to artist follower
            System.out.println(getUsername() + " is now following " + artist.getFullName());
        } else {
            System.out.println(getUsername() + " is already following " + artist.getFullName());
        }
    }


    //register a comment for the song
    public void commentOnSong(String songName, String comment) {
        songComments.add(comment);
        System.out.println(getUsername() + " Successfully added a comment on " + songName + ": " + comment);
    }

    // view the list of following artist
    public void viewFollowedArtists() {
        if (followedArtists.isEmpty()) {
            System.out.println(getUsername() + " is not following any artists.");
        } else {
            System.out.println(getUsername() + " is following the following artists:");
            for (Artist artist : followedArtists) {
                System.out.println(artist);
            }
        }
    }

    // helper methods for getting personal information
    public List<Artist> getFollowedArtists() {
        return followedArtists;
    }

    public List<String> getSongComments() {
        return songComments;
    }
}
