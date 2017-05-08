package com.udacity.gradle.builditbigger.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    private static final String JOKE_KEY = JokeActivity.class.getCanonicalName() + ".JOKE_KEY";

    public static Intent createCallingIntent(Context context, String joke) {
        Intent callingIntent = new Intent(context, JokeActivity.class);
        callingIntent.putExtra(JOKE_KEY, joke);
        return callingIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        Intent callingIntent = getIntent();
        if (callingIntent.hasExtra(JOKE_KEY)) {
            TextView jokeTextView = (TextView) findViewById(R.id.joke_textview);
            jokeTextView.setText(callingIntent.getStringExtra(JOKE_KEY));
        }
    }
}
