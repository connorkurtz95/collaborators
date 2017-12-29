package com.example.ckurtz8533.collaborators;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by vogella on 06.09.17.
 */
@Entity
public class Collaboration {
    @PrimaryKey
    public final int id;
    public int firstId;
    public int secondId;

    public Collaboration(int id, int firstId, int secondId) {
        this.id = id;
        this.firstId = firstId;
        this.secondId = secondId;
    }
}