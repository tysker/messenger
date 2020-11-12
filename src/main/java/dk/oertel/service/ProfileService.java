package dk.oertel.service;

import dk.oertel.database.DatabaseClass;
import dk.oertel.model.Profile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ProfileService {

    private static Map<String, Profile> profiles = DatabaseClass.getProfiles();

    public ProfileService(){}

    static {
        profiles.put("joerg", new Profile(1L, "joerg" , "Joerg", "Oertel"));
    }

    public List<Profile> getAllProfiles() {
        return new ArrayList<Profile>(profiles.values());
    }

    public Profile getProfile(String profilName) {
        return profiles.get(profilName);
    }

    public Profile addProfile(Profile profile) {
        profile.setId(profiles.size() + 1);
        profile.setCreated(new Date());
        profiles.put(profile.getProfileName(), profile);
        return profile;
    }

    public Profile updateProfile(Profile profile) {
        if(profile.getId() <= 0) {
            return null;
        }
        profiles.put(profile.getProfileName(), profile);
        return profile;
    }

    public Profile removeProfile(String profileName) {
        return profiles.remove(profileName);
    }
}
