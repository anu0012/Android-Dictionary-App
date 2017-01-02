package com.darkguyy.anuragsharma.enggionary;
import android.content.Intent;

import android.graphics.Color;
import android.os.Bundle;

import android.support.v7.app.ActionBarActivity;

import android.text.Editable;

import android.text.TextWatcher;

import android.view.Menu;

import android.view.MenuItem;

import android.view.View;

import android.view.ViewGroup;
import android.widget.AdapterView;

import android.widget.ArrayAdapter;

import android.widget.EditText;

import android.widget.ListView;

import android.widget.TextView;

/**
 * Created by anuragsharma on 11/05/16.
 */
public class home extends ActionBarActivity {

    private EditText filterText;

    private ArrayAdapter<String> listAdapter;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        filterText = (EditText)findViewById(R.id.editText);

        final ListView itemList = (ListView)findViewById(R.id.listView);

        DatabaseActivity dbBackend = new DatabaseActivity(home.this);

        String[] terms = dbBackend.dictionaryWords();

        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, terms){

            @Override
            public View getView(int position, View convertView, ViewGroup parent){

                View view = super.getView(position, convertView, parent);

                TextView ListItemShow = (TextView) view.findViewById(android.R.id.text1);

                ListItemShow.setTextColor(Color.parseColor("#ffffff"));

                return view;
            }

        };

        itemList.setAdapter(listAdapter);

        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                //Toast.makeText(getApplicationContext(), "Position is "+id, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(home.this, DictionaryActivity.class);
                String obj = listAdapter.getItem(position);
               // intent.putExtra("DICTIONARY_ID", position);
                intent.putExtra("word",obj);

                startActivity(intent);

            }

        });

        filterText.addTextChangedListener(new TextWatcher() {

            @Override

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                home.this.listAdapter.getFilter().filter(s);

            }

            @Override

            public void afterTextChanged(Editable s) {

            }

        });

    }

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {

// Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_main, menu);

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

            //Intent intent=new Intent(this,myterm.class);
            //startActivity(intent);

            final String appPackageName = this.getPackageName();
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Check out Enggionary App at: https://play.google.com/store/apps/details?id=" + appPackageName);
            sendIntent.setType("text/plain");
            startActivity(sendIntent);

        }

        return super.onOptionsItemSelected(item);

    }


}
