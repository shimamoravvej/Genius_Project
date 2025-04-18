import java.util.ArrayList;
import java.util.List;

//content class
public abstract class Content {
    private String title;             // song label
    private String album;             // album name
    private Artist artist;            // main artist
    private String lyrics;            // song name
    private String genre;             // song genre
    private List<String> tags;        // tags
    private int views;                // view number
    private List<Comment> comments;    // comments
    private String releaseDate;       // date of dissemination
    private List<Artist> collaboratingArtists;
    private List<String> editRequests;
    private List<Integer> ratings;
    private int likes;
    private int shares;


    public Content(String title, String album, Artist artist, String lyrics, String genre, String releaseDate) {
        this.title = title;
        this.album = album;
        this.artist = artist;
        this.lyrics = lyrics;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.views = 0;
        this.comments = new ArrayList<>();
        this.collaboratingArtists = new ArrayList<>();
        this.editRequests = new ArrayList<>();
        this.tags = new ArrayList<>();
        this.ratings = new ArrayList<>();
    }

    public abstract int getTrackOrder();

    // view the song and increase view number
    public void viewContent() {
        views++;
        System.out.println("Viewing song: " + title + ". Total views: " + views);
    }

    // add artist to song
    public void addCollaboratingArtist(Artist artist) {
        if (!collaboratingArtists.contains(artist)) {
            collaboratingArtists.add(artist);
            System.out.println(artist.getUsername() + " added as a collaborating artist for the song: " + title);
        } else {
            System.out.println(artist.getUsername() + " is already a collaborating artist.");
        }
    }

    // display collaboration
    public void displayCollaboratingArtists() {
        System.out.println("Collaborating Artists for " + title + ":");
        for (Artist artist : collaboratingArtists) {
            System.out.println(artist.getUsername());
        }
    }

    // suggest to edite lyrics
    public void suggestLyricEdit(String suggestedLyric) {
        editRequests.add(suggestedLyric);
        System.out.println("Edit suggestion for lyrics: " + suggestedLyric);
    }

    //  approve or reject edite lyrics request by artist or manager
    public void approveLyricEdit(String suggestedLyric, boolean approve) {
        if (approve) {
            this.lyrics = suggestedLyric;
            System.out.println("Lyric edit approved: " + suggestedLyric);
        } else {
            System.out.println("Lyric edit rejected: " + suggestedLyric);
        }
    }

    //add comment to song
    public void addComment(String commentText) {
        Comment comment = new Comment(commentText);
        comments.add(comment);
        System.out.println("New comment added: " + comment);
    }

    //like the comment
    public void likeComment(int index) {
        if (index >= 0 && index < comments.size()) {
            comments.get(index).like();
        } else {
            System.out.println("Invalid comment index.");
        }
    }

    //display the comment
    public void displayComments() {
        System.out.println("Comments for song: " + title);
        for (Comment comment : comments) {
            comment.displayComment();
        }
    }


    // display song information
    public void displayContentInfo() {
        System.out.println("Song Title: " + title);
        System.out.println("Album: " + (album != null ? album : "No album"));
        System.out.println("Artist: " + artist.getUsername());
        System.out.println("Genre: " + genre);
        System.out.println("Release Date: " + releaseDate);
        System.out.println("Views: " + views);
        System.out.println("Comments: " + comments);
        System.out.println("Collaborating Artists: " + getCollaboratingArtistsNames());
        System.out.println("Average Rating: " + getAverageRating());
    }

    private String getCollaboratingArtistsNames() {
        StringBuilder names = new StringBuilder();
        for (Artist artist : collaboratingArtists) {
            names.append(artist.getUsername()).append(", ");
        }
        return names.toString().isEmpty() ? "No collaborators" : names.toString();
    }

    // user rating average
    public double getAverageRating() {
        if (ratings.isEmpty()) return 0.0;
        int total = 0;
        for (int rating : ratings) {
            total += rating;
        }
        return total / (double) ratings.size();
    }

    // rate 1 - 5 by users
    public void addRating(int rating) {
        if (rating >= 1 && rating <= 5) {
            ratings.add(rating);
            System.out.println("Rating of " + rating + " stars added for song: " + title);
        } else {
            System.out.println("Invalid rating. Rating should be between 1 and 5.");
        }
    }

    // delete privilege
    public void removeRating(int rating) {
        if (ratings.contains(rating)) {
            ratings.remove(Integer.valueOf(rating));  // حذف امتیاز
            System.out.println("Rating " + rating + " removed from song: " + title);
        } else {
            System.out.println("Rating " + rating + " not found for song: " + title);
        }
    }


    // access to song information
    public String getTitle() {
        return title;
    }

    public String getAlbum() {
        return album;
    }

    public Artist getArtist() {
        return artist;
    }

    public String getLyrics() {
        return lyrics;
    }

    public String getGenre() {
        return genre;
    }

    public List<String> getTags() {
        return tags;
    }

    public int getViews() {
        return views;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public List<Artist> getCollaboratingArtists() {
        return collaboratingArtists;
    }

    public List<String> getEditRequests() {
        return editRequests;
    }

    // add tag to song
    public void addTag(String tag) {
        tags.add(tag);
        System.out.println("Tag added: " + tag);
    }

    // add the song to album
    public void addToAlbum(String albumName) {
        this.album = albumName;
        System.out.println("Song " + title + " added to album: " + albumName);
    }
}
