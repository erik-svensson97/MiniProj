package Model;

public class Message {
    //This class represents the transferable object we need to send between clients in order for the chat to work
    public Message(String sender, String receiver, String messageText){

    }

    public String getSender(){
        return "I don't know if we actually need a sender but fuck it, its here anyway";
    }
    public String getReceiver(){
        return "getReceiver from db later";
    }
}
