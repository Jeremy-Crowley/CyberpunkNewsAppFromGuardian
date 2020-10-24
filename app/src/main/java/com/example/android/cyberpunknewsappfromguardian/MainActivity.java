package com.example.android.cyberpunknewsappfromguardian;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getName();
    public static final String GUARDIAN_REQUEST_URL =
            "https://content.guardianapis.com/search?q=cyberpunk&show-tags=contributor&limit=10&api-key=255dafbf-d5d8-4420-8d76-fec56b5a3b37";
    private StoryAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       ListView storyListView = findViewById(R.id.frontListView);

        mAdapter = new StoryAdapter(this, new ArrayList<NewsStory>());

        storyListView.setAdapter(mAdapter);


        storyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int click, long id) {

                NewsStory thisStory= (NewsStory) mAdapter.getItem(click);
                assert thisStory != null;
                Uri newsUri = Uri.parse(thisStory.getURL());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsUri);
                startActivity(websiteIntent);
            }
        });
        CyberAsyncTask task = new CyberAsyncTask();
        task.execute(GUARDIAN_REQUEST_URL);

    }

    private class CyberAsyncTask extends AsyncTask<String, Void, List<NewsStory>> {

        @Override
        protected List<NewsStory> doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }
            List<NewsStory> result = Utility.fetchGuardianData(urls[0]);
            return result;
        }

        @Override
        protected void onPostExecute(List<NewsStory> data) {
            mAdapter.clear();
            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);
            }
        }


    }
}