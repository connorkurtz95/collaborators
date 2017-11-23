/**
 * Created by ckurtz8533 on 10/12/2017.
 */
package com.example.ckurtz8533.collaborators;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface CurrentUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void signIn(CurrentUser CurrentUser);

    @Query("select max(id) from currentUser")
    public int getId();

    @Query("delete from currentUser")
    void signOut();
}