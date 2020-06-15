package com.example.cheerupapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.example.cheerupapp.entities.DanceSong;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DanceSongDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = DanceSongDatabaseHelper.class.getName();

    private static DanceSongDatabaseHelper mInstance = null;
    private Context context;

    private static final String DATABASE_NAME = "danceSong.db";
    private static final Integer DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "danceSong";

    //creating constants for columns name of table (song)
    private static final String COL_ID = "ID";
    private static final String COL_NAME = "NAME";
    private static final String COL_FAVORITE_VERSE = "VERSE";
    private static final String COL_IMAGE = "IMAGE";
    private static final String COL_RATING = "RATING";
    private static final String COL_VOTES = "VOTES";
    private static final String COL_STARS = "STARS";

    private static final String CREATE_TABLE_STATEMENT = "CREATE TABLE" + " " + TABLE_NAME + "(" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_NAME + " TEXT, " +
            COL_FAVORITE_VERSE + " TEXT, " +
            COL_IMAGE + " TEXT, " +
            COL_RATING + "  INTEGER, " +
            COL_VOTES + " INTEGER DEFAULT 0, " +
            COL_STARS + " INTEGER DEFAULT 0 " +
            ")";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private static final String GET_ALL_STATEMENTS = "SELECT * FROM " + TABLE_NAME;
    private static final String GET_DANCE_SONG_BY_ID = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_ID + "= ?";
    private static final String UPDATE_DANCE_SONG_VOTES = "UPDATE " + TABLE_NAME + " SET " + COL_STARS + " = " + COL_STARS + " + ? " + ", " + COL_VOTES + " = " + COL_VOTES + " + 1" + " WHERE " + COL_ID + "= ?";

    // create this instance only when it is null
    // if it is not null, then it will created whatever has been created before
    // it will help to save the consumption of memory
    public static synchronized DanceSongDatabaseHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DanceSongDatabaseHelper(context.getApplicationContext());
        }
        return mInstance;
    }

    private DanceSongDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    // this method check if given database exists or not
    // so we should keep our SQL statements in this method
    @Override
    public void onCreate(SQLiteDatabase db) {
        // THE DATABASE AND TABLE INSIDE THE DATABASE WILL BE CREATED HERE
        db.execSQL(CREATE_TABLE_STATEMENT);
    }

    // this method executes, when the version of the database changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    // Add a Dance Song to the database
    public Long insert(String name, String favoriteVerse, Long rating) {
        // create an instance of SQLITE database
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valuesOfDanceSongDatabaseTable = new ContentValues();
        valuesOfDanceSongDatabaseTable.put(COL_NAME, name);
        valuesOfDanceSongDatabaseTable.put(COL_FAVORITE_VERSE, favoriteVerse);
        valuesOfDanceSongDatabaseTable.put(COL_IMAGE, getRandomDanceSongImageName());
        valuesOfDanceSongDatabaseTable.put(COL_RATING, rating);

        Long databaseContentResult = db.insert(TABLE_NAME, null, valuesOfDanceSongDatabaseTable);
        db.close();
        return databaseContentResult;
    }

    // get all dance songs in the table called danceSong
    private  Cursor getAll(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(GET_ALL_STATEMENTS, null);
    }

    // it will return if something is updated or not
    public boolean update(Long id, String name, String favoriteVerse, Long rating){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valuesOfDanceSongDatabaseTable = new ContentValues();
        valuesOfDanceSongDatabaseTable.put(COL_ID, id);
        valuesOfDanceSongDatabaseTable.put(COL_NAME, name);
        valuesOfDanceSongDatabaseTable.put(COL_FAVORITE_VERSE, favoriteVerse);
        valuesOfDanceSongDatabaseTable.put(COL_RATING, rating);

        int numOfRowsUpdated = db.update(TABLE_NAME, valuesOfDanceSongDatabaseTable, "ID = ?", new String[]{id.toString()});
        db.close();
        return  numOfRowsUpdated == 1; // condition evaluated if no. of rows updated is 1, it will return true else it is false
    }

    public boolean delete (Long id){
        SQLiteDatabase db = this.getWritableDatabase();
        int numOfRowsDeleted = db.delete(TABLE_NAME, "ID = ?", new String[]{id.toString()});
        db.close();
        return  numOfRowsDeleted == 1;
    }

    private String getRandomDanceSongImageName() {
        Random randomDanceSongImageName = new Random();
        int value = randomDanceSongImageName.nextInt(10) + 1;
        return "DanceSong_" + value;
    }

    // it returns a list of all dance songs from the database table called danceSong.db
    public List<DanceSong> getAllDanceSongs(){
        List<DanceSong> danceSongs = new ArrayList<>();
        Cursor cursor = getAll();

        if (cursor.getCount() > 0){
            DanceSong danceSong;

            while (cursor.moveToNext()){
                Long id = cursor.getLong(0);
                String name = cursor.getString(1);
                String favoriteVerse = cursor.getString(2);
                String imageFileName = cursor.getString(3);
                Long rating = cursor.getLong(4);
                Long votes = cursor.getLong(5);
                Long stars = cursor.getLong(6);

                danceSong = new DanceSong(id, name, favoriteVerse, imageFileName, rating, votes, stars);
                danceSongs.add(danceSong);
            }
        }
        cursor.close();
        return danceSongs;
    }



    /*public  DanceSong getDanceSong(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        DanceSong danceSong = null;
        Cursor cursor = db.rawQuery(GET_DANCE_SONG_BY_ID, new String[]{id.toString()});

        if (cursor.getCount() > 0){
            String name = cursor.getString(1);
            String favoriteVerse = cursor.getString(2);
            String image = cursor.getString(3);
            Long rating = Long.valueOf(cursor.getInt(4));
            Long votes = cursor.getLong(5);
            Long stars = cursor.getLong(6);

            danceSong = new DanceSong(id, name, favoriteVerse, image, rating, votes, stars);
        }
        cursor.close();
        return danceSong;
    }

    public boolean rateDanceSong(Long id, Integer stars){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(UPDATE_DANCE_SONG_VOTES, new String[ ]{stars.toString(), id.toString()});
        return true;
    }*/
}

