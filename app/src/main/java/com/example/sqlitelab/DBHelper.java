package com.example.sqlitelab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "notesDB";
    private static final int DATABASE_VERSION = 1;

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
        db.execSQL(Note.CREATE_TABLE);
    }


    //triggered when version is changed
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Note.TABLE_NAME);

        onCreate(db);
    }


    public void addNote(Note note){
        //get writable databse as we want to write data
        SQLiteDatabase db =  getWritableDatabase();

        ContentValues cv = new ContentValues();
        //id and time stamps are auto inserted

        cv.put(Note.COLUMN_TITLE, note.getTitle());
        cv.put(Note.COLUMN_DESCRIPTION, note.getDescription());

        db.insert(Note.TABLE_NAME, null, cv);
        db.close();

    }

    public String databasetoString(){
        String dbString="";

        SQLiteDatabase db =  getWritableDatabase();

        String query = "SELECT * FROM "+ Note.TABLE_NAME+ " WHERE 1";

        Cursor c = db.rawQuery(query, null);

        c.moveToFirst();

        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex(Note.COLUMN_TITLE)) != null &&
                 c.getString(c.getColumnIndex(Note.COLUMN_DESCRIPTION))!= null){
                dbString += c.getString(c.getColumnIndex(Note.COLUMN_TITLE));
                dbString += "\n";
                dbString += c.getString(c.getColumnIndex(Note.COLUMN_DESCRIPTION));

            }
            c.moveToNext();
        }

        db.close();
        return dbString;
    }

}
