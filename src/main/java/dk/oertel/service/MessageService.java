package dk.oertel.service;

import dk.oertel.database.DatabaseClass;
import dk.oertel.model.Message;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    public Message getMessage(long id) {
        return messages.get(id);
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
