package com.example.android.cyberpunknewsappfromguardian;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class StoryAdapter extends ArrayAdapter {
    public StoryAdapter(Context context, ArrayList<NewsStory> NewsStory){
        super(context,0, NewsStory);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView = convertView;
        if (listItemView== null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.newsviews, parent, false);
        }
        NewsStory currentNewsStory =(NewsStory) getItem(position);
        TextView titleTextView= listItemView.findViewById(R.id.titleFront);
        assert currentNewsStory != null;
        titleTextView.setText(currentNewsStory.getTitle());

        TextView authorTextView= listItemView.findViewById(R.id.authorFront);
        authorTextView.setText(currentNewsStory.getAuthor());

        TextView dateTextView= listItemView.findViewById(R.id.dateFront);
        dateTextView.setText(currentNewsStory.getDate());

        TextView sectionTextView = listItemView.findViewById(R.id.sectionFront);
        sectionTextView.setText(currentNewsStory.getSection());

        return listItemView;
    }
}