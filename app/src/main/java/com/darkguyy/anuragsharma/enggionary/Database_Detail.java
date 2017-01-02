package com.darkguyy.anuragsharma.enggionary;
import android.content.Context;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
/**
 * Created by anuragsharma on 16/05/16.
 */
public class Database_Detail extends SQLiteAssetHelper {

    private static final String DATABASE_NAMES = "quiz.db";

    private static final int DATABASE_VERSION = 1;

    public Database_Detail(Context context) {

        super(context, DATABASE_NAMES, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }
}
