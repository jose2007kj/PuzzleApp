package com.slideingpuzzle.puzzleapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper  extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME ="puzzleApp.db";
    static final String TABLE_NAME = "users";
    static final String USERNAME_COL = "username";
    static final String DURATION_COL = "duration";
    static final String DATE_COL ="date";
    static final String LEVEL_COL = "level";
    static final String IMAGE_NAME_COL = "image";
    static final String ID_COL = "_id";

    public DBHelper(Context context){
        super(context,DATABASE_NAME, null, DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table = " create table " + TABLE_NAME
                + "("
                + ID_COL + " integer primary key autoincrement,"
                + USERNAME_COL + " text default 'unknown' ,"
                + DURATION_COL + " integer default 0,"
                + LEVEL_COL + " integer default 1,"//level = 1,2,3
                + DATE_COL  + " timestamp default CURRENT_TIMESTAMP,"
                + IMAGE_NAME_COL + " text default 'c'"
                + ")";
        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " +  TABLE_NAME);
        onCreate(db);
    }

    public boolean insertPlayer(String usr,int duration, int level , String image_name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERNAME_COL, usr);
        values.put(DURATION_COL, duration);
        values.put(LEVEL_COL, level);
        values.put(IMAGE_NAME_COL, image_name);

        db.insert(TABLE_NAME, null, values);
        return true;
    }

    public Cursor getPlayer(String usr){
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlStr = "select * from " + TABLE_NAME + " where "
                + USERNAME_COL + " = " + " ' " + usr + " ' "
                + " order by " + ID_COL + " desc";
        Cursor cursor = db.rawQuery(sqlStr, null);
        return cursor;
    }

    public Cursor getPlayerAtLevel(String  usr, int level){
        return  null;
    }


    public Cursor getAllPlayers() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlstr = "select * from "
                + TABLE_NAME
                + " order by " + DURATION_COL + " asc, "
                + USERNAME_COL + " asc";
        return db.rawQuery(sqlstr,null);
    }

    public Cursor getALLPlayersAtLevel(int level){
        return  null;
    }




}
