import java.util.Objects;
import java.util.ArrayList;
import java.util.List;

abstract class User {
    private final String username;
    private final String email;
    private final String password;
    private final String fullName;
    private final int age;
    private List<User> followers;  // followers list for followers management

    public User(String username, String email, String password, String fullName, int age) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.age = age;
        this.followers = new ArrayList<>();
    }

    // common methods for all users
    public abstract void login();  // method for user login
    public abstract void logout(); // method for user logout

    //adding followers
    public void addFollower(RegularUser user) {
        if (!followers.contains(user)) {
            followers.add(user);
            System.out.println(user.getUsername() + " started following " + getUsername());
        } else {
            System.out.println(user.getUsername() + " is already following " + getUsername());
        }
    }

    // delete followers
    public void removeFollower(RegularUser user) {
        if (followers.contains(user)) {
            followers.remove(user);
            System.out.println(user.getUsername() + " unfollowed " + getUsername());
        } else {
            System.out.println(user.getUsername() + " is not following " + getUsername());
        }
    }

    //send update to followers
    public void sendUpdatesToFollowers(String updateMessage) {
        if (!followers.isEmpty()) {
            for (User follower : followers) {
                follower.receiveUpdate(updateMessage);
            }
        }
    }

    // method for receive notification from admin
    public void receiveNotification(String message) {
        System.out.println("Notification for " + getUsername() + ": " + message);
    }

    // receive updates for regular users
    public void receiveUpdate(String updateMessage) {
        System.out.println("New update: " + updateMessage);
    }

    public String getRole() {
        return this.getClass().getSimpleName();
    }

    // general features
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public boolean checkPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    public String getFullName() {
        return fullName;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "User: " + fullName + " (" + username + "), Email: " + email + ", Age: " + age;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return username.equals(user.username) && email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email);
    }


}
