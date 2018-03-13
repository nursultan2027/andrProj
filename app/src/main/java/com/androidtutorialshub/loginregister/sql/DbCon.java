package com.androidtutorialshub.loginregister.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLDataException;


public class DbCon {

    Context myContext;
    SQLiteDatabase db;
    DbHelper dbHelper;


    public DbCon(Context c) {
        myContext = c;
    }

    public DbCon open() {
        try {
            dbHelper = new DbHelper(myContext);
            db = dbHelper.getWritableDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return this;
        }
    }

    public void close() {
        db.close();
    }

    public void insert(String name, String surname, String phone) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.NAME, name);
        values.put(dbHelper.SURNAME, surname);
        values.put(dbHelper.PHONE, phone);
        db.insert(dbHelper.TABLE_NAME, null, values);
    }

    public Cursor readAll() {
        String[] columns = new String[]{dbHelper.ID, dbHelper.NAME, dbHelper.SURNAME, dbHelper.PHONE};
        Cursor c = db.query(dbHelper.TABLE_NAME, columns, null, null, null, null, dbHelper.ID + " desc");
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selected(long id) {
        String[] columns = new String[]{dbHelper.ID, dbHelper.NAME, dbHelper.SURNAME, dbHelper.PHONE};
        Cursor c = db.query(dbHelper.TABLE_NAME, columns, dbHelper.ID + "=" + id, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public void delete(long id) {
        open();
        db.delete(dbHelper.TABLE_NAME, dbHelper.ID + "=" + id, null);
        close();
    }

    public void update(long id, String name, String surname, String phone) {
        open();
        ContentValues values = new ContentValues();
        values.put(dbHelper.NAME, name);
        values.put(dbHelper.SURNAME, surname);
        values.put(dbHelper.PHONE, phone);
        db.update(dbHelper.TABLE_NAME, values, dbHelper.ID + "=" + id, null);
        close();
    }

    public class DbHelper extends SQLiteOpenHelper {

        public static final String DB_NAME = "lesson1.db";
        public static final String TABLE_NAME = "example1";
        public static final String ID = "_id";
        public static final String NAME = "name";
        public static final String SURNAME = "surname";
        public static final String PHONE = "phone";
        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + NAME + " TEXT , " + SURNAME + " TEXT, " + PHONE + ");";
        public static final int VERSION = 1;


        public DbHelper(Context context) {
            super(context, DB_NAME, null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

}
