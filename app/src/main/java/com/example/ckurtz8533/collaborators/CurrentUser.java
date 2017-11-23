package com.example.ckurtz8533.collaborators;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by vogella on 06.09.17.
 */
@Entity
public class CurrentUser {
    @PrimaryKey
    public final int id;

    public CurrentUser(int id) {
        this.id = id;
    }
}