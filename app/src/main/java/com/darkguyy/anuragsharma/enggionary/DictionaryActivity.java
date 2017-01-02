package com.darkguyy.anuragsharma.enggionary;
import android.annotation.TargetApi;

import android.content.Intent;

import android.os.Build;

import android.os.Bundle;

import android.speech.tts.TextToSpeech;

import android.support.v7.app.ActionBarActivity;

import android.view.Menu;

import android.view.MenuItem;

import android.view.View;

import android.widget.Button;

import android.widget.TextView;

import java.util.Locale;

public class DictionaryActivity extends ActionBarActivity {

    private TextView wordMeaning;
    private TextView word;

    private TextToSpeech convertToSpeech;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dictionary);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        //int dictionaryId = bundle.getInt("DICTIONARY_ID");
        String temp = bundle.getString("word");
        //int id = dictionaryId + 1;

        word = (TextView) findViewById(R.id.word);

        wordMeaning = (TextView) findViewById(R.id.dictionary);

        Button textToSpeech = (Button) findViewById(R.id.button);

        DatabaseActivity dbBackend = new DatabaseActivity(DictionaryActivity.this);

        //enggionaryObject allQuizQuestions = dbBackend.getQuizById(id);

        enggionaryObject allQuizQuestions = dbBackend.getQuizByWord(temp);

        word.setText(allQuizQuestions.getWord());

        wordMeaning.setText(allQuizQuestions.getDefinition());

        textToSpeech.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                final String convertTextToSpeech = wordMeaning.getText().toString();

                convertToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {

                    @TargetApi(Build.VERSION_CODES.LOLLIPOP)

                    @Override

                    public void onInit(int status) {

                        if (status != TextToSpeech.ERROR) {

                            convertToSpeech.setLanguage(Locale.US);

                            convertToSpeech.speak(convertTextToSpeech, TextToSpeech.QUEUE_FLUSH, null, null);

                        }

                    }

                });

            }

        });

    }

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {

// Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_dictionary, menu);

        return true;

    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {

// Handle action bar item clicks here. The action bar will

// automatically handle clicks on the Home/Up button, so long

// as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

//noinspection SimplifiableIfStatement

        if (id == R.id.action_settings) {

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, word.getText().toString() + "\n" + "MEANING : " + wordMeaning.getText().toString());
            sendIntent.setType("text/plain");
            startActivity(sendIntent);

        }

        return super.onOptionsItemSelected(item);

    }

    @Override

    protected void onPause() {

        if (convertToSpeech != null) {

            convertToSpeech.stop();

            convertToSpeech.shutdown();

        }

        super.onPause();

    }
}