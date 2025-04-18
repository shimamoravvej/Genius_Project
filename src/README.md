#Genius Music Platform

This project is a music management system that offers various features for managing songs, albums, artists, comments, likes, ratings, and lyric edits. The system allows users to view, edit, like, and collaborate with other artists on different music content. The project is written in Java and utilizes object-oriented programming concepts.

Features

Managing Songs and Albums:

Ability to add songs to albums.

View information about songs and albums.

Manage the order of songs in an album.

Managing Artists:

Each artist can add their works and collaborate with other artists.

Ability to view collaborating artists in each song or album.

Editing and Suggesting Lyric Edits:

Users can submit suggestions for editing song lyrics.

Artists can approve or reject the edit requests.

Managing Comments and Likes:

Users can leave comments on songs.

Ability to like comments.

Rating and Average Ratings:

Users can rate songs and the average rating is displayed.

Adding Tags:

Users can add tags to songs.

Managing Song History and Information:

View the number of views and the release date of each song.

Project Structure The project consists of several classes, each responsible for a specific task:

Artist: This class represents an artist and manages information about artists. Album: This class is used to manage albums and the songs within them. Song: This class represents a song and contains features and methods for managing songs. Content: This is an abstract class used as a base for different types of music content (songs, albums, etc.). It includes common features and methods for managing music content. Comment: This class is used to manage user comments about songs.

Class Details:

Artist The Artist class represents an artist and includes the following attributes:

Artist's name

Email

Password

Artist's age

Artist's username

Works published by the artist

Album The Album class is designed for managing albums and the songs within them. It includes attributes like:

Album title

Main artist

Release date

List of songs

Collaborating artists

Song The Song class represents a song and includes attributes such as:

Song title

Album

Main artist

Lyrics

Genre

Release date

Song order in the album

Likes and comments on the song

Content The Content class is an abstract class used for modeling music content. It includes features such as:

Content title

Album

Artist

Lyrics

Genre

Comments

Likes and ratings

Comment The Comment class is designed for managing user comments and includes attributes like:

Comment text

Number of likes

Main Methods:

viewContent(): Displays content and increases the number of views.

addCollaboratingArtist(Artist artist): Adds a collaborating artist.

addComment(String commentText): Adds a comment to the song.

likeComment(int index): Likes a comment.

addRating(int rating): Rates the song.

suggestLyricEdit(String suggestedLyric): Suggests a lyric edit.

approveLyricEdit(String suggestedLyric, boolean approve): Approves or rejects a lyric edit request.

How to Use: To use this project, simply load the code into your Java development environment (IDE), and use the various classes to create artists, songs, albums, and comments.

Installation Steps: Clone the project: First, clone the project via Git:

bash
Copy
Edit
git clone <url>
Compile the Project: To compile the project in your Java IDE, simply open the project and compile it if necessary.

Run the Project: You can use a class like Main to run the project and test the various functionalities.

Example of Using the Classes:

java
Copy
Edit
public class Main {
    public static void main(String[] args) {
        // Create artist
        Artist artist = new Artist("JohnDoe", "john@example.com", "password123", "John Doe", 30);

        // Create song
        Content song = new Song("Song Title", "Album Title", artist, "Lyrics of the song", "Rock", "2025-04-16", 1);

        // View song and increase views
        song.viewContent();

        // Add collaborating artist
        Artist collaborator = new Artist("JaneDoe", "jane@example.com", "password456", "Jane Doe", 28);
        song.addCollaboratingArtist(collaborator);

        // Suggest lyric edit
        song.suggestLyricEdit("Updated lyrics for the song.");

        // Approve lyric edit
        song.approveLyricEdit("Updated lyrics for the song.", true);

        // Add comment
        song.addComment("Great song!");

        // Like comment
        song.likeComment(0);

        // Display comments
        song.displayComments();

        // Add tag
        song.addTag("Best Song");

        // Display song information
        song.displayContentInfo();
    }
}
Sample Output:

yaml
Copy
Edit
Viewing song: Song Title. Total views: 1
JohnDoe added as a collaborating artist for the song: Song Title
Edit suggestion for lyrics: Updated lyrics for the song.
Lyric edit approved: Updated lyrics for the song.
New comment added: Comment: Great song! | Likes: 0
Your like has been added to this comment. Total likes: 1
Comments for song: Song Title
Comment: Great song! | Likes: 1
Tag added: Best Song
Song Title: Song Title
Album: Album Title
Artist: JohnDoe
Genre: Rock
Release Date: 2025-04-16
Views: 1
Comments: [Comment: Great song! | Likes: 1]
Collaborating Artists: JohnDoe, JaneDoe, 
Average Rating: 0.0
Folder Structure:

css
Copy
Edit
/src
    /models
        Artist.java
        Album.java
        Song.java
        Content.java
        Comment.java
    /main
        Main.java
License This project is released under the MIT License.