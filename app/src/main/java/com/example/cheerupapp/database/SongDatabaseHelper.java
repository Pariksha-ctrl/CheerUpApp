package com.example.cheerupapp.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

// database helper class
public class SongDatabaseHelper extends SQLiteOpenHelper {

    // this is the instance which all the databases will share
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
            COL_SINGER + " TEXT, " +
            COL_IMAGE + " TEXT, " +
            COL_RATING + " INTEGER DEFAULT 0)";

    private static final String DROP_TABLE_;

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

    @Override
    public void onCreate(SQLiteDatabase sqLitedb) {
        sqLitedb.execSQL(CREATE_TABLE_STRING);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
