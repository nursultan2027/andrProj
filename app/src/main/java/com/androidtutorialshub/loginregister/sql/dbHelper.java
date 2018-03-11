package com.androidtutorialshub.loginregister.sql;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.androidtutorialshub.loginregister.model.Post;

public class dbHelper extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "contactsManager";

    // Contacts table name
    private static final String TABLE_POST = "posts";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DIS = "dis";
    private static final String KEY_OWNER = "owner";

    public dbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_POST_TABLE = "CREATE TABLE " + TABLE_POST + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_DIS + " TEXT" + ")";
        db.execSQL(CREATE_POST_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POST);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    public void addPost(Post post) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, post.getName()); // Contact Name
        values.put(KEY_DIS, post.getPostDis()); // Contact Phone
        values.put(KEY_OWNER, post.getPostOwner()); // Contact Phone

        // Inserting Row
        db.insert(TABLE_POST, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    Post getPost(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_POST, new String[] { KEY_ID,
                        KEY_NAME, KEY_DIS,KEY_OWNER  }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Post contact = new Post(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3));
        // return contact
        return contact;
    }

    // Getting All Contacts
    public List<Post> getAllPosts() {
        List<Post> contactList = new ArrayList<Post>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_POST;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Post contact = new Post();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPostDis(cursor.getString(2));
                contact.setPostOwner(cursor.getString(3));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // Updating single contact
    public int updateContact(Post post) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, post.getName());
        values.put(KEY_DIS, post.getPostDis());
        values.put(KEY_OWNER, post.getPostOwner());
        // updating row
        return db.update(TABLE_POST, values, KEY_ID + " = ?",
                new String[] { String.valueOf(post.getId()) });
    }

    // Deleting single contact
    public void deletePost(Post post) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_POST, KEY_ID + " = ?",
                new String[] { String.valueOf(post.getId()) });
        db.close();
    }


    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_POST;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}
