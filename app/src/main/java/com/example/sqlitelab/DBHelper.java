package com.example.sqlitelab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "notesDB";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "tbl_notes";

    //for easy access and making it readable
    public static final String COLUMN_ID = "nId";
    public static final String COLUMN_TITLE = "nTitle";
    public static final String COLUMN_DESCRIPTION = "nDescription";

    //query to create table
    public static final String CREATE_TABLE =
            "CREATE TABLE "+TABLE_NAME+"("
                    +COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                    +COLUMN_TITLE+" TEXT,"
                    +COLUMN_DESCRIPTION+" TEXT"
                    +")";

    //aru kunai class bata object banauna paryo bhane
    public DBHelper(Context context) {
        //calls SQLiteOpenHelper class
        //name-database ko naam
        //cursorfactory retrieve value  default- null
        //version pachi SCHMEMA maa kei change garnu paryo bhane
//        super(context, name, factory, version);
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    //called when database is first created when the app is installed
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }


    //triggered when version is changed
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);
    }


    public void addNote(Note note){
        //get writable databse as we want to write data
        SQLiteDatabase db =  getWritableDatabase();

        ContentValues cv = new ContentValues();
        //id and time stamps are auto inserted

        cv.put(COLUMN_TITLE, note.getTitle());
        cv.put(COLUMN_DESCRIPTION, note.getDescription());

        db.insert(TABLE_NAME, null, cv);
        db.close();

    }

//    public String databasetoString(){
//        String dbString="";
//
//        SQLiteDatabase db =  getWritableDatabase();
//
//        String query = "SELECT * FROM "+ TABLE_NAME+ " WHERE 1";
//
//        Cursor c = db.rawQuery(query, null);
//
//        c.moveToFirst();
//
//        while(!c.isAfterLast()){
//            if(c.getString(c.getColumnIndex(COLUMN_TITLE)) != null &&
//                 c.getString(c.getColumnIndex(COLUMN_DESCRIPTION))!= null){
//                dbString += c.getString(c.getColumnIndex(COLUMN_TITLE));
//                dbString += "\n";
//                dbString += c.getString(c.getColumnIndex(COLUMN_DESCRIPTION));
//                dbString += "\n";
//                dbString += "\n";
//
//            }
//            c.moveToNext();
//        }
//
//        db.close();
//        return dbString;
//    }


    public List<Note> databaseToString(){
        List<Note> notes = new ArrayList<>();

        SQLiteDatabase db =  getWritableDatabase();

        String query = "SELECT * FROM "+ TABLE_NAME+ " WHERE 1";

        Cursor c = db.rawQuery(query, null);

        c.moveToFirst();

        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex(COLUMN_TITLE)) != null &&
                 c.getString(c.getColumnIndex(COLUMN_DESCRIPTION))!= null){
                Note note = new Note();
                note.setTitle(c.getString(c.getColumnIndex(COLUMN_TITLE)));
                note.setDescription(c.getString(c.getColumnIndex(COLUMN_DESCRIPTION)));

                notes.add(note);

            }
            c.moveToNext();
        }

        db.close();
        return notes;
    }

}
