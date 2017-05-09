package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.udacity.gradle.builditbigger.backend.jokesApi.JokesApi;
import com.udacity.gradle.builditbigger.ui.JokeActivity;
import java.io.IOException;

public class RetrieveJokeAsyncTask extends AsyncTask<Void, Void, String> {

    private static JokesApi jokesApi = null;
    private Context context;

    public RetrieveJokeAsyncTask(Context context) {
        this.context = context;
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
        super.onPostExecute(joke);
        Intent callingIntent = JokeActivity.createCallingIntent(context, joke);
        context.startActivity(callingIntent);
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
