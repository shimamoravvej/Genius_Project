public class Comment {
    private String text;  // comment song
    private int likes;    // like number

    public Comment(String text) {
        this.text = text;
        this.likes = 0;
    }

    public String getText() {
        return text;
    }

    public int getLikes() {
        return likes;
    }

    // like the comment
    public void like() {
        likes++;
        System.out.println("Your like has been added to this comment. Total likes: " + likes);
    }

    // display the comment
    public void displayComment() {
        System.out.println("Comment: " + text + " | Likes: " + likes);
    }
}
