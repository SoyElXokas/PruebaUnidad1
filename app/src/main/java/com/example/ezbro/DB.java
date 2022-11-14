package com.example.ezbro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DB extends SQLiteOpenHelper
{

    public static final String DBNAME = "Login.db";

    public DB(@Nullable Context context) {
        super(context,"Login.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase MyDB)
    {
        MyDB.execSQL("create Table usuario(username TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion)
    {
        MyDB.execSQL("drop Table if exists usuario");
    }

    public Boolean insertarDatos(String username, String password)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long resultado = MyDB.insert("usuario", null, contentValues);
        if(resultado == -1) return false;
        else
            return true;
    }

    public Boolean chequeo (String username)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from usuario where username = ?", new String[]{username});
        if(cursor.getCount() > 0 )
            return true;
        else
            return false;
    }

    public Boolean chequeoPass (String username, String password)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[] {username, password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}
