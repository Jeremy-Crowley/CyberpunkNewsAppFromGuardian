package com.example.android.cyberpunknewsappfromguardian;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements LoaderCallbacks<List<NewsStory>> {

    private static final String LOG_TAG = MainActivity.class.getName();

    private static final int PUNK_LOADER_ID = 1;
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

                NewsStory thisStory = (NewsStory) mAdapter.getItem(click);
                assert thisStory != null;
                Uri newsUri = Uri.parse(thisStory.getURL());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsUri);
                startActivity(websiteIntent);
            }
        });
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(PUNK_LOADER_ID, null, this);

    }

    @Override
    public Loader<List<NewsStory>> onCreateLoader(int i, Bundle bundle) {
        return new PunkLoader(this, GUARDIAN_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<NewsStory>> loader, List<NewsStory> stories) {

            mAdapter.clear();
            if (stories != null && !stories.isEmpty()){
                mAdapter.addAll(stories);
            }
    }
    @Override
    public void onLoaderReset(Loader<List<NewsStory>> loader){
        mAdapter.clear();
    }
}