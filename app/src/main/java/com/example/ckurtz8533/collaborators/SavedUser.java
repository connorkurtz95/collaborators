package com.example.ckurtz8533.collaborators;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by vogella on 06.09.17.
 */
@Entity
public class SavedUser {
    @PrimaryKey
    public final int id;
    public String username;
    public String password;

    public SavedUser(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}