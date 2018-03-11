package com.androidtutorialshub.loginregister.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.androidtutorialshub.loginregister.model.Post;

import java.util.ArrayList;
import java.util.List;


public class dbHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "UserManager.db";

    // User table name
    private static final String TABLE_POST = "post";

    // User Table Columns names
    private static final String COLUMN_POST_ID = "post_id";
    private static final String COLUMN_POST_NAME = "post_name";
    private static final String COLUMN_POST_DIS = "post_dis";
    private static final String COLUMN_POST_OWNER = "post_owner";

    // create table sql query
    private String CREATE_POST_TABLE = "CREATE TABLE " + TABLE_POST + "("
            + COLUMN_POST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_POST_NAME + " TEXT,"
            + COLUMN_POST_DIS + " TEXT," + COLUMN_POST_OWNER + " TEXT" + ")";

    // drop table sql query
    private String DROP_POST_TABLE = "DROP TABLE IF EXISTS " + TABLE_POST;

    public dbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_POST_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL(DROP_POST_TABLE);

        // Create tables again
        onCreate(db);

    }


    public void addPost(Post post) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_POST_NAME, post.getName());
        values.put(COLUMN_POST_DIS, post.getPostDis());
        values.put(COLUMN_POST_OWNER, post.getPostOwner());

        // Inserting Row
        db.insert(TABLE_POST, null, values);
        db.close();
    }

    public List<Post> getAllPost() {
        String[] columns = {
                COLUMN_POST_ID,
                COLUMN_POST_NAME,
                COLUMN_POST_DIS,
                COLUMN_POST_OWNER
        };
        String sortOrder =
                COLUMN_POST_NAME + " ASC";
        List<Post> postList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_POST, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order

        if (cursor.moveToFirst()) {
            do {
                Post post = new Post();
                post.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_POST_ID))));
                post.setName(cursor.getString(cursor.getColumnIndex(COLUMN_POST_NAME)));
                post.setPostDis(cursor.getString(cursor.getColumnIndex(COLUMN_POST_DIS)));
                post.setPostOwner(cursor.getString(cursor.getColumnIndex(COLUMN_POST_OWNER)));
                // Adding user record to list
                postList.add(post);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return postList;
    }
    public void updatePost(Post post) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_POST_NAME, post.getName());
        values.put(COLUMN_POST_DIS, post.getPostDis());
        values.put(COLUMN_POST_OWNER, post.getPostOwner());

        // updating row
        db.update(TABLE_POST, values, COLUMN_POST_ID + " = ?",
                new String[]{String.valueOf(post.getId())});
        db.close();
    }

    public void deletePost(Post post) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_POST, COLUMN_POST_ID + " = ?",
                new String[]{String.valueOf(post.getId())});
        db.close();
    }
}
