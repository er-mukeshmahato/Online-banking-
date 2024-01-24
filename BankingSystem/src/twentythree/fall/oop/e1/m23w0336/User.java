package twentythree.fall.oop.e1.m23w0336;

public class User {

    public int getUserId() {
        return userId;
    }

    private int userId;

    public String getUsername() {
        return username;
    }

    private String username;

    // Constructors, getters, and setters for User class

    // toString method for easy printing
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                '}';
    }

    public void setUserId(int userID) {
        userId=userID;
    }

    public void setUsername(String retrievedUsername) {
            username=retrievedUsername;
    }
}
