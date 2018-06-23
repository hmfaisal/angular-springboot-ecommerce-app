package com.basketbird.backend.model;

import java.util.Date;

public class Session {

    private final User user;
    private final Date loggedIn;
    private final Date lastSeen;

    public Session(User user, Date loggedIn, Date lastSeen) {
        this.user = user;
        this.loggedIn = loggedIn;
        this.lastSeen = lastSeen;
    }

    public User getUser() {
        return user;
    }

    public Date getLoggedIn() {
        return loggedIn;
    }

    public Date getLastSeen() {
        return lastSeen;
    }
}
