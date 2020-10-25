package com.example.android.cyberpunknewsappfromguardian;

import android.content.Context;

import android.content.AsyncTaskLoader;

import java.util.List;

public class PunkLoader extends AsyncTaskLoader<List<NewsStory>> {
    private static final String LOG_TAG = PunkLoader.class.getSimpleName();

    private String mUrl;

    public PunkLoader(Context context, String url) {
        super(context);
        mUrl = url;

    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<NewsStory> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        List<NewsStory> stories = Utility.fetchGuardianData(mUrl);
        return stories;
    }
}
