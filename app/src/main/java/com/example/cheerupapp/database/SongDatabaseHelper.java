package com.example.cheerupapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;

// database helper class
public class SongDatabaseHelper extends SQLiteOpenHelper {

    // this is the instance which all objects of this databases will share
    private static SongDatabaseHelper songInstance = null;
    private Context context;

    //creating constants for database
    private static final String DATABASE_NAME = "song.db";
    private static final Integer DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "song";

    //creating constants for columns name of table (song)
    private static final String COL_ID = "ID";
    private static final String COL_NAME = "NAME";
    private static final String COL_FAVORITE_VERSE = "VERSE";
    private static final String COL_SINGER = "SINGER";
    private static final String COL_IMAGE = "IMAGE";
    private static final String COL_RATING = "RATING";

    //Creating sql statements initial version
    private static final String CREATE_TABLE_STRING = "CREATE TABLE" + TABLE_NAME + "(" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_NAME + " TEXT, " +
            COL_FAVORITE_VERSE + " TEXT, " +
            COL_SINGER + " TEXT DEFAULT 'Sonu Nigum'," +
            COL_IMAGE + " TEXT, " +
            COL_RATING + " INTEGER )";

    // if table doesn't exists it will give an error and delete that table only if it exists in database
    private static final String DROP_TABLE_STRING = "DROP_TABLE_IF_EXISTS, " + TABLE_NAME;
    // this will return all the data from the database
    private static final String GET_ALL_STRING = "SELECT * FROM " + TABLE_NAME;

    public SongDatabaseHelper(Context applicationContext) {
        super();
    }

    // this method is use to avoiding creating large number of instance of Song
    public static synchronized SongDatabaseHelper getInstance(Context context){
        if (songInstance == null){
            //this is executed only for the first time
            songInstance = new SongDatabaseHelper(context.getApplicationContext());
        }
        //when Song is not null anymore
        return songInstance;
    }


    public SongDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public SongDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public SongDatabaseHelper(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
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
    public List<Song> getSongs(){
        List<Song> songs = new ArrayList<>();
        Cursor cursor = getAll();

        if (cursor.getCount() > 0){
            Song song;
            while (cursor.moveToNext()){
                Long id = cursor.getLong(0);
                String name = cursor.getString(1);
                String favoriteVerse = cursor.getString(2);
                String singer = cursor.getString(3);
                String imageName = cursor.getString(4);
                Long rating = cursor.getLong(5);

                song = new Song(id, name, favoriteVerse, singer, imageName, rating);
                songs.add(song);
            }
        }
        cursor.close();
        return songs;
    }
}
