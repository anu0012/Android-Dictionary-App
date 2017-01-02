package com.darkguyy.anuragsharma.enggionary;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
/**
 * Created by anuragsharma on 16/05/16.
 */
public class DatabaseObject {

    private static Database_Detail dbHelper;
    private  SQLiteDatabase db;

    public DatabaseObject(Context context) {

        dbHelper = new Database_Detail(context);

        this.db = dbHelper.getReadableDatabase();

    }

    public SQLiteDatabase getDbConnection(){

        return this.db;

    }

    public void closeDbConnection(){

        if(this.db != null){

            this.db.close();

        }
    }
}
