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
public interface CollaborationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addCollaboration(Collaboration collaboration);

    @Query("select * from collaboration where firstId = :userId or secondId = :userId")
    public List<Collaboration> getCollaborationsByUser(int userId);

    @Query("select * from user u join collaboration c on u.id = c.firstId or u.id = c.secondId " +
            "where (c.firstId = :userId or c.secondId = :userId) and u.id != :userId")
    public List<User> getCollaborationUsers(int userId);

    @Query("select * from collaboration where id = :id")
    public Collaboration getCollaboration(int id);

    @Query("delete from collaboration where id = :id")
    void removeCollaboration(int id);

    @Query("select max(id) from collaboration")
    public int collaborationMax();
}