import java.util.ArrayList;
import java.util.List;

// Admin class inherits from User
public class Admin extends User {
    private List<Artist> pendingArtists;
    private List<Artist> approvedArtists;
    private List<String> editRequests;
    private List<String> changeHistory;

    public Admin(String username, String email, String password, String fullName, int age) {
        super(username, email, password, fullName, age);
        this.pendingArtists = new ArrayList<>();
        this.approvedArtists = new ArrayList<>();
        this.editRequests = new ArrayList<>();
        this.changeHistory = new ArrayList<>();
    }

    @Override
    public void login() {
        System.out.println(getUsername() + " logged in as Admin.");
    }

    @Override
    public void logout() {
        System.out.println(getUsername() + " logged out.");
    }

    // review and approve the edite song lyrics by users
    public void approveLyricEditRequest(String songName, boolean approve, String userName) {
        if (approve) {
            System.out.println(getUsername() + " approved the lyric edit request for the song: " + songName);
            System.out.println("User " + userName + ", your lyric edit for song " + songName + " was approved!");
        } else {
            System.out.println(getUsername() + " rejected the lyric edit request for the song: " + songName);
            //send notification to users
            System.out.println("User " + userName + ", your lyric edit for song " + songName + " was rejected.");
        }
        // save in changes hisory
        changeHistory.add("Lyric edit for " + songName + " was " + (approve ? "approved" : "rejected") + " by " + getUsername());
    }

    // add artist to pending artist list
    public void addPendingArtist(Artist artist) {
        pendingArtists.add(artist);
        System.out.println("Artist " + artist.getUsername() + " is now pending approval.");
        changeHistory.add("Artist " + artist.getUsername() + " added to pending list.");
    }

    // approve or reject the artist register
    public void approveArtistRegistration(String artistUsername, boolean approve) {
        Artist artist = findArtistByUsername(artistUsername, pendingArtists);
        if (artist != null) {
            if (approve) {
                approvedArtists.add(artist);
                pendingArtists.remove(artist);
                System.out.println("Admin approved " + artistUsername + "'s registration.");
                // send notification to artist
                System.out.println("Artist " + artistUsername + ", your registration has been approved!");
                changeHistory.add("Artist " + artistUsername + "'s registration approved.");
            } else {
                pendingArtists.remove(artist);
                System.out.println("Admin rejected " + artistUsername + "'s registration.");
                // ارسال اعلان به هنرمند
                System.out.println("Artist " + artistUsername + ", your registration has been rejected.");
                changeHistory.add("Artist " + artistUsername + "'s registration rejected.");
            }
        } else {
            System.out.println("Artist " + artistUsername + " not found in pending list.");
        }
    }

    // display the changes history
    public void displayChangeHistory() {
        System.out.println("Change History:");
        for (String change : changeHistory) {
            System.out.println(change);
        }
    }

    //adding lyric editing request by users
    public void addEditRequest(String songName) {
        editRequests.add(songName);
        System.out.println("Lyric edit request for song: " + songName + " has been submitted.");
    }

    //display admin profile
    public void displayProfile() {
        System.out.println("Admin Profile of " + getUsername() + ":");
        System.out.println("Full Name: " + getFullName());
        System.out.println("Age: " + getAge());
        System.out.println("Pending Artists: " + pendingArtists);
        System.out.println("Edit Requests: " + editRequests);
        System.out.println("Change History: " + changeHistory);
    }

    //helper methods for management
    public List<Artist> getPendingArtists() {
        return pendingArtists;
    }

    public List<String> getEditRequests() {
        return editRequests;
    }

    //finding artist by userName
    private Artist findArtistByUsername(String username, List<Artist> artistsList) {
        for (Artist artist : artistsList) {
            if (artist.getUsername().equals(username)) {
                return artist;
            }
        }
        return null;
    }

    //display the approved artists
    public void displayApprovedArtists() {
        System.out.println("Approved Artists:");
        for (Artist artist : approvedArtists) {
            System.out.println(artist.getUsername());
        }
    }

    // display edite requests
    public void displayEditRequests() {
        System.out.println("Pending Edit Requests:");
        for (String songName : editRequests) {
            System.out.println("Song: " + songName);
        }
    }
}
