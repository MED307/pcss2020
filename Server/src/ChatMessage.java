
import java.io.Serializable;

public class ChatMessage implements Serializable{
	  /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String message;

    private User user;

    private int roomID;


    public ChatMessage(String msg, User user, int roomID)
    {
        this.message = msg;
        this.user = user;
        this.setRoomID(roomID);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }


}
