package dk.oertel.database;

import dk.oertel.model.Message;
import dk.oertel.model.Profile;

import java.util.HashMap;
import java.util.Map;

public class DatabaseClass {

    /**
     * Contains all messages of the api
     */
    private static Map<Long, Message> messages = new HashMap<>();

    /**
     * Contains all profiles of the api
     * Profile Name is the key of the Map()
     */
    private static Map<String, Profile> profiles = new HashMap<>();

    public static Map<Long, Message> getMessages() {
        return messages;
    }

    public static Map<String, Profile> getProfiles() {
        return profiles;
    }
}
