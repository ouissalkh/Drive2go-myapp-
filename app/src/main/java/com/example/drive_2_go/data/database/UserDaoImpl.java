package com.example.drive_2_go.data.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.drive_2_go.data.dao.UserDao;
import com.example.drive_2_go.data.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private final AppDatabase database;

    public UserDaoImpl(AppDatabase database) {
        this.database = database;
    }

    @Override
    public void insert(User user) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", user.name);
        values.put("email", user.email);

        db.insert("users", null, values);
        db.close();
    }


    public void update(User user) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", user.name);
        values.put("email", user.email);

        db.update("users", values, "id=?", new String[]{String.valueOf(user.id)});
        db.close();
    }


    public void delete(int id) {
        SQLiteDatabase db = database.getWritableDatabase();
        db.delete("users", "id=?", new String[]{String.valueOf(id)});
        db.close();
    }

    // Nouvelle version avec LiveData
    @Override
    public LiveData<List<User>> getAllUsers() {
        MutableLiveData<List<User>> liveData = new MutableLiveData<>();

        new Thread(() -> {
            List<User> users = new ArrayList<>();
            SQLiteDatabase db = database.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM users", null);

            if (cursor.moveToFirst()) {
                do {
                    User user = new User(
                            cursor.getString(cursor.getColumnIndexOrThrow("name")),
                            cursor.getString(cursor.getColumnIndexOrThrow("email"))
                    );
                    user.id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                    users.add(user);
                } while (cursor.moveToNext());
            }

            cursor.close();
            db.close();

            liveData.postValue(users);
        }).start();

        return liveData;
    }

    @Override
    public LiveData<Integer> getUsersCount() {
        MutableLiveData<Integer> liveData = new MutableLiveData<>();

        new Thread(() -> {
            int count = 0;
            SQLiteDatabase db = database.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT COUNT(*) AS total FROM users", null);

            if (cursor.moveToFirst()) {
                count = cursor.getInt(cursor.getColumnIndexOrThrow("total"));
            }

            cursor.close();
            db.close();

            liveData.postValue(count);
        }).start();

        return liveData;
    }
}
