import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        Admin admin = new Admin("Admin1256", "adminUsername@gmial.com", "password", "Admin User", 30);
        admin.displayProfile();
        //artists list for follow management
        List<Artist> artistList = new ArrayList<>();
        artistList.add(new Artist("Eminem", "eminem@gmail.com", "pass123", "Marshall Mathers", 50));
        artistList.add(new Artist("TaylorSwift", "taylor@gmail.com", "tt456", "Taylor Swift", 34));
        artistList.add(new Artist("Drake", "drake@gmail.com", "pass789", "Aubrey Graham", 37));
        artistList.add(new Artist("Kim dracula", "kim@gmail.com", "drac1763", "Samuel Wiling", 27));
        artistList.add(new Artist("Linkin Park", "linkin@gmail.com", "california1996", "Mike Shinoda, Emily Armstrong", 48));

        //adding artist to pending list
        for (Artist artist : artistList) {
            admin.addPendingArtist(artist);
        }

        //approve the artist by admin
        for (Artist artist : artistList) {
            admin.approveArtistRegistration(artist.getUsername(), true); //approve the artist register
        }

        //display the approved artist list
        admin.displayApprovedArtists();

        //display editing lyrics request
        admin.displayEditRequests();

        //send notification to approved artist
        for (Artist artist : artistList) {
            artist.receiveNotification("Your registration has been approved.");
        }

        // options menu
        System.out.println("1. show the song lyric");
        System.out.println("2. edit the song lyric");
        System.out.println("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline character


        Lyrics songLyrics = new Lyrics("Song 1", "Album 1", new Artist("Artist Name", "emailaddress@gmail.com","password", "Artist name", 20), "Old lyrics", "Pop", "2025-01-01");
        Song song = new Song(
                "Lose Yourself",
                "8 Mile",
                artistList.get(0), // مثلا امینم
                "Look, if you had one shot...",
                "Hip-Hop",
                "2002",
                1
        );

        // اضافه کردن همکاری
        song.addCollaboratingArtist(artistList.get(1)); // تیلور سویفت
        song.displayCollaboratingArtists();

        song.viewContent();
        song.addComment("Great song!");
        song.likeComment(0);
        song.displayComments();
        song.addRating(5);
        song.addTag("FeelGood");
        song.displayContentInfo();
        song.suggestLyricEdit("New lyrics here");
        song.approveLyricEdit("New lyrics here", true);
        song.addCollaboratingArtist(artistList.get(1)); // Taylor Swift
        song.displayCollaboratingArtists();
        song.addToAlbum("Best hits collection"); // add to new album
        song.removeRating(5); // delete one rate
        song.addTag("Classic"); //add new  tag
        System.out.println("Average Rating: " + song.getAverageRating()); // display average rating

        song.likeContent();
        song.shareContent();
        System.out.println("Total likes: " + song.getLikes());
        System.out.println("Total shares: " + song.getShares());

        switch (choice) {
            case 1:
                //display the lyrics
                songLyrics.displayLyrics();
                break;
            case 2:
                // edite lyric
                System.out.print("Enter new lyrics: ");
                String newLyrics = scanner.nextLine();
                songLyrics.editLyrics(newLyrics);
                break;
            default:
                System.out.println("Invalid choice");
        }

        scanner.close();

        // songs and albums list
        List<Song> songList = new ArrayList<>();
        List<Album> albumList = new ArrayList<>();

        while (running) {
            System.out.println("\n📌 Welcome to Genius Platform!");
            System.out.println("1. Login / Sign up");
            System.out.println("2. Exit");
            System.out.print("Select an option: ");
            int mainChoice = scanner.nextInt();
            scanner.nextLine();  // مصرف خط جدید

            if (mainChoice == 2) {
                System.out.println("Exiting Genius Platform... Goodbye! 👋");
                running = false;
                break;
            }

            //identity crisis
            System.out.print("Enter your full name: ");
            String fullName = scanner.nextLine();

            System.out.print("Enter your age: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter your email: ");
            String email = scanner.nextLine();

            System.out.print("Enter your username: ");
            String username = scanner.nextLine();

            System.out.print("Enter your password: ");
            String password = scanner.nextLine();

            //role selection
            System.out.println("\nSelect your role: ");
            System.out.println("1. Regular User");
            System.out.println("2. Artist");
            System.out.println("3. Admin");
            int userType = scanner.nextInt();
            scanner.nextLine();

            //user creation based on selection
            User user;
            switch (userType) {
                case 1:
                    user = new RegularUser(username, email, password, fullName, age);
                    break;
                case 2:
                    user = new Artist(username, email, password, fullName, age);
                    break;
                case 3:
                    user = new Admin(username, email, password, fullName, age);
                    break;
                default:
                    System.out.println("Invalid selection. Returning to main menu...");
                    continue;
            }

            //enter to system
            user.login();
            System.out.println("✅ Welcome, " + user.getFullName() + "! You are logged in as " + user.getRole());

            //executing operation based on user role
            if (user instanceof RegularUser) {
                regularUserOperations((RegularUser) user, scanner, artistList, songList);
            } else if (user instanceof Artist) {
                artistOperations((Artist) user, scanner, songList,albumList, artistList);
            } else if (user instanceof Admin) {
                adminOperations((Admin) user, scanner);
            }


        }

        scanner.close();
    }

    //method for finding the songs based on the names
    public static Song findSongByName(String songName, List<Song> songList) {
        for (Song song : songList) {
            if (song.getSongName().equalsIgnoreCase(songName)) {
                return song;
            }
        }
        return null;


    }

    public static void displayFullSongDetails(Content song) {
        System.out.println("\n🎵 Full Details for: " + song.getTitle());
        System.out.println("Album: " + song.getAlbum());
        System.out.println("Artist: " + song.getArtist().getUsername());
        System.out.println("Lyrics: " + song.getLyrics());
        System.out.println("Genre: " + song.getGenre());
        System.out.println("Release Date: " + song.getReleaseDate());
        System.out.println("Views: " + song.getViews());
        System.out.println("Tags: " + song.getTags());
        System.out.println("Collaborating Artists: ");
        for (Artist a : song.getCollaboratingArtists()) {
            System.out.println("- " + a.getUsername());
        }
        System.out.println("Average Rating: " + song.getAverageRating());
        System.out.println("Comments: ");
        for (Comment c : song.getComments()) {
            c.displayComment();
        }
        System.out.println("Edit Requests: " + song.getEditRequests());
    }

    //regular users activities
    private static void regularUserOperations(RegularUser regularUser, Scanner scanner, List<Artist> artistList, List<Song> songList) {
        boolean running = true;
        while (running) {
            System.out.println("\n📌 Regular User Menu:");
            System.out.println("1. View Song Lyrics");
            System.out.println("2. Suggest Lyric Edit");
            System.out.println("3. Follow Artist");
            System.out.println("4. Comment on Song");
            System.out.println("5. View Followed Artists");
            System.out.println("6. View Artist Updates");
            System.out.println("7. Add Rating to Song");
            System.out.println("8.Remove Rating from Song");
            System.out.println("9. Remove Rating from Song");
            System.out.println("10. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine();
            String songName;
            String artistName = "Adele";

            switch (choice) {
                case 1:
                    System.out.print("Enter song name to view: ");
                    songName = scanner.nextLine();
                    Song songToView = findSongByName(songName, songList);
                    if (songToView != null) {
                        songToView.viewContent();  //increase the view
                    } else {
                        System.out.println("Song not found.");
                    }
                    break;
                case 2:
                    System.out.print("Enter song name to edit: ");
                    songName = scanner.nextLine();
                    System.out.print("Enter new lyrics: ");
                    String newLyrics = scanner.nextLine();
                    regularUser.suggestLyricEdit(songName, newLyrics);
                    break;
                case 3:
                    System.out.print("Enter artist username to follow: ");
                    artistName = scanner.nextLine();

                    //finding artist in list
                    Artist foundArtist = null;
                    for (Artist artist : artistList) {
                        if (artist.getUsername().equalsIgnoreCase(artistName)) {
                            foundArtist = artist;
                            break;
                        }
                    }

                    if (foundArtist != null) {
                        regularUser.followArtist(foundArtist);
                    } else {
                        System.out.println("Artist not found!");
                    }
                    break;


                case 4:
                    System.out.print("Enter comment: ");
                    String comment = scanner.nextLine();
                    regularUser.commentOnSong("Song 1", comment);
                    break;
                case 5:
                    regularUser.viewFollowedArtists();
                    break;
                case 6:
                   regularUser.getArtistUpdates(artistName);
                    break;
                case 7:
                    //rating the song
                    System.out.print("Enter song name to rate: ");
                    songName = scanner.nextLine();
                    System.out.print("Enter rating (1 to 5): ");
                    int rating = scanner.nextInt();
                    scanner.nextLine();
                    regularUser.addRating(songName,rating);
                    break;
                case 8:
                    //delete the rating
                    System.out.print("Enter song name to remove rating: ");
                    songName = scanner.nextLine();
                    System.out.print("Enter rating to remove (1 to 5): ");
                    int removeRating = scanner.nextInt();
                    scanner.nextLine();
                    regularUser.removeRating(removeRating);
                    break;
                case 9:
                    System.out.print("Enter song name to view full details: ");
                    songName = scanner.nextLine();
                    Song songDetails = findSongByName(songName, songList);
                    if (songDetails != null) {
                        displayFullSongDetails(songDetails);
                    } else {
                        System.out.println("Song not found.");
                    }
                    break;
                case 10:
                    System.out.println("Logging out...");
                    regularUser.logout();
                    running = false;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    //artist activities
    private static void artistOperations(Artist artist, Scanner scanner, List<Song> songList, List<Album> albumList, List<Artist> artistList) {
        boolean running = true;
        while (running) {
            System.out.println("\n🎤 Artist Menu:");
            System.out.println("1. Add New Song");
            System.out.println("2. Edit Song Lyrics");
            System.out.println("3. Approve Lyric Edit");
            System.out.println("4. Create New Album");
            System.out.println("5. Add song to Album");
            System.out.println("6. Display Profile");
            System.out.println("7. Send Updates to Followers");
            System.out.println("8. Add Rating to Song");
            System.out.println("9. Remove Rating from Song");
            System.out.println("10. View Full Song Details");
            System.out.println("11. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine();

            String songName;
            String lyrics;
            String artistName;
            String genre;
            String releaseDate;
            Song songToEdit;

            switch (choice) {
                case 1:
                    System.out.print("Enter song name: ");
                    songName = scanner.nextLine();
                    Song songToView = findSongByName(songName, songList);
                    if (songToView != null) {
                        songToView.viewContent();  //increase the view
                    } else {
                        System.out.println("Song not found.");
                    }
                    System.out.print("Enter lyrics: ");
                    lyrics = scanner.nextLine();
                    //receive more information for new song
                    System.out.print("Enter album name: ");
                    String album = scanner.nextLine();

                    System.out.print("Enter artist name: ");
                    artistName = scanner.nextLine();

                    System.out.print("Enter genre: ");
                    genre = scanner.nextLine();

                    System.out.print("Enter release date (yyyy-mm-dd): ");
                    releaseDate = scanner.nextLine();

                    System.out.print("Enter track order: ");
                    int trackOrder = scanner.nextInt();
                    scanner .nextLine();
                    artist.addNewSong(songName, lyrics,album, artistName, genre, releaseDate, trackOrder);
                    break;
                case 2:
                    System.out.print("Enter song name to edit: ");
                    songName = scanner.nextLine();
                    songToEdit = findSongByName(songName, songList);  // searching the song
                    if (songToEdit != null) {
                        System.out.print("Enter new lyrics: ");
                        String newLyrics = scanner.nextLine();
                        songToEdit.editLyrics(newLyrics);  //editing lyric
                    } else {
                        System.out.println("Song not found.");
                    }
                    break;
                case 3:
                    //changing manner of the song
                    System.out.print("Enter song name to change track order: ");
                    songName = scanner.nextLine();
                    songToEdit = findSongByName(songName, songList);  // searching the song
                    if (songToEdit != null) {
                        System.out.print("Enter new track order: ");
                        int newTrackOrder = scanner.nextInt();
                        songToEdit.setTrackOrder(newTrackOrder);
                    } else {
                        System.out.println("Song not found.");
                    }

                    System.out.print("Enter song name to approve edit: ");
                    songName = scanner.nextLine();
                    artist.approveLyricEdit(songName, true);
                    break;
                case 4:
                    System.out.print("Enter album name: ");
                    String albumName = scanner.nextLine();
                    artist.createNewAlbum(albumName); //create new album

                    System.out.print("Enter artist username: ");
                    artistName = scanner.nextLine();

                    Artist foundArtist = null;
                    for (Artist a : artistList) {
                        if (a.getUsername().equalsIgnoreCase(artistName)) {
                            foundArtist = a;
                            break;
                        }
                    }

                    if (foundArtist == null) {
                        System.out.println("Artist not found!");
                        break;
                    }

                    System.out.print("Enter genre: ");
                    genre = scanner.nextLine();

                    System.out.print("Enter release date (yyyy-mm-dd): ");
                    releaseDate = scanner.nextLine();
                    Album newAlbum = new Album(albumName, foundArtist, genre,releaseDate);
                    albumList.add(newAlbum);
                    artist.createNewAlbum(albumName);
                    break;
                case 5:
                    System.out.print("Enter song name to add to album: ");
                    songName = scanner.nextLine();
                    System.out.print("Enter album name: ");
                    String albumToAdd = scanner.nextLine();
                    artist.addSongToAlbum(songName, albumToAdd);
                    break;
                case 6:
                    //display artist profile
                    artist.displayProfile();
                    break;
                case 7:
                    artist.sendUpdatesToFollowers();  //send updates to followers
                    break;
                case 8:
                    //adding rate to song
                    System.out.print("Enter song name to rate: ");
                    songName = scanner.nextLine();
                    System.out.print("Enter rating (1 to 5): ");
                    int rating = scanner.nextInt();
                    scanner.nextLine();
                    artist.addRating(songName,rating);
                    break;
                case 9:
                    // delete the rate
                    System.out.print("Enter song name to remove rating: ");
                    songName = scanner.nextLine();
                    System.out.print("Enter rating to remove (1 to 5): ");
                    int removeRating = scanner.nextInt();
                    scanner.nextLine();
                    artist.removeRating(removeRating);
                    break;
                case 10:
                    System.out.print("Enter song name to view full details: ");
                    songName = scanner.nextLine();
                    Song songDetails = findSongByName(songName, songList);
                    if (songDetails != null) {
                        displayFullSongDetails(songDetails);
                    } else {
                        System.out.println("Song not found.");
                    }
                    break;
                case 11:
                    System.out.println("Logging out...");
                    artist.logout();
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    //admin activities
    private static void adminOperations(Admin admin, Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("\n🔧 Admin Menu:");
            System.out.println("1. Approve Artist Registration");
            System.out.println("2. Approve Lyric Edit Request");
            System.out.println("3. Display Approved Artists");
            System.out.println("4. Display Edit Requests");
            System.out.println("5. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter artist username to approve: ");
                    String artistUsername = scanner.nextLine();
                    admin.approveArtistRegistration(artistUsername, true);
                    break;
                case 2:
                    System.out.print("Enter song name to approve edit: ");
                    String songName = scanner.nextLine();

                    //receive user verification status
                    System.out.print("Enter approval status (true/false): ");
                    boolean approve = scanner.nextBoolean();
                    scanner.nextLine();

                    //receive artist userName
                    System.out.print("Enter artist username: ");
                    String userName = scanner.nextLine();

                    admin.approveLyricEditRequest(songName, true, userName);
                    break;
                case 3:
                    admin.displayApprovedArtists();
                    break;
                case 4:
                    admin.displayEditRequests();
                    break;
                case 5:
                    System.out.println("Logging out...");
                    admin.logout();
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
