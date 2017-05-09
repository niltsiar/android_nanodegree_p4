package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class RetrieveJokeAsyncTaskTest {

    @Test
    public void testAsyncTask() throws
                                Exception {
        RetrieveJokeAsyncTask retrieveJokeAsyncTask = new RetrieveJokeAsyncTask(joke -> {

        });
        retrieveJokeAsyncTask.execute();

        String joke = retrieveJokeAsyncTask.get(45, TimeUnit.SECONDS);

        assertNotNull(joke);
        assertFalse(joke.isEmpty());
    }
}
