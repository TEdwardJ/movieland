package edu.eteslenko.movieland.web;

import edu.eteslenko.movieland.entity.User;

public class UserHolder {

    private static final ThreadLocal<User> CURRENT_USER = new ThreadLocal<>();

    public static void setCurrentUser(User user){
        CURRENT_USER.set(user);
   };

    public static User getCurrentUser() {
        return CURRENT_USER.get();
    }
}
