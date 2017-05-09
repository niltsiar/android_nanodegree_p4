package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.udacity.gradle.builditbigger.backend.jokesApi.JokesApi;

import java.io.IOException;

public class RetrieveJokeAsyncTask extends AsyncTask<Void, Void, String> {

    public interface OnJokeRetrieved {
        void onJokeRetrieved(String joke);
    }

    private static JokesApi jokesApi = null;
    private OnJokeRetrieved listener;

    public RetrieveJokeAsyncTask(OnJokeRetrieved listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(Void... params) {
        createApiIfNeeded();

        try {
            return jokesApi.tellJoke()
                           .execute()
                           .getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String joke) {
        listener.onJokeRetrieved(joke);
    }

    private synchronized void createApiIfNeeded() {
        if (null != jokesApi) {
            return;
        }

        JokesApi.Builder jokesBuilder = new JokesApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null);
        jokesApi = jokesBuilder.setRootUrl(BuildConfig.APPENGINE_URL)
                               .setApplicationName(BuildConfig.APPLICATION_ID)
                               .build();
    }
}
