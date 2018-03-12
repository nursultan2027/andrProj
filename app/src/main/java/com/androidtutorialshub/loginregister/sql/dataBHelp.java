package com.androidtutorialshub.loginregister.sql;


import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;

import com.androidtutorialshub.loginregister.model.Post;

public class dataBHelp extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "userstore.db"; // название бд
    private static final int SCHEMA = 1; // версия базы данных
    public static final String TABLE = "post"; // название таблицы в бд
    // названия столбцов
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DIS = "dis";
    public static final String COLUMN_OWNER= "owner";

    public dataBHelp(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE users (" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME
                + " TEXT, " + COLUMN_DIS + " TEXT, " + COLUMN_OWNER + " TEXT);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }

    public void addPost(Post post) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, post.getName()); // Contact Name
        values.put(COLUMN_DIS, post.getPostDis()); // Contact Phone
        values.put(COLUMN_DIS, post.getPostOwner()); // Contact Phone
        // Inserting Row
        db.insert("post", null, values);
        db.close(); // Closing database connection
    }
}