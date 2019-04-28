package com.example.sqlitelab;

public class Note {
    private int id;
    private String title, description;

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


    public Note(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
