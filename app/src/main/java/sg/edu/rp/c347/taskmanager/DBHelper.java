package sg.edu.rp.c347.taskmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by 15017612 on 12/5/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "tasks.db";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_TASK = "task";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "task_name";
    private static final String COLUMN_DESCRIPTION = "task_description";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createNoteTableSql = "CREATE TABLE " + TABLE_TASK + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_DESCRIPTION + " TEXT)";
        db.execSQL(createNoteTableSql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(("ALTER TABLE " + TABLE_TASK + " ADD COLUMN module_name TEXT"));
        onCreate(db);
    }

    public long insertTask(String name, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DESCRIPTION, description);
        long result = db.insert(TABLE_TASK, null, values);
        db.close();
        Log.d("SQL Insert",""+ result); //id returned, shouldn’t be -1
        return result;
    }

    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> tasks = new ArrayList<Task>();

        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_NAME + "," + COLUMN_DESCRIPTION +  " FROM " + TABLE_TASK;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String description = cursor.getString(2);

                Task task1 = new Task(id, name, description);
                tasks.add(task1);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tasks;
    }

//    public int updateSong(Song data){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_TITLE, data.getTitle());
//        values.put(COLUMN_SINGER, data.getSinger());
//        values.put(COLUMN_YEAR, data.getYear());
//        values.put(COLUMN_STARS, data.getStars());
//        String condition = COLUMN_ID + "= ?";
//        String[] args = {String.valueOf(data.get_id())};
//        int result = db.update(TABLE_SONG, values, condition, args);
//        db.close();
//        return result;
//    }

//    public int deleteSong(int id){
//        SQLiteDatabase db = this.getWritableDatabase();
//        String condition = COLUMN_ID + "= ?";
//        String[] args = {String.valueOf(id)};
//        int result = db.delete(TABLE_SONG, condition, args);
//        db.close();
//        return result;
//    }

    // get songs with the specified year
//    public ArrayList<Song> getAllSongs(String keyword) {
//        ArrayList<Song> songs = new ArrayList<Song>();
//
//        SQLiteDatabase db = this.getReadableDatabase();
//        String[] columns= {COLUMN_ID, COLUMN_NOTE_CONTENT};
//        String condition = COLUMN_NOTE_CONTENT + " Like ?";
//        String[] args = { "%" +  keyword + "%"};
//        Cursor cursor = db.query(TABLE_NOTE, columns, condition, args,
//                null, null, null, null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                int id = cursor.getInt(0);
//                String noteContent = cursor.getString(1);
//                Song song = new Song(id, noteContent);
//                songs.add(song);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        db.close();
//        return songs;
//    }

    // get songs with the specified year
//    public ArrayList<Song> get5StarsSong() {
//        ArrayList<Song> songs = new ArrayList<Song>();
//
//        SQLiteDatabase db = this.getReadableDatabase();
//        String selectQuery = "SELECT " + COLUMN_ID + ","
//                + COLUMN_TITLE + "," + COLUMN_SINGER + "," + COLUMN_YEAR + "," + COLUMN_STARS +  " FROM " + TABLE_SONG
//                + " WHERE " + COLUMN_STARS + "= 5";
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                int id = cursor.getInt(0);
//                String title = cursor.getString(1);
//                String singer = cursor.getString(2);
//                int year = cursor.getInt(3);
//                int stars = cursor.getInt(4);
//
//                Song song1 = new Song(title, singer, year, stars, id);
//                songs.add(song1);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        db.close();
//        return songs;
//    }


    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }



}
