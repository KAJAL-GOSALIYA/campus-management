package com.android.collegemanagementsystem;

/**
 * Created by Dell on 02-07-2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MESSAGES_DB";

    private static final int DATABASE_VERSION = 1;
    //table name
    private static final String TABLE_MESSAGES = "table_messages";
    //columns name-TABLE_MESSAGES
    private static final String KEY_ID = "id";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PERSON_NAME = "name";
    private static final String KEY_MOBILE = "number";

    private static final String CREATE_TABLE_MESSAGES = " CREATE TABLE IF NOT EXISTS "
            + TABLE_MESSAGES + " ( "
            + KEY_ID + " INTEGER (100) PRIMARY KEY , "
            + KEY_PERSON_NAME + " VARCHAR (500) , "
            + KEY_MOBILE + " VARCHAR (500) , "
            + KEY_EMAIL + " VARCHAR (40) "
            + " ) ";

    public DBHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MESSAGES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_MESSAGES);
    }

    public void insertMessages(MessageModel messageModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID,messageModel.getMessageID());
        values.put(KEY_EMAIL,messageModel.getTextEmail());
        values.put(KEY_PERSON_NAME,messageModel.getTextName());
        values.put(KEY_MOBILE,messageModel.getTextNumber());

        db.insert(TABLE_MESSAGES,null,values);
        Log.d("DBHelper","Insert Messages Query Executed");
    }

    public ArrayList<MessageModel> readAllMessages(){
        ArrayList<MessageModel> temp = new ArrayList<>();

        String selectQuery = "SELECT * FROM "+TABLE_MESSAGES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery,null);

        if(c.moveToFirst()){

            do{
                MessageModel tempModel = new MessageModel();
                tempModel.setMessageID(c.getInt(c.getColumnIndex(KEY_ID)));
                tempModel.setTextName(c.getString(c.getColumnIndex(KEY_PERSON_NAME)));
                tempModel.setTextNumber(c.getString(c.getColumnIndex(KEY_MOBILE)));
                tempModel.setTextEmail(c.getString(c.getColumnIndex(KEY_EMAIL)));
                temp.add(tempModel);
            }while(c.moveToNext());
        }

        Log.d("DBHelper","Fetching Messages Size: "+temp.size());
        return temp;
    }
}
