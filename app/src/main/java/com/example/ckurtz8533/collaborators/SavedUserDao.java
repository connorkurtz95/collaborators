/**
 * Created by ckurtz8533 on 10/12/2017.
 */
package com.example.ckurtz8533.collaborators;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface SavedUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addSavedUser(SavedUser SavedUser);

    @Query("select * from savedUser")
    public List<SavedUser> getSavedUsers();

    @Query("select * from savedUser where username = :username")
    public List<SavedUser> getSavedUser(String username);

    @Query("delete from savedUser where username = :username")
    void removeSavedUser(String username);

    @Query("select count(id) from savedUser")
    public int savedUserCount();

    @Query("delete from savedUser")
    void removeAllSavedUsers();
}