package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A placeholder fragment containing a simple view.
 */
public abstract class BaseActivityFragment extends Fragment {

    protected Button jokeButton;

    public BaseActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        jokeButton = (Button) root.findViewById(R.id.joke_button);

        jokeButton.setOnClickListener(view -> showJokeInActivity());

        return root;
    }

    protected void showJokeInActivity() {
        RetrieveJokeAsyncTask jokeTask = new RetrieveJokeAsyncTask(getContext());
        jokeTask.execute();
    }
}
