package application.dataTypes;

import java.io.Serializable;

public class ChatMessage implements Serializable{
      /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String message;

    private String userID;

    private String roomID;


    public ChatMessage(String msg, String userID, String roomID)
    {
        this.message = msg;
        this.userID = userID;
        this.setRoomID(roomID);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUser() {
        return userID;
    }

    public void setUser(String userID) {
        this.userID = userID;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }
    
    @Override
    public boolean equals(Object m)
    {
    	if(m instanceof ChatMessage)
    	{
    		if (((ChatMessage)m).message.compareTo(this.message) == 0 && ((ChatMessage)m).roomID.compareTo(this.roomID) == 0 && ((ChatMessage)m).userID.compareTo(this.userID) == 0)
    		{
    			return true;
    		}
    		else
    		{
    			return false;
    		}
    		
    	}
    	else
    	{
    		return false;
    	}
		
    }


}