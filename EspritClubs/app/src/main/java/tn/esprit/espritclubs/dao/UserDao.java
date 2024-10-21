package tn.esprit.espritclubs.dao;

import androidx.room.*;

import java.util.List;

import tn.esprit.espritclubs.entities.User;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    User findByName(String first, String last);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);

    @Insert
    void insertUser(User user);

    @Update
    void updateUser(User user);

    @Query("SELECT * FROM user WHERE email = :email")
    User getUserByEmail(String email);

    @Query("SELECT * FROM user WHERE email = :email AND password = :password")
    User getUserByEmailAndPassword(String email, String password);

    @Query("SELECT * FROM user WHERE uid = :uid")
    User getUserByUid(int uid);

}
