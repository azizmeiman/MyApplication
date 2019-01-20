package com.example.abdulaziz.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBConnections extends SQLiteOpenHelper {

    public static final String dbname = "SesonalEmploymentSystem.db";
    public static final int version = 1;

    public DBConnections(Context context) {

        super(context, dbname,null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Admin (AdminID INTEGER PRIMARY KEY, username TEXT, password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if EXISTS Admin");
        onCreate(db);
    }


    public void insertIntoAdmin (){
         int ID =1 ;
         String name = "youssef";
         String pass = "123";

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValue = new ContentValues();
        contentValue.put("AdminID",ID);
        contentValue.put("username",name);
        contentValue.put("password",pass);
        db.insert("Admin",null,contentValue);

    }


}
