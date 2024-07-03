package com.example.exemplo_sqlite02.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.exemplo_sqlite02.Entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAO
{
    private SQLiteDatabase db;

    public UserDAO(SQLiteDatabase db)
    {
        this.db = db;
    }

    public long insertUser(User user)
    {
        if(db!=null)
        {
            ContentValues values = new ContentValues();

            values.put(UserContract.COLUMN_NAME, user.getName());

            values.put(UserContract.COLUMN_AGE, user.getAge());

            long id = db.insert(UserContract.TABLE_NAME,null,values);

            return id;
        }else
        {
            return -1;
        }
    }
    public List<User> getAll()
    {
        if(db!=null)
        {
            List<User> users = new ArrayList<>();

            String[] columns = {UserContract._ID, UserContract.COLUMN_NAME, UserContract.COLUMN_AGE};

            Cursor cursor = db.query(UserContract.TABLE_NAME, columns,
                    null, null, null, null,
                    null);

            while (cursor.moveToNext())
            {
                int id = cursor.getColumnIndex(UserContract._ID);

                long id_user = cursor.getLong(id);

                int name = cursor.getColumnIndex(UserContract.COLUMN_NAME);

                String name_user = cursor.getString(name);

                int age = cursor.getColumnIndex(UserContract.COLUMN_AGE);

                int age_user = cursor.getInt(age);

                users.add(new User(name_user, age_user));
            }

            cursor.close();

            return users;

        } else
        {
            return null;
        }
    }
}





