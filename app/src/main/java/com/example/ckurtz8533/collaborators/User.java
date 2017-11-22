package com.example.ckurtz8533.collaborators;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by vogella on 06.09.17.
 */
@Entity
public class User {
    @PrimaryKey
    public final int id;
    public String username;
    public String password;
    public String screenName;
    public String email;

    public User(int id, String username, String password,
                String screenName, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.screenName = screenName;
        this.email = email;
    }
}