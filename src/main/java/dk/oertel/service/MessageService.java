package dk.oertel.service;

import dk.oertel.database.DatabaseClass;
import dk.oertel.exception.DataNotFoundException;
import dk.oertel.model.Message;

import java.util.*;

public class MessageService {

    private static Map<Long, Message> messages = DatabaseClass.getMessages();

    public MessageService(){}

    static {
        messages.put(1L, new Message(1, "Hello World!", "Joerg"));
        messages.put(2L, new Message(2, "Hello Europe!", "Steve"));
    }

    public List<Message> getAllMessages() {
        return new ArrayList<Message>(messages.values());
    }

    public List<Message> getAllMessagesForYear(int year){
        List<Message> messagesForYear = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        for(Message message : messages.values()) {
            cal.setTime(message.getCreated());
            if(cal.get(Calendar.YEAR) == year) {
                messagesForYear.add(message);
            }
        }
        return messagesForYear;
    }

    public List<Message> getAllMessagesPaginated(int start, int size) {
        ArrayList<Message> list = new ArrayList<Message>(messages.values());
        return list.subList(start, start + size);
    }

    public Message getMessage(long id) {
        Message message = messages.get(id);
        if(message == null) {throw new DataNotFoundException("Message with id " + id + " not found."); }
        return message;
    }

    public Message addMessage(Message message) {
        message.setId(messages.size() + 1);
        message.setCreated(new Date());
        messages.put(message.getId(), message);
        return message;
    }

    public Message updateMessage(Message message) {
        if(message.getId() <= 0) {
            return null;
        }
        messages.put(message.getId(), message);
        return message;
    }

    public Message removeMessage(long id) {
        return messages.remove(id);
    }

}
