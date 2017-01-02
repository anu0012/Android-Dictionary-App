package com.darkguyy.anuragsharma.enggionary;
import android.content.Context;

import android.database.Cursor;

import java.util.ArrayList;
/**
 * Created by anuragsharma on 16/05/16.
 */
public class DatabaseActivity extends DatabaseObject {

    public DatabaseActivity(Context context){
        super(context);
    }

    public String[] dictionaryWords(){

        String query = "Select * from dictionary";

        Cursor cursor = this.getDbConnection().rawQuery(query, null);

        ArrayList<String> wordTerms = new ArrayList<>();

        if(cursor.moveToFirst()){

            do{

                String word = cursor.getString(cursor.getColumnIndexOrThrow("word"));

                wordTerms.add(word);

            }while(cursor.moveToNext());

        }

        cursor.close();
        //closeDbConnection();
        String[] dictionaryWords = new String[wordTerms.size()];

        dictionaryWords = wordTerms.toArray(dictionaryWords);

        return dictionaryWords;

    }

    public enggionaryObject getQuizById(int quizId){

        enggionaryObject enggObject = null;

        String query = "select * from dictionary where _id = " + quizId;

        Cursor cursor = this.getDbConnection().rawQuery(query, null);

        if(cursor.moveToFirst()){

            do{

                String word = cursor.getString(cursor.getColumnIndexOrThrow("word"));

                String meaning = cursor.getString(cursor.getColumnIndexOrThrow("meaning"));

                enggObject = new enggionaryObject(word, meaning);

            }while(cursor.moveToNext());

        }

        cursor.close();

        return enggObject;

    }

    public enggionaryObject getQuizByWord(String wordy){

        enggionaryObject enggObject = null;

        String query = "select * from dictionary where word = \"" + wordy + "\"";

        Cursor cursor = this.getDbConnection().rawQuery(query, null);

        if(cursor.moveToFirst()){

            do{

                String word = cursor.getString(cursor.getColumnIndexOrThrow("word"));

                String meaning = cursor.getString(cursor.getColumnIndexOrThrow("meaning"));

                enggObject = new enggionaryObject(word, meaning);

            }while(cursor.moveToNext());

        }

        cursor.close();

        return enggObject;

    }


}
