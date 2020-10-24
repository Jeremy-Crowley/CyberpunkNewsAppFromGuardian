package com.example.android.cyberpunknewsappfromguardian;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String GUARDIAN_REQUEST_URL =
            "https://content.guardianapis.com/search?q=cyberpunk&show-tags=contributor&limit=10&api-key=255dafbf-d5d8-4420-8d76-fec56b5a3b37";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<NewsStory> stories = Utility.extractStory();

        final ListView storyListView = findViewById(R.id.frontListView);
        final StoryAdapter adapter = new StoryAdapter(this, stories);

        storyListView.setAdapter(adapter);

        storyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int click, long id) {

                NewsStory currentStory = (NewsStory) adapter.getItem(click);
                assert currentStory != null;
                Uri newsUri = Uri.parse(currentStory.getURL());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsUri);
                startActivity(websiteIntent);
            }
        });
    }


}
