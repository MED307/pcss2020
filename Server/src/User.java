import java.util.Random;

public class User {

  // An attribute that stores the username of the user
  private String username;

  // An attribute that stores the password of the user
  private String password;

  // An attribute that stores the id of the user
  private String id;

    // A constructor the creates a User object with a specified username and password
    public User(String username, String password){

        this.username = username;
        this.password = password;

        // A random integer is assigned as the id
        Random cd = new Random();
        id = Integer.toString(cd.nextInt());
    }

    // a method that return the username of the user
    public String getUsername() {
        return username;
    }

    // a method that changes the username of the user
    public void setUsername(String username) {
        this.username = username;
    }

    // a method that returns the password
    public String getPassword() {
        return password;
    }

    // a method that changes the password of the user
    public void setPassword(String password) {
        this.password = password;
    }

    // a method that returns that id of the user
    public String getId() {
        return id;
    }

    // A method that changes the id of the user
    public void setId(String id) {
        this.id = id;
    }

}