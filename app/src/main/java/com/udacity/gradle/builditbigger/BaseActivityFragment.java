package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.udacity.gradle.builditbigger.ui.JokeActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public abstract class BaseActivityFragment extends Fragment implements RetrieveJokeAsyncTask.OnJokeRetrieved {

    protected Button jokeButton;
    protected ProgressBar progressBar;

    public BaseActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        jokeButton = (Button) root.findViewById(R.id.joke_button);
        progressBar = (ProgressBar) root.findViewById(R.id.progressBar);

        jokeButton.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);
            retrieveJoke();
        });

        return root;
    }

    protected void retrieveJoke() {
        RetrieveJokeAsyncTask jokeTask = new RetrieveJokeAsyncTask(this);
        jokeTask.execute();
    }

    @Override
    public void onJokeRetrieved(String joke) {
        progressBar.setVisibility(View.GONE);
        Intent callingIntent = JokeActivity.createCallingIntent(getContext(), joke);
        startActivity(callingIntent);
    }
}
