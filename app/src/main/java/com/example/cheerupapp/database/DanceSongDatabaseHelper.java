package com.example.cheerupapp.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cheerupapp.entities.DanceSong;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// database helper class
public class DanceSongDatabaseHelper extends SQLiteOpenHelper {

    // this is the instance which all objects of this databases will share
    private static DanceSongDatabaseHelper danceSongInstance = null;
    private Context context;

    //creating constants for database
    private static final String DATABASE_NAME = "danceSong.db";
    private static final Integer DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "danceSong";

    //creating constants for columns name of table (song)
    private static final String COL_ID = "ID";
    private static final String COL_NAME = "NAME";
    private static final String COL_FAVORITE_VERSE = "VERSE";
    private static final String COL_SINGER = "SINGER";
    private static final String COL_IMAGE = "IMAGE";
    private static final String COL_RATING = "RATING";

    //Creating sql statements initial version
    private static final String CREATE_TABLE_STRING = "CREATE TABLE " + TABLE_NAME + "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " TEXT, " +
                COL_FAVORITE_VERSE + " TEXT, " +
                COL_SINGER + " TEXT DEFAULT 'Sonu Nigum'," +
                COL_IMAGE + " TEXT, " +
                COL_RATING + " INTEGER )";

    // if table doesn't exists it will give an error and delete that table only if it exists in database
    private static final String DROP_TABLE_STRING = "DROP TABLE IF EXISTS " + TABLE_NAME;
    // this will return all the data from the database
    private static final String GET_ALL_STRING = "SELECT * FROM " + TABLE_NAME;

    // this method is use to avoiding creating large number of instance of Song
    public static synchronized DanceSongDatabaseHelper getInstance(Context context){
        //this is executed only for the first time
        if (danceSongInstance == null) {
            danceSongInstance = new DanceSongDatabaseHelper(context.getApplicationContext());
        }
        //when Song is not null anymore
        return danceSongInstance;
    }
    private DanceSongDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    // this method is executed only if the database does not exists
    @Override
    public void onCreate(SQLiteDatabase sqLitedb) {
        sqLitedb.execSQL(CREATE_TABLE_STRING);
    }

    // this method will executed every time we chase the database version
    @Override
    public void onUpgrade(SQLiteDatabase sqLitedb, int oldVersion, int newVersion) {
        sqLitedb.execSQL(DROP_TABLE_STRING);
        onCreate(sqLitedb);
    }

    // INSERT METHOD
    public Long insert(String name, String favoriteVerse, Long rating){
        // creating an instance of SQLITE database
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, name);
        contentValues.put(COL_FAVORITE_VERSE, favoriteVerse);
        contentValues.put(COL_RATING, rating);
        contentValues.put(COL_IMAGE, getRandomImageName());

        long result = db.insert(TABLE_NAME, null, contentValues);
        db.close();
        // insert will not be performed due to error, if result is -1
        return result;
    }

    // UPDATE METHOD
    public boolean update(Long id, String name, String favoriteVerse, Long rating){
        // creating an instance of SQLITE database
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID, id);
        contentValues.put(COL_NAME, name);
        contentValues.put(COL_FAVORITE_VERSE, favoriteVerse);
        contentValues.put(COL_RATING, rating);

        int updatedNumOfRows = db.update(TABLE_NAME, contentValues, "ID=?", new String[]{id.toString()});
        db.close();
        return updatedNumOfRows == 1;
    }

    public boolean delete(Long id){
        // creating an instance of SQLITE database
        SQLiteDatabase db = this.getWritableDatabase();
        int deletedNumOfRows = db.delete(TABLE_NAME, "ID=?", new String[]{id.toString()});
        db.close();
        return deletedNumOfRows == 1;
    }

    // using random function
    private String getRandomImageName(){
        Random randomImage = new Random();
        int imageNum = randomImage.nextInt(10) + 1;
        return  "Song _" + imageNum;
    }

    // returning the list all songs from the SONG database table
    public List<DanceSong> getDanceSongs(){
        List<DanceSong> danceSongs = new ArrayList<>();
        Cursor cursor = getAll();

        if (cursor.getCount() > 0){
            DanceSong danceSong;
            while (cursor.moveToNext()){
                Long id = cursor.getLong(0);
                String name = cursor.getString(1);
                String favoriteVerse = cursor.getString(2);
                String singer = cursor.getString(3);
                String imageName = cursor.getString(4);
                Long rating = cursor.getLong(5);

                danceSong = new DanceSong(id, name, favoriteVerse, singer, imageName, rating);
                danceSongs.add(danceSong);
            }
        }
        cursor.close();
        return danceSongs;
    }

    //this method returns a cursor of all songs in the 'Song' table
    private Cursor getAll(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(GET_ALL_STRING, null);
    }
}
